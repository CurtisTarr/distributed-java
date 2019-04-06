package question2;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/**
 * Server that creates a Calculator and then makes it available as a Remote Object
 */
public class Server {

    private static final int PORT = 7000;

    public static void main(String args[]) {

        try {
            // Create a Calculator and make it available on PORT
            Calculator calculator = new Calculator();
            ICalculator calculatorStub = (ICalculator) UnicastRemoteObject.exportObject(calculator, PORT);
            // Create a Registry on PORT and bind the Calculator to the name calculator
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("calculator", calculatorStub);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("RMI server ready");
    }
}
