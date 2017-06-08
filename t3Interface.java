package t3Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.plaf.metal.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class t3Interface extends JFrame {

    private String diretorio = System.getProperty("user.dir");
    private Container painel = getContentPane();

    private ImageIcon logo = null;
    
    JScrollPane SCROLLpainel = new JScrollPane();
    JTextArea TEXTOjava = new JTextArea();
    JLabel LABELjava = new JLabel();
    
    String melodia[]={"instrumento1", "instrumento2", "instrumento3"}; 
    
    final JButton botaoABRIR             = constroiBotao("Abrir");
    final JButton botaoSELECIONARinstrumento             = constroiBotao("Selecionar Instrumento");
    final JLabel MOSTRADORarquivo = new JLabel("Nenhum arquivo selecionado");
      
    
    // Estilo "Metal", "System", "Motif" ou "GTK"
    final static String ESTILO = "Metal";
    // Para o estilo "Metal", duas opções de tema: "DefaultMetal" ou "Ocean"
    final static String TEMA = "Ocean";
    
    
    public static void main(String[] args) {
        t3Interface gui = new t3Interface();
    }
    
    
    
    private t3Interface() {
        // Aplica o estilo definido no código
        iniciaEstilo();
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Cria janela
        JFrame frame = new JFrame("Trabalho 3 - Introdução à Computação Sônica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Component componentes = criaComponentes();
        
        JPanel imgPanel = new ImgPanel();
        
        imgPanel.add(componentes, BorderLayout.CENTER);
        frame.setContentPane(imgPanel);
        logo = new javax.swing.ImageIcon(getClass().getResource("logo.png"));
        frame.setIconImage(logo.getImage());
        
       

        //Display the window.
        frame.pack();
        frame.setSize(1100, 820);
        frame.setResizable(true);
        frame.setVisible(true);
    }
    
    class ImgPanel extends JPanel {
    	Image img = new ImageIcon(getClass().getResource("background.png")).getImage();
    	
    	public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, 1100, 800, this);
        }
    }
    
    public Component criaComponentes() {
        //JPanel painel = new JPanel();
        //painel.setLayout( new BoxLayout( painel, BoxLayout.Y_AXIS ) );
        JPanel painel = new JPanel();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dimensao = tk.getScreenSize();

        logo = new javax.swing.ImageIcon(getClass().getResource("logo.png"));
        setIconImage(logo.getImage());

        int posX = (dimensao.width / 2) - (this.getWidth() / 2);
        int posY = (dimensao.height / 2) - (this.getHeight() / 2);

        this.setLocation(posX, posY);
        this.setResizable(true);
        
        JComboBox instrumento = new JComboBox(melodia);
        
        TEXTOjava.setColumns(20);
        TEXTOjava.setRows(5);
        SCROLLpainel.setViewportView(TEXTOjava);

        LABELjava.setText("Java");

        // inicializar os botões
        botaoABRIR.setEnabled(true);
        botaoSELECIONARinstrumento.setEnabled(false);
        
        painel.setLayout(new GridLayout(3,0));
        
        JPanel p1 = new JPanel();
        JPanel pcombobox = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        
        Color cor = new Color(255, 255, 255);
        
        
        
        botaoABRIR.addActionListener(
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        botaoABRIR.setEnabled(true);
                        botaoSELECIONARinstrumento.setEnabled(true);

                        abra();
                    }
                }
        );
        
        botaoSELECIONARinstrumento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {       

                String data = (String) instrumento.getItemAt(instrumento.getSelectedIndex());  
                

            }  
        });
        
        p1.setBackground(cor);
        pcombobox.setBackground(cor);
        p2.setBackground(cor);
        p3.setBackground(cor);
        
        p1.add(botaoABRIR);
        p1.add(MOSTRADORarquivo);
        pcombobox.add(instrumento);
        p2.add(pcombobox);
        p2.add(botaoSELECIONARinstrumento);
        p3.add(LABELjava);
        p3.add(SCROLLpainel);
        
        
       
        painel.setBackground(cor);
        painel.add(p1);
        painel.add(p2);
        painel.add(p3);
        
        return painel;
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

        /*try{
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
        }*/
    }
    
    public JButton constroiBotao(String legenda) {
        // usa 9 como o tamanho de fonte default.
            return constroiBotao(legenda, 12);
        }

        public JButton constroiBotao(String legenda, float tamanho) {
            JButton botao = new JButton(legenda);
            botao.setMargin(new Insets(3, 3, 3, 3));
            botao.setFocusable(false);
            botao.setFont(botao.getFont().deriveFont(Font.PLAIN));
            botao.setFont(botao.getFont().deriveFont(tamanho));
            return botao;
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
