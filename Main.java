class Main {

    public static GameManager gameManager;
    public static boolean running = true;

    public static void main(String[] args) {
        gameManager = new GameManager();

        // Start every Manager
        gameManager.Start();

        // Gameloop
        while (running) {
            gameManager.Update();
        }
    }

}