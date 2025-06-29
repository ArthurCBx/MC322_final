package interface_swing;

import Gerencia.Estoque.GerenciadorEstoque;
import Gerencia.GerenciadorGeral;
import pessoa.Cliente;
import pessoa.UserManager;

import javax.swing.*;
import java.awt.*;

public class MenuInicial {

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel container;

    public static void iniciarMenuInicial() {
        frame = new JFrame("Menu Inicial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Menu Principal:

        JPanel principal = new JPanel();

        JButton btnLogin = new JButton("Login");
        JButton btnCadastro = new JButton("Novo Cadastro");
        JButton btnSair = new JButton("Sair");

        btnLogin.addActionListener(e -> {
            login(frame);
        });

        btnCadastro.addActionListener(e -> {
            cadastroCliente(frame);
        });

        btnSair.addActionListener(e -> {
            GerenciadorEstoque.salvaProduto();
            System.exit(0);
        });


        principal.add(btnLogin);
        principal.add(btnCadastro);
        principal.add(btnSair);

        container.add(principal, "Principal");

        // Menu Funcionario
        JPanel funcionario = MenuFuncionario.iniciarMenuFuncionario(frame, cardLayout, container);
        container.add(funcionario, "Funcionario");

        JPanel gerente = MenuGerente.iniciarMenuGerente(frame, cardLayout, container);
        container.add(gerente, "Gerente");

        frame.add(container);
        frame.setVisible(true);
    }

    public static void mostrarMenuPrincipal() {
        frame.setTitle("Menu Inicial");
        cardLayout.show(container, "Principal");

    }

    public static void mostrarMenuUsuario(String login) {
        frame.setTitle("Menu Usuario");
        JPanel usuario = MenuUsuario.iniciarMenuUsuario(login);
        container.add(usuario, "Usuario");
        frame.setPreferredSize(new java.awt.Dimension (500, 500));
        frame.pack();
        cardLayout.show(container, "Usuario");
    }

    public static void mostrarMenuFuncionario() {
        frame.setTitle("Menu Funcionario");
        cardLayout.show(container, "Funcionario");

    }

    public static void mostrarMenuGerente() {
        frame.setTitle("Menu Gerente");
        cardLayout.show(container, "Gerente");
    }

    protected static void cadastroCliente(JFrame parent) {
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

        if (result == JOptionPane.OK_OPTION) {
            String nomeCliente = nome.getText().trim();
            String cpfCliente = cpf.getText().trim();
            String dataNascimentoCliente = dataNascimento.getText().trim();
            String emailCliente = email.getText().trim();
            String loginCliente = login.getText().trim();
            String senhaCliente = senha.getText().trim();

            Boolean[] podeCriar = UserManager.loginExists(loginCliente, senhaCliente);
            while (podeCriar[0]) {
                loginCliente = JOptionPane.showInputDialog(parent, "Login já existe. Por favor, escolha outro login:", loginCliente);
                if (loginCliente == null || loginCliente.trim().isEmpty()) return; // Cancela a operação
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
            if (!confirmLogin[0]) {
                JOptionPane.showMessageDialog(parent, "Login não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (!confirmLogin[1]) {
                JOptionPane.showMessageDialog(parent, "Senha incorreta", "Erro", JOptionPane.ERROR_MESSAGE);
            } else if (confirmLogin[2]) {
                JOptionPane.showMessageDialog(parent, "Login de cliente realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                mostrarMenuUsuario(loginUsuario);

            } else if (confirmLogin[3]) {
                JOptionPane.showMessageDialog(parent, "Login de funcionário realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                GerenciadorGeral.setFuncionario(UserManager.getFuncionarios().stream().filter(funcionario -> funcionario.getLogin().equals(loginUsuario)).findFirst().get());
                mostrarMenuFuncionario();

            } else if (confirmLogin[4]) {
                JOptionPane.showMessageDialog(parent, "Login de gerente realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                GerenciadorGeral.setFuncionario(UserManager.getFuncionarios().stream().filter(funcionario -> funcionario.getLogin().equals(loginUsuario)).findFirst().get());
                mostrarMenuGerente();

            }
        }
    }
}
