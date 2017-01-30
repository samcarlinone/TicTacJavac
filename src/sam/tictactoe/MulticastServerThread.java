package sam.tictactoe;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by CARLINSE1 on 1/29/2017.
 */
public class MulticastServerThread extends Thread{
    protected DatagramSocket socket = null;
    protected boolean moreQuotes = true;

    private long FIVE_SECONDS = 5000;

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        try {
            listen();
        } catch( IOException e) {
            System.out.println("Network Error");
        }
    }

    public void bcast() throws IOException {
        try {
            socket = new DatagramSocket(35035);
        } catch(SocketException e) {
            System.out.println("SOcket error");
        }

        while (moreQuotes) {
            try {
                byte[] buf = new byte[256];

                // construct quote
                String dString = "SUp bros?";
                buf = dString.getBytes();

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
                moreQuotes = false;
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

        socket.setSoTimeout(5000);

        try {
            socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Quote of the Moment: " + received);
        } catch(SocketTimeoutException e){
            System.out.println("Timed out");
        }

        socket.leaveGroup(address);
        socket.close();
    }
}
