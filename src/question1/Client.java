package question1;

import java.io.*;

/**
 * Client that connects to a server and sends and receives messages
 */
class Client {

    private static final String HOST = "localhost";
    private static final String PORT = "7000";
    private static final String END_MESSAGE = ".";

    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        try {
            // Create new ClientHelper to manage the connection to the server
            ClientHelper helper = new ClientHelper(HOST, PORT);
            String message;
            boolean running = true;
            while (running) {
                System.out.println("Enter an integer to be sent to the server, 'sort' to get the integers sorted, or '.' to quit");
                message = bufferedReader.readLine();

                if ((message.trim().toLowerCase().equals(END_MESSAGE))) {
                    running = false;
                    helper.done();
                } else {
                    System.out.println(helper.sendMessage(message));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

