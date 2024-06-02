package main.lab4.dao;

public interface ParserDaoFactory<T> {
    ParserDao<T> createDao();
}