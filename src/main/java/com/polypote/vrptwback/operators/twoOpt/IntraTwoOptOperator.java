package com.polypote.vrptwback.operators.twoOpt;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.IntraOperator;

import java.util.LinkedList;
import java.util.List;

public class IntraTwoOptOperator extends IntraOperator {
    @Override
    protected void parseRoute(Solution solution, List<Solution> result, int routeIterator, LinkedList<Camion> newRoutes, Camion currentCamion, LinkedList<Client> currentRoutes) {
        LinkedList<Client> clonedRoutes = new LinkedList<>(currentRoutes);
        for (int clientIterator1 = 1; clientIterator1 < currentRoutes.size() - 2; clientIterator1++) {
            for (int clientIterator2 = 1; clientIterator2 < currentRoutes.size() - 2; clientIterator2++) {
                if (Math.abs(clientIterator1 - clientIterator2) <= 1) {
                    continue;
                }
                Camion newCamion = towOpt(currentCamion, clonedRoutes, clientIterator1, clientIterator2);
                clonedRoutes = new LinkedList<>(currentRoutes);
                addToResult(result, routeIterator, newRoutes, newCamion);
                newRoutes = new LinkedList<>(solution.routes());
            }
        }
    }

    private Camion towOpt(Camion currentCamion, LinkedList<Client> clonedRoutes, int clientIterator1, int clientIterator2) {
        int nextPointOne = clientIterator1 + 1;
        int nextPointTwo = clientIterator2 + 1;
        Client nextClient1 = clonedRoutes.get(nextPointOne);
        Client nextClient2 = clonedRoutes.get(nextPointTwo);
        clonedRoutes.remove(nextClient1);
        clonedRoutes.remove(nextClient2);
        Camion newCamion = Camion.builder().route(new LinkedList<>(clonedRoutes)).distance(currentCamion.getDistance()).build();
        if (nextPointOne > nextPointTwo) {
            newCamion.getRoute().add(clientIterator1, nextClient2);
            newCamion.getRoute().add(nextPointOne, nextClient1);
        } else {
            newCamion.getRoute().add(clientIterator2, nextClient1);
            newCamion.getRoute().add(nextPointTwo, nextClient2);
        }
        newCamion.setDistance(Utils.getDistance(newCamion.getRoute()));
        return newCamion;
    }

}
