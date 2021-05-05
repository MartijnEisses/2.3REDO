package Root.Pages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


/**
 * Gebruikte terminal voor het spelen van reversi via CommandLine.
 * Leest input van via de BufferedReader.
 */
public class Terminal {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String input = "";
    String command;
    String direct;
    String[] commandWords = new String[]{"stop", "set","moves"};
    List<String> list = Arrays.asList(commandWords);
    public String parce() {
        try {
            System.out.println("enter your move");
            input = in.readLine();
            String[] fullWord = input.split(" ");
            command = fullWord[0];
            if(list.contains(command)) {
                return input;
            }
            else{
                System.out.println("use correct words");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "xenomorph";
    }
}
