package Gerencia;

import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.Filme.Filme;
import Produtos.Generico.Generico;
import Produtos.Livro.Livro;
import Produtos.Produto;
import excecoes.ProdutoNaoEncontrado;
import pessoa.Cliente;
import pessoa.Funcionario;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
     * @return Um ArrayList de livros filtrados da lista de produtos no estoque
     */
    public static ArrayList<Livro> getLivros(){
        return GerenciadorEstoque.getProdutos().stream().filter(produto -> produto instanceof Livro).map(produto -> (Livro)produto).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @return Um ArrayList de filmes filtrados da lista de produtos no estoque
     */
    public static ArrayList<Filme> getFilmes(){
        return GerenciadorEstoque.getProdutos().stream().filter(produto -> produto instanceof Filme).map(produto -> (Filme)produto).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @return Um ArrayList de produtos Genericos filtrados da lista de produtos no estoque
     */
    public static ArrayList<Generico> getGenericos(){
        return GerenciadorEstoque.getProdutos().stream().filter(produto -> produto instanceof Generico).map(produto -> (Generico)produto).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Realiza uma busca por nome de produto e retorna a interface do produto
     * @param nome nome do produto
     * @return Retorna a interface do produto
     */
    public static Produto realizarBusca(String nome){
        Produto produto;
        try {
            produto = GerenciadorEstoque.getProdutos().stream().filter(produtob -> produtob.getNome().equals(nome)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + nome + "' nao foi encontrado");
        }
        return produto;
    }

}
