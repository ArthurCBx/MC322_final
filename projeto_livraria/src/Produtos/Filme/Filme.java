package Produtos.Filme;

import Produtos.Produto;
import Produtos.Livro.Genero;

import java.util.ArrayList;

public class Filme implements Produto {

    private String nome;
    private double preco;
    private String id;
    private ArrayList<Genero> generos;
    private int quantidadeDisponivel;
    private boolean isAlugavel;
    private boolean isCompravel;
    private int secao;

    public Filme(String nome, float preco, String id, int quantidadeDisponivel, ArrayList<Genero> generos, int secao) {
        this.nome = nome;
        this.preco = preco;
        this.generos = generos;
        this.id = id;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.isAlugavel = true;
        this.isCompravel = true;
        this.secao = secao;

    }

    public Filme(String props) {
        String[] lines = props.split("\n");

        for (int i = 0; i < lines.length; i++)
            lines[i] = lines[i].substring(' ').trim();

        this.nome = lines[0];
        this.id = lines[1];
        this.quantidadeDisponivel = Integer.parseInt(lines[2]);
        this.preco = Integer.parseInt(lines[4]);
        this.secao = Integer.parseInt(lines[5]);
        String[] gens = lines[6].split(", ");
        for (String gen : gens) {
            if (gen.length() < 3) break;
            this.generos.add(Genero.fromString(gen));
        }

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

    public ArrayList<Genero> getGeneros() {
        return this.generos;
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

    public int decrementarQuantidadeFilme() {
        if (this.quantidadeDisponivel - 1 >= 0) {
            this.quantidadeDisponivel--;
            return this.quantidadeDisponivel;
        } else
            return -1;
    }

    public int aumentarQuantidadeFilme() {
        this.quantidadeDisponivel++;
        return this.quantidadeDisponivel;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("Nome: ").append(getNome()).append("\n")
                .append("ID: ").append(getId()).append("\n")
                .append("Quantidade: ").append(getQuantidadeDisponivel()).append("\n")
                .append("Propriedades:").append("\n")
                .append("Preco: ").append(getPreco()).append("\n")
                .append("Secao: ").append(getSecao()).append("\n")
                .append("Generos: ");

        for (Genero genero : getGeneros())
            s.append(genero.getGenero()).append(", ");
        s.append("\n");

        return s.toString();
    }

}
