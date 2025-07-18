package pessoa;

import Gerencia.Caixa.Caixa;
import Gerencia.Estoque.GerenciadorEstoque;
import Produtos.Produto;
import pagamento.TipoCartao;
import pagamento.TipoPagamento;

/*
    Classe Funcionario que herda de Pessoa.
    Representa um funcionário do sistema, com a capacidade de cadastrar clientes e efetuar pagamentos.
    Pode remover produtos do estoque, caso alguém deseje alugá-lo.
    Ele possui um ID único.
 */
public class Funcionario extends Pessoa{
    private static int id_geral = 0;
    private final int id;

    public Funcionario(String nome, String CPF, String data_nascimento, String email, String login, String senha) {
        super(nome, CPF, data_nascimento, email, login, senha);
        this.id = ++id_geral;
    }

    public int getId() {
        return id;
    }

    public void cadastraCliente(Cliente cliente){
        UserManager.writeClientToFile(cliente);
    }

    public void efetuaPagamento(TipoPagamento tipo, TipoCartao cartao){
        Caixa.registrarVenda(tipo, cartao);
    }

    /**
     * Remove uma quantidade de um produto já existente no estoque. Pode ser utilizado para aluguel de produtos ou devolução.
     * @param produto Produto a ser removido ou alterado no estoque.
     * @param quantidade Quantidade do produto a ser removida ou alterada.
     */
    public void removeProduto(Produto produto, int quantidade){
        GerenciadorEstoque.carregaProduto(produto);
        GerenciadorEstoque.alteraProduto(-quantidade);
    }
}