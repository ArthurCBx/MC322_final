package Produtos.Filme;

import Produtos.Livro.Genero;

import java.util.ArrayList;

public class Filme {

    private String nome;
    private float preco;
    private ArrayList<Genero> genero;

    public Filme(String nome, float preco, ArrayList<Genero> genero) {
        this.nome = nome;
        this.preco = preco;
        this.genero = genero;
    }
}
