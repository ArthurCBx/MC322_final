package Produtos.Generico;

import Produtos.Produto;

/*
    Classe Generico que herda de Produto. São outros produtos vendidos pela loja.
    Representa um produto que pode ser vendido e não alugado, possuindo nome, preço,
    uma quantidade no estoque, possui um ID único.
*/
public class Generico implements Produto{
    private String nome;
    private float preco;
    private String id;
    private int quantidadeDisponivel;
    private boolean isAlugavel;
    private boolean isCompravel;
    private int secao;


    public Generico(String nome, double preco, String id,int quantidadeDisponivel, int secao) {
        this.nome = nome;
        this.preco = Math.round(preco * 100f) / 100f;
        this.id = id;
        this.quantidadeDisponivel = quantidadeDisponivel;
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
        this.preco = Math.round(Float.parseFloat(lines[3]) * 100f) / 100f;
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

    public float getPreco(){
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
