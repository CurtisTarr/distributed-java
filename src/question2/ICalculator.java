package question2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


interface ICalculator extends Remote {

    int calcMean(List<Integer> numbers) throws RemoteException;

    int calcMode(List<Integer> numbers) throws RemoteException;

    int calcMedian(List<Integer> numbers) throws RemoteException;

    String sortAsc(List<Integer> numbers) throws RemoteException;
}
