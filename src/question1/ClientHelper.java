package question1;

import java.net.*;
import java.io.*;

class ClientHelper {

    private static final String RETURN_MESSAGE = "/";
    private static final String END_MESSAGE = ".";
    private final StreamSocket socket;
    private final InetAddress serverHost;
    private final int serverPort;

    ClientHelper(String hostName, String portNum) throws SocketException, UnknownHostException, IOException {
        this.serverHost = InetAddress.getByName(hostName);
        this.serverPort = Integer.parseInt(portNum);
        this.socket = new StreamSocket(this.serverHost, this.serverPort);
    }

    // send the parameter (message) to the server and reads and returns the reply from the server
    void sendInt(String message) throws SocketException, IOException {
        socket.sendMessage(message);
    }

    String getOrderedInts() throws SocketException, IOException {
        String ints = "";
        socket.sendMessage(RETURN_MESSAGE);
        ints = socket.receiveMessage();
        return ints;
    }

    // sends the end message "." to the server
    void done() throws SocketException, IOException {
        socket.sendMessage(END_MESSAGE);
        socket.close();
    }

}
