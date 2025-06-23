package Gerencia.Estoque;

import Produtos.Produto;
import Produtos.Propriedade;
import excecoes.ProdutoNaoEncontrado;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GerenciadorEstoque {

    private static final String LogEstoque = "projeto_livraria/src/arquivos/Estoque.txt";
    private static final File logEstoque = new File(LogEstoque);

    /**
     * Produto carregado e utilizado para a operação na lista de Entradas
     */
    private static Produto produto;
    /**
     * Lista de entradas de produtos, diz se um produto teve suas caracteristias alteradas e em qual linha do .txt está armazenado
     */
    private static ArrayList<Produto> produtos = new ArrayList<>();

    // Getters e Setters:

    public static Produto getProduto() {
        return produto;
    }

    public static void setProduto(Produto produto) {
        GerenciadorEstoque.produto = produto;
    }

    public static ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public static void setProdutos(ArrayList<Produto> produtos) {
        GerenciadorEstoque.produtos = produtos;
    }

    /**
     * Carrega os dados armazenados em Estoque.txt na lista de entradas
     */
    public static void carregarLista() {

        if (!getProdutos().isEmpty()) {
            setProdutos(new ArrayList<>());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(LogEstoque))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Produto:")) {
                    switch (line){
                        //case():

                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Adiciona o produto carregado como uma nova entrada na lista de entradas
     *
     * @param quantidade quantidade desse produto adicionado
     */
    public static void appendProduto(int quantidade) {
        getProdutos().add(getProduto());
    }

    /**
     * Deleta uma entrada da lista de entradas
     *
     * @param id ID da entrada
     */
    public static void deleteProduto(String id) {
        getProdutos().remove(buscaProduto(id));
    }

    /**
     * Busca um produto por ID na lista do estoque (entradas), retorna seu indice ou joga Exceção ProdutoNaoEncontrado
     *
     * @param id ID do produto
     * @return Retorna seu indice
     */
    public static int buscaProduto(String id) {
        int index;  // Irá retornar o indice
        try {       // Lista de Entradas -> index da entrada que contem o produto com o ID fornecido
            index = getProdutos().indexOf(getProdutos().stream().filter(Produto -> Produto.getID().equals(id)).findFirst().get());
        } catch (NoSuchElementException e) {
            throw new ProdutoNaoEncontrado("Produto: '" + id + "' nao foi encontrado");
        }
        return index;
    }

    /**
     * Carrega uma interface produto via ID na propriedade "produto" do Gerenciador
     *
     * @param id um ID de produto
     */
    public static void carregaProduto(String id) {
        setProduto(GerenciadorEstoque.getProdutos().get(buscaProduto(id)));
    }

    /**
     * Carrega uma interface produto na propriedade "produto" do Gerenciador
     *
     * @param produto uma interface produto
     */
    public static void carregaProduto(Produto produto) {
        setProduto(produto);
    }

    /**
     * Registra a lista de entradas no arquivo texto "Estoque.txt"
     */
    public static void salvaProduto() {

        if (!logEstoque.exists() || !logEstoque.isFile()) {
            try {
                logEstoque.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogEstoque, false))) {
            StringBuilder s = new StringBuilder();
            for (Produto produto : getProdutos()) {
                s.append("Produto: ").append(produto.getClass()).append("\n")
                        .append("Nome: ").append(produto.getNome()).append("\n")
                        .append("ID: ").append(produto.getID()).append("\n")
                        .append("Quantidade: ").append(produto.getQuantidadeDisponivel()).append("\n")
                        .append("Propriedades:").append("\n");
                for (Propriedade propriedade : produto.getPropriedades()) {
                    s.append(propriedade.getNomePropriedade()).append(": ").append(propriedade.getValorPropriedade()).append("\n");
                }
                s.append(";.;\n\n");
                writer.write(s.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + LogEstoque);
        }

    }

    /**
     * Altera a quantidade de uma entrada com o minimo de zero
     *
     * @param quantidade soma essa quantidade na quantidade atual da entrada
     */
    public static void alteraProduto(int quantidade) {
        int index = buscaProduto(getProduto().getID());
        getProdutos().get(index).setQuantidadeDisponivel(Math.min(getProdutos().get(index).getQuantidadeDisponivel() + quantidade, 0));
    }

}
