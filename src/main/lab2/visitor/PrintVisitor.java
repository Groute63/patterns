package main.lab2.visitor;

import main.lab2.command.VehicleColumnWriteCommand;
import main.lab2.command.VehicleRowWriteCommand;
import main.model.vehicle.Bike;
import main.model.vehicle.Car;
import main.model.vehicle.VehicleUtil;

import java.io.OutputStreamWriter;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        VehicleUtil.setVehicleCommand(new VehicleRowWriteCommand());
        VehicleUtil.printInStream(car, new OutputStreamWriter(System.out));
    }

    @Override
    public void visit(Bike bike) {
        VehicleUtil.setVehicleCommand(new VehicleColumnWriteCommand());
        VehicleUtil.printInStream(bike, new OutputStreamWriter(System.out));
    }
}
