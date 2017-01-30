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

        try {
            MulticastDiscoveryThread lan_discovery = new MulticastDiscoveryThread();

            BlockingQueue<String> queue = new ArrayBlockingQueue<String>(128);

            lan_discovery.start(queue);
            System.out.println("Thread running");

            String s = "";

            try {
                s = queue.take();
            } catch (InterruptedException e) {
                System.out.println("Threading Error");
            }

            System.out.println(s);

        } catch(IOException e) {
            System.out.println("Network error");
        }
    }
}