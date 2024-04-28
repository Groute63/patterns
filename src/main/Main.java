package main;

import main.lab2.chain.VehicleColumnWriter;
import main.lab2.chain.VehicleRowWriter;
import main.lab2.chain.VehicleWriter;
import main.lab2.command.VehicleColumnWriteCommand;
import main.lab2.command.VehicleCommand;
import main.lab2.strategy.ArrayCounter;
import main.lab2.strategy.ArrayCountingStrategy;
import main.lab2.strategy.HashMapCountingStrategy;
import main.lab2.visitor.PrintVisitor;
import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;
import main.model.exception.VehicleWriterException;
import main.model.vehicle.Bike;
import main.model.vehicle.Car;
import main.model.vehicle.Vehicle;
import main.model.vehicle.VehicleUtil;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws NoSuchModelNameException, InterruptedException, IOException, DuplicateModelNameException, VehicleWriterException {
        Vehicle v = new Bike("brand", 10);
        Vehicle v2 = new Bike("brand2", 2);


        System.out.println("---Chain of Responsibility and Command---");
        VehicleWriter writer = (VehicleWriter.setChain(
                new VehicleRowWriter("src/resources/testRow.txt"),
                new VehicleColumnWriter("src/resources/testColumn.txt")
        ));
        writer.writeToFile(v);
        writer.writeToFile(v2);


        System.out.println("---Command---");
        VehicleUtil.printInStream(v, new FileWriter("src/resources/commandTest.txt"));
        VehicleUtil.setVehicleCommand(new VehicleColumnWriteCommand());
        VehicleUtil.printInStream(v, new FileWriter("src/resources/commandTest2.txt"));


        System.out.println("---Iterator---");
        Car car = new Car("TestIterator", 4);
        for (Car.Model m : car) {
            System.out.println(m);
        }


        System.out.println("-----Memento------");
        Car.Memento memento = car.createMemento();
        try {
            car.deleteModel("model1");
        } catch (NoSuchModelNameException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Удаление модели");
        car.forEach(System.out::println);

        System.out.println("Восстановление через снимок");
        car.setMemento(memento);
        car.forEach(System.out::println);


        System.out.println("-----Strategy------");
        int[] array = {1, 2, 3, 3, 5, 2, 3, 4, 5, 6}; // Пример массива

        try (FileOutputStream fos = new FileOutputStream("src/resources/tt.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(array);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (args.length != 0) {
            String fileName = args[0];
            try {
                ArrayCounter counter = new ArrayCounter(new HashMapCountingStrategy());
                Map<Integer, Integer> counts = counter.countOccurrences(fileName);
                System.out.println("HashMap: " + counts);

                counter = new ArrayCounter(new ArrayCountingStrategy());
                counts = counter.countOccurrences(fileName);
                System.out.println("Array: " + counts);
            } catch (IOException e) {
                System.err.println("Error " + e.getMessage());
            }
        }


        System.out.println("-----Visitor------");
        PrintVisitor prw = new PrintVisitor();
        System.out.println("Car");
        v.accept(prw);
        System.out.println("-----Visitor------");
        System.out.println("Bike");
        v2.accept(prw);

    }
}