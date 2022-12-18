package com.codecool.ants.ants_sim.model.ants;

import com.codecool.ants.ants_sim.model.Animal;
import com.codecool.ants.ants_sim.model.Colony;
import com.codecool.ants.ants_sim.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.HashMap;
import java.util.Map;

class AntColonyImplTest {

    @Test
    void addMember() {
        // given
        Colony<Ant> antColony = new AntColonyImpl(100, 50);

        // when
        Drone newMember = new Drone(new Position(0, 0));
        antColony.addMember(newMember);

        //then
        Assertions.assertEquals(1, antColony.getColony().size());
        Assertions.assertEquals(newMember, antColony.getColony().get(0));
    }

    @Test
    void removeMember() {
    }

    @Test
    void generateColonyWithNotAllowedAntClass() {
        //given
        Colony<Ant> antColony = new AntColonyImpl(100, 50);
        Map<Class<? extends Ant>, Integer> colonyConfiguration = new HashMap<>();
        colonyConfiguration.put(NotExistingAnt.class, 40);

        //when
        Executable executable = () -> antColony.generateColony(colonyConfiguration);

        //then
        Assertions.assertThrows(RuntimeException.class, executable);

    }


    @Test
    void generateColony() {
        // given
        Colony<Ant> antColony = new AntColonyImpl(100, 50);

        Map<Class<? extends Ant>, Integer> colonyConfiguration = new HashMap<>();
        colonyConfiguration.put(Drone.class, 40);
        colonyConfiguration.put(Worker.class, 30);
        colonyConfiguration.put(Soldier.class, 20);

        // when
        antColony.generateColony(colonyConfiguration);

        //then
        long countQueen = antColony.getColony().stream().filter(ant -> ant instanceof Queen).count();
        long countDrone = antColony.getColony().stream().filter(ant -> ant instanceof Drone).count();
        long countWorker = antColony.getColony().stream().filter(ant -> ant instanceof Worker).count();
        long countSoldier = antColony.getColony().stream().filter(ant -> ant instanceof Soldier).count();

        Assertions.assertEquals(91, antColony.getColony().size());
        Assertions.assertEquals(40, countDrone);
        Assertions.assertEquals(1, countQueen);
        Assertions.assertEquals(30, countWorker);
        Assertions.assertEquals(20, countSoldier);

    }


    @Test
    void getColonyInfo() {
        // given
        Colony<Ant> antColony = new AntColonyImpl(100, 50);

        Map<Class<? extends Ant>, Integer> colonyConfiguration = new HashMap<>();
        colonyConfiguration.put(Drone.class, 40);
        colonyConfiguration.put(Worker.class, 30);
        colonyConfiguration.put(Soldier.class, 20);
        antColony.generateColony(colonyConfiguration);

        // when
        Map<Class<? extends Animal>, Long> colonyInfo = antColony.getColonyInfo();

        //then
        Assertions.assertEquals(1, colonyInfo.get(Queen.class));
        Assertions.assertEquals(40, colonyInfo.get(Drone.class));
        Assertions.assertEquals(30, colonyInfo.get(Worker.class));
        Assertions.assertEquals(20, colonyInfo.get(Soldier.class));
    }

    @Test
    void getWidth() {
    }

    @Test
    void getHeight() {
    }
}