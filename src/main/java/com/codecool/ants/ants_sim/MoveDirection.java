package com.codecool.ants.ants_sim;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@AllArgsConstructor
@Getter
public enum MoveDirection {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    public static MoveDirection getRandomMove() {
        return values()[new Random().nextInt(0, values().length)];
    }
}
