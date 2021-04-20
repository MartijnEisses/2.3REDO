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

public class Connection implements Runnable {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader bufferedReader;
    private BlockingQueue<String> commandQueue;
    private Thread thread;
    private List<String> playerList;
    private List<String> challenges;
    private List<String> firstServerResponse = Arrays.asList("Strategic Game Server Fixed [Version 1.1.0]",
            "(C) Copyright 2015 Hanzehogeschool Groningen");
    private boolean runner = true;
    //PrintWriter is voor output
    //BufferedReader is voor input

    public Connection() throws IOException {
        commandQueue = new LinkedBlockingQueue<>();
    }

    public void connectToServer(String ip, int port) {
        System.out.println("Logging into server: " + ip + " on port: " + port);
        try {
            socket = new Socket(ip, port);
            writer = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

       //ExecutorService executorService = Executors.newFixedThreadPool(2);
       // executorService.execute(writer);
       // executorService.execute(new Conversation());

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
    }

    public BlockingQueue<String> getCommandQueue() {
        return commandQueue;
    }

    @Override
    public void run() {
        String command;
        String inputLine;

        while (runner) {
            try {

                while ((command = commandQueue.poll()) != null) {
                    //System.out.println("Conversation  is reached");
                    writer.println(command);
                    writer.flush();
                    if (command.equals("logout")) {
                        runner = false;
                    }
                    // e.printStackTrace();
                }
            } catch (NullPointerException e) {
                //e.printStackTrace();
            }

            try{
                while(bufferedReader.ready() && (inputLine = bufferedReader.readLine()) != null){
                    if(firstServerResponse.contains(inputLine)){
                        continue;
                    }
                    System.out.println("Server response: " + inputLine);
                    //Hier moet de interpreter nog worden aangeroepen
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
