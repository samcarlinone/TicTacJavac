package sam.tictactoe;

import java.util.Scanner;

/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scnr = new Scanner(System.in);
        Game[] games = new Game[] {new LANTwoPlayerGame(), new TwoPlayerGame()};

        while(true) {
            System.out.println("List of available games: ");
            for(int i=0; i<games.length; i++) {
                System.out.println("\t"+i+" : "+games[i].description());
            }
            System.out.println("Input game id, or \"exit\"");

            String input = scnr.nextLine();

            if(input.equals("exit")) {
                break;
            }

            int choice;

            try {
                choice = new Integer(input);
            } catch (NumberFormatException e){
                System.out.println("Invalid input");
                continue;
            }

            if(choice < games.length) {
                games[choice].run();
            }
        }
    }
}