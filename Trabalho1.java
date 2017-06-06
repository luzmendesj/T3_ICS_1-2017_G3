

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import java.text.DecimalFormat;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class Trabalho1 extends JFrame implements Runnable{

    public static void main (String[] args) {
        Trabalho1 player = new Trabalho1();
        Thread     thread  = new Thread(player);
        thread.start();
    }

    private String diretorio = System.getProperty("user.dir");
    private Container painel = getContentPane();

    private ImageIcon logo = null;
    private boolean   soando = false;
    private long       inicio = 0;

    final JButton botaoABRIR             = constroiBotao("Abrir");
    final JButton botaoTOCAR             = constroiBotao("\u25b6");
    final JButton botaoPAUSA             = constroiBotao("\u25ae\u25ae");
    final JButton botaoPARAR             = constroiBotao("\u25fc");
    final JButton botaoINFO              = constroiBotao("Info");
    final JButton botaoBANCO             = constroiBotao("Banco de Sons");

    final JLabel MOSTRADORarquivo = new JLabel("Nenhum arquivo selecionado");
    final JLabel MOSTRADORcaminho  = new JLabel("Nenhum diretório selecionado");
    final JLabel MOSTRADORbanco  = new JLabel("Nenhum banco selecionado");
    final JLabel MOSTRADORvolume  = new JLabel(" ", JLabel.CENTER);
    final JLabel MOSTRADORinstante  = new JLabel("00h 00m 00s");
    final JLabel MOSTRADORduracao  = new JLabel("");

    private int          volumeATUAL             = 64;
    private JSlider      sliderVolume            = new JSlider(JSlider.VERTICAL, 0, 127, volumeATUAL);
    private JProgressBar sliderProgresso         = new JProgressBar();

    // Estilo "Metal", "System", "Motif" ou "GTK"
    final static String ESTILO = "Metal";

    // Para o estilo "Metal", duas opções de tema: "DefaultMetal" ou "Ocean"
    final static String TEMA = "Ocean";

    Sequence sequencia = null;
    Sequencer sequenciador = null;
    Receiver receptor = null;
    double duracao;
    Dimension compasso = new Dimension(1,1);
    int resolucao, totalSeminimas, totalCompassos;
    long totalTiques;
    float durTique, durSeminima, bpm, durCompasso;
    List<String> Informacoes = new ArrayList<String>();
    Soundbank    bancoSELECIONADO;

    Synthesizer sintetizador = null;

    public Trabalho1() {
        // Aplica o estilo definido no código
        iniciaEstilo();
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Cria janela
        JFrame frame = new JFrame("Trabalho 1 - Introdução a Computação Sônica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Component componentes = criaComponentes();
        frame.getContentPane().add(componentes, BorderLayout.CENTER);

        Component sliderVolume = criaSliderVolume();
        frame.getContentPane().add(sliderVolume, BorderLayout.EAST);

        logo = new javax.swing.ImageIcon(getClass().getResource("logo.png"));
        frame.setIconImage(logo.getImage());

        //Display the window.
        frame.pack();
        frame.setSize(500, 500);
        frame.setVisible(true);
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

    public Component criaSliderVolume() {
        JPanel painel = new JPanel(new GridLayout(0, 1));

        JLabel vol = new JLabel("Volume: ", JLabel.CENTER);
        sliderVolume.setPreferredSize( new Dimension( 120, 20 ) );
        sliderVolume.setFocusable(false);
        MOSTRADORvolume.setText("" + (volumeATUAL*100)/127 + "%");

        sliderVolume.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                JSlider source = (JSlider)e.getSource();
                if(!source.getValueIsAdjusting())
                {
                    int valor = (int)source.getValue();
                    volume(valor);
                    volumeATUAL = valor;
                    MOSTRADORvolume.setText("" + (volumeATUAL*100)/127 + "%");
                }
            }
        });

        painel.add(vol);
        painel.add(sliderVolume);
        painel.add(MOSTRADORvolume);

        painel.setBorder(BorderFactory.createLineBorder(Color.blue));

        return painel;
    }

    public Component criaComponentes() {
        JPanel painel = new JPanel(new GridLayout(5, 0));

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimensao = tk.getScreenSize();

        logo = new javax.swing.ImageIcon(getClass().getResource("logo.png"));
        setIconImage(logo.getImage());

        int posX = (dimensao.width / 2) - (this.getWidth() / 2);
        int posY = (dimensao.height / 2) - (this.getHeight() / 2);

        this.setLocation(posX, posY);
        this.setResizable(true);

        // inicializar os botões
        botaoABRIR.setEnabled(true);
        botaoTOCAR.setEnabled(false);
        botaoPAUSA.setEnabled(false);
        botaoPARAR.setEnabled(false);
        botaoINFO.setEnabled(false);
        MOSTRADORduracao.setEnabled(false);

        painel.setLayout(new GridLayout(5,0));

        Color cor = new Color(230, 240, 255);
        JPanel p1 = new JPanel();
        p1.setBackground(cor);
        JPanel p2 = new JPanel();
        p2.setBackground(cor);
        JPanel p3 = new JPanel();
        p3.setBackground(cor);
        JPanel p4 = new JPanel();
        p4.setBackground(cor);
        JPanel p5 = new JPanel();
        p5.setBackground(cor);

        // fazer os ActionListeners para os botões
        botaoABRIR.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    botaoABRIR.setEnabled(true);
                    botaoTOCAR.setEnabled(true);
                    botaoPAUSA.setEnabled(false);
                    botaoPARAR.setEnabled(false);
                    botaoINFO.setEnabled(true);
                    MOSTRADORduracao.setEnabled(true);

                    abra();
                }
            }
        );

        botaoTOCAR.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    botaoABRIR.setEnabled(false);
                    botaoTOCAR.setEnabled(false);
                    botaoPAUSA.setEnabled(true);
                    botaoPARAR.setEnabled(true);
                    botaoINFO.setEnabled(true);
                    MOSTRADORduracao.setEnabled(true);

                    tocar(inicio);
                }
            }
        );

        botaoPAUSA.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    botaoABRIR.setEnabled(true);
                    botaoTOCAR.setEnabled(true);
                    botaoPAUSA.setEnabled(false);
                    botaoPARAR.setEnabled(false);
                    botaoINFO.setEnabled(true);
                    MOSTRADORduracao.setEnabled(true);

                    pausa();
                }
            }
        );

        botaoPARAR.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    botaoABRIR.setEnabled(true);
                    botaoTOCAR.setEnabled(true);
                    botaoPAUSA.setEnabled(false);
                    botaoPARAR.setEnabled(false);
                    botaoINFO.setEnabled(true);
                    MOSTRADORduracao.setEnabled(true);

                    para();
                }
            }
        );

        botaoINFO.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    // Mostra a partitura
                    mostraInfo();
                }
            }
        );

        botaoBANCO.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    abreBanco();
                }
            }
        );

        p1.add(botaoABRIR);
        p1.add(MOSTRADORarquivo);

        p2.add(MOSTRADORcaminho);

        sliderProgresso.setPreferredSize(new Dimension(200,20));
        sliderProgresso.setFocusable(false);
        p3.add(MOSTRADORinstante);
        p3.add(sliderProgresso);
        p3.add(MOSTRADORduracao);

        p4.add(botaoTOCAR);
        p4.add(botaoPAUSA);
        p4.add(botaoPARAR);
        p4.add(botaoINFO);

        p5.add(botaoBANCO);
        p5.add(MOSTRADORbanco);

        //Adiciona o panel ao frame
        painel.add(p1);
        painel.add(p2);
        painel.add(p3);
        painel.add(p4);
        painel.add(p5);
        painel.setBorder(BorderFactory.createLineBorder(Color.blue));

        return painel;
    }

    public JButton constroiBotao(String legenda) {
    // usa 9 como o tamanho de fonte default.
        return constroiBotao(legenda, 9);
    }

    public JButton constroiBotao(String legenda, float tamanho) {
        JButton botao = new JButton(legenda);
        botao.setMargin(new Insets(3, 3, 3, 3));
        botao.setFocusable(false);
        botao.setFont(botao.getFont().deriveFont(Font.PLAIN));
        botao.setFont(botao.getFont().deriveFont(tamanho));
        return botao;
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
        MOSTRADORcaminho.setText("DIR: " + diretorio);

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

        }catch (Throwable e1) {
            System.out.println("Erro em carregaArquivoMidi: "+ e1.toString());
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


    public void tocar(long inicio)
    {
        try
        {
        	espera(300);
            sequenciador.start();
            receptor = sequenciador.getTransmitters().iterator().next().getReceiver();
            sequenciador.getTransmitter().setReceiver(receptor);


		long duracao  = sequencia.getMicrosecondLength()/1000000;
            MOSTRADORduracao.setText("\nDura\u00e7\u00e3o:"+ formataInstante(duracao));
            MOSTRADORinstante.setText(formataInstante(0));

            sequenciador.setMicrosecondPosition(inicio);

            if (sequenciador.isRunning())
            { duracao = sequenciador.getMicrosecondLength();
              soando = true;


            }
            else { soando = false;
                   sequenciador.stop();
                   sequenciador.close();
                   inicio = 0L;
                   duracao = 0;
                 }

             botaoABRIR.setEnabled(false);
             botaoTOCAR.setEnabled(false);
             botaoPAUSA.setEnabled(true);
             botaoPARAR.setEnabled(true);

        }
        catch(MidiUnavailableException e1) { System.out.println(e1+" : Dispositivo midi nao disponivel.");}
        catch(Exception e){  System.out.println(e.toString());  }
	}

    void espera(int milisegundos){
            try {Thread.sleep(milisegundos);
            } catch(InterruptedException e){ }

    }

    public void pausa(){
    		soando = false;
            sequenciador.stop();
            inicio = 0L;
    }

    public void para(){
    		soando = false;
            sequenciador.stop();
            sequenciador.close();
            sequenciador = null;
            sliderProgresso.setValue(0);
            MOSTRADORinstante.setText(formataInstante(0));
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

    public int getSegundos(long tempo){
        return (int) ( ( tempo / 1000000 ) % 60);
    }

    public int getMinutos(long tempo){
        return (int) ( ( ( tempo / 1000000 ) / 60 ) % 60 );
    }

    public int getHoras(long tempo){
        return (int) ( ( ( tempo / 1000000 ) / 60 ) % 60 );
    }

    public String getTempo(){
    	int temp;
    	long tempoAtual = sequenciador.getMicrosecondPosition();
    	String tempoString;
    	temp = getSegundos(tempoAtual);
    	if(temp < 10)
    		tempoString = "0" + Integer.toString(temp);
    	else
    		tempoString = Integer.toString(temp);

    	temp = getMinutos(tempoAtual);
    	if(temp < 10)
    		tempoString = "0" + Integer.toString(temp) + ":" + tempoString;
    	else
    		tempoString = Integer.toString(temp) + ":" + tempoString;

    	temp = getHoras(tempoAtual);
    	if(temp < 10)
    		tempoString = "0" + Integer.toString(temp) + ":" + tempoString;
    	else
    		tempoString = Integer.toString(temp) + ":" + tempoString;

    	return tempoString;
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

        MOSTRADORbanco.setText("Banco selecionado: \"" + arquivoSF2.getName() + "\"");

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

        sintetizador.loadAllInstruments(bancoSELECIONADO);
    }

    public void mostraInfo(){
        Informacoes = getMidi();
        String[] info = new String[Informacoes.size()];
        info = Informacoes.toArray( info );

        JFrame janelaInfo = new JFrame("Informações do Arquivo");
        //janelaInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JList tabela = new JList( info );

        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setLayoutOrientation(JList.VERTICAL);
        tabela.setVisibleRowCount(-1);

        JScrollPane painel = new JScrollPane(tabela);
        //painel.getViewport().add(tabela);

        janelaInfo.getContentPane().add( painel, BorderLayout.CENTER );

        janelaInfo.setIconImage(logo.getImage());
        janelaInfo.pack();
        janelaInfo.setSize(400, 600);
        janelaInfo.setVisible(true);

        for (Iterator<String> it = Informacoes.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    }

    public List<String> getMidi() {
        Track[] trilhas = sequencia.getTracks();
        Informacoes = new ArrayList<String>();

        for(int i=0; i<trilhas.length; i++)
        {
            Informacoes.add("  Início da trilha nº " + i + " **********************");
            System.out.println("  ------------------------------------------");
            Track trilha =  trilhas[i];

            for(int j=0; j<trilha.size(); j++)
            {
                Informacoes.add("  Trilha nº " + i );
                Informacoes.add("  Evento nº " + j);
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
                Informacoes.add("  ------------------------------------------");
            }
        }

        return Informacoes;
    }

    public String formataInstante(double t1)
    {
      String inicio    = "";

      //--------início
      double h1  = (int)(t1/3600.0);
      double m1  = (int)((t1 - 3600*h1)/60);
      double s1  = (t1 - (3600*h1 +60*m1));


      double h1r  = t1/3600.0;
      double m1r  = (t1 - 3600*h1)/60.0f;
      double s1r  = (t1 - (3600*h1 +60*m1));

      String sh1="";
      String sm1="";
      String ss1="";

      if     (h1 ==0) sh1 = "00";
      else if(h1 <10) sh1 = "0"+reformata(h1, 0);
      else if(h1<100) sh1 = "" +reformata(h1, 0);
      else            sh1 = "" +reformata(h1, 0);

      if     (m1 ==0) sm1 = "00";
      else if(m1 <10) sm1= "0"+reformata(m1, 0);
      else if(m1 <60) sm1 = ""+reformata(m1, 0);

      if     (s1 ==0) ss1 = "00";
      else if(s1 <10) ss1 = "0"+reformata(s1r, 2);
      else if(s1 <60) ss1 = reformata(s1r, 2);

      return inicio = "\n" + "   "+sh1+"h "+       sm1+"m "+    ss1+"s";
    }


    public String reformata(double x, int casas)
    { DecimalFormat df = new DecimalFormat() ;
      df.setGroupingUsed(false);
      df.setMaximumFractionDigits(casas);
      return df.format(x);
    }

    public void run()
    {
        double dur;
        double t;
        int    pos =0;

        while(true)
        {
            if (soando)
            { dur   = sequenciador.getMicrosecondLength()/1000000;
              t     = sequenciador.getMicrosecondPosition()/1000000;
              pos   = (int) ((t*100)/dur);
              try {
                    sliderProgresso.setValue(pos);
                    MOSTRADORinstante.setText(formataInstante(t));
                    espera(1000);
                    if(t>=dur) {  sliderProgresso.setValue(0);
                                  MOSTRADORinstante.setText(formataInstante(0));

                                  botaoABRIR.setEnabled(true);
                                  botaoTOCAR.setEnabled(true);
                                  botaoPAUSA.setEnabled(false);
                                  botaoPARAR.setEnabled(false);
                               }
                  }
              catch(Exception e) { System.out.println(e.getMessage());
                                 }
            }

            else{ try{ espera(1000);
                     }
                  catch(Exception e) { System.out.println(e.getMessage());
                                     }
                 }
        }


	}

}
