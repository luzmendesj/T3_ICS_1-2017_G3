/*
Trabalho 3 de Introducao a Computacao Sonica

Jorge Mendes        12/0014599
Marcos Fleury       12/00
Luc√≠lia Oliveira    12/00
Paulo Santos        12/005
*/

//Packages de Som
import sintese.*;
import sintese.Melodia;
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
import java.util.ListIterator;

public class Trabalho3 implements Runnable
{
    public Sequencer sequenciador = null;
    public Sequence sequencia = null;
    List<String> Informacoes = new ArrayList<>();
    public Melodia melodiaPronta;
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
        
        //Pega as trilhas do Midi lido
        double duracao = sequencia.getMicrosecondLength()/1000.0d;
        double totalTiques = sequencia.getTickLength();

        double durTique = duracao/totalTiques;
        
        Track[] trilhas = sequencia.getTracks();
        Informacoes = new ArrayList<String>();
        
        //Cria uma lista para NoteOn/Off de cada canal
        ArrayList<ArrayList<NoteON>> noteON_v;
        noteON_v = new ArrayList<ArrayList<NoteON>>(16);
        ArrayList<ArrayList<NoteOFF>> noteOFF_v;
        noteOFF_v = new ArrayList<ArrayList<NoteOFF>>(16);
        
        //Para todas as trilhas
        for(int i=0; i<trilhas.length; i++)
        {
            Informacoes.add("  In\u00edcio da trilha n\u00ba " + i + " **********************");
            Informacoes.add("  ------------------------------------------");
            Track trilha =  trilhas[i];
           noteON_v.add(new ArrayList<NoteON>(16));
           noteOFF_v.add(new ArrayList<NoteOFF>(16));
            //Para todos os eventos na trilha
            for(int j=0; j<trilha.size(); j++)
            {
                Informacoes.add("  Trilha n\u00ba " + i );
                Informacoes.add("  Evento n\u00ba " + j);
                MidiEvent   e          = trilha.get(j);
                MidiMessage mensagem   = e.getMessage();
                long        tique      = e.getTick();
                String      infoAdicional = "";
                   double f;
                int byte0 = mensagem.getStatus();
                
                int tamanho = mensagem.getLength();
                byte[] msgBytes = new byte[tamanho];
                msgBytes = mensagem.getMessage();

                //f = fo.2^(nota/12) para Note On, convers√£o de bin√°rio para frequ√™ncia 
                //para uso posterior na classe sintese.Nota a ser adicionada a Melodia

                String nomecomando = ""+msgBytes[0];
                int canal = byte0 % 16;
                switch(byte0)
                {
                    case 0b10000000:    
                        nomecomando = "noteOFF"; 
                        f = 55f*pow(2, msgBytes[1]/12);
                        infoAdicional = "       Frequencia: " + f ; 
                        noteOFF_v.get(canal).add(new NoteOFF(f, tique*durTique)); 
                        break;
                    case 0b10010000:
                        nomecomando = "noteON";
                        f = 55f*pow(2, msgBytes[1]/12);
                        infoAdicional = "       Frequencia: " + f ; 
                        noteON_v.get(canal).add(new NoteON(f, msgBytes[2], tique*durTique));    
                        break; 
                    case 0b10110000:    nomecomando = "Control Change"; break;
                    case 0b11000000:    nomecomando = "Program Change"; break;  
                    case 0b11100000:    nomecomando = "Pitch Bend"; break;
                    case 255:           nomecomando = "Mensagem Desconhecida"; break;
                    default:            nomecomando = "MetaMensagem  (a ser decodificada)"; break;
                    //---(introduzir outros casos)
                }

                Informacoes.add("       Mensagem: " + nomecomando + "   No canal " + canal);
                Informacoes.add(infoAdicional);
                Informacoes.add("       Instante: " + tique );
                Informacoes.add("  ------------------------------------------");
            }
            
            Informacoes.add("  Fim da trilha n\u00ba " + i + " **********************");
            Informacoes.add("  ------------------------------------------");
        }
        //Informacoes.add(noteON_v.toString());
        //Informacoes.add(noteOFF_v.toString());
        
        melodiaPronta =geraMelodia(noteON_v, noteOFF_v);
        return Informacoes;
    }
    
    void espera(int milisegundos){
            try {Thread.sleep(milisegundos);
            } catch(InterruptedException e){ }

    }
    
    Melodia geraMelodia(ArrayList<ArrayList<NoteON>> noteON_v, ArrayList<ArrayList<NoteOFF>> noteOFF_v){
        
        int n_canais = noteON_v.size();
        Melodia M = new Melodia();
        
        //Para cada canal
        for (int i = 0; i < n_canais; i++){
            if (noteON_v.get(i).isEmpty()) continue;
            //Adiciona o silÍncio ao comeÁo da trilha
            double inicioCanal = noteON_v.get(i).get(0).getInicio();
            if (noteON_v.get(i).get(0).getInicio() != 0){
                Nota primeiraNota = new Nota((float) inicioCanal);
                M.addNota(primeiraNota);
            }
                
            //Para cada NoteON
            for(int j = 0; j < noteON_v.get(i).size(); j++){
                //Encontra o par de note On e note off
                NoteON n_ON = noteON_v.get(i).get(j);
                //int prox_off = noteOFF_v.get(i).indexOf(new NoteOFF(n_ON.getFreq(), n_ON.getInicio()));
                int prox_off = 0;
                for (int k = 0; k < noteOFF_v.get(i).size(); k++){
                    if (n_ON.getFreq() ==  noteOFF_v.get(i).get(k).getFreq()){
                        prox_off = k;
                        break;
                    }
                }
                //System.out.println(prox_off);
                NoteOFF n_OFF = noteOFF_v.get(i).get(prox_off);
                
                double duracaoNota = n_ON.getInicio() - n_OFF.getFim();
                noteOFF_v.get(i).remove(prox_off);
                Nota novaNota = new Nota(duracaoNota, n_ON.getFreq(), n_ON.getIntensidade());
                
                //Insere nota correspondente
                M.addNota(novaNota);
                
                ///Insere silÍncio atÈ a prÛxima nota
                
                
            }
        }
        return M;
    }
}



class NoteON{
    public double freq;
    public double intensidade;
    public double inicio;
    
    public NoteON(double f, double i, double s){
        this.freq = f;
        this.intensidade = i;
        this.inicio = s;
    }
    
    double getFreq(){
        return this.freq;
    }
    
    double getIntensidade(){
        return this.intensidade;
    }
    
    double getInicio(){
        return this.inicio;
    }
            
}

class NoteOFF implements Comparable<NoteOFF>{
    public double freq;
    public double fim;
    
    public NoteOFF(double f, double s){
        this.freq = f;
        
        this.fim = s;
    }
    
    double getFreq(){
        return this.freq;
    }
    
    double getFim(){
        return this.fim;
    }
    
    public boolean equals(NoteOFF nOff){
        if (!(nOff instanceof NoteOFF))
            return false;
        if (nOff == null || this == null)
            return false;
            
            
        if(this.freq == nOff.getFreq()) 
            return true;
        else 
            return false;
        
    }
    
    @Override
    public int compareTo(NoteOFF nOff){
        if (this.freq < nOff.getFreq()){
            return -1;
        }
        else if (this.freq == nOff.getFreq()){
            if (this.fim < nOff.getFim()){
                return -1;
            }
            else if (this.fim == nOff.getFim()){
                return 0;
            }
            else return 1;
        }
        else return 1;
            
    }
}