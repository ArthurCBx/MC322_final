package Produtos.Generico;

import Produtos.Produto;
import Produtos.Propriedade;

import java.util.ArrayList;

public class Generico implements Produto{
    private String nome;
    private double preco;
    private String id;
    private int quantidadeDisponivel;
    private boolean isAlugavel;
    private boolean isCompravel;
    private int secao;
    private ArrayList<Propriedade> propriedades;


    public Generico(String nome, float preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome(){
        return this.nome;
    }

    public String getId(){
        return this.id;
    }

    public int getQuantidadeDisponivel(){
        return this.quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel){
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public double getPreco(){
        return this.preco;
    }

    public boolean isAlugavel(){
        return this.isAlugavel;
    }

    public boolean isCompravel(){
        return this.isCompravel;
    }

    public int getSecao(){
        return this.secao;
    }

    public ArrayList<Propriedade> getPropriedades(){
        return propriedades;
    }
}
