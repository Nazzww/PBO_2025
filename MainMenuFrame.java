package ui;

import model.*;
import javax.swing.*;
import java.util.ArrayList;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame(ArrayList<User> users, Toko toko) {
        setTitle("Menu Awal");
        setSize(300,200);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton a = new JButton("Login Admin");
        JButton k = new JButton("Login Kasir");

        a.setBounds(70,40,150,30);
        k.setBounds(70,90,150,30);

        add(a); add(k);

        a.addActionListener(e -> {
            dispose();
            new LoginFrame("ADMIN", users, toko);
        });

        k.addActionListener(e -> {
            dispose();
            new LoginFrame("KASIR", users, toko);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
