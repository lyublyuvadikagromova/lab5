import java.io.*;
import java.net.*;

public class TCPServer {
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("TCP Server started");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);


                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket clientSocket;

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String task = reader.readLine();
                System.out.println("Received task from client: " + task);


                int number = Integer.parseInt(task);
                long result = calculateFactorial(number);


                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                writer.println("Factorial of " + number + " is: " + result);


                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private long calculateFactorial(int n) {
            if (n <= 1) {
                return 1;
            }
            return n * calculateFactorial(n - 1);
        }
    }
}
