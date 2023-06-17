package com.polypote.vrptwback.operators;

import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;

import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.singletonList;

public class OperatorsTestUtils {

    public static Solution createIntraSolution(List<Client> clients, int fitness) {
        return Solution.builder()
                .routes(singletonList(
                        createCamion(clients, fitness)
                ))
                .fitness(fitness)
                .build();
    }

    public static Camion createCamion(List<Client> clients, int distance) {
        return Camion.builder()
                .route(new LinkedList<>(clients))
                .distance(distance)
                .build();
    }

    public static Solution createInterSolution(List<Camion> camions, int fitness) {
        return Solution.builder()
                .routes(camions)
                .fitness(fitness)
                .build();
    }

    public static Client createClient(String idName, int x, int y) {
        return Client.builder()
                .id_name(idName)
                .x(x)
                .y(y)
                .build();
    }

    public static Client createClient(String idName, int x, int y, int readyTime, int dueTime) {
        return Client.builder()
                .id_name(idName)
                .x(x)
                .y(y)
                .due_time(dueTime)
                .ready_time(readyTime)
                .service(10)
                .build();
    }
}
