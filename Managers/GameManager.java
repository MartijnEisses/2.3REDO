package Managers;

import Managers.ReversiManager;
import Managers.TicTacToeManager;
import Managers.UIManager;
import Server.Connection;

import java.io.IOException;

public class GameManager {

    private Manager[] managers;
    private Connection connection;

    public GameManager() {
        connection = new Connection();
        managers = new Manager[] { new UIManager(), new TicTacToeManager(), new ReversiManager(),

        };
    }

    // Runs on setup-
    public void Start() throws IOException {
        if(connection.connectToServer("127.0.0.1", 7789)){
            connection.login("testing");
            System.out.println("test");
        }

        for (int i = 0; i < managers.length; i++) {
            managers[i].Start();
        }

    }

    // Runs constantly
    public void Update() {
        for (int i = 0; i < managers.length; i++) {
            managers[i].Update();
        }
    }

}
