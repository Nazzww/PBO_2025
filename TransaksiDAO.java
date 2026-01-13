package dao;

import database.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransaksiDAO {

    public static void simpan(String kasir, double total) {
        String sql = "INSERT INTO transaksi(kasir, total) VALUES (?, ?)";

        try (Connection c = DatabaseHelper.getConnection();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, kasir);
            p.setDouble(2, total);
            p.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
