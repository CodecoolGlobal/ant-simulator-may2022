package com.codecool.ants.ants_sim.view;

import com.codecool.ants.ants_sim.model.Animal;
import com.codecool.ants.ants_sim.model.Colony;
import com.codecool.ants.ants_sim.model.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import lombok.Getter;


public class ColonyCanvas<T extends Animal> {

    @Getter
    private final int cellSize;
    @Getter
    private final Colony<T> colony;
    private final GraphicsContext graphicsContext2D;
    @Getter
    private Canvas canvas;


    public ColonyCanvas(int cellSize, Colony<T> colony) {
        this.cellSize = cellSize;
        this.colony = colony;

        Canvas canvas = new Canvas();
        canvas.setWidth((colony.getWidth() * cellSize) + 5.5);
        canvas.setHeight((colony.getHeight() * cellSize) + 5.5);
        this.canvas = canvas;
        this.graphicsContext2D = canvas.getGraphicsContext2D();
    }

    private void drawLines() {
        graphicsContext2D.setLineWidth(1);
        graphicsContext2D.setStroke(Color.RED);

        for (double i = 0.5; i <= (colony.getWidth() * cellSize) + 1; i = i +cellSize) {
            graphicsContext2D.strokeLine(i, 0, i, colony.getHeight() *cellSize);
        }

        for (double i = 0.5; i <= (colony.getHeight() * cellSize) + 1; i = i +cellSize) {
            graphicsContext2D.strokeLine(0, i, cellSize * colony.getWidth(), i);
        }

    }

    public void draw() {
        graphicsContext2D.clearRect(0,0, canvas.getWidth(), canvas.getHeight());
        drawLines();
        drawColony();

    }

    private void drawColony() {
        colony.getColony().forEach(ant -> {
            Image image = ant.getImage(cellSize);
            Position position = ant.getPosition();

            double startXPosition = position.getX() * cellSize;
            double startYPosition = position.getY() * cellSize;

            graphicsContext2D.drawImage(image, startXPosition, startYPosition);

        });
    }

}
