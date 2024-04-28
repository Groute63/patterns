package main.lab2;

import main.lab2.adapter.StringArrayToOutputStreamAdapter;
import main.model.exception.DuplicateModelNameException;
import main.model.vehicle.Bike;
import main.model.vehicle.Vehicle;
import main.model.vehicle.VehicleUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws InterruptedException, DuplicateModelNameException {
        //printAdapterOutputStream();
        synchronizedVehicleTest();
    }

    private static void printAdapterOutputStream() {
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            StringArrayToOutputStreamAdapter stringAdapter1 = new StringArrayToOutputStreamAdapter(baos);
            String[] string = new String[]{"test", "test2", "test3"};
            stringAdapter1.write(string);
            System.out.println(baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void synchronizedVehicleTest() throws InterruptedException, DuplicateModelNameException {
        Vehicle v1 = new Bike("b1", 30);
        Vehicle synchronizedCar = VehicleUtil.synchronizedVehicle(v1);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    synchronizedCar.addModel("ThreadFirst" + i, BigDecimal.valueOf(100));
                } catch (DuplicateModelNameException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                try {
                    synchronizedCar.addModel("ThreadTwo" + i, BigDecimal.valueOf(100));
                } catch (DuplicateModelNameException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        VehicleUtil.printModelNames(v1);
    }
}
