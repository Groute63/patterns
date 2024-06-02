package main.lab4.dao;

import main.model.vehicle.Vehicle;

public class VehicleByteParserFactory implements ParserDaoFactory<Vehicle> {
    @Override
    public ParserDao<Vehicle> createDao() {
        return new VehicleByteParser();
    }
}
