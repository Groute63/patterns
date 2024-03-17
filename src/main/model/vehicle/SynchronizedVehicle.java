package main.model.vehicle;

import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;

import java.math.BigDecimal;

public class SynchronizedVehicle implements Vehicle {
    private volatile Vehicle vehicle;

    public SynchronizedVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public synchronized String getBrand() {
        return vehicle.getBrand();
    }

    @Override
    public synchronized void setBrand(String mark) {
        vehicle.setBrand(mark);
    }

    @Override
    public synchronized Integer getModelCount() {
        return vehicle.getModelCount();
    }

    @Override
    public synchronized String[] getModelNames() {
        return vehicle.getModelNames();
    }

    @Override
    public synchronized BigDecimal getModelPrice(String name) throws NoSuchModelNameException {
        return vehicle.getModelPrice(name);
    }

    @Override
    public synchronized void setModelName(String newName, String oldName) throws NoSuchModelNameException, DuplicateModelNameException {
        vehicle.setModelName(oldName,newName);
    }

    @Override
    public synchronized void setModelPrice(String name, BigDecimal newPrice) throws NoSuchModelNameException {
        vehicle.setModelPrice(name, newPrice);
    }

    @Override
    public synchronized BigDecimal[] getModelPrices() {
        return vehicle.getModelPrices();
    }

    @Override
    public synchronized void addModel(String modelName, BigDecimal price) throws DuplicateModelNameException {
        vehicle.addModel(modelName, price);
    }

    @Override
    public synchronized void deleteModel(String modelName) throws NoSuchModelNameException {
        vehicle.deleteModel(modelName);
    }

    @Override
    public synchronized Vehicle clone() {
        return vehicle.clone();
    }
}
