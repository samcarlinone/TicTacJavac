package sam.tictactoe;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by CARLINSE1 on 1/31/2017.
 */
public class TCPLink {
    private ServerSocket server_socket;
    private Socket socket;
    private BufferedReader read;
    private DataOutputStream write;

    static private int PORT = 23658;

    public TCPLink(String host) throws IOException {
        if(host == "") {
            server_socket = new ServerSocket(PORT);
            socket = server_socket.accept();
        } else {
            socket = new Socket(host, PORT);
        }

        read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        write = new DataOutputStream(socket.getOutputStream());
    }

    public String read() {
        try {
            return read.readLine();
        } catch(IOException e) {
            System.out.println("TCP Recieve Failed");
            return "";
        }
    }

    public void write(String tx) {
        if(!tx.endsWith("\n"))
            tx = tx+"\n";

        try {
            write.writeBytes(tx);
        } catch(IOException e) {
            System.out.println("TCP Send Failed");
            return;
        }
    }
}
