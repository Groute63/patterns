package main.lab3.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayCountingStrategy implements CountingStrategy {
    @Override
    public Map<Integer, Integer> countOccurrences(int[] array) {
        int max = Arrays.stream(array).max().orElse(0);
        int min = Arrays.stream(array).min().orElse(0);
        int range = max - min + 1;
        int[] counts = new int[range];
        for (int num : array) {
            counts[num - min]++;
        }
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                result.put(i + min, counts[i]);
            }
        }
        return result;
    }
}
