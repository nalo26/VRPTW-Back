package com.polypote.vrptwback.model;


import com.polypote.vrptwback.Utils;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.LinkedList;

import static java.util.Arrays.asList;

@Data
@Builder
@RequiredArgsConstructor
public class Camion {
    @NonNull
    LinkedList<Client> route;
    @NonNull
    int distance;
    int currentCamionTime;

    public Camion(Client depot) {
        route = new LinkedList<>(asList(depot, depot));
        distance = 0;
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
            currentCamionTime += client.getReady_time() - currentCamionTime;
        }
        boolean isFittingTimeWindow = currentCamionTime == client.getReady_time() && currentCamionTime + client.getService() <= route.get(0).getReady_time();
        boolean canAdd = isFittingQuantity && isFittingDistance && isFittingTimeWindow;
        if (canAdd) {
            route.add(route.size() - 1, client);
            distance = newDistance;
            currentCamionTime += client.getService();
        }
        return canAdd;
    }
}
