package main.model.validator;

import main.model.exception.ModelPriceOutOfBoundsException;

import java.math.BigDecimal;

public class PriceValidator {
    public void validate(BigDecimal price){
        if(price.doubleValue() < 0){
            throw new ModelPriceOutOfBoundsException();
        }
    }
}
