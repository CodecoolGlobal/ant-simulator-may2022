package com.codecool.ants.ants_sim.model.ants;

import com.codecool.ants.ants_sim.model.Position;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Queen extends Ant{

    private static Image image;

    @Getter @Setter
    private boolean isAvailable = true;

    @Getter @Setter
    private Drone partner;

    @Getter @Setter
    private int mattingCounter;

    @Getter @Setter
    private int restingCounter;

    public Queen(Position position) {
        super(position);
    }

    @Override
    public Position getNextPosition() {
        throw new RuntimeException("Queen cannot move!");
    }

    @Override
    public Image getImage(double cellSize) {
        if(Objects.isNull(image)) {
            image = new Image(Queen.class.getResourceAsStream("/queen.png"), cellSize, cellSize, false, false);
        }
        return image;
    }

    @Override
    public void setPosition(Position position) {
        throw new RuntimeException("Cannot set position for Queen!");
    }
}
