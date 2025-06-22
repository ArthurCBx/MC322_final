package Gerencia.Estoque;

import Produtos.IntProduto;
import excecoes.ProdutoNaoEncontrado;

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class GerenciadorEstoque {

    private static final String LogEstoque = "a";
    private static final File logEstoque = new File(LogEstoque);

    /**
     * Produto carregado e utilizado para a operação na lista de Entradas
     */
    private static IntProduto produto;
    /**
     * Lista de entradas de produtos, diz se um produto teve suas caracteristias alteradas e em qual linha do .txt está armazenado
     */
    private static ArrayList<IntProduto> produtos = new ArrayList<>();

    // Getters e Setters:

    public static IntProduto getProduto() {
        return produto;
    }

    public static void setProduto(IntProduto produto) {
        GerenciadorEstoque.produto = produto;
    }

    public static ArrayList<IntProduto> getProdutos() {
        return produtos;
    }

    /**
     * Carrega os dados armazenados em Estoque.txt na lista de entradas
     */
    public static void carregarLista() {


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
    public static void carregaProduto(Produtos.IntProduto produto) {
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
            for IntProduto
            s.append("Produto: ").append().append(funcionario.getId()).append("\n")
                    .append("Login: ").append(funcionario.getLogin()).append("\n")
                    .append("Senha: ").append(funcionario.getSenha()).append("\n\n");
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo: " + LogFile);
        }

        /*
        ArrayList<Entrada> novasEntradas = getEntradas().stream().filter(Entrada::isModificado).collect(Collectors.toCollection(ArrayList::new));

        StringBuilder s = new StringBuilder();
        String linha;

        try (BufferedReader reader = new BufferedReader(new FileReader(LogEstoque))) {
            while ((linha = reader.readLine()) != null && !novasEntradas.isEmpty()) {
                for (Entrada entradas : novasEntradas) {
                    if (linha.contains("Produto: " + entradas.getProduto().getNome())) {
                        novasEntradas.remove(entradas);
                        break;
                    }
                }
                    while(!reader.readLine().contains(";.;")){
                        reader.readLine(); // Pula linhas ate encontrar ";.;", que é o indicador de final de um produto
                    }
                reader.readLine();  // Pula a linha de ";.;"
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!novasEntradas.isEmpty())
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logEstoque, true))) {
                while ((linha = reader.readLine()) != null && !novasEntradas.isEmpty()) {
                    for (Entrada entradas : novasEntradas) {
                        if (linha.contains("Produto: " + entradas.getProduto().getNome())) {
                            novasEntradas.remove(entradas);
                            break;
                        }
                    }
                    while(!reader.readLine().contains(";.;")){
                        reader.readLine(); // Pula linhas ate encontrar ";.;", que é o indicador de final de um produto
                    }
                    reader.readLine();  // Pula a linha de ";.;"

                for (Entrada entrada : getEntradas().stream().filter(Entrada::isModificado).toList()) {
                    while (linha = reader.read)

                        s.append("Cliente: ").append(cliente.getNome()).append("\n")
                                .append("Login: ").append(cliente.getLogin()).append("\n")
                                .append("Senha: ").append(cliente.getSenha()).append("\n\n");

                    writer.write(s.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao escrever no arquivo: " + logEstoque);
            }


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, false))) {
            writer.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


         */

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
