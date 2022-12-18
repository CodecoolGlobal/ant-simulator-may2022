package com.codecool.ants.ants_sim.model;

import javafx.scene.image.Image;

public interface Animal {
    Position getNextPosition();
    Position getPosition();
    Image getImage(double cellSize);
    void setPosition(Position position);
}
