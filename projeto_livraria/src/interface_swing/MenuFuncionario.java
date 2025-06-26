package interface_swing;

import javax.swing.*;

public class MenuFuncionario {

    public static JPanel iniciarMenuFuncionario() {

        JButton btnCompra = new JButton("Realizar Compra");
        JButton btnVenda = new JButton("Realizar Venda");
        JButton btnSair = new JButton("Sair");

        btnCompra.addActionListener(e -> {

        });

        btnVenda.addActionListener(e -> {

        });

        btnSair.addActionListener(e -> {
            MenuInicial.mostrarMenuPrincipal();
        });

        JPanel panel = new JPanel();
        panel.add(btnCompra);
        panel.add(btnVenda);
        panel.add(btnSair);

        return panel;

    }

}
