package main.model.vehicle;

import main.model.exception.DuplicateModelNameException;
import main.model.exception.NoSuchModelNameException;
import main.model.validator.PriceValidator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

public class Car implements Vehicle{
    private PriceValidator priceValidator = new PriceValidator();
    private String brand;
    private Model[] models;

    public Car(String brand, Integer modelCount){
        this.brand = brand;
        this.models = new Model[modelCount];
        fillModelList();
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
        String[] modelNames = new String[models.length];
        for(int i = 0; i < models.length; i++){
            modelNames[i] = models[i].getName();
        }
        return modelNames;
    }

    @Override
    public BigDecimal[] getModelPrices(){
        BigDecimal[] modelPrices = new BigDecimal[models.length];
        for(int i = 0; i < models.length; i++){
            modelPrices[i] = models[i].getPrice();
        }
        return modelPrices;
    }

    @Override
    public void setModelName(String prevName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(!prevName.equals(newName)){
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
    public Integer getModelCount(){
        return models.length;
    }

    private Model findModelOrThrow(String modelName) throws NoSuchModelNameException {
        Model model = findModelOrReturnNull(modelName);
        if(model == null){
            throw new NoSuchModelNameException();
        }
        return model;
    }

    private Model findModelOrReturnNull(String modelName){
        for(Model model: models){
            if(model.getName().equals(modelName)){
                return model;
            }
        }
        return null;
    }

    private void validateDuplicateModel(String modelName) throws DuplicateModelNameException {
        if(findModelOrReturnNull(modelName) != null){
            throw new DuplicateModelNameException();
        }
    }

    private void fillModelList(){
         for(int i = 0; i < models.length; i++){
             if(models[i] == null){
                 models[i] = new Model("model" + i, new BigDecimal(new Random().nextInt(0,100)));
             }
         }
    }

    private void setModels(Model[] models){
        this.models = models;
    }

    @Override
    public Car clone(){
        Car clonedCars = new Car(this.getBrand(),0);
        Model[] sampleModels = this.models;
        Model[] clonedModels = new Model[sampleModels.length];
        for(int i = 0; i< sampleModels.length; i++){
            clonedModels[i] = sampleModels[i].clone();
        }
        clonedCars.setModels(clonedModels);
        return clonedCars;
    }

    private class Model implements Cloneable{
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
        public Model clone(){
            return new Model(this.getName(),this.getPrice());
        }
    }
}
