package com.polypote.vrptwback.operators.relocate;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.IntraOperator;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class IntraRelocateOperator extends IntraOperator {
    private Camion relocate(LinkedList<Client> clonedRoutes, int clientIterator1, int clientIterator2) {
        Client currentClient = clonedRoutes.remove(clientIterator1);

        clonedRoutes.add(clientIterator2, currentClient);
        Camion newCamion = Camion.builder().route(clonedRoutes).distance(Utils.getDistance(clonedRoutes)).build();

        return newCamion;
    }

    @Override
    protected void parseRoute(Solution solution, List<Solution> result, int routeIterator, LinkedList<Camion> newRoutes, Camion currentCamion, LinkedList<Client> currentRoutes) {
        LinkedList<Client> clonedRoutes = new LinkedList<>(currentRoutes);
        for (int clientIterator1 = 1; clientIterator1 < currentRoutes.size() - 1; clientIterator1++) {
            for (int clientIterator2 = 1; clientIterator2 < currentRoutes.size() - 1; clientIterator2++) {
                if (clientIterator1 == clientIterator2) {
                    continue;
                }
                Camion newCamion = relocate(clonedRoutes, clientIterator1, clientIterator2);
                clonedRoutes = new LinkedList<>(currentRoutes);
                if (!checkRoute(newCamion)) {
                    newRoutes = new LinkedList<>(solution.routes());
                    continue;
                }
                addToResult(result, routeIterator, newRoutes, newCamion);
                newRoutes = new LinkedList<>(solution.routes());
            }
        }
        System.out.println("Solutions successfully relocated in China");
    }
}
