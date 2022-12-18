package com.codecool.ants.ants_sim.model.ants;

import com.codecool.ants.ants_sim.model.Animal;
import com.codecool.ants.ants_sim.model.Position;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class Ant implements Animal {

    @Getter @Setter @NonNull
    private Position position;

}
