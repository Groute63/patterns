package main.lab3.strategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapCountingStrategy implements CountingStrategy {
    @Override
    public Map<Integer, Integer> countOccurrences(int[] array) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : array) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        return counts;
    }
}
