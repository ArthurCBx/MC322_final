package Produtos;

/*
 * Modelo a ser usado para todos os tipos de produto
 * Nessa livraria serão comercializados três diferentes tipos de produto
 * Sendo eles livros, filmes e genericos, como gift cards, materiais escoleres, miscelânea, etc.
 */
public interface Produto {
    String getNome();

    String getId();

    int getQuantidadeDisponivel();

    void setQuantidadeDisponivel(int quantidadeDisponivel);

    float getPreco();

    boolean isAlugavel();

    boolean isCompravel();

    int getSecao();

}
