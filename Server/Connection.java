package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Connection{
    private Socket socket;
    private LinkedBlockingQueue<String> commandQueue;
    private Thread thread;
    private List<String> playerList;
    private List<String> challenges;
    private Serversocket serversocket;


    //PrintWriter is voor output
    //BufferedReader is voor input

    public Connection() {
        commandQueue = new LinkedBlockingQueue<>();
    }

    public boolean connectToServer(String ip, int port) throws IOException {
        System.out.println("Logging into server: " + ip + " on port: " + port);
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        serversocket = new Serversocket(socket, commandQueue);
        thread = new Thread(serversocket);
        thread.start();
        return true;
    }

    public void login(String name) {
        System.out.println("-------------Logging in-------------");
        commandQueue.add("login " + name);
        System.out.println("Logged in as: " + name);
        printQueue();
        popQueue();
        //printQueue();
    }

    public void logout() {
        commandQueue.add("logout");
    }

    public List<String> getPlayerlist() {
        commandQueue.add("get playerlist");
        return playerList;
    }

    public void getGamelist() {
        commandQueue.add("get gamelist");

    }

    public void acceptGameChallenge(int gameID) {
        System.out.println("Start acceptGameChallenge, sending challenge accept!");
        commandQueue.add("challenge accept " + gameID);
        printQueue();
        popQueue();
    }

    public void challengePlayer(String opponent, String gamemode) {
        commandQueue.add("challenge \"" + opponent + "\" \"" + gamemode + "\"");
    }

    public void subscribe(String game) {
        System.out.println("Subscribing to Reversi!");
        commandQueue.add("subscribe " + game);
        printQueue();
        popQueue();
        //printQueue();
    }

    public void setMove(int position) {
        commandQueue.add("move " + position);
    }

    public void forfeit() {
        commandQueue.add("forfeit");
    }

    public void getHelp() {
        commandQueue.add("help");
    }

    public void printQueue() {
        System.out.println("Printing commandQueue: " + commandQueue);
    }

    public void popQueue() {
        String test = commandQueue.peek();
        commandQueue.remove(test);
        printQueue();
    }

    public BlockingQueue<String> getCommandQueue() {
        return commandQueue;
    }

}
