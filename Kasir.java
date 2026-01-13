package model;

public class Kasir extends User {
    public Kasir(String u, String p) {
        super(u, p);
    }

    public String getRole() {
        return "KASIR";
    }
}
