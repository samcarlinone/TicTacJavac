package sam.tictactoe;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by CARLINSE1 on 1/29/2017.
 */
public class MulticastDiscoveryThread extends Thread{
    protected DatagramSocket socket = null;

    private long FIVE_SECONDS = 5000;
    private BlockingQueue<String> queue;

    public MulticastDiscoveryThread() throws IOException {
        super("MulticastDiscoveryThread");
    }

    public void start(BlockingQueue<String> queue) {
        run(queue);
    }

    public void run(BlockingQueue<String> queue) {
        String target = "";
        this.queue = queue;

        try {
            target = listen();
        } catch( IOException e) {
            System.out.println("Network Error");
        }

        if(target.equals("")) {
            try {
                bcast();
            } catch( IOException e) {
                System.out.println("Network Error");
            }
        } else {
            try {
                queue.put(target);
            } catch(InterruptedException e) {
                System.out.println("Thread communication error");
            }
        }
    }

    public void bcast() throws IOException {
        try {
            socket = new DatagramSocket(35035);
        } catch(SocketException e) {
            System.out.println("Socket error");
        }

        boolean waiting = true;

        while (waiting) {
            try {
                byte[] buf = new byte[256];

                // construct quote
                String dString = InetAddress.getLocalHost().toString();
                buf = dString.getBytes();

                System.out.println(dString);

                // send it
                InetAddress group = InetAddress.getByName("230.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);

                socket.send(packet);

                // sleep for a while
                try {
                    sleep((long)(Math.random() * FIVE_SECONDS));
                } catch (InterruptedException e) { }
            } catch (IOException e) {
                e.printStackTrace();
                waiting = false;
            }
        }
        socket.close();
    }

    public String listen() throws IOException {
        MulticastSocket socket = new MulticastSocket(4446);
        InetAddress address = InetAddress.getByName("230.0.0.1");
        socket.joinGroup(address);

        DatagramPacket packet;


        byte[] buf = new byte[256];
        packet = new DatagramPacket(buf, buf.length);

        socket.setSoTimeout(10000);

        String recieved = "";

        try {
            socket.receive(packet);
            recieved = new String(packet.getData(), 0, packet.getLength());
            System.out.println("InetAddress: " + recieved);
        } catch(SocketTimeoutException e){
            System.out.println("Timed out");
        }

        socket.leaveGroup(address);
        socket.close();

        return recieved;
    }
}
