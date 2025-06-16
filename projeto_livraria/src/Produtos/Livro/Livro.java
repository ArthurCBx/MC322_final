package Produtos.Livro;

import Produtos.Produto;

import java.util.ArrayList;

public class Livro extends Produto {

    private ArrayList<Genero> genero;

    public Livro(String nome, int preco, ArrayList<Genero> genero) {
        super(nome, preco);
        this.genero = genero;
    }
}
