package com.example.demo;

import com.example.demo.facade.TrafficFacade;
import com.example.demo.model.TrafficLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class CarApplication extends Application {
    private final String ROAD_IMAGE_PATH = "demo/src/main/resources/road.jpg";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarApplication.class.getResource("traffic.fxml"));

        VBox root = fxmlLoader.load();
        TrafficLight trafficLight = new TrafficLight(1000);
        TrafficFacade trafficFacade = new TrafficFacade(
                stage,
                root,
                trafficLight
        );
        trafficFacade.start();
    }

    public static void main(String[] args) {
        launch();
    }

    private void a(VBox root){
        try {
            ImageView carImage = new ImageView(new Image(new FileInputStream(ROAD_IMAGE_PATH)));
            root.getChildren().add(carImage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}