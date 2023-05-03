package com.polypote.vrptwback.operators.relocate;

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
public class IntraRelocateOperator extends AbstractOperator {
    @Override
    public List<Solution> getNeighbours(Solution solution) {
        List<Solution> result = new ArrayList<>();
        for (int routeIterator = 0; routeIterator < solution.routes().size(); routeIterator++) {
            LinkedList<Camion> newRoutes = new LinkedList<>(solution.routes());
            Camion currentCamion = newRoutes.get(routeIterator);
            LinkedList<Client> currentRoutes = currentCamion.getRoute();
            parseRoute(solution, result, routeIterator, newRoutes, currentCamion, currentRoutes);
        }
        System.out.println("Solutions successfully relocated in China");
        return result;
    }

    private void parseRoute(Solution solution, List<Solution> result, int routeIterator, LinkedList<Camion> newRoutes, Camion currentCamion, LinkedList<Client> currentRoutes) {
        LinkedList<Client> clonedRoutes = new LinkedList<>(currentRoutes);
        for (int clientIterator1 = 1; clientIterator1 < currentRoutes.size() - 1; clientIterator1++) {
            for (int clientIterator2 = 1; clientIterator2 < currentRoutes.size() - 1; clientIterator2++) {
                if (clientIterator1 == clientIterator2) {
                    continue;
                }
                Client currentClient = clonedRoutes.remove(clientIterator1);

                clonedRoutes.add(clientIterator2, currentClient);
                Camion newCamion = Camion.builder().route(clonedRoutes).distance(Utils.getDistance(clonedRoutes)).build();

                newRoutes.set(routeIterator, newCamion);
                Solution newSolution = Solution.builder().routes(newRoutes).fitness(Utils.getFitness(newRoutes)).build();
                if (!result.contains(newSolution)) {
                    result.add(newSolution);
                }
                newRoutes = new LinkedList<>(solution.routes());
                clonedRoutes = new LinkedList<>(currentRoutes);
            }
        }
    }
}
