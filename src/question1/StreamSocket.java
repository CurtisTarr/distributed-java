package question1;

import java.net.*;
import java.io.*;

/**
 * A wrapper class of Socket which contains methods for sending and receiving
 * messages
 */
class StreamSocket extends Socket {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    // creates the socket given machine host address and specified port and gets input and output streams
    StreamSocket(InetAddress acceptorHost, int acceptorPort) throws SocketException, IOException {
        socket = new Socket(acceptorHost, acceptorPort);
        setStreams();
    }

    // accepts a socket as a parameter  and gets input and output streams
    StreamSocket(Socket socket) throws IOException {
        this.socket = socket;
        setStreams();
    }

    private void setStreams() throws IOException {
        InputStream inStream = socket.getInputStream();
        input = new BufferedReader(new InputStreamReader(inStream));
        OutputStream outStream = socket.getOutputStream();
        output = new PrintWriter(new OutputStreamWriter(outStream));
    }

    // sends a message across the socket
    public void sendMessage(String message) throws IOException {
        output.println(message);
        output.flush();
    }

    // receives a message across the socket
    public String receiveMessage() throws IOException {
        String message = input.readLine();
        return message;
    }

}