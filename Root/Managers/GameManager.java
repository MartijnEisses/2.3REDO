package Root.Managers;

import Root.Managers.ReversiManager;
import Root.Managers.TicTacToeManager;
import Root.Managers.UIManager;
import Root.Server.Connection;

import java.io.IOException;

public class GameManager {

    private Manager[] managers;

    public GameManager() {
        managers = new Manager[] { new UIManager(), new TicTacToeManager(), new ReversiManager(),
        };
    }

    // Runs on setup-
    public void Start() throws IOException {

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
