package com.polypote.vrptwback.generator;

import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class RandomSolutionGenerator {

    private final Random random = new Random();

    public Solution generate(final Root root) {
        final List<Camion> result = new ArrayList<>();
        final List<Client> clients = new ArrayList<>(root.getClients());
        final LinkedList<Client> routes = new LinkedList<>();
        //TODO gérer le dépot*
        routes.add(clients.remove(0));
        while (!clients.isEmpty()) {
            final Client currentClient = clients.remove(random.nextInt(clients.size()));
            routes.add(currentClient);
        }
        Camion camion = new Camion(routes, getDistance(routes));
        result.add(camion);
        return Solution.builder().routes(result).fitness(result.stream().map(Camion::distance).reduce(0, Integer::sum)).build();
    }

    public int getDistance(LinkedList<Client> routes) {
        int result = 0;
        for (int clientIterator = 0; clientIterator < routes.size(); clientIterator++) {
            if (clientIterator + 1 < routes.size()) {
                Client current = routes.get(clientIterator);
                Client next = routes.get(clientIterator + 1);
                result += getEuclideanDistance(current.getX(), current.getY(), next.getX(), next.getY());
            }
        }
        return result;
    }

    private int getEuclideanDistance(int x1, int y1, int x2, int y2) {
        return (int) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    }
}
