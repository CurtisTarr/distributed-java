package question1;

import java.net.*;
import java.io.*;

class ClientHelper {

    private static final String END_MESSAGE = ".";

    private final StreamSocket socket;

    ClientHelper(String hostName, String portNum) throws IOException {
        InetAddress serverHost = InetAddress.getByName(hostName);
        int serverPort = Integer.parseInt(portNum);
        this.socket = new StreamSocket(serverHost, serverPort);
    }

    String sendMessage(String message) throws IOException {
        socket.sendMessage(message);
        return socket.receiveMessage();
    }

    void done() throws IOException {
        socket.sendMessage(END_MESSAGE);
        socket.close();
    }

}
