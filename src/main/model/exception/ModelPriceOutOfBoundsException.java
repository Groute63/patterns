package main.model.exception;

public class ModelPriceOutOfBoundsException extends RuntimeException{
    public ModelPriceOutOfBoundsException() {
        super("Недопустимая цена модели");
    }
}
