package main.model.vehicle;

import main.lab3.visitor.Visitor;
import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Vehicle extends Cloneable, Serializable {
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
    void accept(Visitor visitor);

}
