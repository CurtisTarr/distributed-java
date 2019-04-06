package question1;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IterativeServer that will hold and sort ints for one client at a time
 */
class IterativeServer {

    private static final String SORT_MESSAGE = "sort";
    private static final String END_MESSAGE = ".";
    private static final int PORT = 7000;

    public static void main(String[] args) {
        try {
            // start server
            List<Integer> ints = new ArrayList<>();
            ServerSocket connectionSocket = new ServerSocket(PORT);
            System.out.println("Iterative server ready");
            while (true) {
                // wait for a client to connect
                System.out.println("Waiting for a connection");
                StreamSocket socket = new StreamSocket(connectionSocket.accept());
                socket.sendMessage("Connection accepted");
                System.out.println("Connection accepted");
                boolean running = true;
                while (running) {
                    // wait for message
                    String message = socket.receiveMessage();
                    System.out.println("Message received: " + message);

                    // determine what to do with the message
                    switch ((message.trim().toLowerCase())) {
                        case END_MESSAGE:
                            System.out.println("Session over");
                            socket.close();
                            ints.clear();
                            running = false;
                            break;
                        case SORT_MESSAGE:
                            Collections.sort(ints);
                            socket.sendMessage("Numbers sorted: " + ints.toString());
                            break;
                        default:
                            try {
                                int result = Integer.parseInt(message);
                                ints.add(result);
                                socket.sendMessage("Current numbers: " + ints.toString());
                            } catch (NumberFormatException ex) {
                                socket.sendMessage("Not an int or command");
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
