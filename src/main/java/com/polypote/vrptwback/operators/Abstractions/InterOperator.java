package com.polypote.vrptwback.operators.Abstractions;


import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class InterOperator implements Operator {
    @Override
    public List<Solution> getNeighbours(Solution solution) {
        List<Solution> result = new ArrayList<>();
        for (int routeIterator1 = 0; routeIterator1 < solution.routes().size(); routeIterator1++) {
            for (int routeIterator2 = 0; routeIterator2 < solution.routes().size(); routeIterator2++) {
                if (routeIterator1 == routeIterator2) {
                    continue;
                }
                LinkedList<Camion> newRoutes = new LinkedList<>(solution.routes());
                Camion route1 = solution.routes().get(routeIterator1);
                Camion route2 = solution.routes().get(routeIterator2);

                final LinkedList<Client> clientList1 = route1.getRoute();
                final LinkedList<Client> clientList2 = route2.getRoute();
                parseClients(solution, result, routeIterator1, routeIterator2, newRoutes, route1, route2, clientList1, clientList2);
            }
        }
        return result;
    }

    protected abstract void parseClients(Solution solution, List<Solution> result, int routeIterator1, int routeIterator2, LinkedList<Camion> newRoutes, Camion route1, Camion route2, LinkedList<Client> clientList1, LinkedList<Client> clientList2);
}
