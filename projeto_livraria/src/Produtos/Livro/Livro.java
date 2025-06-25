package Produtos.Livro;

import java.util.ArrayList;

import Produtos.Produto;

public class Livro implements Produto{

    private String nome;
    private float preco;
    private String id;
    private ArrayList<Genero> generos;
    private int quantidadeDisponivel;
    private boolean isAlugavel;
    private boolean isCompravel;
    private int secao;

    public Livro(String nome, float preco, String id, ArrayList<Genero> generos, int secao) {
        this.nome = nome;
        this.preco = preco;
        this.id = id;
        this.generos = generos;
        this.quantidadeDisponivel = 1;
        this.isAlugavel = true;
        this.isCompravel = true;
        this.secao = secao;
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

    public ArrayList<Genero> getGeneros() {
        return this.generos;
    }

    public int decrementarQuantidadeLivros(){
        if(this.quantidadeDisponivel - 1 >= 0){
            this.quantidadeDisponivel--;
            return this.quantidadeDisponivel;
        }
        else
            return -1;
    }

    public int aumentarQuantidadeLivros(){
        this.quantidadeDisponivel++;
        return this.quantidadeDisponivel;
    }
}
