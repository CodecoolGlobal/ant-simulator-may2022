package com.codecool.ants.ants_sim.model.ants;

import com.codecool.ants.ants_sim.MoveDirection;
import com.codecool.ants.ants_sim.model.Position;
import javafx.scene.image.Image;

import java.util.Objects;

public class Drone extends Ant {

    private static Image image;

    public Drone(Position position) {
        super(position);
    }

    @Override
    public Position getNextPosition() {
        MoveDirection randomMove = MoveDirection.getRandomMove();
        return new Position(getPosition().getX() + randomMove.getDx(), getPosition().getY() + randomMove.getDy());
    }

    @Override
    public Image getImage(double cellSize) {
        if (Objects.isNull(image)) {
            image = new Image(Drone.class.getResourceAsStream("/drone.png"), cellSize, cellSize, false, false);
        }
        return image;
    }
}
