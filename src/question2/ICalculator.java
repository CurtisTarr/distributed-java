package question2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Calculator interface
 */
interface ICalculator extends Remote {

    int calcMean(List<Integer> numbers) throws RemoteException;

    List calcMode(List<Integer> numbers) throws RemoteException;

    int calcMedian(List<Integer> numbers) throws RemoteException;

    List sortAsc(List<Integer> numbers) throws RemoteException;
}
