package interface_swing;

import Gerencia.Estoque.GerenciadorEstoque;
import Gerencia.GerenciadorGeral;
import Produtos.Filme.Filme;
import Produtos.Livro.Livro;
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

        JButton btnBuscarLivro = new JButton("Buscar Livro por nome");
        JButton btnBuscarFilme= new JButton("Buscar Filme por nome");
        JButton btnAdicionarCartao = new JButton("Adicionar cartão à conta");
        JButton filmesDisponiveis = new JButton("Verificar Filmes disponíveis na livraria");
        JButton livrosDisponiveis = new JButton("Verificar Livros disponíveis na livraria");
        JButton sair = new JButton("Sair");

        menuCliente.setLayout(new BoxLayout(menuCliente, BoxLayout.Y_AXIS));
        menuCliente.setSize(new java.awt.Dimension(800, 800));

        btnBuscarLivro.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(20));
        menuCliente.add(btnBuscarLivro);

        btnBuscarFilme.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        menuCliente.add(Box.createVerticalStrut(15));
        menuCliente.add(btnBuscarFilme);

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

        btnBuscarLivro.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Digite o nome do livro a ser buscado:");
            if (nome != null && !nome.trim().isEmpty()) {
                try{
                    boolean found = false;
                    ArrayList<Livro> livros = GerenciadorGeral.getLivros();
                    if (livros == null || livros.isEmpty()) {
                        throw new ProdutoNaoEncontrado("Produto não encontrado no estoque.");
                    }
                    for (Livro livro : livros) {
                        if (livro.getNome().equalsIgnoreCase(nome)) {
                            JOptionPane.showMessageDialog(null, "Livro encontrado! \n" + livro.toString());
                            found = true;
                        }
                    }
                    if (!found) {
                        throw new ProdutoNaoEncontrado("Produto não encontrado no estoque.");
                    }
                } catch (ProdutoNaoEncontrado ex){
                    JOptionPane.showMessageDialog(null, "Produto não encontrado no estoque.");
                }
            }
        });

        btnBuscarFilme.addActionListener(e -> {
            String nome = JOptionPane.showInputDialog("Digite o nome do filme a ser buscado:");
            if (nome != null && !nome.trim().isEmpty()) {
                try{
                    boolean found = false;
                    ArrayList<Filme> filmes = GerenciadorGeral.getFilmes();
                    if (filmes == null || filmes.isEmpty()) {
                        throw new ProdutoNaoEncontrado("Produto não encontrado no estoque.");
                    }
                    for (Filme filme : filmes) {
                        if (filme.getNome().equalsIgnoreCase(nome)) {
                            JOptionPane.showMessageDialog(null, "Filme encontrado! \n" + filme.toString());
                            found = true;
                        }
                    }
                    if (!found) {
                        throw new ProdutoNaoEncontrado("Produto não encontrado no estoque.");
                    }
                } catch (ProdutoNaoEncontrado ex){
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
            ArrayList<Filme> filmes = GerenciadorEstoque.getProdutos().stream()
                    .filter(produto -> produto instanceof Filme)
                    .map(produto -> (Filme) produto)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (!filmes.isEmpty()) {
                StringBuilder lista = new StringBuilder("Filmes disponíveis:\n");
                for (Filme filme : filmes) {
                    lista.append("- ").append(filme.getNome()).append(". Disponível na seção ").append(filme.getSecao()).append(".\n");
                }
                JOptionPane.showMessageDialog(null, lista.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum filme disponível no momento.");
            }
        });

        livrosDisponiveis.addActionListener(e -> {
            ArrayList<Livro> livros = GerenciadorEstoque.getProdutos().stream()
                    .filter(produto -> produto instanceof Livro)
                    .map(produto -> (Livro) produto)
                    .collect(Collectors.toCollection(ArrayList::new));
            if (!livros.isEmpty()) {
                StringBuilder lista = new StringBuilder("Livros disponíveis:\n");
                for (Livro livro : livros) {
                    lista.append("- ").append(livro.getNome()).append(". Disponível na seção ").append(livro.getSecao()).append(".\n");
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