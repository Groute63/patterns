package com.example.demo.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class TrafficLightStatus {
    private final StringProperty status;
    TrafficLightStatus() {
        this.status = new SimpleStringProperty(Color.RED.toString());
    }
    public Color getStatus() {
        return Color.valueOf(status.getValue());
    }

    public void setStatus(Color status) {
        this.status.set(status.toString());
    }

    public enum Color {
        GREEN(new Image("file:demo/src/main/resources/trafficLightGreen.png")),
        YELLOW(new Image("file:demo/src/main/resources/trafficLightYellow.png")),
        RED(new Image("file:demo/src/main/resources/trafficLightRed.png"));

        private final Image image;

        public Image getImage() {
            return image;
        }

        Color(Image image) {
            this.image = image;
        }
    }
}
