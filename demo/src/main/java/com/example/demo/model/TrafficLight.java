package com.example.demo.model;

import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class TrafficLight {
    private final TrafficLightStatus status;
    private double x;
    private double y;
    private final ImageView trafficLightImage;

    public ImageView getTrafficLightImage() {
        return trafficLightImage;
    }

    public TrafficLightStatus getStatus() {
        return status;
    }

    public void setStatus(TrafficLightStatus.Color color) {
        this.status.setStatus(color);
        this.trafficLightImage.setImage(color.getImage());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        this.trafficLightImage.setTranslateX(x);
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        this.trafficLightImage.setTranslateY(y);
    }

    public TrafficLight(int period) {
        this.trafficLightImage = initializeImageView();
        this.status = new TrafficLightStatus();
        setX(550);
        setY(10);
        Timer timer = new Timer("changeColor", false);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (status.getStatus()) {
                    case RED -> setStatus(TrafficLightStatus.Color.GREEN);
                    case YELLOW -> setStatus(TrafficLightStatus.Color.RED);
                    case GREEN -> setStatus(TrafficLightStatus.Color.YELLOW);
                }
            }
        }, 0, period);
    }

    private ImageView initializeImageView() {
        ImageView trafficLightImage = new ImageView();
        trafficLightImage.setFitHeight(100);
        trafficLightImage.setFitWidth(90);
        return trafficLightImage;
    }
}
