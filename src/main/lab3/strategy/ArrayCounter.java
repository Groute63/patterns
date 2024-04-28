package main.lab3.strategy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class ArrayCounter {
    private CountingStrategy strategy;

    public ArrayCounter(CountingStrategy strategy) {
        this.strategy = strategy;
    }

    public Map<Integer, Integer> countOccurrences(String fileName) throws IOException {
        return strategy.countOccurrences(readArrayFromFile(fileName));
    }

    private int[] readArrayFromFile(String fileName) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (int[]) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
