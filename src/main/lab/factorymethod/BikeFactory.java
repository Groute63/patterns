package main.lab.factorymethod;

import main.model.exception.DuplicateModelNameException;
import main.model.vehicle.Bike;
import main.model.vehicle.Vehicle;

public class BikeFactory implements TransportFactory {
    @Override
    public Vehicle createInstance(String brand, Integer modelCount) throws DuplicateModelNameException {
        return new Bike(brand, modelCount);
    }
}
