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

    private static final String SORT_MESSAGE = "sort";
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
                boolean running = true;
                while (running) {
                    String message = dataSocket.receiveMessage();
                    System.out.println("message received: " + message);

                    switch ((message.trim().toLowerCase())) {
                        case END_MESSAGE:
                            System.out.println("Session over.");
                            dataSocket.close();
                            running = false;
                            break;
                        case SORT_MESSAGE:
                            Collections.sort(ints);
                            dataSocket.sendMessage("Numbers sorted: " + ints.toString());
                            break;
                        default:
                            try {
                                int result = Integer.parseInt(message);
                                ints.add(result);
                                dataSocket.sendMessage("Current numbers: " + ints.toString());
                            } catch (NumberFormatException ex) {
                                dataSocket.sendMessage("Not an int or command");
                            }
                            break;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
