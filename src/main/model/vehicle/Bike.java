package main.model.vehicle;

import main.lab3.visitor.Visitor;
import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;
import main.model.validator.PriceValidator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

public class Bike implements Vehicle{
    private transient PriceValidator priceValidator = new PriceValidator();
    private String brand;
    private Model head = new Model();
    private int size = 0;

    {
        head.setPrev(head);
        head.setNext(head);
    }

    public Bike(String brand, Integer modelCount) throws DuplicateModelNameException {
        this.brand = brand;
        for(int i = 0; i < modelCount; i++){
            createModel(new Model("model" + i, new BigDecimal(new Random().nextInt(0,100))));
        }
    }

    @Override
    public String toString() {
        Model p = head.next;
        Model[] models = new Model[size];
        for (int i = 0; i < models.length; i++) {
            models[i] = p;
            p = p.next;
        }
        StringJoiner sj = new StringJoiner(", ", Bike.class.getSimpleName() + "[", "]")
                .add("Марка = '" + brand + "'")
                .add("Линейка моделей (" + size + ") = " + Arrays.toString(models));
        sj.add(Arrays.toString(models));
        return sj.toString();
    }

    @Override
    public String getBrand(){
        return brand;
    }

    @Override
    public void setBrand(String brand){
        this.brand = brand;
    }

    @Override
    public String[] getModelNames(){
        String[] modelNames = new String[size];
        Model model = head.getNext();
        int i = 0;
        while (model != head){
            modelNames[i] = model.getName();
            model = model.getNext();
            i++;
        }
        return modelNames;
    }

    @Override
    public BigDecimal[] getModelPrices(){
        BigDecimal[] modelPrice = new BigDecimal[size];
        Model model = head.getNext();
        int i = 0;
        while (model != head){
            modelPrice[i] = model.getPrice();
            model = model.getNext();
            i++;
        }
        return modelPrice;
    }

    @Override
    public void setModelName(String prevName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
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
        priceValidator.validate(modelPrice);
        validateDuplicateModel(modelName);
        createModel(new Model(modelName, modelPrice));
    }


    private void createModel(Model model) throws DuplicateModelNameException {
        Model lastModel = head.getPrev();
        lastModel.setNext(model);
        model.setPrev(lastModel);
        model.setNext(head);
        head.setPrev(model);
        size++;
    }

    public void addModel(Model model) throws DuplicateModelNameException {
        addModel(model.name, model.price);
    }

    @Override
    public void deleteModel(String modelName) throws NoSuchModelNameException {
        Model model = findModelOrThrow(modelName);
        Model nextModel = model.getNext();
        Model prevModel = model.getPrev();

        prevModel.setNext(nextModel);
        prevModel.setPrev(nextModel);
        size--;
    }

    @Override
    public Integer getModelCount(){
        return size;
    }

    private Model findModelOrThrow(String modelName) throws NoSuchModelNameException {
        Model model = findModelOrReturnNull(modelName);
        if(model == null){
            throw new NoSuchModelNameException();
        }
        return model;
    }

    private void validateDuplicateModel(String modelName) throws DuplicateModelNameException {
        if(findModelOrReturnNull(modelName) != null){
            throw new DuplicateModelNameException();
        }
    }

    private Model findModelOrReturnNull(String modelName){
        Model model = head.getNext();
        while (model != head){
           if(model.getName().equals(modelName)){
               return model;
           }
           model = model.getNext();
        }
        return null;
    }



    @Override
    public Bike clone(){
        try {
            Bike clonedBikes = (Bike) super.clone();
            Model model = head.getNext();
            clonedBikes.head = new Model();
            clonedBikes.head.setPrev(clonedBikes.head);
            clonedBikes.head.setNext(clonedBikes.head);
            clonedBikes.size = 0;
            while (model != head){
                clonedBikes.addModel(model);
                model = model.getNext();
            }
            return clonedBikes;
        } catch (DuplicateModelNameException | CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    private class Model implements Serializable {
        private String name;
        private BigDecimal price;
        private Model prev = null;
        private Model next = null;

        public Model(String name, BigDecimal price) {
            this.name = name;
            this.price = price;
        }

        public Model() {}

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

        public Model getPrev() {
            return prev;
        }

        public void setPrev(Model prev) {
            this.prev = prev;
        }

        public Model getNext() {
            return next;
        }

        public void setNext(Model next) {
            this.next = next;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }
    }
}
