package com.polypote.vrptwback.model;


import com.polypote.vrptwback.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;

import static com.polypote.vrptwback.Utils.getEuclideanDistance;
import static java.util.Arrays.asList;

@Data
@Builder
@AllArgsConstructor
public class Camion {
    private LinkedList<Client> route;
    private int distance;
    private int currentCamionTime;

    public Camion(Client depot) {
        route = new LinkedList<>(asList(depot, depot));
        distance = 0;
        currentCamionTime = 0;
    }

    public Camion(LinkedList<Client> clients, int distance) {
        route = clients;
        this.distance = distance;
        currentCamionTime = 0;
    }

    public boolean addClient(Client client, Root root) {
        boolean isFittingQuantity = route.stream().map(Client::getDemand).reduce(0, Integer::sum) + client.getDemand() <= root.getHeaders().getMaxQuantity();
        LinkedList<Client> routeClone = new LinkedList<>(route);
        routeClone.add(route.size() - 1, client);
        int newDistance = Utils.getDistance(routeClone);
        boolean isFittingDistance = newDistance <= route.getFirst().getDue_time();
        //Fake wait if Camion is in advance
        if (currentCamionTime < client.getReady_time()) {
            currentCamionTime = client.getReady_time();
        }
        boolean isFittingTimeWindow = currentCamionTime <= client.getDue_time();
        final Client depot = route.get(0);
        boolean canReachDepot = currentCamionTime + client.getService() + getEuclideanDistance(client.getX(), client.getY(), depot.getX(), depot.getY()) <= depot.getDue_time();
        boolean canAdd = isFittingQuantity && isFittingDistance && isFittingTimeWindow && canReachDepot;
        if (canAdd) {
            route.add(route.size() - 1, client);
            distance = newDistance;
            currentCamionTime += client.getService();
        }
        return canAdd;
    }
}
