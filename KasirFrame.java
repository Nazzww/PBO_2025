package ui;

import model.Toko;
import model.Kasir;
import model.Produk;
import model.User;

import javax.swing.*;
import java.util.ArrayList;

public class KasirFrame extends JFrame {

    private double total = 0;

    private void refreshProduk(Toko toko, JComboBox<Produk> comboProduk) {
        comboProduk.removeAllItems();
        for (Produk p : toko.getProduk()) {
            comboProduk.addItem(p);
        }
    }

    public KasirFrame(Kasir kasir, Toko toko, ArrayList<User> users) {

        setTitle("Kasir");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== COMBOBOX PRODUK =====
        JComboBox<Produk> comboProduk = new JComboBox<>();
        for (Produk p : toko.getProduk()) {
            comboProduk.addItem(p);
        }
        comboProduk.setBounds(20, 20, 300, 25);
        add(comboProduk);

        // ===== LABEL JUMLAH YANG DIBELI =====
        JLabel lblJumlah = new JLabel("Jumlah");
        lblJumlah.setBounds(330, 0, 120, 25); // posisi di atas txtQty
        add(lblJumlah);

        // ===== FIELD JUMLAH =====
        JTextField txtQty = new JTextField();
        txtQty.setBounds(330, 20, 50, 25);
        add(txtQty);

        // ===== BUTTON TAMBAH =====
        JButton btnTambah = new JButton("Tambah");
        btnTambah.setBounds(390, 20, 80, 25);
        add(btnTambah);

        // ===== LABEL TOTAL =====
        JLabel lblTotal = new JLabel("Total: Rp 0");
        lblTotal.setBounds(20, 70, 200, 25);
        add(lblTotal);

        // ===== LABEL UANG YANG DIBAYARKAN =====
        JLabel lblBayarLabel = new JLabel("Uang yang Dibayarkan");
        lblBayarLabel.setBounds(20, 100, 150, 25); // di atas txtBayar
        add(lblBayarLabel);

        // ===== FIELD BAYAR =====
        JTextField txtBayar = new JTextField();
        txtBayar.setBounds(180, 100, 120, 25);
        add(txtBayar);

        // ===== BUTTON BAYAR =====
        JButton btnBayar = new JButton("Bayar");
        btnBayar.setBounds(310, 100, 80, 25);
        add(btnBayar);

        // ===== LABEL KEMBALIAN =====
        JLabel lblKembalian = new JLabel("Kembalian: Rp 0");
        lblKembalian.setBounds(20, 140, 250, 25);
        add(lblKembalian);

        // ===== BUTTON LOGOUT =====
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(380, 260, 80, 25);
        add(btnLogout);

        // ===== ACTION TAMBAH =====
        btnTambah.addActionListener(e -> {
            try {
                Produk p = (Produk) comboProduk.getSelectedItem();
                int qty = Integer.parseInt(txtQty.getText());

                if (qty > p.stok) {
                    JOptionPane.showMessageDialog(this, "Stok tidak cukup!");
                    return;
                }

                total += p.harga * qty;
                toko.kurangiStok(p.id, qty); // pastikan method kurangiStok ada di Toko

                lblTotal.setText("Total: Rp " + total);
                txtQty.setText("");

                // REFRESH STOK DI COMBOBOX
                refreshProduk(toko, comboProduk);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input salah");
            }
        });

        // ===== ACTION BAYAR =====
        btnBayar.addActionListener(e -> {
            try {
                double bayar = Double.parseDouble(txtBayar.getText());
                double kembalian = bayar - total;

                if (kembalian < 0) {
                    JOptionPane.showMessageDialog(this, "Uang kurang");
                    return;
                }

                // Simpan transaksi
                toko.simpanTransaksi(kasir.getUsername(), total); // pastikan method simpanTransaksi ada

                // Tampilkan struk
                String struk = "TOKO PAKAIAN\n" +
                        "Kasir: " + kasir.getUsername() + "\n" +
                        "Total: Rp " + total + "\n" +
                        "Bayar: Rp " + bayar + "\n" +
                        "Kembalian: Rp " + kembalian;
                JOptionPane.showMessageDialog(this, struk);

                // Reset total
                total = 0;
                lblTotal.setText("Total: Rp 0");
                txtBayar.setText("");
                lblKembalian.setText("Kembalian: Rp 0");

                // REFRESH STOK DI COMBOBOX
                refreshProduk(toko, comboProduk);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Input bayar salah");
            }
        });

        // ===== ACTION LOGOUT =====
        btnLogout.addActionListener(e -> {
            dispose();
            new MainMenuFrame(users, toko);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
