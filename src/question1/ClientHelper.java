package question1;

import java.net.*;
import java.io.*;

/**
 * Helper class for the Client that manages the connection to the server
 */
class ClientHelper {

    private static final String END_MESSAGE = ".";

    private final StreamSocket socket;

    /**
     * Constructor that connects to the given server
     *
     * @param hostName host's name
     * @param portNum host's port
     * @throws IOException
     */
    ClientHelper(String hostName, String portNum) throws IOException {
        InetAddress serverHost = InetAddress.getByName(hostName);
        int serverPort = Integer.parseInt(portNum);
        this.socket = new StreamSocket(serverHost, serverPort);
        System.out.println("Waiting for server to accept connection");
        System.out.println(socket.receiveMessage());
    }

    /**
     * Sends message to server and returns its response
     *
     * @param message the message to be sent
     * @return the server's response
     * @throws IOException
     */
    String sendMessage(String message) throws IOException {
        socket.sendMessage(message);
        return socket.receiveMessage();
    }

    /**
     * Notifies the server that the client is ending its session and disconnects
     *
     * @throws IOException
     */
    void done() throws IOException {
        socket.sendMessage(END_MESSAGE);
        socket.close();
    }
}
