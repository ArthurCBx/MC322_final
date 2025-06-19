package Gerencia.Caixa;

import Produtos.IntProduto;

import java.util.ArrayList;

public class Caixa {

    private static float saldo;

    private static ArrayList<CompraVenda> comprasvendas = new ArrayList<>();

    public static float getSaldo() {
        return saldo;
    }

    public static void setSaldo(float saldo) {
        Caixa.saldo = saldo;
    }

    public static ArrayList<CompraVenda> getComprasvendas() {
        return comprasvendas;
    }

    public static void setComprasvendas(ArrayList<CompraVenda> comprasvendas) {
        Caixa.comprasvendas = comprasvendas;
    }




    public void adiconarCompraVenda(IntProduto produto, int quantidade){
        getComprasvendas().add(new CompraVenda(produto,quantidade));
    }

    public void removerCompraVenda(String id){
        try {
            getComprasvendas().stream().filter(compraVenda -> compraVenda.getProduto().getID().equals(id)).map(
        } catch ()
    }

    public void registrarCompra(String msg, int valor){

    }

    public void registrarVenda(String msg, int valor){}






}
