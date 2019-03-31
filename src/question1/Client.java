package question1;

import java.io.*;

class Client {

    private static final String RETURN_MESSAGE = "/";
    private static final String END_MESSAGE = ".";
    private static final String HOST = "localhost";
    private static final String PORT = "7000";

    public static void main(String[] args) {
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
            ClientHelper helper = new ClientHelper(HOST, PORT);
            String message;
            boolean done = false;
            while (!done) {
                System.out.println("Enter an integer to be sent to the server, '/' to get the integers ordered, or '.' to quit");
                message = br.readLine();
                if ((message.trim().toLowerCase()).equals(END_MESSAGE)) {
                    done = true;
                    helper.done();
                } else if ((message.trim().toLowerCase()).equals(RETURN_MESSAGE)) {
                    System.out.println(helper.getOrderedInts());
                } else {
                    helper.sendInt(message);
                }
            }
        }
        catch (Exception ex) {
        }
    }
}

