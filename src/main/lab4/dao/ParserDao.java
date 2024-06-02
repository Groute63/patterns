package main.lab4.dao;

import java.util.List;

public interface ParserDao<T> {
    void write(String filename, List<T> objects);
    List<T> readAll(String filename);
    boolean isValidFile(String fileType);
}
