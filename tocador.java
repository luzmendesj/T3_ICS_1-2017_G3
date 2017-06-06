import java.text.DecimalFormat;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.*;

public class tocador {
	
	Sequence sequencia = null;
	Sequencer sequenciador = null;
	Receiver receptor = null;
	Soundbank    bancoSELECIONADO;
	double duracao;
	Dimension compasso;
	int resolucao, totalSeminimas, totalCompassos;
	long totalTiques;
	float durTique, durSeminima, bpm, durCompasso, tempoAtual;
	List<String> Informacoes = new ArrayList<String>();
	
	public void toca(int compassoInicio){
		espera(300);
		sequenciador.start();
		receptor = sequenciador.getTransmitters().iterator().next().getReceiver();
		sequenciador.getTransmitter().setReceiver(receptor);
		sequenciador.setMicrosecondPosition((long) (compassoInicio*durCompasso));
		
	}
	
	void espera(int milisegundos){
		try {Thread.sleep(milisegundos);
		} catch(InterruptedException e){ }
		
	}
	
	public void pausa(){
		sequenciador.stop();
	}
	
	public void para(){
		sequenciador.stop();
		sequenciador.close();
		sequenciador = null;
	}

	public void abra(){
		File arquivoMidi;
		JFileChooser escolha = new JFileChooser(".");
		FileFilter filtro = new FileFilter() {

			@Override
			public boolean accept(File f) {
				if(!f.isFile())
					return true;
				String nome = f.getName().toLowerCase();
				if(nome.endsWith(".mid"))
					return true;
				if(nome.endsWith(".midi"))
					return true;
				return false;
			}

			@Override
			public String getDescription() {
				return "Arquivos Midi (*.mid, *,midi)";
			}
			
		};
		
		escolha.setFileSelectionMode(JFileChooser.FILES_ONLY);
		escolha.setFileFilter(filtro);
		escolha.showOpenDialog(this);
		
		arquivoMidi = escolha.getSelectedFile();
		try{
			if(sequenciador != null && sequenciador.isRunning()){
				sequenciador.stop();
				sequenciador.close();
				sequenciador = null;
			}
			
			sequencia = MidiSystem.getSequence(arquivoMidi);
			getDados(0);
			sequenciador = MidiSystem.getSequencer();
			sequenciador.setSequence(sequencia);
			sequenciador.open();
			
		}catch (Throwable e1) { System.out.println("Erro em carregaArquivoMidi: "+ e1.toString());
        }  
		
	}

	public void abreBanco()
	{
		//Escolha do Arquivo SoundFont desejado
		File arquivoSF2;

		JFileChooser escolha = new JFileChooser(".");
			FileFilter filtro = new FileFilter() {

				@Override
				public boolean accept(File f) {
					if(!f.isFile())
						return true;
					String nome = f.getName().toLowerCase();
					if(nome.endsWith(".sf2"))
						return true;
					if(nome.endsWith(".sfz"))
						return true;
					return false;
				}

				@Override
				public String getDescription() {
					return "Arquivos Soundfont (*.sf2, *,.sfz)";
				}
				
			};

		escolha.setFileSelectionMode(JFileChooser.FILES_ONLY);
		escolha.setFileFilter(filtro);
		escolha.showOpenDialog(escolha);

		arquivoSF2 = escolha.getSelectedFile();


		//Tentativa de substituir pela nova soundfont
		try 
		{ 
			sintetizador = MidiSystem.getSynthesizer();
	        sintetizador.open();
	  	}
	  	catch (Exception ex) 
	  	{ 
	  		System.out.println("Erro em MidiSystem.getSynthesizer(): " + ex);                                  
			return; 
		}
	                    
		Soundbank bancodefault = sintetizador.getDefaultSoundbank();
		if(bancodefault != null)
		{
			sintetizador.unloadAllInstruments(bancodefault);          
		}         
		
		try 
		{ 
			bancoSELECIONADO = MidiSystem.getSoundbank(arquivoSF2); 
		}
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		}

		sintetizador.loadAllInstruments( bancoSELECIONADO);
	}

	public void getMidi()
	            Track[] trilhas = sequencia.getTracks();
             	Informacoes = new ArrayList<String>();
              for(int i=0; i<trilhas.length; i++)
              {
                Informacoes.add("Início da trilha nº " + i + " **********************");
	             System.out.println("------------------------------------------");
                Track trilha =  trilhas[i];
                


                for(int j=0; j<trilha.size(); j++)
                {
                  Informacoes.add("Trilha nº " + i );
                  Informacoes.add("Evento nº " + j);
                  MidiEvent   e          = trilha.get(j);
                  MidiMessage mensagem   = e.getMessage();
                  long        tique      = e.getTick();
                  
                  int n = mensagem.getStatus();
                  
                  String nomecomando = ""+n;
                  
                  switch(n)
                  {
                      case 128: nomecomando = "noteON"; break;
                      case 144: nomecomando = "noteOFF"; break;
                      case 255: nomecomando = "MetaMensagem  (a ser decodificada)"; break; 
                      //---(introduzir outros casos)
                  }
                  
                  Informacoes.add("       Mensagem: " + nomecomando );
                  Informacoes.add("       Instante: " + tique );
	              Informacoes.add("------------------------------------------");                                    
                }
              }

	  }
	
	public void volume(int valor){
		ShortMessage mensagemVolume = new ShortMessage();
		for(int i=0; i<16; i++){
			try{
				mensagemVolume.setMessage(ShortMessage.CONTROL_CHANGE, i, 7, valor);
				receptor.send(mensagemVolume, -1);
			} catch (InvalidMidiDataException e1) {}
		}
	}
	
	public void getDados(int trilha){
		Track[] trilhas = sequencia.getTracks();
		
		duracao = sequencia.getMicrosecondLength()/1000000.0d;
		resolucao = sequencia.getResolution();
		totalTiques = sequencia.getTickLength();
		
		durTique = (float)duracao/totalTiques;
		durSeminima = durTique*resolucao;
		bpm = 60/durSeminima;
		totalSeminimas = (int)(duracao/durSeminima);
		durCompasso = (float) (compasso.getWidth()*durTique*(compasso.getHeight())/4);
		totalCompassos = (int) (duracao/durCompasso);
		
		compasso.setSize(1, 1);
		
		for(int i=0; i<trilhas[trilha].size(); i++){
			MidiMessage m = trilhas[trilha].get(i).getMessage();
			if(m instanceof MetaMessage){
				if(((MetaMessage)m).getType()==0x58){
					MetaMessage mm = (MetaMessage)m;
					byte[] data = mm.getData();
					compasso.setSize(data[0], data[1]);
					return;
				}
			}
		}
		return;
	}
    
    public void atualizaTempo(){
        tempoAtual = sequenciador.getMicrosecondLength();
    }
    
    public int getSegundos(long tempo){
        return ( ( tempo / 1000000 ) % 60);
    }
	
    public int getMinutos(long tempo){
        return ( ( ( tempo / 1000000 ) / 60 ) % 60 );
    }
    
    public int getHoras(long tempo){
        return ( ( ( tempo / 1000000 ) / 60 ) % 60 );
    }
    
}
