package main.model.vehicle;

import main.lab3.visitor.Visitor;
import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;
import main.model.validator.PriceValidator;

import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.StringJoiner;

public class Car implements Vehicle, Iterable<Car.Model> {
    private transient PriceValidator priceValidator = new PriceValidator();
    private String brand;
    private Model[] models;

    public Car(String brand, Integer modelCount) {
        this.brand = brand;
        this.models = new Model[modelCount];
        fillModelList();
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String[] getModelNames() {
        String[] modelNames = new String[models.length];
        for (int i = 0; i < models.length; i++) {
            modelNames[i] = models[i].getName();
        }
        return modelNames;
    }

    @Override
    public BigDecimal[] getModelPrices() {
        BigDecimal[] modelPrices = new BigDecimal[models.length];
        for (int i = 0; i < models.length; i++) {
            modelPrices[i] = models[i].getPrice();
        }
        return modelPrices;
    }

    @Override
    public void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if (!prevName.equals(newName)) {
            validateDuplicateModel(newName);
            findModelOrThrow(prevName).setName(newName);
        }
    }

    @Override
    public BigDecimal getModelPrice(String modelName) throws NoSuchModelNameException {
        return findModelOrThrow(modelName).getPrice();
    }

    @Override
    public void setModelPrice(String modelName, BigDecimal newPrice) throws NoSuchModelNameException {
        priceValidator.validate(newPrice);
        findModelOrThrow(modelName).setPrice(newPrice);
    }

    @Override
    public void addModel(String modelName, BigDecimal modelPrice) throws DuplicateModelNameException {
        validateDuplicateModel(modelName);
        priceValidator.validate(modelPrice);
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = new Model(modelName, modelPrice);
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        for (int i = 0; i < models.length; i++) {
            if (models[i].getName().equals(modelName)) {
                Model[] copyModels = Arrays.copyOf(models, models.length - 1);
                if (i != models.length - 1) {
                    System.arraycopy(models, i + 1, copyModels, i, copyModels.length - i);
                }
                models = copyModels;
                return;
            }
        }
        throw new NoSuchModelNameException();
    }


    @Override
    public Integer getModelCount() {
        return models.length;
    }

    private Model findModelOrThrow(String modelName) throws NoSuchModelNameException {
        Model model = findModelOrReturnNull(modelName);
        if (model == null) {
            throw new NoSuchModelNameException();
        }
        return model;
    }

    private Model findModelOrReturnNull(String modelName) {
        for (Model model : models) {
            if (model.getName().equals(modelName)) {
                return model;
            }
        }
        return null;
    }

    private void validateDuplicateModel(String modelName) throws DuplicateModelNameException {
        if (findModelOrReturnNull(modelName) != null) {
            throw new DuplicateModelNameException();
        }
    }

    private void fillModelList() {
        for (int i = 0; i < models.length; i++) {
            if (models[i] == null) {
                models[i] = new Model("model" + i, new BigDecimal(new Random().nextInt(0, 100)));
            }
        }
    }

    private void setModels(Model[] models) {
        this.models = models;
    }

    @Override
    public Car clone() {
        Car clonedCars = null;
        try {
            clonedCars = (Car) super.clone();
            Model[] sampleModels = this.models;
            Model[] clonedModels = new Model[sampleModels.length];
            for (int i = 0; i < sampleModels.length; i++) {
                clonedModels[i] = sampleModels[i].clone();
            }
            clonedCars.setModels(clonedModels);
            return clonedCars;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public class Model implements Cloneable, Serializable {
        private String name;
        private BigDecimal price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Model(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Model.class.getSimpleName() + "[", "]")
                    .add("Название = '" + name + "'")
                    .add("Цена = " + price)
                    .toString();
        }
    }

    @Override
    public Iterator<Model> iterator() {
        return new CarIterator(models);
    }

    static class CarIterator implements Iterator<Model> {

        int current = 0;
        Model[] models;

        protected CarIterator(Model[] models) {
            this.models = models;
        }

        @Override
        public boolean hasNext() {
            return current < models.length;
        }

        @Override
        public Model next() {
            return models[current++];
        }
    }

    public Memento createMemento() {
        Memento memento = new Memento();
        memento.setCar(this);
        return memento;
    }

    public void setMemento(Memento memento)  {
        Car state = memento.getCar();
        this.brand = state.brand;
        this.models = state.models;
    }

    public static class Memento {
        private final ByteArrayOutputStream memory = new ByteArrayOutputStream();

        public void setCar(Car car) {
            this.memory.reset();
            try(ObjectOutputStream oIS = new ObjectOutputStream(memory)) {
                oIS.writeObject(car);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        public Car getCar() {
            byte[] byteArray = memory.toByteArray();
            try (ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(byteArray);
                 ObjectInputStream objectInput = new ObjectInputStream(byteArrayInput)) {
                return (Car) objectInput.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex.getMessage());
            }
        }
    }
}
