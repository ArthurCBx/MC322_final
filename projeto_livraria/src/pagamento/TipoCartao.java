package pagamento;

public enum TipoCartao {
    CREDITO(200),
    DEBITO(300);

    private final int valor_limite_sem_senha;

    TipoCartao(int valor_limite_sem_senha) {
        this.valor_limite_sem_senha = valor_limite_sem_senha;
    }

    public int getValor_limite_sem_senha() {
        return valor_limite_sem_senha;
    }
}
