package sam.tictactoe;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;

/**
 * Created by CARLINSE1 on 1/31/2017.
 */
public class LANConnector {
    public void LANConnector() {
        //Do nothing
    }

    public TCPLink connect() {
        TCPLink link = null;

        try {
            MulticastDiscoveryThread lan_discovery = new MulticastDiscoveryThread();
            lan_discovery.start();

            String host = "";

            try {
                host = lan_discovery.queue.take();

                link = new TCPLink(host);

                if(host == "") {
                    //Block and wait for connection
                    link.read();

                    lan_discovery.queue.put("terminate");
                } else {
                    //Send message to signal connection
                    link.write("Hello world.");
                }
            } catch (InterruptedException e) {
                System.out.println("Threading Error");
            }
        } catch(IOException e) {
            System.out.println("Network error");
        }

        return link;
    }
}
