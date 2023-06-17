package com.polypote.vrptwback.operators.exchange;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.InterOperator;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Primary
public class InterExchangeOperator extends InterOperator {
    @Override
    protected void parseClients(Solution solution, List<Solution> result, int routeIterator1, int routeIterator2, LinkedList<Camion> newRoutes, Camion route1, Camion route2, LinkedList<Client> clientList1, LinkedList<Client> clientList2) {
        for (int clientIterator1 = 1; clientIterator1 < clientList1.size() - 1; clientIterator1++) {
            for (int clientIterator2 = 1; clientIterator2 < clientList2.size() - 1; clientIterator2++) {
                Pair<Camion, Camion> exchangedCamion = exchange(clientIterator1, clientIterator2, clientList1, clientList2, route1.getDistance(), route2.getDistance());
                if (!(checkRoute(exchangedCamion.getLeft()) && checkRoute(exchangedCamion.getRight()))) {
                    newRoutes = new LinkedList<>(solution.routes());
                    continue;
                }
                newRoutes.set(routeIterator1, exchangedCamion.getLeft());
                newRoutes.set(routeIterator2, exchangedCamion.getRight());

                addToResult(result, newRoutes);
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
