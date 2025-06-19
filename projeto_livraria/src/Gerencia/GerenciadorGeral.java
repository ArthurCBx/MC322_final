package Gerencia;

import Produtos.IntProduto;
import pessoa.Cliente;
import pessoa.Funcionario;

import java.util.ArrayList;

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



    public static void adicionarProduto(String id){
        getProdutos().add(GerenciadorEstoque.getEntradas().get(GerenciadorEstoque.buscaProduto(id)).getProduto());
    }

    public static void removerProduto(int pos){
        getProdutos().remove(pos);
    }

    public static void realizarCompra(){}

    public static void realizarVenda(){}

    public static void realizarBusca(){}

    public static void realizarAlteracao(){}



}
