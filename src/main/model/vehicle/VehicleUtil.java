package main.model.vehicle;

import main.lab.factorymethod.BikeFactory;
import main.lab.factorymethod.CarFactory;
import main.lab.factorymethod.FactoryType;
import main.lab.factorymethod.TransportFactory;
import main.lab2.decorator.SynchronizedVehicle;
import main.lab3.command.VehicleCommand;
import main.lab3.command.VehicleRowWriteCommand;
import main.lab4.dao.ParserDao;
import main.lab4.dao.ParserDaoFactory;
import main.lab4.dao.VehicleTextParserFactory;
import main.model.exception.DuplicateModelNameException;

import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VehicleUtil{
    private static TransportFactory activeFactory = new CarFactory();
    private static TransportFactory carFactory = new CarFactory();
    private static TransportFactory bikeFactory = new BikeFactory();
    private static VehicleCommand vehicleCommand = new VehicleRowWriteCommand();

    private static ParserDaoFactory<Vehicle> daoFactory = new VehicleTextParserFactory();


    public static void setTransportFactory(FactoryType factory){
        switch (factory){
            case CAR_FACTORY -> {
                activeFactory = carFactory;
            }
            case BIKE_FACTORY -> {
                activeFactory = bikeFactory;
            }
        }
    }

    public static ParserDao<Vehicle> createDao() {
        return daoFactory.createDao();
    }

    public static void setDaoFactory(ParserDaoFactory<Vehicle> daoFactory) {
        VehicleUtil.daoFactory = daoFactory;
    }

    public static void setVehicleCommand(VehicleCommand vehicleCommand) {
        VehicleUtil.vehicleCommand = vehicleCommand;
    }

    public static void printInStream(Vehicle vehicle, OutputStreamWriter outputStream) {
        vehicleCommand.execute(vehicle, outputStream);
    }

    public static Vehicle createInstance(String brand, Integer modelCount) throws DuplicateModelNameException {
        return activeFactory.createInstance(brand,modelCount);
    }

    public static BigDecimal getAverageModelPrice(Vehicle vehicle){
        BigDecimal[] prices = vehicle.getModelPrices();
        BigDecimal sum = BigDecimal.ZERO;
        for(BigDecimal price : prices){
            sum = sum.add(price);
        }
        return sum.divide(BigDecimal.valueOf(prices.length), RoundingMode.HALF_UP);
    }

    public static void printModelNames(Vehicle vehicle){
        String[] modelNames = vehicle.getModelNames();
        System.out.printf("Названия моделей брэнда %s:\n", vehicle.getBrand());
        for(String name : modelNames){
            System.out.println(name);
        }
    }

    public static void printModelPrices(Vehicle vehicle){
        BigDecimal[] modelPrices = vehicle.getModelPrices();
        System.out.printf("Цены на модели брэнда %s:\n", vehicle.getBrand());
        for(BigDecimal price : modelPrices){
            System.out.println(price);
        }
    }

    public static SynchronizedVehicle synchronizedVehicle(Vehicle vehicle) {
        return new SynchronizedVehicle(vehicle);
    }
}
