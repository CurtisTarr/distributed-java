package question2;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String args[]) {

        try {
            Calculator calculator = new Calculator();
            ICalculator calculatorStub = (ICalculator) UnicastRemoteObject.exportObject(calculator, 7000);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Calculator", calculatorStub);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("Server started waiting ...");
    }

}
