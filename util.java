
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.*;

public class util {
    static Color corBG = new Color(230, 240, 255);
    static Color corTitulo = new Color(180, 190, 255);
    
    static final public JLabel constroiRotulo(String texto) {
        JLabel rotulo = new JLabel( texto, SwingConstants.CENTER );
        Color corTxt = new Color(60, 30, 100);
        
        rotulo.setFont( new Font("Serif", Font.PLAIN, 18) );
        rotulo.setForeground(corTxt);
        
        return rotulo;
    }
    
    static final public JButton constroiBotao(String legenda) {
    // usa 9 como o tamanho de fonte default.
        return constroiBotao(legenda, 15);
    }

    static final public JButton constroiBotao(String legenda, float tamanho) {
        JButton botao = new JButton(legenda);
        botao.setMargin(new Insets(4, 4, 4, 4));
        botao.setFocusable(false);
        botao.setFont(botao.getFont().deriveFont(Font.PLAIN));
        botao.setFont(botao.getFont().deriveFont(tamanho));
        return botao;
    }
    
}
