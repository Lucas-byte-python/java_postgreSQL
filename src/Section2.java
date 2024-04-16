import javax.swing.*;
import java.awt.*;

public class Section2 extends JPanel {
    public Section2(JMenuBar menuBar) {
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // JPanel com layout de alinhamento central
        JLabel label2 = new JLabel("<html><div style='text-align: center;'><b>Bem-Vindo ao Home</b></div></html>");
        label2.setFont(label2.getFont().deriveFont(Font.BOLD, 16)); // Define a fonte em negrito e tamanho 16
        centerPanel.add(label2); // Adiciona o JLabel ao JPanel de alinhamento central

        add(centerPanel, BorderLayout.CENTER); // Adiciona o JPanel de alinhamento central à Seção 2
        add(menuBar, BorderLayout.NORTH);
    }
}
