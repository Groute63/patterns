package com.example.demo.model;

import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Car {
    private double x = 50;
    private double y = 0;
    private double speed = 0;
    private final double acceleration;
    private final double MAX_SPEED = 8;
    private final ImageView carImage;
    private final String CAR_IMAGE_PATH = "demo/src/main/resources/car.jpg";

    public Car(double acceleration) throws FileNotFoundException {
        this.acceleration = acceleration;
        this.carImage = initializeImageView(x, y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if(this.x > 1200){
            this.x = x - 1200;
        }else {
            this.x = x;
        }
        this.carImage.setTranslateX(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.carImage.setTranslateY(y);
    }

    public ImageView getCarImage() {
        return carImage;
    }

    public void speedUp() {
        if (speed < MAX_SPEED) {
            speed += acceleration;
        }
        setX(x + speed);
    }

    public void stop() {
        speed = 0;
    }

    private ImageView initializeImageView(double x, double y) throws FileNotFoundException {
        ImageView carImage = new ImageView(new Image(new FileInputStream(CAR_IMAGE_PATH)));
        carImage.setTranslateX(x);
        carImage.setTranslateY(y);
        carImage.setFitHeight(170);
        carImage.setFitWidth(280);
        carImage.setRotate(0);
        return carImage;
    }
}
