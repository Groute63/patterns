package main.model.exception;

public class NoSuchModelNameException extends Exception{
    public NoSuchModelNameException() {
        super("Модель с указанным именем не найдена");
    }
}
