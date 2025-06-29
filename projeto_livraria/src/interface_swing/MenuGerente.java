package interface_swing;

import Gerencia.Caixa.Caixa;
import Gerencia.Caixa.CompraVenda;
import Gerencia.Estoque.GerenciadorEstoque;
import Gerencia.GerenciadorGeral;
import Produtos.Filme.Filme;
import Produtos.Generico.Generico;
import Produtos.Genero;
import Produtos.Livro.Livro;
import Produtos.Produto;
import excecoes.ProdutoNaoEncontrado;
import pagamento.TipoCartao;
import pagamento.TipoPagamento;
import pessoa.Cliente;
import pessoa.UserManager;
import pessoa.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuGerente {

    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel container;

    public static JPanel iniciarMenuGerente(JFrame parent, CardLayout Layout, JPanel containeR) {

        frame = parent;
        cardLayout = Layout;
        container = containeR;

        // Menu iniciol do Gerente:

        JPanel panel = new JPanel();

        JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        JButton btnRemoverProduto = new JButton("Remover Produto");
        JButton btnMoveFuncionario = new JButton("Adicionar/Remover Funcionário");
        JButton btnCompra = new JButton("Realizar Compra");
        JButton btnVenda = new JButton("Realizar Venda");
        JButton btnverificaSaldo = new JButton("Verificar Saldo");
        JButton btnSair = new JButton("Sair");

        panel.add(btnCadastrarProduto);
        panel.add(btnAdicionarProduto);
        panel.add(btnRemoverProduto);
        panel.add(btnMoveFuncionario);
        panel.add(btnverificaSaldo);
        panel.add(btnCompra);
        panel.add(btnVenda);
        panel.add(btnSair);

        btnCadastrarProduto.addActionListener(e -> {
            mostrarMenuCadP();
        });

        btnAdicionarProduto.addActionListener(e -> {

        });

        btnRemoverProduto.addActionListener(e -> {

        });

        btnMoveFuncionario.addActionListener(e -> {
            String[] opcoes = {"Adicionar Funcionário", "Remover Funcionário", "Cancelar"};
            int escolha = JOptionPane.showOptionDialog(
                    frame,
                    "O que deseja fazer?",
                    "Gerenciar Funcionário",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == 0) {
                iniciarMenuGerencia();
            } else if (escolha == 1) {
                JPanel removerPanel = new JPanel();
                removerPanel.setLayout(new BoxLayout(removerPanel, BoxLayout.Y_AXIS));
                JTextField nomeField = new JTextField(20);
                JTextField loginField = new JTextField(20);

                removerPanel.add(new JLabel("Nome do Funcionário:"));
                removerPanel.add(nomeField);
                removerPanel.add(new JLabel("Login do Funcionário:"));
                removerPanel.add(loginField);

                int result = JOptionPane.showConfirmDialog(
                        frame,
                        removerPanel,
                        "Remover Funcionário",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (result == JOptionPane.OK_OPTION) {
                    String nome = nomeField.getText().trim();
                    String login = loginField.getText().trim();
                    Funcionario funcionario = UserManager.getFuncionarios().stream()
                            .filter(func -> func.getNome().equalsIgnoreCase(nome) && func.getLogin().equalsIgnoreCase(login))
                            .findFirst()
                            .orElse(null);
                    if (nome.isEmpty() || login.isEmpty() || funcionario == null) {
                        JOptionPane.showMessageDialog(frame, "Funcionário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }else{
                        UserManager.removeFuncionario(funcionario.getId());
                    }
                    JOptionPane.showMessageDialog(frame,nome+" foi removido do sistema com sucesso!", "Funcionário Removido", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnCompra.addActionListener(e -> {
            mostrarMenuCompra();
        });

        btnverificaSaldo.addActionListener(e -> {
            String saldo = String.format("Saldo atual: R$ %.2f", Caixa.getSaldo());
            JOptionPane.showMessageDialog(frame, saldo, "Saldo do Caixa", JOptionPane.INFORMATION_MESSAGE);
        });

        btnVenda.addActionListener(e -> {
            mostrarMenuVenda();
        });

        btnSair.addActionListener(e -> {
            MenuInicial.mostrarMenuPrincipal();
        });

        // Menu CadastrarProduto:

        JPanel cadastrarP = iniciarMenuCadastroProduto();
        container.add(cadastrarP, "cadastrarP");

        // Menu de Realizar Compras:

        JPanel compra = iniciarMenuCompra();
        container.add(compra, "Comprag");

        // Menu de Realizar Vendas:

        JPanel venda = iniciarMenuVenda();
        container.add(venda, "Vendag");

        return panel;

    }

    private static void mostrarMenuCadP() {
        frame.setSize(1200, 400);
        cardLayout.show(container, "cadastrarP");
    }

    private static void mostrarMenuCadastraFuncioario(){
        frame.setSize(1200, 400);
        cardLayout.show(container, "Cadastra Funconario");
    }

    private static void mostrarMenuCompra() {
        frame.setSize(1200, 400);
        cardLayout.show(container, "Comprag");
    }

    private static void mostrarMenuVenda() {
        frame.setSize(1200, 400);
        GerenciadorGeral.setCliente(null);
        cardLayout.show(container, "Vendag");
    }


    private static JPanel iniciarMenuGerencia() {
        JPanel gerencia = new JPanel();
        gerencia.setLayout(new BorderLayout()); // Layout do painel como BorderLayout

        JTextField nome = new JTextField(30);
        JTextField cpf = new JTextField(15);
        JTextField dataNascimento = new JTextField(10);
        JTextField email = new JTextField(30);
        JTextField login = new JTextField(15);
        JTextField senha = new JPasswordField(15);

        gerencia.setLayout(new BoxLayout(gerencia, BoxLayout.Y_AXIS));
        gerencia.add(new JLabel("Nome:"));
        gerencia.add(nome);
        gerencia.add(new JLabel("CPF:"));
        gerencia.add(cpf);
        gerencia.add(new JLabel("Data de Nascimento:"));
        gerencia.add(dataNascimento);
        gerencia.add(new JLabel("Email:"));
        gerencia.add(email);
        gerencia.add(new JLabel("Login:"));
        gerencia.add(login);
        gerencia.add(new JLabel("Senha:"));
        gerencia.add(senha);
        int result = JOptionPane.showConfirmDialog(frame, gerencia, "Cadastro de Funcionario", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String nomeFuncionario = nome.getText().trim();
            String cpfFuncionario = cpf.getText().trim();
            String dataNascimentoFuncionario = dataNascimento.getText().trim();
            String emailFuncionario = email.getText().trim();
            String loginFuncionario = login.getText().trim();
            String senhaFuncionario = senha.getText().trim();

            Boolean[] podeCriar = UserManager.loginExists(loginFuncionario, senhaFuncionario);
            while (podeCriar[0]) {
                loginFuncionario = JOptionPane.showInputDialog(frame, "Login já existe. Por favor, escolha outro login:", loginFuncionario);
                podeCriar = UserManager.loginExists(loginFuncionario.trim(), senhaFuncionario);
            }

            Funcionario funcionario = new Funcionario(nomeFuncionario, cpfFuncionario, dataNascimentoFuncionario, emailFuncionario, loginFuncionario, senhaFuncionario);
            UserManager.writeFuncionarioToFile(funcionario);
            JOptionPane.showMessageDialog(frame, "Cadastro de novo funcionário realizado com sucesso!");
        }
        return gerencia;
    }

    private static JPanel iniciarMenuCadastroProduto() {
        // Painel principal com BorderLayout
        JPanel cadastroP = new JPanel(new BorderLayout(10, 10));
        cadastroP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // === COMPONENTES ===

        // Campos comuns
        JTextField txtNome = new JTextField();
        JTextField txtId = new JTextField();
        JTextField txtPreco = new JTextField();
        JTextField txtQuantidade = new JTextField();

        // Campos adicionais
        JTextField txtSecao = new JTextField();
        JTextField txtGeneros = new JTextField();

        // ComboBox de tipo de produto
        String[] tipos = {"Livro", "Filme", "Genérico"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);

        // === TOPO ===
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(new JLabel("Tipo de Produto:"), BorderLayout.NORTH);
        painelTopo.add(comboTipo, BorderLayout.CENTER);
        cadastroP.add(painelTopo, BorderLayout.NORTH);

        // === CAMPOS COMUNS ===
        JPanel painelNormal = new JPanel(new GridLayout(5, 2, 10, 10));
        painelNormal.add(new JLabel("Nome:"));
        painelNormal.add(txtNome);

        painelNormal.add(new JLabel("ID:"));
        painelNormal.add(txtId);

        painelNormal.add(new JLabel("Preço:"));
        painelNormal.add(txtPreco);

        painelNormal.add(new JLabel("Quantidade:"));
        painelNormal.add(txtQuantidade);

        painelNormal.add(new JLabel("Seção:"));
        painelNormal.add(txtSecao);

        // === CAMPOS EXTRAS ===
        JPanel painelExtras = new JPanel(new GridLayout(1, 2, 10, 10));
        painelExtras.add(new JLabel("Gêneros:(Separados por ,)"));
        painelExtras.add(txtGeneros);

        // === CENTRO ===
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.add(painelNormal);
        painelCentro.add(Box.createVerticalStrut(10));
        painelCentro.add(painelExtras);
        cadastroP.add(painelCentro, BorderLayout.CENTER);

        // === BOTÕES ===
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnVoltar = new JButton("Voltar");

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        painelBotoes.add(btnRegistrar);
        painelBotoes.add(btnVoltar);

        cadastroP.add(painelBotoes, BorderLayout.SOUTH);

        // === LÓGICA ===

        // Mostrar ou ocultar campos extras
        comboTipo.addActionListener(e -> {
            String tipoSelecionado = (String) comboTipo.getSelectedItem();
            painelExtras.setVisible(!tipoSelecionado.equals("Genérico"));
        });

        // Iniciar visível
        painelExtras.setVisible(true);

// Ação do botão Registrar
        btnRegistrar.addActionListener(e -> {
            String tipo = (String) comboTipo.getSelectedItem();
            String nome = txtNome.getText().trim();
            String id = txtId.getText().trim();
            String precoStr = txtPreco.getText().trim();
            String qtdStr = txtQuantidade.getText().trim();
            String secaoStr = txtSecao.getText().trim();
            String generos = txtGeneros.getText().trim();

            Produto produto;

            try {
                double preco = Double.parseDouble(precoStr);
                int quantidade = Integer.parseInt(qtdStr);
                int secao = Integer.parseInt(secaoStr);

                // Verificação de valores negativos
                if (preco < 0 || quantidade < 0 || secao < 0) {
                    JOptionPane.showMessageDialog(cadastroP,
                            "Preço, quantidade e seção não podem ser negativos!",
                            "Erro de validação", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (tipo.equals("Genérico")) {
                    produto = new Generico(nome, preco, id, quantidade, secao);
                    JOptionPane.showMessageDialog(cadastroP, "Produto Genérico criado com sucesso!");
                } else {
                    // Transformar gêneros em lista
                    ArrayList<Genero> listaGeneros = new ArrayList<>();
                    for (String generoStr : generos.split(",")) {
                        if (!generoStr.trim().isEmpty()) {
                            listaGeneros.add(Genero.fromString(generoStr.trim()));
                        }
                    }

                    float precoFloat = (float) preco;

                    if (tipo.equals("Livro")) {
                        produto = new Livro(nome, precoFloat, id, quantidade, listaGeneros, secao);
                        JOptionPane.showMessageDialog(cadastroP, "Livro criado com sucesso!");
                    } else {
                        produto = new Filme(nome, precoFloat, id, quantidade, listaGeneros, secao);
                        JOptionPane.showMessageDialog(cadastroP, "Filme criado com sucesso!");
                    }
                }

                GerenciadorEstoque.carregaProduto(produto);
                GerenciadorEstoque.appendProduto();
                MenuInicial.mostrarMenuGerente();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(cadastroP,
                        "Erro de conversão numérica: verifique os campos!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(cadastroP,
                        "Erro ao criar produto: " + ex.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão Voltar
        btnVoltar.addActionListener(e -> {
            MenuInicial.mostrarMenuGerente();
        });

        return cadastroP;

    }

    private static JPanel iniciarMenuCompra() {
        JPanel compra = new JPanel();
        compra.setLayout(new BorderLayout()); // Layout do painel como BorderLayout

        // Criar o DefaultListModel que irá armazenar os itens da lista
        DefaultListModel<CompraVenda> listaModel = new DefaultListModel<>();

        // Criar o JList usando o DefaultListModel
        JList<CompraVenda> listaDeCompras = new JList<>(listaModel);
        listaDeCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Seleção de um item por vez

        // Adicionar a JList dentro de um JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(listaDeCompras);

        // Adicionar o JScrollPane ao JFrame, em cima
        compra.add(scrollPane, BorderLayout.CENTER);

        // Painel para os botões (Adicionar, Remover e Alterar Quantidade)
        JPanel painelBotoes = new JPanel();
        compra.add(painelBotoes, BorderLayout.SOUTH); // O painel de botões fica na parte inferior

        // Campo de texto para o nome do item
        JTextField campoTexto = new JTextField(15);
        painelBotoes.add(campoTexto);

        // Campo de texto para a quantidade do item
        JTextField campoQuantidade = new JTextField(5);
        painelBotoes.add(campoQuantidade);

        // Botões relacionados a compra
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

        // Botões de Cancelar e Concluir a compra
        JButton btnConcluir = new JButton("Concluir Compra");
        JButton btnCancelar = new JButton("Cancelar Compra");

        painelBotoes.add(btnConcluir);
        painelBotoes.add(btnCancelar);

        btnConcluir.addActionListener(e -> {
            Caixa.registrarCompra();
            listaModel.clear();
            MenuInicial.mostrarMenuGerente();
        });

        btnCancelar.addActionListener(e -> {
            listaModel.clear();
            Caixa.getComprasvendas().clear();
            MenuInicial.mostrarMenuGerente();
        });

        return compra;
    }

    private static JPanel iniciarMenuVenda() {
        JPanel venda = new JPanel();
        venda.setLayout(new BorderLayout()); // Layout do painel como BorderLayout

        // Criar o DefaultListModel que irá armazenar os itens da lista
        DefaultListModel<CompraVenda> listaModel = new DefaultListModel<>();

        // Criar o JList usando o DefaultListModel
        JList<CompraVenda> listaDeCompras = new JList<>(listaModel);
        listaDeCompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Seleção de um item por vez

        // Adicionar a JList dentro de um JScrollPane para rolagem
        JScrollPane scrollPane = new JScrollPane(listaDeCompras);

        // Adicionar o JScrollPane ao JFrame, em cima
        venda.add(scrollPane, BorderLayout.CENTER);

        // Painel para os botões (Adicionar, Remover e Alterar Quantidade)
        JPanel painelBotoes = new JPanel();

        // Campo de texto para o nome do item
        JTextField campoTexto = new JTextField(15);
        painelBotoes.add(campoTexto);

        // Campo de texto para a quantidade do item
        JTextField campoQuantidade = new JTextField(5);
        painelBotoes.add(campoQuantidade);

        // Botões relacionados a venda
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

        // Campo de texto para o nome do cliente
        JTextField campoCliente = new JTextField(15);
        campoCliente.setToolTipText("Digite o nome do cliente");
        painelCliente.add(campoCliente);

        // Botão para selecionar cliente
        JButton selecionarClienteButton = new JButton("Selecionar Cliente");
        painelCliente.add(selecionarClienteButton);

        // Botão para adicionar um novo cliente
        JButton adicionarClienteButton = new JButton("Adicionar Cliente");
        painelCliente.add(adicionarClienteButton);

        // Display do cliente selecionado
        JLabel clienteSelecionadoLabel = new JLabel("Cliente selecionado: Nenhum");
        painelCliente.add(clienteSelecionadoLabel);

        JComboBox<TipoPagamento> metodoPagamentoComboBox = new JComboBox<>(TipoPagamento.values());
        painelCliente.add(new JLabel("  |  Pagamento:"));
        painelCliente.add(metodoPagamentoComboBox);

        JComboBox<TipoCartao> tipoCartaoComboBox = new JComboBox<>(TipoCartao.values());
        painelCliente.add(new JLabel("Tipo de Cartão:"));
        painelCliente.add(tipoCartaoComboBox);

        // Esconde inicialmente o combo de tipo de cartão
        tipoCartaoComboBox.setVisible(false);

        JPanel painelInferior = new JPanel(new BorderLayout());
        venda.add(painelInferior, BorderLayout.SOUTH);

        // Adicione inicialmente o painel de botões
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
            MenuInicial.mostrarMenuGerente();
        });

// Ação do botão "Adicionar Funcionario"
        adicionarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuInicial.cadastroCliente(frame);  // Redireciona para o cadastro de cliente
            }
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
            MenuInicial.mostrarMenuGerente();
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
