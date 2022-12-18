package com.codecool.ants.ants_sim.model.ants;

import com.codecool.ants.ants_sim.MoveDirection;
import com.codecool.ants.ants_sim.model.Position;
import javafx.scene.image.Image;
import lombok.Getter;

import java.util.Objects;

public class Worker extends Ant{

    @Getter
    private static Image image;

    public Worker(Position position) {
        super(position);
    }

    @Override
    public Position getNextPosition() {
        MoveDirection randomMove = MoveDirection.getRandomMove();
        return new Position(getPosition().getX() + randomMove.getDx(), getPosition().getY() + randomMove.getDy());
    }

    @Override
    public Image getImage(double cellSize) {
        if(Objects.isNull(image)) {
            image = new Image(Worker.class.getResourceAsStream("/worker.png"), cellSize, cellSize, false, false);
        }
        return image;
    }
}
