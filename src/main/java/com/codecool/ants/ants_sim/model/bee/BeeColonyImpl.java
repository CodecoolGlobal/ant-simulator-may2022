package com.codecool.ants.ants_sim.model.bee;

import com.codecool.ants.ants_sim.model.Animal;
import com.codecool.ants.ants_sim.model.Colony;
import com.codecool.ants.ants_sim.model.ants.Queen;

import java.util.List;
import java.util.Map;

public class BeeColonyImpl<T extends Animal> implements Colony<T> {
    @Override
    public List<T> getColony() {
        return null;
    }

    @Override
    public Map<Class<? extends Animal>, Long> getColonyInfo() {
        return null;
    }


    @Override
    public void update() {

    }

    @Override
    public void addMember(T newMember) {

    }

    @Override
    public void removeMember(T memberToRemove) {

    }

    @Override
    public Queen getQueen() {
        return null;
    }

    @Override
    public void generateColony(Map<Class<? extends T>, Integer> colonyConfiguration) {

    }


}
