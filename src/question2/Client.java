package question2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static final String HOST = "localhost";
    private static final String END_MESSAGE = ".";
    private static final String MEAN = "mean";
    private static final String MODE = "mode";
    private static final String MEDIAN = "median";
    private static final String SORT = "sort";

    public static void main(String[] args) {

        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        List<Integer> numbers = new ArrayList<>();

        try {
            Registry registry = LocateRegistry.getRegistry(HOST);
            ICalculator calculatorStub = (ICalculator) registry.lookup("Calculator");
            String command = "";

            boolean done = false;
            while (!done) {
                System.out.println("Enter an integer to add to the list, 'mean', 'mode', 'median', 'sort' or '.' to end the session");
                command = br.readLine();
                switch (command.trim().toLowerCase()) {
                    case END_MESSAGE:
                        done = true;
                        break;
                    case MEAN:
                        System.out.println(calculatorStub.calcMean(numbers));
                        break;
                    case MODE:
                        System.out.println(calculatorStub.calcMode(numbers));
                        break;
                    case MEDIAN:
                        System.out.println(calculatorStub.calcMedian(numbers));
                        break;
                    case SORT:
                        System.out.println(calculatorStub.sortAsc(numbers));
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
        } catch (Exception e) {
            System.out.println(e);
        }
        System.exit(0);
    }
}
