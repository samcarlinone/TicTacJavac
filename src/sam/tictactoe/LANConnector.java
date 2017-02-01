package sam.tictactoe;

import java.io.IOException;

/**
 * Created by CARLINSE1 on 1/31/2017.
 */
public class LANConnector {
    public void LANConnector() {
        //Do nothing
    }

    public void connect() {
        try {
            MulticastDiscoveryThread lan_discovery = new MulticastDiscoveryThread();
            lan_discovery.start();

            System.out.println("Thread running");

            String host = "";

            try {
                host = lan_discovery.queue.take();

                TCPLink link = new TCPLink(host);

                if(host == "") {
                    System.out.println("No hosts found, broadcasting ...");

                    System.out.println(link.read());

                    lan_discovery.queue.put("terminate");
                } else {
                    System.out.println("Found: " + host);

                    link.write("Hello world.");
                }
            } catch (InterruptedException e) {
                System.out.println("Threading Error");
            }

            System.out.println("Main thread terminating");

        } catch(IOException e) {
            System.out.println("Network error");
        }
    }
}
