package main.lab3.chain;

import main.lab3.command.VehicleRowWriteCommand;
import main.model.exception.VehicleWriterException;
import main.model.vehicle.Vehicle;

import java.io.FileWriter;
import java.io.IOException;

public class VehicleRowWriter extends VehicleWriter {
    private final VehicleRowWriteCommand vehicleRowWriteCommand = new VehicleRowWriteCommand();
    private final int VEHICLE_COUNT = 3 ;

    public VehicleRowWriter(String path) {
        super(path);
    }


    @Override
    public boolean writeToFile(Vehicle vehicle) throws VehicleWriterException {
        if (vehicle.getModelCount() <= VEHICLE_COUNT) {
            try (FileWriter out = new FileWriter(path)) {
                vehicleRowWriteCommand.execute(vehicle, out);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return true;
        }
        return executeNext(vehicle);
    }
}
