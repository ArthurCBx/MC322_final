package interface_swing;

import Gerencia.Caixa.Caixa;
import Gerencia.Caixa.CompraVenda;
import Gerencia.Estoque.GerenciadorEstoque;
import Gerencia.GerenciadorGeral;
import Produtos.Produto;
import excecoes.ProdutoNaoEncontrado;
import pagamento.TipoCartao;
import pagamento.TipoPagamento;
import pessoa.Cliente;
import pessoa.UserManager;

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
        frame.setSize(1500, 400);
        cardLayout.show(container, "Compra");
    }

    private static void mostrarMenuVenda() {
        frame.setSize(1500, 400);
        GerenciadorGeral.setCliente(null);
        cardLayout.show(container, "Venda");
    }

    private static JPanel iniciarMenuCompra() {
        JPanel compra = new JPanel();
        compra.setLayout(new BorderLayout());

        DefaultListModel<CompraVenda> listaModel = new DefaultListModel<>();
        JList<CompraVenda> listaDeCompras = new JList<>(listaModel);
        listaDeCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaDeCompras);
        compra.add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        compra.add(painelBotoes, BorderLayout.SOUTH);

        // Painel para labels e campos
        JPanel painelCampos = new JPanel();
        painelCampos.add(new JLabel("ID do Produto:"));
        JTextField campoTexto = new JTextField(15);
        painelCampos.add(campoTexto);
        painelCampos.add(new JLabel("Quantidade:"));
        JTextField campoQuantidade = new JTextField(5);
        painelCampos.add(campoQuantidade);

        painelBotoes.add(painelCampos);

        JButton adicionarButton = new JButton("Adicionar Produto");
        JButton removerButton = new JButton("Remover produto");
        JButton aumentarButton = new JButton("Aumentar Quantidade");
        JButton diminuirButton = new JButton("Diminuir Quantidade");

        painelBotoes.add(adicionarButton);
        painelBotoes.add(removerButton);
        painelBotoes.add(aumentarButton);
        painelBotoes.add(diminuirButton);

        JButton btnConcluir = new JButton("Concluir Compra");
        JButton btnCancelar = new JButton("Cancelar Compra");
        painelBotoes.add(btnConcluir);
        painelBotoes.add(btnCancelar);

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
                            JOptionPane.showMessageDialog(compra, "Quantidade deve ser maior que 0", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (ProdutoNaoEncontrado ex) {
                        JOptionPane.showMessageDialog(compra, "Produto não Encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(compra, "Digite uma quantidade válida", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(compra, "Preencha todos os campos", "Erro", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(compra, "Selecione um item para remover", "Erro", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(compra, "Selecione um item para aumentar a quantidade", "Erro", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(compra, "A quantidade não pode ser menor que 1", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(compra, "Selecione um item para diminuir a quantidade", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

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
        venda.setLayout(new BorderLayout());

        DefaultListModel<CompraVenda> listaModel = new DefaultListModel<>();
        JList<CompraVenda> listaDeCompras = new JList<>(listaModel);
        listaDeCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaDeCompras);
        venda.add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();

        // Painel para labels e campos
        JPanel painelCampos = new JPanel();
        painelCampos.add(new JLabel("ID do Produto:"));
        JTextField campoTexto = new JTextField(15);
        painelCampos.add(campoTexto);
        painelCampos.add(new JLabel("Quantidade:"));
        JTextField campoQuantidade = new JTextField(5);
        painelCampos.add(campoQuantidade);

        painelBotoes.add(painelCampos);

        JButton adicionarButton = new JButton("Adicionar Produto");
        JButton removerButton = new JButton("Remover produto");
        JButton aumentarButton = new JButton("Aumentar Quantidade");
        JButton diminuirButton = new JButton("Diminuir Quantidade");

        painelBotoes.add(adicionarButton);
        painelBotoes.add(removerButton);
        painelBotoes.add(aumentarButton);
        painelBotoes.add(diminuirButton);

        // **Novo Painel de Botões para Cliente**
        JPanel painelCliente = new JPanel();

        JTextField campoCliente = new JTextField(15);
        campoCliente.setToolTipText("Digite o nome do cliente");
        painelCliente.add(campoCliente);

        JButton selecionarClienteButton = new JButton("Selecionar Cliente");
        painelCliente.add(selecionarClienteButton);

        JButton adicionarClienteButton = new JButton("Adicionar Cliente");
        painelCliente.add(adicionarClienteButton);

        JLabel clienteSelecionadoLabel = new JLabel("Cliente selecionado: Nenhum");
        painelCliente.add(clienteSelecionadoLabel);

        JComboBox<TipoPagamento> metodoPagamentoComboBox = new JComboBox<>(TipoPagamento.values());
        painelCliente.add(new JLabel("  |  Pagamento:"));
        painelCliente.add(metodoPagamentoComboBox);

        JComboBox<TipoCartao> tipoCartaoComboBox = new JComboBox<>(TipoCartao.values());
        painelCliente.add(new JLabel("Tipo de Cartão:"));
        painelCliente.add(tipoCartaoComboBox);

        tipoCartaoComboBox.setVisible(false);

        JPanel painelInferior = new JPanel(new BorderLayout());
        venda.add(painelInferior, BorderLayout.SOUTH);

        painelInferior.add(painelBotoes, BorderLayout.CENTER);

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
                                if (Caixa.getComprasvendas().get(index).getQuantidade() + quantidade <= produto.getQuantidadeDisponivel()) {
                                    Caixa.getComprasvendas().get(index).setQuantidade(quantidade + Caixa.getComprasvendas().get(index).getQuantidade());
                                    listaModel.setElementAt(Caixa.getComprasvendas().get(index), index);
                                    campoTexto.setText("");  // Limpa o campo de texto
                                    campoQuantidade.setText("");  // Limpa o campo de quantidade
                                } else {
                                    JOptionPane.showMessageDialog(venda, "Não há produto suficiente no Estoque! Disponivel: " + Caixa.getComprasvendas().get(index).getProduto().getQuantidadeDisponivel(), "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (ProdutoNaoEncontrado ex) {
                                // Adiciona o item na lista
                                if (quantidade <= produto.getQuantidadeDisponivel()) {
                                    Caixa.adiconarCompraVenda(produto, quantidade);
                                    listaModel.addElement(Caixa.getComprasvendas().getLast());
                                    campoTexto.setText("");  // Limpa o campo de texto
                                    campoQuantidade.setText("");  // Limpa o campo de quantidade
                                } else {
                                    JOptionPane.showMessageDialog(venda, "Não há produto suficiente no Estoque! Disponivel: " + produto.getQuantidadeDisponivel(), "Erro", JOptionPane.ERROR_MESSAGE);
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
                    if (compravenda.getQuantidade() + 1 <= compravenda.getProduto().getQuantidadeDisponivel()) {
                        compravenda.setQuantidade(compravenda.getQuantidade() + 1);
                    } else {
                        JOptionPane.showMessageDialog(venda, "Não há produto suficiente no Estoque! Disponivel: " + compravenda.getProduto().getQuantidadeDisponivel(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    listaModel.setElementAt(compravenda, indiceSelecionado); // Atualiza a lista
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


// Botões de Cancelar e Concluir a venda
        JButton btnPagamento = new JButton("Pagamento");
        JButton btnCancelar = new JButton("Cancelar Venda");

        painelBotoes.add(btnPagamento);
        painelBotoes.add(btnCancelar);

        btnPagamento.addActionListener(e -> {
            painelInferior.removeAll();
            painelInferior.add(painelCliente, BorderLayout.CENTER);
            painelInferior.revalidate();
            painelInferior.repaint();
        });

        btnCancelar.addActionListener(e -> {
            listaModel.clear();
            Caixa.getComprasvendas().clear();
            MenuInicial.mostrarMenuFuncionario();
        });


// Ação do botão "Adicionar Cliente"
        adicionarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuInicial.cadastroCliente(frame);  // Redireciona para o cadastro de cliente
            }
        });

// Ação do botão "Selecionar Cliente"
        selecionarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campoCliente.getText();
                if (!login.isEmpty()) {
                    if (UserManager.getClientes().stream().map(Cliente::getLogin).toList().contains(login)) {
                        // Atualiza o display do cliente selecionado e o GerenciadorGeral
                        GerenciadorGeral.setCliente(UserManager.getClientes().stream().filter(cliente -> cliente.getLogin().equals(login)).findFirst().get());
                        clienteSelecionadoLabel.setText("Cliente selecionado: " + GerenciadorGeral.getCliente().getNome());
                    } else {
                        JOptionPane.showMessageDialog(venda, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(venda, "Digite o nome do cliente para selecionar", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Seleção de Tipo de Cartão
        metodoPagamentoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoPagamento selecionado = (TipoPagamento) metodoPagamentoComboBox.getSelectedItem();
                if (selecionado != null && selecionado.name().equalsIgnoreCase("CARTAO")) {
                    tipoCartaoComboBox.setVisible(true);
                } else {
                    tipoCartaoComboBox.setVisible(false);
                }
                painelCliente.revalidate();
                painelCliente.repaint();
            }
        });

// Botões de Cancelar e Concluir a venda
        JButton btnConcluir = new JButton("Concluir Venda");
        JButton btnVoltar = new JButton("Voltar");

        painelCliente.add(btnConcluir);
        painelCliente.add(btnVoltar);

        btnConcluir.addActionListener(e -> {
            if (GerenciadorGeral.getCliente() == null) {
                JOptionPane.showMessageDialog(venda, "Selecione o cliente antes de concluir a venda", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            TipoPagamento metodoSelecionado = (TipoPagamento) metodoPagamentoComboBox.getSelectedItem();
            if (metodoSelecionado == null) {
                JOptionPane.showMessageDialog(venda, "Selecione um método de pagamento", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (metodoSelecionado.name().equalsIgnoreCase("CARTAO")) {
                TipoCartao tipoCartaoSelecionado = (TipoCartao) tipoCartaoComboBox.getSelectedItem();
                if (tipoCartaoSelecionado == null) {
                    JOptionPane.showMessageDialog(venda, "Selecione o tipo de cartão (crédito ou débito)", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("Pagamento com cartão: " + tipoCartaoSelecionado);
            } else {
                System.out.println("Pagamento por: " + metodoSelecionado);
            }

            Caixa.registrarVenda(metodoSelecionado, (TipoCartao) tipoCartaoComboBox.getSelectedItem());
            listaModel.clear();
            clienteSelecionadoLabel.setText("");
            GerenciadorGeral.setCliente(null);
            campoCliente.setText("");
            painelInferior.removeAll();
            painelInferior.add(painelBotoes, BorderLayout.CENTER);
            painelInferior.revalidate();
            painelInferior.repaint();
            MenuInicial.mostrarMenuFuncionario();
        });

        btnVoltar.addActionListener(e -> {
            painelInferior.removeAll();
            painelInferior.add(painelBotoes, BorderLayout.CENTER);
            painelInferior.revalidate();
            painelInferior.repaint();
        });

        return venda;
    }

}
