import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class executar {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Duas Seções");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                Section1 section1 = new Section1(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Lógica para ir para a Seção 2
                        frame.getContentPane().removeAll();
                        JMenuBar menuBar = criarMenuNavegacao(frame); // Método para criar o menu de navegação
                        frame.getContentPane().add(new Section2(menuBar));
                        frame.revalidate();
                        frame.repaint();
                    }
                });

                frame.add(section1);
                frame.setVisible(true);
            }
        });
    }

    private static JMenuBar criarMenuNavegacao(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Navegação");
        JMenuItem menuItem1 = new JMenuItem("Página 1");
        JMenuItem menuItem2 = new JMenuItem("Página 2");

        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para voltar para a Seção 1
                frame.getContentPane().removeAll();
                Section1 section1 = new Section1(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Lógica para ir para a Seção 2 novamente
                        frame.getContentPane().removeAll();
                        JMenuBar menuBar = criarMenuNavegacao(frame);
                        frame.getContentPane().add(new Section2(menuBar));
                        frame.revalidate();
                        frame.repaint();
                    }
                });
                frame.getContentPane().add(section1);
                frame.revalidate();
                frame.repaint();
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Não é necessário fazer nada aqui, pois já estamos na Seção 2
            }
        });

        menu.add(menuItem1);
        menu.add(menuItem2);
        menuBar.add(menu);
        return menuBar;
    }
}
