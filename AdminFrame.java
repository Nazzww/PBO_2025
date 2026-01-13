package ui;

import model.*;
import javax.swing.*;
import java.util.ArrayList;

public class AdminFrame extends JFrame {

    JComboBox<Produk> comboProduk;
    JTextArea areaLaporan;

    public AdminFrame(Toko toko, ArrayList<User> users) {

        setTitle("Admin - Kelola Produk & Laporan");
        setSize(650, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== LABEL KOLOM =====
        JLabel lblKolomNama = new JLabel("Nama Barang");
        lblKolomNama.setBounds(20, 40, 100, 25);
        add(lblKolomNama);

        JLabel lblKolomHarga = new JLabel("Harga");
        lblKolomHarga.setBounds(230, 40, 100, 25);
        add(lblKolomHarga);

        JLabel lblKolomStok = new JLabel("Stok");
        lblKolomStok.setBounds(340, 40, 80, 25);
        add(lblKolomStok);

        // ===== FORM PRODUK =====
        comboProduk = new JComboBox<>();
        refreshProduk(toko);

        comboProduk.setBounds(20, 10, 300, 25);
        add(comboProduk);

        JTextField txtNama = new JTextField();
        JTextField txtHarga = new JTextField();
        JTextField txtStok = new JTextField();

        txtNama.setBounds(20, 70, 200, 25);
        txtHarga.setBounds(230, 70, 100, 25);
        txtStok.setBounds(340, 70, 80, 25);

        add(txtNama); add(txtHarga); add(txtStok);

        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnTambah = new JButton("Tambah Baru");

        btnUpdate.setBounds(20, 110, 100, 30);
        btnHapus.setBounds(130, 110, 100, 30);
        btnTambah.setBounds(240, 110, 150, 30);

        add(btnUpdate); add(btnHapus); add(btnTambah);

        // ===== ACTION =====
        comboProduk.addActionListener(e -> {
            Produk p = (Produk) comboProduk.getSelectedItem();
            if (p != null) {
                txtNama.setText(p.nama);
                txtHarga.setText(String.valueOf(p.harga));
                txtStok.setText(String.valueOf(p.stok));
            }
        });

        btnUpdate.addActionListener(e -> {
            Produk p = (Produk) comboProduk.getSelectedItem();
            toko.updateProduk(
                    p.id,
                    txtNama.getText(),
                    Double.parseDouble(txtHarga.getText()),
                    Integer.parseInt(txtStok.getText()));
            refreshProduk(toko);
        });

        btnHapus.addActionListener(e -> {
            Produk p = (Produk) comboProduk.getSelectedItem();
            toko.hapusProduk(p.id);
            refreshProduk(toko);
        });

        btnTambah.addActionListener(e -> {
            toko.tambahProduk(
                    txtNama.getText(),
                    Double.parseDouble(txtHarga.getText()),
                    Integer.parseInt(txtStok.getText()));
            refreshProduk(toko);
        });

        // ===== LAPORAN =====
        JTextField txtTanggal = new JTextField("2024-01-01");
        JButton btnCari = new JButton("Cari Tanggal");

        txtTanggal.setBounds(20, 160, 150, 25);
        btnCari.setBounds(180, 160, 130, 25);

        add(txtTanggal); add(btnCari);

        areaLaporan = new JTextArea();
        JScrollPane sp = new JScrollPane(areaLaporan);
        sp.setBounds(20, 200, 580, 180);
        add(sp);

        btnCari.addActionListener(e -> {
            areaLaporan.setText("");
            for (String s : toko.getLaporanByTanggal(txtTanggal.getText())) {
                areaLaporan.append(s + "\n");
            }
        });

        // ===== LOGOUT =====
        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(500, 400, 100, 30);
        add(btnLogout);

        btnLogout.addActionListener(e -> {
            dispose();
            new MainMenuFrame(users, toko);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshProduk(Toko toko) {
        comboProduk.removeAllItems();
        for (Produk p : toko.getProduk()) {
            comboProduk.addItem(p);
        }
    }
}
