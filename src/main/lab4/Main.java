package main.lab4;

import main.lab4.dao.ParserDao;
import main.lab4.dao.VehicleByteParserFactory;
import main.model.exception.DuplicateModelNameException;
import main.model.vehicle.Bike;
import main.model.vehicle.Car;
import main.model.vehicle.Vehicle;
import main.model.vehicle.VehicleUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) throws DuplicateModelNameException {
        System.out.println("-----DAO------");
        Vehicle vehicle = new Car("CarDao", 5);
        System.out.println(vehicle);

        ParserDao<Vehicle> dao = VehicleUtil.createDao();
        dao.write("src/resources/daoTest.txt", List.of(vehicle));
        List<Vehicle> read = dao.readAll("src/resources/daoTest.txt");

        System.out.println("--------------------------------");
        System.out.println(read);

        vehicle = new Bike("BikeDao", 4);
        System.out.println(vehicle);

        VehicleUtil.setDaoFactory(new VehicleByteParserFactory());
        dao = VehicleUtil.createDao();
        dao.write("src/resources/daoTestBin.txt", List.of(vehicle));
        List<Vehicle> read2 = dao.readAll("src/resources/daoTestBin.txt");

        System.out.println("--------------------------------");
        System.out.println(read2);
    }
}
