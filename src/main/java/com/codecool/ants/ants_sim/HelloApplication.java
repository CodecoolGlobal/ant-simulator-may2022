package com.codecool.ants.ants_sim;

import com.codecool.ants.ants_sim.model.Animal;
import com.codecool.ants.ants_sim.model.Colony;
import com.codecool.ants.ants_sim.model.ants.*;
import com.codecool.ants.ants_sim.view.ColonyCanvas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        int width = 80;
        int height = 40;
        int cellSize = 16;

        SimulatorConfig.cellSize = cellSize;

        Colony<Ant> antColony = new AntColonyImpl(width, height);

        Map<Class<? extends Ant>, Integer> colonyConfiguration = new HashMap<>();
        colonyConfiguration.put(Drone.class, 40);
        colonyConfiguration.put(Worker.class, 30);
        colonyConfiguration.put(Soldier.class, 20);
        antColony.generateColony(colonyConfiguration);

        ColonyCanvas<Ant> colonyCanvas = new ColonyCanvas<>(cellSize, antColony);
        colonyCanvas.draw();

        Button updateButton = new Button("Update Colony");
        updateButton.setOnMouseClicked(mouseEvent -> {
            antColony.update();
            colonyCanvas.draw();
        });

        TextField textField = new TextField();
        textField.setLayoutX(0);
        textField.setLayoutY(180);



        //Ant Canvas
        Canvas canvas = colonyCanvas.getCanvas();
        canvas.setLayoutX(200);
        canvas.setLayoutY(10);

        //Colony info text
        Map<Class<? extends Animal>, Long> colonyInfo = antColony.getColonyInfo();
        StringBuilder sb = new StringBuilder();
        colonyInfo.forEach((k,v) -> {
            sb.append(k.getSimpleName()).append(": ").append(v).append("\n");
        });
        Text colonyInfoText = new Text();
        colonyInfoText.setLayoutX(0);
        colonyInfoText.setLayoutY(40);
        colonyInfoText.setWrappingWidth(canvas.getLayoutX() - 40);
        colonyInfoText.autosize();
        colonyInfoText.setText(sb.toString());

        //Running simulation
        Button runButton = new Button("Run Colony");
        runButton.setLayoutX(0);
        runButton.setLayoutY(220);
        runButton.setOnMouseClicked(mouseEvent -> {
            AtomicReference<Integer> simulationCount = new AtomicReference<>(Integer.parseInt(textField.getText()));
            runButton.setDisable(true);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    antColony.update();
                    colonyCanvas.draw();
                    StringBuilder sb = new StringBuilder();
                    colonyInfo.forEach((k,v) -> {
                        sb.append(k.getSimpleName()).append(": ").append(v).append("\n");
                    });
                    colonyInfoText.setText(sb.toString() + "\n" + simulationCount.get());
                    simulationCount.set(simulationCount.get()-1);
                    textField.setText(simulationCount.get().toString());
                }
            }));
            timeline.setCycleCount(simulationCount.get());
            timeline.play();
            timeline.setOnFinished(h -> runButton.setDisable(false));
        });

        //Control pane
        Pane controlPane = new Pane();
        controlPane.getChildren().addAll(updateButton, colonyInfoText, runButton, textField);
        controlPane.setLayoutX(10);
        controlPane.setLayoutY(10);


        //Main pane
        Pane mainPane = new Pane();
        mainPane.getChildren().addAll(canvas, controlPane);
        Scene simulatorScene = new Scene(mainPane, (width * cellSize) + 220, (height * cellSize) + 20);
        stage.setScene(simulatorScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}