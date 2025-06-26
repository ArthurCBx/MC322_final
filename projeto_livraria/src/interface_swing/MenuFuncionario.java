package interface_swing;

import Gerencia.Caixa.Caixa;
import Gerencia.Caixa.CompraVenda;
import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.Produto;
import excecoes.ProdutoNaoEncontrado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFuncionario {

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel container;

    public static JPanel iniciarMenuFuncionario(JFrame parent, CardLayout Layout, JPanel containeR) {

        frame = parent;
        cardLayout = Layout;
        container = containeR;

        // Menu iniciol do Funcionario:

        JPanel panel = new JPanel();

        JButton btnCompra = new JButton("Realizar Compra");
        JButton btnVenda = new JButton("Realizar Venda");
        JButton btnSair = new JButton("Sair");

        panel.add(btnCompra);
        panel.add(btnVenda);
        panel.add(btnSair);

        btnCompra.addActionListener(e -> {
            mostrarMenuCompra();
        });

        btnVenda.addActionListener(e -> {
            mostrarMenuVenda();
        });

        btnSair.addActionListener(e -> {
            MenuInicial.mostrarMenuPrincipal();
        });

        // Menu de Realizar Compras:

        JPanel compra = iniciarMenuCompra();
        container.add(compra, "Compra");

        // Menu de Realizar Vendas:

        JPanel venda = iniciarMenuVenda();
        container.add(venda, "Venda");

        return panel;

    }

    private static void mostrarMenuCompra() {
        frame.setSize(1200, 400);
        cardLayout.show(container, "Compra");
    }

    private static void mostrarMenuVenda() {
        frame.setSize(1200, 400);
        cardLayout.show(container, "Venda");
    }

    private static JPanel iniciarMenuCompra() {
        JPanel compra = new JPanel();

        // Criar o DefaultListModel que irá armazenar os itens da lista
        DefaultListModel<CompraVenda> listaModel = new DefaultListModel<>();

        // Criar o JList usando o DefaultListModel
        JList<CompraVenda> listaDeCompras = new JList<>(listaModel);
        listaDeCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permitir seleção de um item por vez

        // Adicionar a JList dentro de um JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(listaDeCompras);

        // Adicionar o JScrollPane ao JFrame
        compra.add(scrollPane, BorderLayout.NORTH);

        // Painel para os botões (Adicionar, Remover e Alterar Quantidade)
        JPanel painelBotoes = new JPanel();
        compra.add(painelBotoes, BorderLayout.SOUTH);

        // Campo de texto para o nome do item
        JTextField campoTexto = new JTextField(15);
        painelBotoes.add(campoTexto);

        // Campo de texto para a quantidade do item
        JTextField campoQuantidade = new JTextField(5);
        painelBotoes.add(campoQuantidade);

        // Botões
        JButton adicionarButton = new JButton("Adicionar Produto");
        JButton removerButton = new JButton("Remover produto");
        JButton aumentarButton = new JButton("Aumentar Quantidade");
        JButton diminuirButton = new JButton("Diminuir Quantidade");

        painelBotoes.add(adicionarButton);
        painelBotoes.add(removerButton);
        painelBotoes.add(aumentarButton);
        painelBotoes.add(diminuirButton);

        // Ação do botão "Adicionar"
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = campoTexto.getText();
                String quantidadeTexto = campoQuantidade.getText();

                if (!ID.isEmpty() && !quantidadeTexto.isEmpty()) {
                    try {
                        int quantidade = Integer.parseInt(quantidadeTexto);
                        Produto produto = GerenciadorEstoque.getProdutos().get(GerenciadorEstoque.buscaProduto(ID));
                        if (quantidade > 0) {
                            try {
                                // Se o produto já está na lista, incrementa seu valor
                                int index = Caixa.buscaCompraVenda(produto.getId());
                                Caixa.getComprasvendas().get(index).setQuantidade(quantidade + Caixa.getComprasvendas().get(index).getQuantidade());
                                listaModel.setElementAt(Caixa.getComprasvendas().get(index), index);
                                campoTexto.setText("");  // Limpa o campo de texto
                                campoQuantidade.setText("");  // Limpa o campo de quantidade

                            } catch (ProdutoNaoEncontrado ex) {
                                // Adiciona o item na lista
                                Caixa.adiconarCompraVenda(produto, quantidade);
                                listaModel.addElement(Caixa.getComprasvendas().getLast());
                                campoTexto.setText("");  // Limpa o campo de texto
                                campoQuantidade.setText("");  // Limpa o campo de quantidade

                            }
                        } else {
                            JOptionPane.showMessageDialog(compra, "Quantidade deve ser maior que 0!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ProdutoNaoEncontrado ex) {
                        JOptionPane.showMessageDialog(compra, "Produto não Encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(compra, "Digite uma quantidade válida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(compra, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Remover"
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaDeCompras.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    Caixa.removerCompraVenda(indiceSelecionado);
                    listaModel.remove(indiceSelecionado); // Remove o item selecionado
                } else {
                    JOptionPane.showMessageDialog(compra, "Selecione um item para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Aumentar Quantidade"
        aumentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaDeCompras.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    CompraVenda compravenda = listaModel.getElementAt(indiceSelecionado);
                    compravenda.setQuantidade(compravenda.getQuantidade() + 1);
                    listaModel.setElementAt(compravenda, indiceSelecionado); // Atualiza a lista
                } else {
                    JOptionPane.showMessageDialog(compra, "Selecione um item para aumentar a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Diminuir Quantidade"
        diminuirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaDeCompras.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    CompraVenda compravenda = listaModel.getElementAt(indiceSelecionado);
                    if (compravenda.getQuantidade() > 1) {
                        compravenda.setQuantidade(compravenda.getQuantidade() - 1);
                        listaModel.setElementAt(compravenda, indiceSelecionado); // Atualiza a lista
                    } else {
                        JOptionPane.showMessageDialog(compra, "A quantidade não pode ser menor que 1!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(compra, "Selecione um item para diminuir a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botões de Cancelar e Concluir
        JButton btnConcluir = new JButton("Concluir Compra");
        JButton btnCancelar = new JButton("Cancelar Compra");

        compra.add(btnConcluir);
        compra.add(btnCancelar);

        btnConcluir.addActionListener(e -> {
            Caixa.registrarCompra();
            listaModel.clear();
            MenuInicial.mostrarMenuFuncionario();
        });

        btnCancelar.addActionListener(e -> {
            listaModel.clear();
            Caixa.getComprasvendas().clear();
            MenuInicial.mostrarMenuFuncionario();
        });

        return compra;
    }


    private static JPanel iniciarMenuVenda() {

        JPanel venda = new JPanel();

        // Criar o DefaultListModel que irá armazenar os itens da lista
        DefaultListModel<CompraVenda> listaModel = new DefaultListModel<>();

        // Criar o JList usando o DefaultListModel
        JList<CompraVenda> listaDeCompras = new JList<>(listaModel);
        listaDeCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permitir seleção de um item por vez

        // Adicionar a JList dentro de um JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(listaDeCompras);

        // Adicionar o JScrollPane ao JFrame
        venda.add(scrollPane, BorderLayout.NORTH);

        // Painel para os botões (Adicionar, Remover e Alterar Quantidade)
        JPanel painelBotoes = new JPanel();
        venda.add(painelBotoes, BorderLayout.SOUTH);

        // Campo de texto para o nome do item
        JTextField campoTexto = new JTextField(15);
        painelBotoes.add(campoTexto);

        // Campo de texto para a quantidade do item
        JTextField campoQuantidade = new JTextField(5);
        painelBotoes.add(campoQuantidade);

        // Botões
        JButton adicionarButton = new JButton("Adicionar Produto");
        JButton removerButton = new JButton("Remover produto");
        JButton aumentarButton = new JButton("Aumentar Quantidade");
        JButton diminuirButton = new JButton("Diminuir Quantidade");

        painelBotoes.add(adicionarButton);
        painelBotoes.add(removerButton);
        painelBotoes.add(aumentarButton);
        painelBotoes.add(diminuirButton);

        // Ação do botão "Adicionar"
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = campoTexto.getText();
                String quantidadeTexto = campoQuantidade.getText();

                if (!ID.isEmpty() && !quantidadeTexto.isEmpty()) {
                    try {
                        int quantidade = Integer.parseInt(quantidadeTexto);
                        Produto produto = GerenciadorEstoque.getProdutos().get(GerenciadorEstoque.buscaProduto(ID));
                        if (quantidade > 0) {
                            try {
                                // Se o produto já está na lista, incrementa seu valor
                                int index = Caixa.buscaCompraVenda(produto.getId());
                                if (produto.getQuantidadeDisponivel() >= quantidade + Caixa.getComprasvendas().get(index).getQuantidade()) {
                                    Caixa.getComprasvendas().get(index).setQuantidade(quantidade + Caixa.getComprasvendas().get(index).getQuantidade());
                                    listaModel.setElementAt(Caixa.getComprasvendas().get(index), index);
                                    campoTexto.setText("");  // Limpa o campo de texto
                                    campoQuantidade.setText("");  // Limpa o campo de quantidade
                                } else {
                                    JOptionPane.showMessageDialog(venda, "Não há produto sucifiente no estoque! Numero disponivel: " + produto.getQuantidadeDisponivel(), "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (ProdutoNaoEncontrado ex) {
                                if (produto.getQuantidadeDisponivel() >= quantidade) {
                                    // Adiciona o item na lista
                                    Caixa.adiconarCompraVenda(produto, quantidade);
                                    listaModel.addElement(Caixa.getComprasvendas().getLast());
                                    campoTexto.setText("");  // Limpa o campo de texto
                                    campoQuantidade.setText("");  // Limpa o campo de quantidade
                                } else {
                                    JOptionPane.showMessageDialog(venda, "Não há produto sucifiente no estoque! Numero disponivel: " + produto.getQuantidadeDisponivel(), "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(venda, "Quantidade deve ser maior que 0!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ProdutoNaoEncontrado ex) {
                        JOptionPane.showMessageDialog(venda, "Produto não Encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(venda, "Digite uma quantidade válida!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(venda, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Remover"
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaDeCompras.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    Caixa.removerCompraVenda(indiceSelecionado);
                    listaModel.remove(indiceSelecionado); // Remove o item selecionado
                } else {
                    JOptionPane.showMessageDialog(venda, "Selecione um item para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Aumentar Quantidade"
        aumentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaDeCompras.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    CompraVenda compravenda = listaModel.getElementAt(indiceSelecionado);
                    if (compravenda.getProduto().getQuantidadeDisponivel() > compravenda.getQuantidade()) {
                        compravenda.setQuantidade(compravenda.getQuantidade() + 1);
                        listaModel.setElementAt(compravenda, indiceSelecionado); // Atualiza a lista
                    } else {
                        JOptionPane.showMessageDialog(venda, "Não há produto sucifiente no estoque! Numero disponivel: " + compravenda.getProduto().getQuantidadeDisponivel(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(venda, "Selecione um item para aumentar a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão "Diminuir Quantidade"
        diminuirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSelecionado = listaDeCompras.getSelectedIndex();
                if (indiceSelecionado != -1) {
                    CompraVenda compravenda = listaModel.getElementAt(indiceSelecionado);
                    if (compravenda.getQuantidade() > 1) {
                        compravenda.setQuantidade(compravenda.getQuantidade() - 1);
                        listaModel.setElementAt(compravenda, indiceSelecionado); // Atualiza a lista
                    } else {
                        JOptionPane.showMessageDialog(venda, "A quantidade não pode ser menor que 1!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(venda, "Selecione um item para diminuir a quantidade!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Botões de Cancelar e Concluir
        JButton btnConcluir = new JButton("Concluir Compra");
        JButton btnCancelar = new JButton("Cancelar Compra");

        venda.add(btnConcluir);
        venda.add(btnCancelar);

        btnConcluir.addActionListener(e -> {
            Caixa.registrarCompra();
            listaModel.clear();
            MenuInicial.mostrarMenuFuncionario();
        });

        btnCancelar.addActionListener(e -> {
            listaModel.clear();
            Caixa.getComprasvendas().clear();
            MenuInicial.mostrarMenuFuncionario();
        });


        return venda;

    }

}
