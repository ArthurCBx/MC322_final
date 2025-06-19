package pessoa;

import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.IntProduto;

public class Gerente extends Funcionario{

    public Gerente(String nome, String CPF, String data_nascimento, String email, String id, String login, String senha) {
        super(nome, CPF, data_nascimento, email, id, login, senha);
    }

    /**
     * Um novo produto é adicionado ao estoque
     * @param produto Produto a ser adicionado ou alterado no estoque.
     * @param quantidade Quantidade do produto a ser adicionada ou alterada.
     */
    public void addNewProduto(IntProduto produto, int quantidade){
        GerenciadorEstoque.carregaProduto(produto);
        GerenciadorEstoque.appendProduto(quantidade);
    }

    /**
     * Adiciona uma quantidade de um produto já existente no estoque.
     * @param produto Produto a ser adicionado ou alterado no estoque.
     * @param quantidade Quantidade do produto a ser adicionada ou alterada.
     */
    public void addProduto(IntProduto produto, int quantidade){
        GerenciadorEstoque.carregaProduto(produto);
        GerenciadorEstoque.alteraProduto(quantidade);
    }

    /**
     * Remove uma quantidade de um produto já existente no estoque.
     * @param produto Produto a ser removido ou alterado no estoque.
     * @param quantidade Quantidade do produto a ser removida ou alterada.
     */
    public void removeProduto(IntProduto produto, int quantidade){
        GerenciadorEstoque.carregaProduto(produto);
        GerenciadorEstoque.alteraProduto(-quantidade);
    }

}
