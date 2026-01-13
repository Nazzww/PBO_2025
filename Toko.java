package model;

import database.DatabaseHelper;
import java.sql.*;
import java.util.ArrayList;

public class Toko {

    public ArrayList<Produk> getProduk() {
        ArrayList<Produk> list = new ArrayList<>();
        try (Connection c = DatabaseHelper.getConnection()) {
            ResultSet r = c.createStatement().executeQuery("SELECT * FROM produk");
            while (r.next()) {
                list.add(new Produk(
                        r.getInt("id"),
                        r.getString("nama"),
                        r.getDouble("harga"),
                        r.getInt("stok")
                ));
            }
        } catch (Exception e) {
            System.out.println("GAGAL AMBIL PRODUK");
        }
        return list;
    }

    public void kurangiStok(int id, int qty) {
        try (Connection c = DatabaseHelper.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "UPDATE produk SET stok = stok - ? WHERE id = ?");
            p.setInt(1, qty);
            p.setInt(2, id);
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("GAGAL UPDATE STOK");
        }
    }

    public void simpanTransaksi(String kasir, double total) {
        try (Connection c = DatabaseHelper.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "INSERT INTO transaksi(kasir, total) VALUES (?,?)");
            p.setString(1, kasir);
            p.setDouble(2, total);
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("GAGAL SIMPAN TRANSAKSI");
        }
    }

    public void tambahProduk(String nama, double harga, int stok) {
        try (Connection c = DatabaseHelper.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "INSERT INTO produk (nama, harga, stok) VALUES (?,?,?)");
            p.setString(1, nama);
            p.setDouble(2, harga);
            p.setInt(3, stok);
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("GAGAL TAMBAH PRODUK");
        }
    }

    public void updateProduk(int id, String nama, double harga, int stok) {
        try (Connection c = DatabaseHelper.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "UPDATE produk SET nama=?, harga=?, stok=? WHERE id=?");
            p.setString(1, nama);
            p.setDouble(2, harga);
            p.setInt(3, stok);
            p.setInt(4, id);
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("GAGAL UPDATE PRODUK");
        }
    }

    public void hapusProduk(int id) {
        try (Connection c = DatabaseHelper.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "DELETE FROM produk WHERE id=?");
            p.setInt(1, id);
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("GAGAL HAPUS PRODUK");
        }
    }

    public ArrayList<String> getLaporanByTanggal(String tanggal) {
        ArrayList<String> list = new ArrayList<>();
        try (Connection c = DatabaseHelper.getConnection()) {
            PreparedStatement p = c.prepareStatement(
                    "SELECT * FROM transaksi WHERE DATE(tanggal)=?");
            p.setString(1, tanggal);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                list.add(
                        r.getString("tanggal") + " | " +
                                r.getString("kasir") + " | Rp " +
                                r.getDouble("total"));
            }
        } catch (Exception e) {}
        return list;
    }


    public ArrayList<String> getLaporan() {
        ArrayList<String> list = new ArrayList<>();
        try (Connection c = DatabaseHelper.getConnection()) {
            ResultSet r = c.createStatement().executeQuery("SELECT * FROM transaksi");
            while (r.next()) {
                list.add(
                        r.getString("tanggal") +
                                " | " + r.getString("kasir") +
                                " | Rp " + r.getDouble("total")
                );
            }
        } catch (Exception e) {}
        return list;
    }
}
