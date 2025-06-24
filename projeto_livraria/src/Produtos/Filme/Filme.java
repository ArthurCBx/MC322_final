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
        return this.preco;
    }

    public ArrayList<Genero> getGenero() {
        return this.genero;
    }

    public String getId() {
        return this.id;
    }

    public int getQuantidadeDisponivel() {
        return this.quantidadeDisponivel;
    }

    public boolean isAlugavel() {
        return this.isAlugavel;
    }

    public boolean isCompravel() {
        return this.isCompravel;
    }

    public int getSecao() {
        return this.secao;
    }

    public ArrayList<Propriedade> getPropriedades() {
        return this.propriedades;
    }

    public int decrementarQuantidadeFilme(){
        if(this.quantidadeDisponivel - 1 >= 0){
            this.quantidadeDisponivel--;
            return this.quantidadeDisponivel;
        }
        else
            return -1;
    }

    public int aumentarQuantidadeFilme(){
        this.quantidadeDisponivel++;
        return this.quantidadeDisponivel;
    }
}
