package sam.tictactoe;


import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here
        //TwoPlayerGame.run();

        LANConnector connector = new LANConnector();
        connector.connect();
    }
}