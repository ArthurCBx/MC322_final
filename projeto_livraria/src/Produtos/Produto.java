package Produtos;

import java.util.ArrayList;

public interface Produto {


    // so deixei criado pra poder fazer referencia a interface produto

    String getNome();

    String getID();

    int getQuantidadeDisponivel();

    void setQuantidadeDisponivel(int quantidadeDisponivel);

    double getPreco();

    boolean isAlugavel();

    boolean isCompravel();

    int getSecao();

    ArrayList<Propriedade> getPropriedades();

}
