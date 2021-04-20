package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Serversocket implements Runnable{
    private PrintWriter writer;
    private BufferedReader bufferedReader;
    private boolean runner;
    private BlockingQueue<String> commandQueue;
    private List<String> firstServerResponse = Arrays.asList("Strategic Game Server Fixed [Version 1.1.0]",
            "(C) Copyright 2015 Hanzehogeschool Groningen");

    public Serversocket(Socket socket, LinkedBlockingQueue<String> commands) throws IOException{
        commandQueue = commands;
        writer = new PrintWriter(socket.getOutputStream(), true);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        runner = true;
    }

    @Override
    public void run() {
        String command;
        String inputLine;
        System.out.println("Test for run in Connection");
        while (runner) {
            try {
                while ((command = commandQueue.poll()) != null) {
                    System.out.println("Sending a command");
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
