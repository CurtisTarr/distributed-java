package question1;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This module contains the application logic of an echo server which uses a
 * stream socket for interprocess communication. A command-line argument is
 * required to specify the server port.
 */
class IterativeServer {

    private static final String RETURN_MESSAGE = "/";
    private static final String END_MESSAGE = ".";
    private static final int PORT = 7000;

    public static void main(String[] args) {
        try {
            List<Integer> ints = new ArrayList<>();
            ServerSocket connectionSocket = new ServerSocket(PORT);
            System.out.println("Iterative server ready.");
            while (true) {
                System.out.println("Waiting for a connection.");
                StreamSocket dataSocket = new StreamSocket(connectionSocket.accept());
                System.out.println("connection accepted");
                boolean done = false;
                while (!done) {
                    String message = dataSocket.receiveMessage();
                    System.out.println("message received: " + message);
                    if ((message.trim()).equals(END_MESSAGE)) {
                        System.out.println("Session over.");
                        dataSocket.close();
                        done = true;
                    } else if ((message.trim()).equals(RETURN_MESSAGE)) {
                        Collections.sort(ints);
                        dataSocket.sendMessage(ints.toString());
                    } else {
                        try {
                            int result = Integer.parseInt(message);
                            ints.add(result);
                        } catch (NumberFormatException ex) {
                            System.out.println("Not an int");
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }
}
