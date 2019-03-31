package question2;

import java.rmi.RemoteException;
import java.util.List;

class Calculator implements ICalculator {

    @Override
    public float calcMean(List<Integer> numbers) throws RemoteException {
        return 0;
    }

    @Override
    public float calcMode(List<Integer> numbers) throws RemoteException {
        return 1;
    }

    @Override
    public float calcMedian(List<Integer> numbers) throws RemoteException {
        return 2;
    }

    @Override
    public String sortAsc(List<Integer> numbers) throws RemoteException {
        return numbers.toString();
    }
}
