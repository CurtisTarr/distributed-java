package question1;

import java.io.*;

class Client {

    private static final String HOST = "localhost";
    private static final String PORT = "7000";
    private static final String END_MESSAGE = ".";

    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);

        try {
            ClientHelper helper = new ClientHelper(HOST, PORT);
            String message;
            boolean done = false;
            while (!done) {
                System.out.println("Enter an integer to be sent to the server, 'sort' to get the integers sorted, or '.' to quit");
                message = br.readLine();

                if ((message.trim().toLowerCase().equals(END_MESSAGE))) {
                    done = true;
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

