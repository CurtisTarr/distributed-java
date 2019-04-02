package question1;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ConcurrentServer extends Thread {

    private static final String SORT_MESSAGE = "sort";
    private static final String END_MESSAGE = ".";
    private static final int PORT = 7000;

    private StreamSocket socket;
    private String name;

    private ConcurrentServer(StreamSocket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("New client connected: " + this.name);
            List<Integer> ints = new ArrayList<>();
            boolean running = true;
            while (running) {
                String message = socket.receiveMessage();
                System.out.println(this.name + " message received: " + message);

                switch ((message.trim().toLowerCase())) {
                    case END_MESSAGE:
                        System.out.println(this.name + " Session over");
                        socket.close();
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String args[]) {
        int clientCount = 0;
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("Concurrent server ready");
            while (true) {
                System.out.println("Waiting for connection");
                StreamSocket dataSocket = new StreamSocket(server.accept());
                System.out.println("Accepted connection from " + dataSocket.getInetAddress());
                ConcurrentServer clientHandler = new ConcurrentServer(dataSocket, "[C" + Integer.toString(clientCount) + "]");
                clientHandler.start();
                clientCount += 1;
            }
        } catch (IOException ex) {
            System.err.println("Unable to connect on specified port");
        }
    }
}
