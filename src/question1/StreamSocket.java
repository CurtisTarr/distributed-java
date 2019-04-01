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

    StreamSocket(InetAddress acceptorHost, int acceptorPort) throws IOException {
        socket = new Socket(acceptorHost, acceptorPort);
        setStreams();
    }

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

    void sendMessage(String message) {
        output.println(message);
        output.flush();
    }

    String receiveMessage() throws IOException {
        return input.readLine();
    }

}