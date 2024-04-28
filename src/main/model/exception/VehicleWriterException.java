package main.model.exception;

public class VehicleWriterException extends Exception {
    public VehicleWriterException(String message) {
        super("Обработчик не найден " + message);
    }
}
