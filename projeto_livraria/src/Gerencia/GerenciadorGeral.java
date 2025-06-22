package Gerencia;

import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;
import pessoa.Cliente;
import pessoa.Funcionario;

import java.util.NoSuchElementException;

public class GerenciadorGeral {
    /**
     * Funcionario  logado e realizando opreções
     */
    private static Funcionario funcionario;
    /**
     * Cliente participando de compra ou realizando busca
     */
    private static Cliente cliente;

    // Getters e Setters:

    public static Funcionario getFuncionario() {
        return funcionario;
    }

    public static void setFuncionario(Funcionario funcionario) {
        GerenciadorGeral.funcionario = funcionario;
    }

    public static Cliente getCliente() {
        return cliente;
    }

    public static void setCliente(Cliente cliente) {
        GerenciadorGeral.cliente = cliente;
    }

    /**
     * Realiza uma busca por nome de produto e retorna a interface do produto
     * @param nome nome do produto
     * @return Retorna a interface do produto
     */
    public static Produtos.IntProduto realizarBusca(String nome){
        IntProduto produto;
        try {
            produto = GerenciadorEstoque.getProdutos().stream().filter(produtob -> produtob.getNome().equals(nome)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + nome + "' nao foi encontrado");
        }
        return produto;
    }

}
