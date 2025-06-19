package Produtos;

import java.util.ArrayList;

public class Produto {
    private String nome;
    private int preco;
    private ArrayList<MetodoPagamento> metodos;

    public Produto(String nome, int preco, ArrayList<MetodoPagamento> metodos){
        this.nome = nome;
        this.preco = preco;
        this.metodos = metodos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public ArrayList<MetodoPagamento> getMetodos() {
        return metodos;
    }

    public void setMetodos(ArrayList<MetodoPagamento> metodos) {
        this.metodos = metodos;
    }

    public boolean podeComprar(){
        return this instanceof Compravel;
    }

    public boolean podeAlugar(){
        return this instanceof Alugavel;
    }
}
