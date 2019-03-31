package question2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


interface ICalculator extends Remote {

    public float calcMean(List<Integer> numbers) throws RemoteException;

    public float calcMode(List<Integer> numbers) throws RemoteException;

    public float calcMedian(List<Integer> numbers) throws RemoteException;

    public String sortAsc(List<Integer> numbers) throws RemoteException;
}
