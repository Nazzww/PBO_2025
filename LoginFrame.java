package ui;

import model.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame {

    public LoginFrame(String role, ArrayList<User> users, Toko toko) {

        setTitle("Login " + role);
        setSize(300, 200);
        setLayout(null);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(30, 30, 80, 25);
        add(lblUser);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(120, 30, 120, 25);
        add(txtUser);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(30, 70, 80, 25);
        add(lblPass);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(120, 70, 120, 25);
        add(txtPass);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 120, 80, 30);
        add(btnLogin);

        // ===== ACTION LOGIN =====
        ActionListener loginAction = e -> {
            for (User user : users) {
                if (user.login(txtUser.getText(), new String(txtPass.getPassword()))
                        && user.getRole().equals(role)) {

                    dispose();

                    if (user instanceof Admin) {
                        new AdminFrame(toko, users);
                    } else {
                        new KasirFrame((Kasir) user, toko, users);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Login gagal");
        };

        // tombol login
        btnLogin.addActionListener(loginAction);

        // enter di username
        txtUser.addActionListener(e -> txtPass.requestFocus());

        // enter di password
        txtPass.addActionListener(loginAction);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
