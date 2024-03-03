package main.lab.factorymethod;

import main.model.vehicle.Car;
import main.model.vehicle.Vehicle;

public class CarFactory implements TransportFactory {
    @Override
    public Vehicle createInstance(String brand, Integer modelCount) {
        return new Car(brand, modelCount);
    }
}
