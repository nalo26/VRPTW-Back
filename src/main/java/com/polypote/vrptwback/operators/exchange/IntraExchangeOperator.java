package com.polypote.vrptwback.operators.exchange;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.AbstractOperator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class IntraExchangeOperator extends AbstractOperator {

    private void parseRoute(Solution solution, List<Solution> result, int routeIterator, LinkedList<Camion> newRoutes, Camion currentCamion, LinkedList<Client> currentRoutes) {
        for (int clientIterator1 = 1; clientIterator1 < currentRoutes.size() - 1; clientIterator1++) {
            for (int clientIterator2 = 1; clientIterator2 < currentRoutes.size() - 1; clientIterator2++) {
                if (clientIterator1 == clientIterator2) {
                    continue;
                }
                Camion newCamion = exchangeRoutes(currentCamion, currentRoutes, clientIterator1, clientIterator2);

                newRoutes.set(routeIterator, newCamion);
                Solution newSolution = Solution.builder().routes(newRoutes).fitness(Utils.getFitness(newRoutes)).build();
                if (!result.contains(newSolution)) {
                    result.add(newSolution);
                }
                newRoutes = new LinkedList<>(solution.routes());
            }
        }
    }

    private Camion exchangeRoutes(Camion currentCamion, LinkedList<Client> currentRoutes, int clientIterator1, int clientIterator2) {
        Client client1 = currentRoutes.get(clientIterator1);
        Client client2 = currentRoutes.get(clientIterator2);

        Camion newCamion = new Camion((LinkedList<Client>) currentRoutes.clone(), currentCamion.getDistance());
        newCamion.getRoute().set(clientIterator1, client2);
        newCamion.getRoute().set(clientIterator2, client1);
        newCamion.setDistance(Utils.getDistance(newCamion.getRoute()));
        return newCamion;
    }

    @Override
    public List<Solution> getNeighbours(Solution solution) {
        List<Solution> result = new ArrayList<>();
        for (int routeIterator = 0; routeIterator < solution.routes().size(); routeIterator++) {
            LinkedList<Camion> newRoutes = new LinkedList<>(solution.routes());
            Camion currentCamion = newRoutes.get(routeIterator);
            LinkedList<Client> currentRoutes = currentCamion.getRoute();
            parseRoute(solution, result, routeIterator, newRoutes, currentCamion, currentRoutes);
        }
        return result;
    }
}
