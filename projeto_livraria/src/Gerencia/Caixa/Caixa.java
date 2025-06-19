package Gerencia.Caixa;

public class Caixa {

    private static float saldo;

    public static float getSaldo() {
        return saldo;
    }

    public static void setSaldo(float saldo) {
        Caixa.saldo = saldo;
    }


    public void registrarCompra(String msg, int valor){}

    public void registrarVenda(String msg, int valor){}






}
