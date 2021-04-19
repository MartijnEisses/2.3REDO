class Main {

    public static GameManager gameManager;
    public static boolean running = true;

    public static void main(String[] args) {
        gameManager = new GameManager();

        // Start every start
        gameManager.Start();

        while (running) {
            gameManager.Update();
        }
    }

}