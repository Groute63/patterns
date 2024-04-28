package main.lab3.visitor;

import main.model.vehicle.Bike;
import main.model.vehicle.Car;

public interface Visitor {
    void visit(Car car);
    void visit(Bike bike);
}
