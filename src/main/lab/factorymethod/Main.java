package main.lab.factorymethod;

import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;
import main.model.vehicle.Vehicle;
import main.model.vehicle.VehicleUtil;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {
        Vehicle v1 = VehicleUtil.createInstance("b1", 10);
        VehicleUtil.setTransportFactory(FactoryType.BIKE_FACTORY);
        Vehicle v2 = VehicleUtil.createInstance("b2",5);

        Vehicle v3 = v1.clone();
        v3.setBrand("b3");
        v3.setModelName("model2", "newModel2");
        v3.deleteModel("model1");
        VehicleUtil.printModelNames(v3);
        VehicleUtil.printModelNames(v1);

        Vehicle v4 = v2.clone();
        v4.setBrand("b3");
        v4.setModelName("model2", "newModel2");
        v4.deleteModel("model1");
        VehicleUtil.printModelNames(v4);
        VehicleUtil.printModelNames(v2);

    }
}