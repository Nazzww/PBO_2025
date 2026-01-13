package dao;

import database.DatabaseHelper;
import model.Produk;

import java.sql.*;
import java.util.ArrayList;

public class ProdukDAO {

    public static ArrayList<Produk> getAll() {
        ArrayList<Produk> list = new ArrayList<>();

        String sql = "SELECT * FROM produk";

        try (Connection c = DatabaseHelper.getConnection();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {

            while (r.next()) {
                list.add(new Produk(
                        r.getInt("id"),
                        r.getString("nama"),
                        r.getDouble("harga"),
                        r.getInt("stok")
                ));
            }

        } catch (Exception e) {
            System.out.println("GAGAL AMBIL DATA PRODUK");
            e.printStackTrace();
        }

        return list;
    }

    public static void kurangiStok(int produkId, int jumlah) {
        String sql = "UPDATE produk SET stok = stok - ? WHERE id = ?";

        try (Connection c = DatabaseHelper.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, jumlah);
            p.setInt(2, produkId);
            p.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
