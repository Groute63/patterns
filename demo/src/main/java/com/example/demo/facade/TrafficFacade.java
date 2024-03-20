package com.example.demo.facade;

import com.example.demo.model.Car;
import com.example.demo.model.TrafficLight;
import com.example.demo.model.TrafficLightStatus;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.List;

public class TrafficFacade {
    private final Stage stage;
    private final VBox root;
    private static final Timeline timeline = new Timeline();
    private final List<Car> carList;
    private final TrafficLight trafficLight;

    public TrafficFacade(Stage stage, VBox root, TrafficLight trafficLight) {
        try {
            this.stage = stage;
            this.root = root;
            this.carList = List.of(new Car(0.05), new Car(1));
            this.trafficLight = trafficLight;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        root.getChildren().add(trafficLight.getTrafficLightImage());
        carList.forEach(this::addCar);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);

        timeline.play();

        Scene scene = new Scene(root, 1200, 600);
        stage.setTitle("Traffic");
        stage.setScene(scene);
        stage.show();
    }

    public void addCar(Car car) {
        root.getChildren().add(car.getCarImage());
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.012), event -> {
            if (trafficLight.getStatus().getStatus() == TrafficLightStatus.Color.GREEN
                    || (trafficLight.getX() - car.getX() > 210 || trafficLight.getX() - car.getX() <= 50)) {
                car.speedUp();
            } else {
                car.stop();
            }
        });
        timeline.getKeyFrames().add(keyFrame);
    }
}
