package Produtos.Livro;

import java.util.ArrayList;

import Produtos.Produto;
import Produtos.Propriedade;

public class Livro implements Produto{

    private String nome;
    private float preco;
    private String id;
    private ArrayList<Genero> genero;
    private int quantidadeDisponivel;
    private boolean isAlugavel;
    private boolean isCompravel;
    private int secao;

    public Livro(String nome, float preco, String id, ArrayList<Genero> genero, int quantidadeDisponivel, boolean isAlugavel, int secao) {
        this.nome = nome;
        this.preco = preco;
        this.id = id;
        this.genero = genero;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.isAlugavel = isAlugavel;
        this.isCompravel = true;
        this.secao = secao;
    }

    public String getNome(){
        return this.nome;
    }

    public String getID(){
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
        return secao;
    }

    public ArrayList<Propriedade> getPropriedades(){

    }
}
