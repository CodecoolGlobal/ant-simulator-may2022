package com.codecool.ants.ants_sim.model;

import com.codecool.ants.ants_sim.model.ants.Queen;

import java.util.List;
import java.util.Map;

public interface Colony<T extends Animal> {
    List<T> getColony();

    Map<Class<? extends Animal>, Long> getColonyInfo();

    void update();

    void addMember(T newMember);

    void removeMember(T memberToRemove);

    Queen getQueen();

    void generateColony(Map<Class<? extends T>, Integer> colonyConfiguration);

    default int getWidth() {
        return 40;
    }

    default int getHeight() {
        return 20;
    }
}
