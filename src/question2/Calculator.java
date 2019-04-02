package question2;

import java.util.*;

class Calculator implements ICalculator {

    public int calcMean(List<Integer> numbers) {
        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        return sum / numbers.size();
    }

    public int calcMode(List<Integer> numbers) {
        Map<Integer, Integer> dictionary = new HashMap<>();
        for (int i : numbers) {
            if (dictionary.containsKey(i)) {
                dictionary.put(i, dictionary.get(i) + 1);
            } else {
                dictionary.put(i, 1);
            }
        }

        int mode = 0;
        int maxOccurrences = 0;
        for (Map.Entry<Integer, Integer> map : dictionary.entrySet()) {
            if (map.getValue() > maxOccurrences) {
                mode = map.getKey();
                maxOccurrences = map.getValue();
            }
        }
        return mode;
    }

    public int calcMedian(List<Integer> numbers) {
        Collections.sort(numbers);
        return numbers.get(numbers.size() / 2);
    }

    public String sortAsc(List<Integer> numbers) {
        Collections.sort(numbers);
        return numbers.toString();
    }
}
