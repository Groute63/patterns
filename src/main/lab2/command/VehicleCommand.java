package main.lab2.command;

import main.model.vehicle.Vehicle;

import java.io.OutputStreamWriter;

public interface VehicleCommand {
    void execute(Vehicle vehicle, OutputStreamWriter outputStream);
}
