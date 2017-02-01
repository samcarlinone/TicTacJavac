package sam.tictactoe;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by CARLINSE1 on 1/29/2017.
 */
public class MulticastDiscoveryThread extends Thread{
    protected DatagramSocket socket = null;

    private long FIVE_SECONDS = 5000;
    public ArrayBlockingQueue<String> queue;

    public MulticastDiscoveryThread() throws IOException {
        super("MulticastDiscoveryThread");

        queue = new ArrayBlockingQueue<String>(128, true);
    }

    public void run() {
        String target = "";

        try {
            target = listen();
        } catch( IOException e) {
            System.out.println("Network Error");
        }

        try {
            queue.put(target);
        } catch(InterruptedException e) {
            System.out.println("Queue Error");
        }

        if(target.equals("")) {
            try {
                bcast();
            } catch( IOException e) {
                System.out.println("Network Error");
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
                // Get current internet address
                String dString = getHostAddresses()[0];
                byte[] buf = dString.getBytes();

                System.out.println(dString);

                // send it
                InetAddress group = InetAddress.getByName("230.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);

                socket.send(packet);

                if(queue.poll() != null) {
                    socket.close();
                    return;
                }

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

    /**
     * Gets host address that are actually connected / don't loopback
     * http://stackoverflow.com/questions/2381316/java-inetaddress-getlocalhost-returns-127-0-0-1-how-to-get-real-ip
     * @return Returns stringified ip addresses e.g. (10.23.105.218)
     */
    private String[] getHostAddresses() {
        Set<String> HostAddresses = new HashSet<>();
        try {
            for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!ni.isLoopback() && ni.isUp() && ni.getHardwareAddress() != null) {
                    for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
                        if (ia.getBroadcast() != null) {  //If limited to IPV4
                            HostAddresses.add(ia.getAddress().getHostAddress());
                        }
                    }
                }
            }
        } catch (SocketException e) { }
        return HostAddresses.toArray(new String[0]);
    }
}
