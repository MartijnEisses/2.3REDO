package Root;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) {
        try {
            Main.main(args);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
