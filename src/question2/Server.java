package question2;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Server {

    private static final int PORT = 7000;

    public static void main(String args[]) {

        try {
            Calculator calculator = new Calculator();
            ICalculator calculatorStub = (ICalculator) UnicastRemoteObject.exportObject(calculator, PORT);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("calculator", calculatorStub);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("RMI server ready");
    }

}
