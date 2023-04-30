package com.polypote.vrptwback.operators.exchange;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.AbstractOperator;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExchangeOperatorInter extends AbstractOperator {
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

    private void parseClients(Solution solution, List<Solution> result, int routeIterator1, int routeIterator2, LinkedList<Camion> newRoutes, Camion route1, Camion route2, LinkedList<Client> clientList1, LinkedList<Client> clientList2) {
        for (int clientIterator1 = 1; clientIterator1 < clientList1.size() - 1; clientIterator1++) {
            for (int clientIterator2 = 1; clientIterator2 < clientList2.size() - 1; clientIterator2++) {
                Pair<Camion, Camion> exchangedCamion = exchange(clientIterator1, clientIterator2, clientList1, clientList2, route1.getDistance(), route2.getDistance());

                newRoutes.set(routeIterator1, exchangedCamion.getLeft());
                newRoutes.set(routeIterator2, exchangedCamion.getRight());

                Solution newSolution = Solution.builder().routes(newRoutes).fitness(Utils.getFitness(newRoutes)).build();
                if (!result.contains(newSolution)) {
                    result.add(newSolution);
                }
                newRoutes = new LinkedList<>(solution.routes());
            }
        }
    }

    private Pair<Camion, Camion> exchange(int clientIterator1, int clientIterator2, LinkedList<Client> clientList1, LinkedList<Client> clientList2, int route1Distance, int route2Distance) {
        Client client1 = clientList1.get(clientIterator1);
        Client client2 = clientList2.get(clientIterator2);

        Camion newCamion1 = new Camion((LinkedList<Client>) clientList1.clone(), route1Distance);
        Camion newCamion2 = new Camion((LinkedList<Client>) clientList2.clone(), route2Distance);
        newCamion1.getRoute().set(clientIterator1, client2);
        newCamion2.getRoute().set(clientIterator2, client1);
        newCamion1.setDistance(Utils.getDistance(newCamion1.getRoute()));
        newCamion2.setDistance(Utils.getDistance(newCamion2.getRoute()));

        return new ImmutablePair<>(newCamion1, newCamion2);
    }
}
