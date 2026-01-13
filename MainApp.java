import model.*;
import ui.*;
import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new Admin("admin", "admin"));
        users.add(new Kasir("kasir", "kasir"));

        Toko toko = new Toko();
        new MainMenuFrame(users, toko);
    }
}
