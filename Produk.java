package model;

public class Produk {
    public int id;
    public String nama;
    public double harga;
    public int stok;

    public Produk(int i, String n, double h, int s) {
        id = i;
        nama = n;
        harga = h;
        stok = s;
    }

    public String toString() {
        return nama + " (Rp " + harga + ", stok " + stok + ")";
    }
}
