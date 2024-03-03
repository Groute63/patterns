package main.model.vehicle;

import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;

import java.math.BigDecimal;

public interface Vehicle extends Cloneable{
    String getBrand();

    void setBrand(String brand);

    String[] getModelNames();

    BigDecimal[] getModelPrices();

    void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

    BigDecimal getModelPrice(String modelName) throws NoSuchModelNameException;

    void setModelPrice(String modelName, BigDecimal newPrice) throws NoSuchModelNameException;

    void addModel(String modelName, BigDecimal modelPrice) throws DuplicateModelNameException;

    void deleteModel(String modelName) throws NoSuchModelNameException;

    Integer getModelCount();
    Vehicle clone();
}
