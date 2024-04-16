import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Section1 extends JPanel {
    private JButton goToSection2Button;
    private JTextField emailField;
    private JPasswordField passwordField;
    private boolean passwordVisible = false;

    public Section1(ActionListener goToSection2Listener) {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel label1 = new JLabel("Bem-Vindo a tela de Login");
        JLabel emailLabel = new JLabel("Digite seu Email: ");
        JLabel passwordLabel = new JLabel("Digite sua Senha: ");
        goToSection2Button = new JButton("Ir para a Seção 2");
        goToSection2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String senha = new String(passwordField.getPassword());
                boolean loginSucesso = verificarLogin(email, senha);
                if (loginSucesso) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido! Redirecionando para a Seção 2.");
                    goToSection2Listener.actionPerformed(e);
                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha incorreto.");
                }
            }
        });
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);

        ImageIcon showIcon = new ImageIcon("eye_open.png");
        ImageIcon hideIcon = new ImageIcon("eye_close.png");
        JButton showHidePasswordButton = new JButton(hideIcon);
        showHidePasswordButton.setFocusPainted(false);
        showHidePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordVisible = !passwordVisible;
                showHidePasswordButton.setIcon(passwordVisible ? showIcon : hideIcon);
                passwordField.setEchoChar(passwordVisible ? '\0' : '•');
            }
        });

        passwordField.setEchoChar('•');

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(label1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(emailLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(emailField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(goToSection2Button, constraints);
    }

    private boolean verificarLogin(String email, String senha) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_login", "postgres", "lucas2007");
            String query = "SELECT * FROM login WHERE email = ? AND senha = ?";
            PreparedStatement pst = conexao.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, senha);
            ResultSet rs = pst.executeQuery();
            return rs.next(); // Retorna true se encontrou um resultado, ou seja, login correto
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
