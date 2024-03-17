package main.lab.factorymethod;

import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;
import main.model.vehicle.Vehicle;
import main.model.vehicle.VehicleUtil;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, CloneNotSupportedException {
        Vehicle v1 = VehicleUtil.createInstance("b1", 10);
        VehicleUtil.setTransportFactory(FactoryType.BIKE_FACTORY);
        Vehicle v2 = VehicleUtil.createInstance("b2",5);

        Vehicle v3 = v1.clone();
        v3.setModelName("model2", "newModel2");
        v3.deleteModel("model1");
        VehicleUtil.printModelNames(v3);
        VehicleUtil.printModelNames(v1);


        Vehicle v2c = v2.clone();
        v2c.setModelName("model2", "newModel2");
        v2c.deleteModel("model1");
        VehicleUtil.printModelNames(v2);
        VehicleUtil.printModelNames(v2c);
    }
}