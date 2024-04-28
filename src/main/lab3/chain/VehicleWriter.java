package main.lab3.chain;

import main.model.exception.VehicleWriterException;
import main.model.vehicle.Vehicle;

public abstract class VehicleWriter {
    protected final String path;
    private VehicleWriter next;

    public VehicleWriter(String path) {
        this.path = path;
    }

    public static VehicleWriter setChain(VehicleWriter first, VehicleWriter... chain) {
        VehicleWriter head = first;
        for (VehicleWriter nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public void setNext(VehicleWriter... chain) {
        setChain(this,chain);
    }

    public abstract boolean writeToFile(Vehicle vehicle) throws VehicleWriterException;

    protected boolean executeNext(Vehicle vehicle) throws VehicleWriterException {
        if (next == null) {
            throw new VehicleWriterException(vehicle.getBrand());
        }
        return next.writeToFile(vehicle);
    }
}
