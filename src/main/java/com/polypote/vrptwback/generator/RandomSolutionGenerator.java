package com.polypote.vrptwback.generator;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomSolutionGenerator extends AbstractGenerator {

    private final Random random = new Random();

    public void generate(final Root root) {
        final List<Camion> result = new ArrayList<>();
        final List<Client> clients = new ArrayList<>(root.getClients());
        final LinkedList<Client> routes = new LinkedList<>();
        routes.add(clients.remove(0));
        while (!clients.isEmpty()) {
            final Client currentClient = clients.remove(random.nextInt(clients.size()));
            routes.add(currentClient);
        }
        Camion camion = new Camion(routes, Utils.getDistance(routes));
        result.add(camion);

        sendData(Solution.builder().routes(result).fitness(Utils.getFitness(result)).build());
    }

}
