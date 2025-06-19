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

    private static ArrayList<IntProduto> produtos;
    private static Funcionario funcionario;
    private static Cliente cliente;

    public static ArrayList<IntProduto> getProdutos() {
        return produtos;
    }

    public static void setProdutos(ArrayList<IntProduto> produtos) {
        GerenciadorGeral.produtos = produtos;
    }

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


    public static void realizarBusca(){}




}
