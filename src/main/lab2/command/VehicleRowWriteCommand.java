package main.lab2.command;

import main.model.exception.NoSuchModelNameException;
import main.model.vehicle.Vehicle;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.StringJoiner;

public class VehicleRowWriteCommand implements VehicleCommand {
    @Override
    public void execute(Vehicle vehicle, OutputStreamWriter outputStream) {
        try {
            for (String name : vehicle.getModelNames()) {
                String model = new StringJoiner(", ","[", "] ")
                        .add("Название = '" + name + "'")
                        .add("Цена = " + vehicle.getModelPrice(name))
                        .toString();
                outputStream.write(model);
                outputStream.flush();
            }
        } catch (IOException | NoSuchModelNameException ex) {
            throw new RuntimeException(ex);
        }
    }
}
