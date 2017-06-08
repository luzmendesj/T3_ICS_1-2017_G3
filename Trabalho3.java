/*
Trabalho 3 de Introducao a Computacao Sonica

Jorge Mendes        12/0014599
Marcos Fleury       12/017857
Lucília Oliveira    12/00
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
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.pow;

//Packages de Tipos Abstratos de Dados (TADs)
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.table.TableColumn;

public class Trabalho3 implements Runnable
{
    final JButton botaoABRIR             = util.constroiBotao("Abrir");
    final JButton botaoMIDI             = util.constroiBotao("Exibir MIDI");
    final JButton botaoJAVA             = util.constroiBotao("Exibir Java");
    final JButton botaoTOCAR             = util.constroiBotao("\u25b6");
    final JButton botaoSELECIONARinstru = util.constroiBotao("Mostrar");
    
    final JLabel MOSTRADORarquivo = new JLabel("Nenhum arquivo selecionado");
    
    private String diretorio = System.getProperty("user.dir");

    private ImageIcon logo = null;
    
    // Estilo "Metal", "System", "Motif" ou "GTK"
    final static String ESTILO = "Metal";
    // Para o estilo "Metal", duas opções de tema: "DefaultMetal" ou "Ocean"
    final static String TEMA = "Ocean";
    
    String codigosMidi[] = {"Codigo 1", "Codigo 2", "Codigo 3", "Codigo 4",
                            "Codigo 5", "Codigo 6", "Codigo 7", "Codigo 8"}; 
    
    public Sequencer sequenciador = null;
    public Sequence sequencia = null;
    List<String> Informacoes = new ArrayList<>();
    public Melodia melodiaPronta;
    
    public static void main(String[] args){

        Trabalho3 var = new Trabalho3();
        Thread     thread  = new Thread(var);
        thread.start();
    }

    public Trabalho3(){
        sequenciador = null;
        sequencia = null;
        
        iniciaEstilo();
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Cria janela
        JFrame frame = new JFrame("Trabalho 3 - Introdução a Computação Sônica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Component componentes = criaComponentes();
        
        JPanel imgPanel = new ImgPanel();
        
        imgPanel.add(componentes, BorderLayout.CENTER);
        frame.setContentPane(imgPanel);
        logo = new javax.swing.ImageIcon(getClass().getResource("logo.png"));
        frame.setIconImage(logo.getImage());

        frame.pack();
        frame.setSize(550, 350);
        frame.setResizable(true);
        frame.setVisible(true);
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
        MOSTRADORarquivo.setText("Arquivo: \"" + arquivoMidi.getName() + "\"");

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

    public void mostraJava(){
        JFrame janelaInfo = new JFrame("Arquivo Java Gerado");
        
        JPanel painel = new JPanel();
        JScrollPane SCROLLpainel = new JScrollPane();
        JTextArea TEXTOjava = new JTextArea();
        
        TEXTOjava.setColumns(30);
        TEXTOjava.setRows(100);
        TEXTOjava.setLineWrap(true);
        TEXTOjava.setWrapStyleWord(true);
        SCROLLpainel.setViewportView(TEXTOjava);
        
        painel.add( SCROLLpainel );

        janelaInfo.getContentPane().add( painel, BorderLayout.CENTER );

        janelaInfo.pack();
        janelaInfo.setSize(600, 700);
        janelaInfo.setVisible(true);
    }
    
    public void mostraInfo(){
        JFrame janelaInfo = new JFrame("Informa\u00E7\u00f5es do Arquivo");
        
        String[] colunas = {"Trilha", "Evento", "Mensagem", "Instante", "Info Adicional"};
        
        String[][] dados = getMidiMatriz();
        List<String> dados2 = getMidi();
        
        JTable tabela = new JTable(dados, colunas);
        TableColumn column = null;
        column = tabela.getColumnModel().getColumn(2);
        column.setPreferredWidth(200);
        
        JScrollPane scroll = new JScrollPane(tabela);
        tabela.setFillsViewportHeight(true);
        
        JScrollPane painel = new JScrollPane(scroll);
        //painel.getViewport().add(tabela);

        janelaInfo.getContentPane().add( painel, BorderLayout.CENTER );

        janelaInfo.pack();
        janelaInfo.setSize(800, 800);
        janelaInfo.setVisible(true);
    }
    
    public void mostraInstru( String[][] dados ){
        JFrame janelaInfo = new JFrame("Informa\u00E7\u00f5es do Instrumento");
        
        String[] colunas = {"id", "H", "Lambda", "Fase", "Ganho"};
        
        JTable tabela = new JTable(dados, colunas);
        
        JScrollPane scroll = new JScrollPane(tabela);
        tabela.setFillsViewportHeight(true);
        
        JScrollPane painel = new JScrollPane(scroll);
        //painel.getViewport().add(tabela);

        janelaInfo.getContentPane().add( painel, BorderLayout.CENTER );

        janelaInfo.pack();
        janelaInfo.setSize(500, 200);
        janelaInfo.setVisible(true);
    }
    
    public String[][] getMidiMatriz() {
        Track[] trilhas = sequencia.getTracks();
        int tam = 0;
        
        for(int i=0; i<trilhas.length; i++)
        {
            tam += trilhas[i].size();
        }
        
        String[][] dados = new String[ tam + trilhas.length ][];
        
        int contador = 0;
        for(int i=0; i<trilhas.length; i++)
        {
            Track trilha =  trilhas[i];
            for(int j=0; j<trilha.size(); j++)
            {
                MidiEvent   e          = trilha.get(j);
                MidiMessage mensagem   = e.getMessage();
                long        tique      = e.getTick();
                String      infoAdicional = "";
                   double f;
                int byte0 = mensagem.getStatus();
                
                int tamanho = mensagem.getLength();
                byte[] msgBytes = new byte[tamanho];
                msgBytes = mensagem.getMessage();

                //f = fo.2^(nota/12) para Note On, conversão de binário para frequência 
                //para uso posterior na classe sintese.Nota a ser adicionada a Melodia

                String nomecomando = ""+msgBytes[0];
                int canal = byte0 % 16;
                switch(byte0)
                {
                    case 0b10000000:    
                        // nomecomando = msgBytes[0] + "noteOFF"; 
                        nomecomando = "noteOFF";
                        f = 55f*pow(2, msgBytes[1]/12);
                        break;
                    case 0b10010000:
                        nomecomando = "noteON";
                        f = 55f*pow(2, msgBytes[1]/12);
                        infoAdicional = "       Frequencia: " + f ;
                        break; 
                    case 0b10110000:    nomecomando = "Control Change"; break;
                    case 0b11000000:    nomecomando = "Program Change"; break;  
                    case 0b11100000:    nomecomando = "Pitch Bend"; break;
                    case 255:           nomecomando = "Mensagem Desconhecida"; break;
                    default:            nomecomando = "MetaMensagem  (a ser decodificada)"; break;
                    //---(introduzir outros casos)
                }
                
                String[] dadosTrilha = new String[5];
                
                // Trilha
                dadosTrilha[ 0 ] = String.valueOf(i);
                // Evento
                dadosTrilha[ 1 ] = String.valueOf(j);
                // Mensagem
                dadosTrilha[ 2 ] = nomecomando;
                // Instante
                dadosTrilha[ 3 ] = String.valueOf(tique);
                // Info Adicional
                dadosTrilha[ 4 ] = infoAdicional.trim();
                
                dados[contador] = dadosTrilha;
                        
                contador++;
            }
            
            String[] dadosTrilha = new String[5];
            dadosTrilha[ 0 ] = "";
            dadosTrilha[ 1 ] = "";
            dadosTrilha[ 2 ] = "";
            dadosTrilha[ 3 ] = "";
            dadosTrilha[ 4 ] = "";
            dados[contador] = dadosTrilha;

            contador++;
        }
        
        return dados;
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

                //f = fo.2^(nota/12) para Note On, conversão de binário para frequência 
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
        GeraJava arquivoJava = new GeraJava();
        System.out.println("teste1?");
        //Para cada canal
        for (int i = 0; i < n_canais; i++){
            if (noteON_v.get(i).isEmpty()) continue;
            //Adiciona o sil�ncio ao come�o da trilha
            double inicioCanal = noteON_v.get(i).get(0).getInicio();
            if (noteON_v.get(i).get(0).getInicio() != 0){
                Nota primeiraNota = new Nota((float) inicioCanal);
                M.addNota(primeiraNota);
                arquivoJava.criaNota((float) inicioCanal);
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
                arquivoJava.criaNota((float)duracaoNota, (float)n_ON.getFreq(), (float)n_ON.getIntensidade());
                
                ///Insere sil�ncio at� a pr�xima nota
                
            }
        }
        arquivoJava.fechaJava();
        return M;
    }
    
    public Component criaComponentes() {
        JPanel painel = new JPanel(new GridLayout(4, 0));
        
        JComboBox instrumento = new JComboBox(codigosMidi);

        Color cor = new Color(255, 255, 255);
        
        botaoABRIR.setEnabled(true);
        botaoMIDI.setEnabled(false);
        botaoJAVA.setEnabled(false);
        botaoSELECIONARinstru.setEnabled(true);
        botaoTOCAR.setEnabled(false);
        
        botaoABRIR.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    botaoABRIR.setEnabled(true);
                    botaoMIDI.setEnabled(true);
                    botaoJAVA.setEnabled(true);
                    botaoTOCAR.setEnabled(true);

                    abra();
                }
            }
        );
        
        botaoSELECIONARinstru.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = (String) instrumento.getItemAt(instrumento.getSelectedIndex());
                
                if( data.equals("Codigo 1") ){
                    
                    mostraInstru( instrumentos.instru1 );
                    
                }else if( data.equals("Codigo 2") ){
                    //instrumentos.instrumento2();
                    mostraInstru( instrumentos.instru2 );
                    
                }else if( data.compareTo("Codigo 3") != 0 ){
                    //instrumentos.instrumento3();
                    mostraInstru( instrumentos.instru3 );
                    
                }else if( data.compareTo("Codigo 4") != 0 ){
                    //instrumentos.instrumento4();
                    mostraInstru( instrumentos.instru4 );
                    
                }else if( data.compareTo("Codigo 5") != 0 ){
                    //instrumentos.instrumento5();
                    mostraInstru( instrumentos.instru5 );
                    
                }else if( data.compareTo("Codigo 6") != 0 ){
                   // instrumentos.instrumento6();
                    mostraInstru( instrumentos.instru6 );
                    
                }else if( data.compareTo("Codigo 7") != 0 ){
                   // instrumentos.instrumento7();
                    mostraInstru( instrumentos.instru7 );
                    
                }else if( data.compareTo("Codigo 8") != 0 ){
                    //instrumentos.instrumento8();
                    mostraInstru( instrumentos.instru8 );
                }
            }  
        });
        
        botaoMIDI.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    mostraInfo();
                }
            }
        );
        
        botaoTOCAR.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    //Mostra o código gerado + toca o arquivo midi selecionado
                    mostraJava();
                    // toca();
                }
            }
        );
        
        // Abrir + arquivo
        JPanel p1 = new JPanel();
        p1.add( botaoABRIR );
        p1.add( MOSTRADORarquivo );
        p1.setBackground(cor);
        
        // Instrumentos
        JPanel p2 = new JPanel();
        JPanel pcombobox = new JPanel();
        pcombobox.add(instrumento);
        pcombobox.setBackground(cor);
        p2.add(pcombobox);
        p2.add(botaoSELECIONARinstru);
        p2.setBackground(cor);
        
        // MIDI
        JPanel p3 = new JPanel();
        p3.add( botaoMIDI );
        p3.setBackground(cor);
        
        // Tocar
        JPanel p4 = new JPanel();
        p4.add( botaoTOCAR );
        p4.setBackground(cor);
        
        painel.add( p1 );
        painel.add( p2 );
        painel.add( p3 );
        painel.add( p4 );
        
        return painel;
    }
    
    private static void iniciaEstilo() {
        String strEstilo = null;

        if (ESTILO != null) {
            if (ESTILO.equals("Metal")) {
              // strEstilo = UIManager.getCrossPlatformLookAndFeelClassName();
              //  an alternative way to set the Metal L&F is to replace the
              // previous line with:
              strEstilo = "javax.swing.plaf.metal.MetalLookAndFeel";
            }

            else if (ESTILO.equals("System")) {
                strEstilo = UIManager.getSystemLookAndFeelClassName();
            }

            else if (ESTILO.equals("Motif")) {
                strEstilo = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            }

            else if (ESTILO.equals("GTK")) {
                strEstilo = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            }

            else {
                System.err.println("Unexpected value of ESTILO specified: "
                                   + ESTILO);
                strEstilo = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            try {
                UIManager.setLookAndFeel(strEstilo);

                // If L&F = "Metal", set the theme
                if (ESTILO.equals("Metal")) {
                  if (TEMA.equals("DefaultMetal"))
                     MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
                  else //if (TEMA.equals("Ocean"))
                     MetalLookAndFeel.setCurrentTheme(new OceanTheme());

                  UIManager.setLookAndFeel(new MetalLookAndFeel());
                }
            }
            catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:"
                                   + strEstilo);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            }

            catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel ("
                                   + strEstilo
                                   + ") on this platform.");
                System.err.println("Using the default look and feel.");
            }

            catch (Exception e) {
                System.err.println("Couldn't get specified look and feel ("
                                   + strEstilo
                                   + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
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

class ImgPanel extends JPanel {
    Image img = new ImageIcon(getClass().getResource("background.png")).getImage();

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, 600, 320, this);
    }
}