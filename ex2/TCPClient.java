import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);


            int number = 10;
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(number);


            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String result = reader.readLine();
            System.out.println("Result from server: " + result);


            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

