package com.polypote.vrptwback.model;


import com.polypote.vrptwback.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;

import static java.util.Arrays.asList;

@Data
@Builder
@AllArgsConstructor
public class Camion {
    LinkedList<Client> route;
    int distance;

    public Camion(Client depot) {
        route = new LinkedList<>(asList(depot, depot));
        distance = 0;
    }

    public boolean addClient(Client client, Root root) {
        boolean isFittingQuantity = route.stream().map(Client::getDemand).reduce(0, Integer::sum) + client.getDemand() <= root.getHeaders().getMaxQuantity();
        LinkedList<Client> routeClone = new LinkedList<>(route);
        routeClone.add(route.size() - 1, client);
        int newDistance = Utils.getDistance(routeClone);
        boolean isFittingDistance = newDistance <= route.getFirst().getDue_time();
        //TODO Verifier les fenetres de temps (ready_time + due_time client)
        boolean canAdd = isFittingQuantity && isFittingDistance;
        if (canAdd) {
            route.add(route.size() - 1, client);
            distance = newDistance;
        }
        return canAdd;
    }
}
