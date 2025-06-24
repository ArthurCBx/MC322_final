package Produtos.Filme;

import Produtos.Produto;
import Produtos.Propriedade;
import Produtos.Livro.Genero;

import java.util.ArrayList;

public class Filme implements Produto{

    private String nome;
    private double preco;
    private ArrayList<Genero> genero;

    private String id;
    private int quantidadeDisponivel;
    private boolean isAlugavel;
    private boolean isCompravel;
    private int secao;
    private ArrayList<Propriedade> propriedades;

    public Filme(String nome, float preco, ArrayList<Genero> genero, String id, int secao, ArrayList<Propriedade> propriedades) {
        this.nome = nome;
        this.preco = preco;
        this.genero = genero;
        this.id = id;
        this.quantidadeDisponivel = 1;
        this.isAlugavel = true;
        this.isCompravel = true;
        this.secao = secao;
        this.propriedades = propriedades;
    }

    public String getNome() {
        return nome;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public double getPreco() {
        return preco;
    }

    public ArrayList<Genero> getGenero() {
        return genero;
    }

    public String getId() {
        return id;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public boolean isAlugavel() {
        return isAlugavel;
    }

    public boolean isCompravel() {
        return isCompravel;
    }

    public int getSecao() {
        return secao;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return propriedades;
    }


}
