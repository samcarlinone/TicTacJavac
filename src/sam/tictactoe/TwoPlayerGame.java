package sam.tictactoe;

import java.util.Scanner;

/**
 * Created by CARLINSE1 on 1/28/2017.
 */
public class TwoPlayerGame implements Game {
    /**
     * Run the game (synchronous)
     */

    public void run() {
        Board board = new Board(3);
        Boolean player1Turn = true;
        Scanner scnr = new Scanner(System.in);

        while(board.boardState != Board.BoardState.WON && board.boardState != Board.BoardState.FULL) {
            System.out.println("\n" + (player1Turn ? "Player 1:" : "Player 2:") + " input move, or 'help' for options ...");

            String input = scnr.nextLine();

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
                if (player1Turn) {
                    board.setChar('X', move);
                } else {
                    board.setChar('O', move);
                }
            } else {
                System.out.println("Invalid move");
                continue;
            }

            player1Turn = !player1Turn;
            GridRender.print(GridRender.render(board.getData()), 13);
        }

        if(board.boardState == Board.BoardState.FULL) {
            System.out.println("\n\nGame is a draw");
        }

        if(board.boardState == Board.BoardState.WON) {
            System.out.println("\n\n" + (board.winner == 'X' ? "Player 1 wins" : "Player 2 wins"));
        }
    }

    /**
     * Prints help message
     */
    public void printHelp() {
        GridRender.print(GridRender.render("123456789".toCharArray()), 13);
    }

    public String description() {
        return "Simple two player game, players take turns";
    }
}
