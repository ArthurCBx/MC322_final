package Gerencia;

import Gerencia.Estoque.Entrada;
import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;
import pessoa.Cliente;
import pessoa.Funcionario;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GerenciadorGeral {

    private static Funcionario funcionario;
    private static Cliente cliente;

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


    public static IntProduto realizarBusca(String nome){
        IntProduto produto;
        try {
            produto = GerenciadorEstoque.getEntradas().stream().map(Entrada::getProduto).filter(produtob -> produtob.getNome().equals(nome)).findFirst().get();
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + nome + "' nao foi encontrado");
        }
        return produto;
    }

}
