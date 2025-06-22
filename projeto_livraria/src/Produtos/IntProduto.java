package Produtos;

public interface IntProduto {


    // so deixei criado pra poder fazer referencia a interface produto

    String getID();
    String getNome();
    int quantidadeDisponivel();
    int getQuantidadeDisponivel();
    void setQuantidadeDisponivel(int quantidadeDisponivel);
    double getPreco();
    boolean isAlugavel();
    boolean isCompravel();
    int getSecao();

}
