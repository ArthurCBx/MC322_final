package Produtos;

public interface Produto {


    // so deixei criado pra poder fazer referencia a interface produto

    String getNome();

    String getId();

    int getQuantidadeDisponivel();

    void setQuantidadeDisponivel(int quantidadeDisponivel);

    float getPreco();

    boolean isAlugavel();

    boolean isCompravel();

    int getSecao();

}
