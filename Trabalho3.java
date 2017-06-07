/*
Trabalho 3 de Introdução à Computação Sônica

Jorge Mendes        12/0014599
Marcos Fleury       12/00
Lucília Oliveira    12/00
Paulo Santos        12/005
*/

//Packages de Som
import sintese.*;
import javax.sound.midi.*;

//Packages de I/O
import java.io.File;
import java.io.IOException;

//Packages de Interface
import javax.swing.filechooser.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.*;
import java.awt.BorderLayout;
import static java.lang.Math.pow;

//Packages de Tipos Abstratos de Dados (TADs)
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Trabalho3 implements Runnable
{
    public Sequencer sequenciador = null;
    public Sequence sequencia = null;
    List<String> Informacoes = new ArrayList<>();
    
    public static void main(String[] args){

        Trabalho3 var = new Trabalho3();
        Thread     thread  = new Thread(var);
        thread.start();
        var.abra();
        var.mostraInfo();
    }

    public Trabalho3(){
        sequenciador = null;
        sequencia = null;
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
            escolha.showOpenDialog(escolha);

            arquivoMidi = escolha.getSelectedFile();

            try{
                    if(sequenciador != null && sequenciador.isRunning()){
                            sequenciador.stop();
                            sequenciador.close();
                            sequenciador = null;
                    }

                    sequencia = MidiSystem.getSequence(arquivoMidi);
                    sequenciador = MidiSystem.getSequencer();
                    sequenciador.setSequence(sequencia);
                    sequenciador.open();

            }catch (Throwable e1) {
                System.out.println("Erro ao carregar arquivo Midi: "+ e1.toString());
            }
        }

    public void run(){
        
        while(true)
        {

            try{ 
                espera(1000);
            }
            catch(Exception e) { 
                System.out.println(e.getMessage());
            }
                 
        }


    }

    public void mostraInfo(){
        Informacoes = getMidi();
        String[] info = new String[Informacoes.size()];
        info = Informacoes.toArray( info );

        JFrame janelaInfo = new JFrame("Informa\u00E7\u00f5es do Arquivo");
        //janelaInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JList<String> tabela = new JList<String>( info );

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setLayoutOrientation(JList.VERTICAL);
        tabela.setVisibleRowCount(-1);

        JScrollPane painel = new JScrollPane(tabela);
        //painel.getViewport().add(tabela);

        janelaInfo.getContentPane().add( painel, BorderLayout.CENTER );

        janelaInfo.pack();
        janelaInfo.setSize(400, 600);
        janelaInfo.setVisible(true);

        /*for (Iterator<String> it = Informacoes.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }*/
    }

    public List<String> getMidi() {
        Track[] trilhas = sequencia.getTracks();
        Informacoes = new ArrayList<String>();

        for(int i=0; i<trilhas.length; i++)
        {
            Informacoes.add("  In\u00edcio da trilha n\u00ba " + i + " **********************");
            Informacoes.add("  ------------------------------------------");
            Track trilha =  trilhas[i];

            for(int j=0; j<trilha.size(); j++)
            {
                Informacoes.add("  Trilha n\u00ba " + i );
                Informacoes.add("  Evento n\u00ba " + j);
                MidiEvent   e          = trilha.get(j);
                MidiMessage mensagem   = e.getMessage();
                long        tique      = e.getTick();
                String      infoAdicional = "";

                int byte1 = mensagem.getStatus();
                
                int tamanho = mensagem.getLength();
                byte[] msgBytes = new byte[tamanho];
                msgBytes = mensagem.getMessage();

                //f = fo.2^(nota/12) para Note On, conversão de binário para frequência 
                //para uso posterior na classe sintese.Nota a ser adicionada a Melodia

                String nomecomando = ""+msgBytes[0];

                switch(byte1)
                {
                    case 0b10000000:    nomecomando = "noteOFF"; break;
                    case 0b10010000:    nomecomando = "noteON"; 
                                        infoAdicional = "       Frequencia: " + 55f*pow(2, msgBytes[1]/12) ; break; 
                    case 0b10110000:    nomecomando = "Control Change"; break;
                    case 0b11000000:    nomecomando = "Program Change"; break;
                    case 0b11100000:    nomecomando = "Pitch Bend"; break;
                    case 255:           nomecomando = "Mensagem Desconhecida"; break;
                    default:            nomecomando = "MetaMensagem  (a ser decodificada)"; break;
                    //---(introduzir outros casos)
                }

                Informacoes.add("       Mensagem: " + nomecomando );
                Informacoes.add(infoAdicional);
                Informacoes.add("       Instante: " + tique );
                Informacoes.add("  ------------------------------------------");
            }
        }

        return Informacoes;
    }

    void espera(int milisegundos){
            try {Thread.sleep(milisegundos);
            } catch(InterruptedException e){ }

    }
}
