package pagamento;

/*
    Classe Cartao que servirá como um dos atributos de cliente.
    Possui como atributos as informações de um cartão e o tipo (crédito ou débito).
    O seu metodo pedeSenha apenas verifica se o valor numa compra exige senha ou não.
 */

public class Cartao {
    private final int numero;
    private final int codigo_de_seguranca;
    private final String validade;
    private final TipoCartao tipoCartao;

    public Cartao(int numero, int codigo_de_seguranca, String validade, TipoCartao tipoCartao) {
        this.numero = numero;
        this.codigo_de_seguranca = codigo_de_seguranca;
        this.validade = validade;
        this.tipoCartao = tipoCartao;
    }

    public int getNumero() {
        return numero;
    }

    public int getCodigo_de_seguranca() {
        return codigo_de_seguranca;
    }

    public String getValidade() {
        return validade;
    }

    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }

    public boolean pedeSenha(int valor){
        return tipoCartao.getValor_limite_sem_senha() > valor;
    }
}
