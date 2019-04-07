package question2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 * Client that connects to a server hosted Calculator
 */
public class Client {

    private static final String HOST = "localhost";
    private static final int PORT = 7000;
    private static final String END_MESSAGE = ".";
    private static final String MEAN = "mean";
    private static final String MODE = "mode";
    private static final String MEDIAN = "median";
    private static final String SORT = "sort";

    public static void main(String[] args) {

        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStream);
        List<Integer> numbers = new ArrayList<>();

        try {
            // try to get a reference to the registry and stub the Calculator that should be there
            Registry registry = LocateRegistry.getRegistry(HOST, PORT);
            ICalculator calculatorStub = (ICalculator) registry.lookup("calculator");
            String command;

            boolean running = true;
            while (running) {
                System.out.println("Enter an integer to add to the list, 'mean', 'mode', 'median', 'sort' or '.' to end the session");
                command = bufferedReader.readLine();

                // determine what command to perform
                switch (command.trim().toLowerCase()) {
                    case END_MESSAGE:
                        running = false;
                        break;
                    case MEAN:
                        System.out.println("Mean: " + calculatorStub.calcMean(numbers));
                        break;
                    case MODE:
                        System.out.println("Mode(s): " + calculatorStub.calcMode(numbers).toString());
                        break;
                    case MEDIAN:
                        System.out.println("Median: " + calculatorStub.calcMedian(numbers));
                        break;
                    case SORT:
                        System.out.println("Sorted: " + calculatorStub.sortAsc(numbers).toString());
                        break;
                    default:
                        try {
                            int result = Integer.parseInt(command);
                            numbers.add(result);
                            System.out.println(numbers.toString());
                        } catch (NumberFormatException ex) {
                            System.out.println("Not an int or command");
                        }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
