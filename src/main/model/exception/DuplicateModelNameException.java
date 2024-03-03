package main.model.exception;

public class DuplicateModelNameException extends Exception{
    public DuplicateModelNameException() {
        super("Модель с указанным именем уже существует");
    }
}
