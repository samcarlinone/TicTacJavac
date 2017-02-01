package sam.tictactoe;

import java.util.Scanner;

/**
 * Created by CARLINSE1 on 1/28/2017.
 */
public class LANTwoPlayerGame {
    /**
     * Run the game (synchronous)
     */

    public static void run(TCPLink link) {
        Board board = new Board(3);
        Boolean myTurn = link.isHost;

        char myChar;
        char hisChar;

        if(myTurn) {
            myChar = 'X';
            hisChar = 'O';
        } else {
            myChar = 'O';
            hisChar = 'X';
        }

        Scanner scnr = new Scanner(System.in);

        while(board.boardState != Board.BoardState.WON && board.boardState != Board.BoardState.FULL) {
            String input = "";

            if(myTurn) {
                System.out.println("\nYour turn, input move, or 'help' for options ...");
                input = scnr.nextLine();

                if(input.equals("help")) {
                    printHelp();
                    continue;
                }

                int move;

                try {
                    move = new Integer(input) - 1;
                } catch (NumberFormatException e){
                    System.out.println("Invalid input");
                    continue;
                }

                if(board.getChar(move) == ' ') {
                    board.setChar(myChar, move);
                } else {
                    System.out.println("Invalid move");
                    continue;
                }

                link.write(input);
            } else {
                System.out.println("\nWaiting for opponent ...");
                input = link.read();

                int move;

                try {
                    move = new Integer(input) - 1;
                } catch (NumberFormatException e){
                    System.out.println("Opponent has made invalid move, game aborted");
                    return;
                }

                if(board.getChar(move) == ' ') {
                    board.setChar(hisChar, move);
                } else {
                    System.out.println("Opponent has made invalid move, game aborted");
                    return;
                }
            }

            myTurn = !myTurn;
            GridRender.print(GridRender.render(board.getData()), 13);
        }

        if(board.boardState == Board.BoardState.FULL) {
            System.out.println("\n\nGame is a draw");
        }

        if(board.boardState == Board.BoardState.WON) {
            System.out.println("\n\n" + (board.winner == myChar ? "You have won" : "Opponent wins"));
        }
    }

    /**
     * Prints help message
     */
    public static void printHelp() {
        GridRender.print(GridRender.render("123456789".toCharArray()), 13);
    }
}
