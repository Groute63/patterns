package main.lab.singleton;

public class Main {
    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
        System.out.println(propertiesLoader.getProperties());
    }
}