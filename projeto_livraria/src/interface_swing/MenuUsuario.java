package interface_swing;

import Gerencia.Estoque.GerenciadorEstoque;
import Gerencia.GerenciadorGeral;
import Produtos.Produto;
import excecoes.ProdutoNaoEncontrado;
import pagamento.Cartao;
import pagamento.TipoCartao;
import pessoa.Cliente;
import pessoa.UserManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MenuUsuario {

    public static JPanel iniciarMenuUsuario(String login) {
        JPanel menuCliente = new JPanel();

        JButton btnBuscar = new JButton("Buscar Produto por nome");
        JButton btnBuscarId = new JButton("Buscar Produto por ID");
        JButton btnAdicionarCartao = new JButton("Adicionar cartão à conta");
        JButton filmesDisponiveis = new JButton("Filmes Disponíveis");
        JButton livrosDisponiveis = new JButton("Livros Disponíveis");
        JButton sair = new JButton("Sair");

        menuCliente.setLayout(new BoxLayout(menuCliente, BoxLayout.Y_AXIS));
        menuCliente.setSize(new java.awt.Dimension(800, 800));

        btnBuscar.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(20));
        menuCliente.add(btnBuscar);

        btnBuscarId.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(15));
        menuCliente.add(btnBuscarId);

        btnAdicionarCartao.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(15));
        menuCliente.add(btnAdicionarCartao);

        filmesDisponiveis.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(15));
        menuCliente.add(filmesDisponiveis);

        livrosDisponiveis.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(15));
        menuCliente.add(livrosDisponiveis);

        sair.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(15));
        menuCliente.add(sair);

        btnBuscar.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Digite o nome do produto:");
            if (nome != null && !nome.trim().isEmpty()) {
                try{
                    GerenciadorGeral.realizarBusca(nome);
                } catch (ProdutoNaoEncontrado ex){
                    JOptionPane.showMessageDialog(null, "Produto não encontrado no estoque.");
                }
            }
        });

        btnBuscarId.addActionListener(e -> {
            String id = JOptionPane.showInputDialog("Digite o ID do produto:");
            if (id != null && !id.trim().isEmpty()) {
                try{
                    GerenciadorEstoque.buscaProduto(id);
                } catch (ProdutoNaoEncontrado ex) {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado no estoque.");
                }
            }
        });

        btnAdicionarCartao.addActionListener(e -> {
            JTextField numeroField = new JTextField(16);
            JTextField codigoField = new JTextField(4);
            JTextField validadeField = new JTextField(5);
            JTextField tipoField = new JTextField(10);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.add(new JLabel("Número do cartão:(digite apenas números)"));
            panel.add(numeroField);
            panel.add(new JLabel("Código de segurança:"));
            panel.add(codigoField);
            panel.add(new JLabel("Validade (MM/AA):(digite neste formato, incluindo a barra)"));
            panel.add(validadeField);
            panel.add(new JLabel("Tipo de cartão (crédito/débito):(digite no formato 'crédito' ou 'débito')"));
            panel.add(tipoField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Cartão", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String numero = numeroField.getText().trim();
                    int codigo = Integer.parseInt(codigoField.getText().trim());
                    String validade = validadeField.getText().trim();
                    String tipo = tipoField.getText().trim().toLowerCase();
                    Cartao cartao = null;
                    TipoCartao tipoCartao;
                    if ((!tipo.equals("crédito") && !tipo.equals("débito"))) {
                        JOptionPane.showMessageDialog(null, "Tipo de cartão inválido! Use 'crédito' ou 'débito'.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (!validade.matches("\\d{2}/\\d{2}")) {
                        JOptionPane.showMessageDialog(null, "Formato de validade inválido! Use MM/AA.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (tipo.equals("crédito")) {
                        tipoCartao = TipoCartao.CREDITO;
                        cartao = new Cartao(numero, codigo, validade, tipoCartao);
                    } else {
                        tipoCartao = TipoCartao.DEBITO;
                        cartao = new Cartao(numero, codigo, validade, tipoCartao);
                    }
                    ArrayList<Cliente> clientes = UserManager.getClientes();
                    for (Cliente cliente : clientes) {
                        if (cliente.getLogin().equals(login)) {
                            cliente.setCartao(cartao);
                            JOptionPane.showMessageDialog(null, "Cartão cadastrado com sucesso!");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Número ou código inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        filmesDisponiveis.addActionListener(e -> {
            ArrayList<String> filmes = GerenciadorEstoque.getProdutos().stream()
                    .filter(Produto -> Produto.getClass().getSimpleName().equals("Filme"))
                    .map(Produto::getNome)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (!filmes.isEmpty()) {
                StringBuilder lista = new StringBuilder("Filmes disponíveis:\n");
                for (String filme : filmes) {
                    lista.append("- ").append(filme).append("\n");
                }
                JOptionPane.showMessageDialog(null, lista.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum filme disponível no momento.");
            }
        });

        livrosDisponiveis.addActionListener(e -> {
            ArrayList<String> livros = GerenciadorEstoque.getProdutos().stream()
                    .filter(Produto -> Produto.getClass().getSimpleName().equals("Livro"))
                    .map(Produto::getNome)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (!livros.isEmpty()) {
                StringBuilder lista = new StringBuilder("Livros disponíveis:\n");
                for (String livro : livros) {
                    lista.append("- ").append(livro).append("\n");
                }
                JOptionPane.showMessageDialog(null, lista.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum livro disponível no momento.");
            }
        });

        sair.addActionListener(e -> {
            MenuInicial.mostrarMenuPrincipal();
        });

        return menuCliente;
    }
}