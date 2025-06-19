package pessoa;

import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.IntProduto;

public class Gerente extends Funcionario{

    public Gerente(String nome, String CPF, int idade, String data_nascimento, String email, String id, String login, String senha) {
        super(nome, CPF, idade, data_nascimento, email, id, login, senha);
    }

    public void addNewProduto(IntProduto produto, int quantidade){
        GerenciadorEstoque.carregaProduto(produto);
        GerenciadorEstoque.appendProduto(quantidade);
    }

    public void addProduto(IntProduto produto, int quantidade){
        GerenciadorEstoque.addProduto(produto, quantidade);
    }

    public void removeProduto(IntProduto produto, int quantidade){

    }

}
