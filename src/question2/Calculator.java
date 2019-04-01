package question2;

import java.rmi.RemoteException;
import java.util.*;

class Calculator implements ICalculator {

    @Override
    public int calcMean(List<Integer> numbers) throws RemoteException {
        int sum = 0;
        for (int i: numbers) {
            sum += i;
        }
        return sum / numbers.size();
    }

    @Override
    public int calcMode(List<Integer> numbers) throws RemoteException {
        Map<Integer, Integer> dictionary = new HashMap<>();
        for (int i: numbers) {
            if (dictionary.containsKey(i)) {
                dictionary.put(i, dictionary.get(i) + 1);
            } else {
                dictionary.put(i, 1);
            }
        }

        int mode = 0;
        int maxOccurrences = 0;
        for (Map.Entry<Integer, Integer> map: dictionary.entrySet()) {
            if (map.getValue() > maxOccurrences) {
                mode = map.getKey();
                maxOccurrences = map.getValue();
            }
        }
        return mode;
    }

    @Override
    public int calcMedian(List<Integer> numbers) throws RemoteException {
        return numbers.get(numbers.size()/2);
    }

    @Override
    public String sortAsc(List<Integer> numbers) throws RemoteException {
        Collections.sort(numbers);
        return numbers.toString();
    }
}
