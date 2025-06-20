package pessoa;

import Gerencia.Caixa.Caixa;
import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.IntProduto;

/*
    Classe Gerente que herda de Funcionario.
    Representa um gerente do sistema, com a capacidade de adicionar livros já existentes (caso estivessem alugados),
    remover e comprar novos livros.
 */

public class Gerente extends Funcionario{

    public Gerente(String nome, String CPF, String data_nascimento, String email, String id, String login, String senha) {
        super(nome, CPF, data_nascimento, email, id, login, senha);
    }

    /**
     * Adiciona uma quantidade de um produto já existente no estoque. Utilizada para atualizar o estoque em caso de aluguel de produtos.
     * @param produto Produto a ser adicionado ou alterado no estoque.
     * @param quantidade Quantidade do produto a ser adicionada ou alterada.
     */
    public void addProduto(IntProduto produto, int quantidade){
        GerenciadorEstoque.carregaProduto(produto);
        GerenciadorEstoque.alteraProduto(quantidade);
    }

    /**
     * Registra uma compra de um produto, adicionando-o ao caixa.
     * @param produto Produto a ser comprado.
     * @param quantidade Quantidade do produto a ser comprada.
     */
    public void ComprarProduto(IntProduto produto, int quantidade){
        Caixa.adiconarCompraVenda(produto, quantidade);
        Caixa.registrarCompra();
    }


}
