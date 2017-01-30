package sam.tictactoe;


import java.io.IOException;

/**
 * Created by CARLINSE1 on 1/27/2017.
 */
public class Main {
    public static void main(String[] args) {
        // write your code here
        //TwoPlayerGame.run();

        try {
            MulticastClientThread client = new MulticastClientThread();
            client.run();
            for(int i=0; i<10; i++)
                client.run();
            System.out.println("DOne clienting");

            MulticastServerThread server = new MulticastServerThread();
            server.run();
        } catch(IOException e) {
            System.out.println("Network error");
        }
    }
}