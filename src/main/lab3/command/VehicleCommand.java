package main.lab3.command;

import main.model.vehicle.Vehicle;

import java.io.OutputStreamWriter;

public interface VehicleCommand {
    void execute(Vehicle vehicle, OutputStreamWriter outputStream);
}
