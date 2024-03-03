package main.lab.factorymethod;

import main.model.exception.DuplicateModelNameException;
import main.model.vehicle.Vehicle;

public interface TransportFactory {

    Vehicle createInstance(String brand, Integer modelCount) throws DuplicateModelNameException;
}
