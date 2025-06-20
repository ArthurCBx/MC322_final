package interface_swing;

import pessoa.Cliente;
import pessoa.UserManager;

import javax.swing.*;

public class MenuInicial {
    public static void mostrarMenuInicial() {
        JFrame frame = new JFrame("Menu Inicial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JButton btnLogin = new JButton("Login");
        JButton btnCadastro = new JButton("Novo Cadastro");
        JButton btnSair = new JButton("Sair");

        btnLogin.addActionListener(e -> {
            login(frame);
        });

        btnCadastro.addActionListener(e -> {
            cadastroCliente(frame);
        });

        btnSair.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        panel.add(btnLogin);
        panel.add(btnCadastro);
        panel.add(btnSair);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void cadastroCliente(JFrame parent){
        JTextField nome = new JTextField(30);
        JTextField cpf = new JTextField(15);
        JTextField dataNascimento = new JTextField(10);
        JTextField email = new JTextField(30);
        JTextField login = new JTextField(15);
        JTextField senha = new JPasswordField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Nome:"));
        panel.add(nome);
        panel.add(new JLabel("CPF:"));
        panel.add(cpf);
        panel.add(new JLabel("Data de Nascimento:"));
        panel.add(dataNascimento);
        panel.add(new JLabel("Email:"));
        panel.add(email);
        panel.add(new JLabel("Login:"));
        panel.add(login);
        panel.add(new JLabel("Senha:"));
        panel.add(senha);
        int result = JOptionPane.showConfirmDialog(parent, panel, "Cadastro de Cliente", JOptionPane.OK_CANCEL_OPTION);

        if(result == JOptionPane.OK_OPTION) {
            String nomeCliente = nome.getText().trim();
            String cpfCliente = cpf.getText().trim();
            String dataNascimentoCliente = dataNascimento.getText().trim();
            String emailCliente = email.getText().trim();
            String loginCliente = login.getText().trim();
            String senhaCliente = senha.getText().trim();

            Boolean[] podeCriar = UserManager.loginExists(loginCliente, senhaCliente);
            while(podeCriar[0]){
                loginCliente = JOptionPane.showInputDialog(parent, "Login já existe. Por favor, escolha outro login:", loginCliente);
                if(loginCliente == null || loginCliente.trim().isEmpty()) return; // Cancela a operação
                podeCriar = UserManager.loginExists(loginCliente.trim(), senhaCliente);
            }

            Cliente cliente = new Cliente(nomeCliente, cpfCliente, dataNascimentoCliente, emailCliente, loginCliente, senhaCliente);
            UserManager.writeClientToFile(cliente);
            JOptionPane.showMessageDialog(parent, "Cadastro realizado com sucesso!");
        }
    }

    private static void login(JFrame parent) {
        JTextField login = new JTextField(15);
        JTextField senha = new JPasswordField(15);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Login:"));
        panel.add(login);
        panel.add(new JLabel("Senha:"));
        panel.add(senha);

        int result = JOptionPane.showConfirmDialog(parent, panel, "Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String loginUsuario = login.getText();
            String senhaUsuario = senha.getText();

            Boolean[] confirmLogin = UserManager.loginExists(loginUsuario, senhaUsuario);
            if (!confirmLogin[0] ) {
                JOptionPane.showMessageDialog(parent, "Login não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if(!confirmLogin[1]) {
                JOptionPane.showMessageDialog(parent, "Senha incorreta", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (confirmLogin[2]) {
                JOptionPane.showMessageDialog(parent, "Login de cliente realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Lógica do cliente aqui

            } else if (confirmLogin[3]) {
                JOptionPane.showMessageDialog(parent, "Login de funcionário realizado com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Lógica de funcionário aqui

            } else if (confirmLogin[4]) {
                JOptionPane.showMessageDialog(parent, "Login de gerente realizado com sucesso!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
                // Lógica de gerente aqui

            }
        }
    }
}
