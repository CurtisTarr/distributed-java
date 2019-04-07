package question2;

import java.util.*;

/**
 * Implementation of ICalculator
 */
class Calculator implements ICalculator {

    /**
     * Calculates the mean of a list of ints
     *
     * @param numbers a list of ints
     * @return the mean of numbers
     */
    public int calcMean(List<Integer> numbers) {
        if (numbers.size() == 0) {
            return 0;
        }

        int sum = 0;
        for (int i : numbers) {
            sum += i;
        }
        return sum / numbers.size();
    }

    /**
     * Calculates the mode(s) of a list of ints
     *
     * @param numbers a list of ints
     * @return the mode(s) of numbers
     */
    public List calcMode(List<Integer> numbers) {
        if (numbers.size() == 0) {
            return numbers;
        }

        // turn the list into a map of each unique number and how many times it occurs
        Map<Integer, Integer> dictionary = new HashMap<>();
        for (int i : numbers) {
            if (dictionary.containsKey(i)) {
                dictionary.put(i, dictionary.get(i) + 1);
            } else {
                dictionary.put(i, 1);
            }
        }

        // find the number with the most occurrences
        List<Integer> mode = new ArrayList<>();
        int modeOccurrences = 0;
        for (Map.Entry<Integer, Integer> map : dictionary.entrySet()) {
            if (map.getValue() > modeOccurrences) {
                mode.clear();
                mode.add(map.getKey());
                modeOccurrences = map.getValue();
            } else if (map.getValue() == modeOccurrences) {
                mode.add(map.getKey());
            }
        }
        return mode;
    }

    /**
     * Calculates the median of a list of ints
     *
     * @param numbers a list of ints
     * @return the median of numbers
     */
    public int calcMedian(List<Integer> numbers) {
        if (numbers.size() == 0) {
            return 0;
        }

        Collections.sort(numbers);
        return numbers.get(numbers.size() / 2);
    }

    /**
     * Sorts a list of ints in ascending order
     *
     * @param numbers a list of ints
     * @return numbers sorted in ascending order
     */
    public List sortAsc(List<Integer> numbers) {
        Collections.sort(numbers);
        return numbers;
    }
}
