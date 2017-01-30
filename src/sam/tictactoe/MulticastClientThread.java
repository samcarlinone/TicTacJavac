package sam.tictactoe;

import com.sun.org.apache.xpath.internal.operations.Mult;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by CARLINSE1 on 1/29/2017.
 */

public class MulticastClientThread extends Thread {

    public MulticastClientThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        try {
            listen();
        } catch( IOException e) {
            System.out.println("Network Error");
        }
    }

    public void listen() throws IOException{
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
