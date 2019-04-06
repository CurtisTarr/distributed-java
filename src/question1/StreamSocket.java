package question1;

import java.net.*;
import java.io.*;

/**
 * A Socket wrapper class that provides functions to send and receive messages
 */
class StreamSocket extends Socket {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    /**
     * Constructor that creates socket for a host and port
     *
     * @param acceptorHost the host's address
     * @param acceptorPort the host's port
     * @throws IOException
     */
    StreamSocket(InetAddress acceptorHost, int acceptorPort) throws IOException {
        socket = new Socket(acceptorHost, acceptorPort);
        setStreams();
    }

    /**
     * Constructor for existing socket
     *
     * @param socket socket to create streams for
     * @throws IOException
     */
    StreamSocket(Socket socket) throws IOException {
        this.socket = socket;
        setStreams();
    }

    private void setStreams() throws IOException {
        InputStream inputStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inputStream));
        OutputStream outputStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outputStream));
    }

    /**
     * Sends message to the socket
     *
     * @param message what is to be sent
     */
    void sendMessage(String message) {
        output.println(message);
        output.flush();
    }

    /**
     * Listens for and returns the next message from the socket
     *
     * @return incoming message
     * @throws IOException
     */
    String receiveMessage() throws IOException {
        return input.readLine();
    }
}