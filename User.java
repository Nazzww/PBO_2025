package model;

public abstract class User {
    protected String username;
    protected String password;

    public User(String u, String p) {
        username = u;
        password = p;
    }

    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    public String getUsername() {
        return username;
    }

    public abstract String getRole();
}
