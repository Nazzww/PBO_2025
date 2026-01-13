package model;

public class Admin extends User {
    public Admin(String u, String p) {
        super(u, p);
    }

    public String getRole() {
        return "ADMIN";
    }
}
