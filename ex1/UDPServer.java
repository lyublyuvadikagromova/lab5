import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class UDPServer {
    private static final int SERVER_PORT = 12345;
    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
            byte[] receiveData = new byte[BUFFER_SIZE];
            byte[] sendData;
            List<String> clients = new ArrayList<>();

            System.out.println("UDP Server started");

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String clientData = new String(receivePacket.getData(), 0, receivePacket.getLength());

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                if (!clients.contains(clientData)) {
                    clients.add(clientData);
                }

                String response = String.join(", ", clients);
                sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
