package question1;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ConcurrentServer extends Thread {

    private static final String RETURN_MESSAGE = "/";
    private static final String END_MESSAGE = ".";
    private static final int PORT = 7000;

    private StreamSocket socket;

    private ConcurrentServer(StreamSocket socket) {
        System.out.println("New client.  ");
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            List<Integer> ints = new ArrayList<>();
            System.out.println("connection accepted");
            boolean done = false;
            while (!done) {
                String message = socket.receiveMessage();
                System.out.println("message received: " + message);
                if ((message.trim().toLowerCase()).equals(END_MESSAGE)) {
                    System.out.println("Session over.");
                    socket.close();
                    done = true;
                } else if ((message.trim().toLowerCase()).equals(RETURN_MESSAGE)) {
                    Collections.sort(ints);
                    socket.sendMessage(ints.toString());
                } else {
                    try {
                        int result = Integer.parseInt(message);
                        ints.add(result);
                    } catch (NumberFormatException ex) {
                        System.out.println("Not an int");
                    }
                }
            }
            return;
        } catch (Exception ex) {
        }
    }

    public static void main(String args[]) throws IOException {
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (true) {
                System.out.println("Waiting");
                StreamSocket dataSocket = new StreamSocket(server.accept());
                System.out.println("Accepted from " + dataSocket.getInetAddress());
                ConcurrentServer clientHandler = new ConcurrentServer(dataSocket);
                clientHandler.start();
            }
        } catch (IOException ex) {
            System.err.println("Unable to connect on specified port");
        }
    }
}
