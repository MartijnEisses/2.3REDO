import Server.Connection;

public class GameManager {

    private Manager[] managers;
    private static Connection connection;

    public GameManager() {
        managers = new Manager[] { new UIManager(), new TicTacToeManager(), new ReversiManager(),

        };
    }

    // Runs on setup
    public void Start() {
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
