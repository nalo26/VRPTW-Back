package com.polypote.vrptwback.operators.relocate;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.InterOperator;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class InterRelocateOperator extends InterOperator {
    @Override
    protected void parseClients(Solution solution, List<Solution> result, int routeIterator1, int routeIterator2, LinkedList<Camion> newRoutes, Camion route1, Camion route2, LinkedList<Client> clientList1, LinkedList<Client> clientList2) {
        LinkedList<Client> clonedRoute1 = new LinkedList<>(clientList1);
        LinkedList<Client> clonedRoute2 = new LinkedList<>(clientList2);
        for (int clientIterator1 = 1; clientIterator1 < clientList1.size() - 1; clientIterator1++) {
            for (int clientIterator2 = 1; clientIterator2 < clientList2.size(); clientIterator2++) {
                Pair<Camion, Camion> relocatedCamions = relocate(clonedRoute1, clonedRoute2, clientIterator1, clientIterator2);

                newRoutes.set(routeIterator1, relocatedCamions.getLeft());
                newRoutes.set(routeIterator2, relocatedCamions.getRight());

                clonedRoute1 = new LinkedList<>(clientList1);
                clonedRoute2 = new LinkedList<>(clientList2);
                Solution newSolution = Solution.builder().routes(newRoutes).fitness(Utils.getFitness(newRoutes)).build();
                if (!result.contains(newSolution)) {
                    result.add(newSolution);
                }
                newRoutes = new LinkedList<>(solution.routes());
            }
        }
    }

    private Pair<Camion, Camion> relocate(LinkedList<Client> clonedRoute1, LinkedList<Client> clonedRoute2, int clientIterator1, int clientIterator2) {
        Client removedClient = clonedRoute1.remove(clientIterator1);
        clonedRoute2.add(clientIterator2, removedClient);
        Camion newCamion1 = Camion.builder().route(clonedRoute1).distance(Utils.getDistance(clonedRoute1)).build();
        Camion newCamion2 = Camion.builder().route(clonedRoute2).distance(Utils.getDistance(clonedRoute2)).build();
        return new ImmutablePair<>(newCamion1, newCamion2);
    }

}
