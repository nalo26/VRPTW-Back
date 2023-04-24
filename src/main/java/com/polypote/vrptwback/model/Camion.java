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
        //La somme des `due_time` des clients en enlevant les dépôts doit être inférieur au `due_time` du dépôt.
        boolean isFittingTime = route.stream().map(Client::getDue_time).reduce(0, Integer::sum) + client.getDue_time() - (2 * route.getFirst().getDue_time()) <= route.getFirst().getDue_time();
        boolean canAdd = isFittingQuantity && isFittingTime;
        if (canAdd) {
            route.add(route.size() - 1, client);
            distance = Utils.getDistance(route);
        }
        return canAdd;
    }
}
