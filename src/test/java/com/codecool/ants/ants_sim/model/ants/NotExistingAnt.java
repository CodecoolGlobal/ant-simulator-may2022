package com.codecool.ants.ants_sim.model.ants;

import com.codecool.ants.ants_sim.model.Position;
import javafx.scene.image.Image;

public class NotExistingAnt extends Ant{
    public NotExistingAnt(Position position) {
        super(position);
    }

    @Override
    public Position getNextPosition() {
        return null;
    }

    @Override
    public Image getImage(double cellSize) {
        return null;
    }


}
