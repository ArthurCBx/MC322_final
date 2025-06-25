package Produtos.Generico;

import Produtos.Livro.Genero;
import Produtos.Produto;

import java.util.ArrayList;

public class Generico implements Produto{
    private String nome;
    private double preco;
    private String id;
    private int quantidadeDisponivel;
    private boolean isAlugavel;
    private boolean isCompravel;
    private int secao;


    public Generico(String nome, double preco, String id, int secao) {
        this.nome = nome;
        this.preco = preco;
        this.id = id;
        this.quantidadeDisponivel = 1;
        this.isAlugavel = false;
        this.isCompravel = true;
        this.secao = secao;
    }

    public Generico(String props) {
        String[] lines = props.split("\n");

        for (int i = 0; i < lines.length; i++)
            lines[i] = lines[i].substring(lines[i].indexOf(' ') + 1);

        this.nome = lines[0];
        this.id = lines[1];
        this.quantidadeDisponivel = Integer.parseInt(lines[2]);
        this.preco = Float.parseFloat(lines[3]);
        this.secao = Integer.parseInt(lines[4]);

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

    public int decrementarQuantidadeProduto(){
        if(this.quantidadeDisponivel - 1 >= 0){
            this.quantidadeDisponivel--;
            return this.quantidadeDisponivel;
        }
        else
            return -1;
    }

    public int aumentarQuantidadeProduto(){
        this.quantidadeDisponivel++;
        return this.quantidadeDisponivel;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Nome: ").append(getNome()).append("\n")
                .append("ID: ").append(getId()).append("\n")
                .append("Quantidade: ").append(getQuantidadeDisponivel()).append("\n")
                .append("Preco: ").append(getPreco()).append("\n")
                .append("Secao: ").append(getSecao()).append("\n");

        return s.toString();
    }
}
