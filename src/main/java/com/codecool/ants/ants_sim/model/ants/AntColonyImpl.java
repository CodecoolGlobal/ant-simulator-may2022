package com.codecool.ants.ants_sim.model.ants;

import com.codecool.ants.ants_sim.model.Animal;
import com.codecool.ants.ants_sim.model.Colony;
import com.codecool.ants.ants_sim.model.Position;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.stream.IntStream.range;

@RequiredArgsConstructor
public class AntColonyImpl implements Colony<Ant> {

    @Getter
    private final int width, height;

    @Getter
    private final List<Ant> colony = new ArrayList<>();
    @Getter
    private Queen queen;


    @Override
    public Map<Class<? extends Animal>, Long> getColonyInfo() {
        long countQueen = colony.stream().filter(ant -> ant instanceof Queen).count();
        long countDrone = colony.stream().filter(ant -> ant instanceof Drone).count();
        long countWorker = colony.stream().filter(ant -> ant instanceof Worker).count();
        long countSoldier = colony.stream().filter(ant -> ant instanceof Soldier).count();
        return Map.of(Queen.class, countQueen, Drone.class, countDrone, Worker.class, countWorker, Soldier.class, countSoldier);
    }

    @Override
    public void update() {
        //czy jest kolizja
        //czy jest w ramach kolnii
        colony.forEach(ant -> {
            try {
                while (true) {
                    Position nextPosition = ant.getNextPosition();
                    if (isMoveInColonyBorders(nextPosition)) {
                        ant.setPosition(nextPosition);
                        break;
                    }
                }
            } catch (RuntimeException ignored) {
            }
        });
    }

    private boolean isMoveInColonyBorders(Position position) {
        return position.getX() >= 0 && position.getX() < width
                && position.getY() >= 0 && position.getY() < height;
    }

    @Override
    public void addMember(Ant newMember) {
        colony.add(newMember);
    }

    @Override
    public void removeMember(Ant memberToRemove) {
        colony.remove(memberToRemove);
    }

    @Override
    public void generateColony(Map<Class<? extends Ant>, Integer> colonyConfiguration) {
        colonyConfiguration.forEach(((antClass, population) -> {
            if (antClass.equals(Soldier.class)) {
                range(0, population).forEach(i -> colony.add(new Soldier(generateRandomPosition())));
            }
            else if (antClass.equals(Drone.class)) {
                range(0, population).forEach(i -> colony.add(new Drone(generateRandomPosition())));
            }
            else if (antClass.equals(Worker.class)) {
                range(0, population).forEach(i -> colony.add(new Worker(generateRandomPosition())));
            } else {
                throw new RuntimeException("Such ant not supported");
            }
        }));

        queen = new Queen(new Position(width/2, height/2));
        colony.add(queen);
    }

    private Position generateRandomPosition(){
        Random random = new Random();
        return new Position(random.nextInt(width), random.nextInt(height));
    }
}
