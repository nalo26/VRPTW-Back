package com.polypote.vrptwback.operators.Abstractions;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class IntraOperator implements Operator {
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

    protected abstract void parseRoute(Solution solution, List<Solution> result, int routeIterator, LinkedList<Camion> newRoutes, Camion currentCamion, LinkedList<Client> currentRoutes);

    protected void addToResult(List<Solution> result, int routeIterator, LinkedList<Camion> newRoutes, Camion newCamion) {
        newRoutes.set(routeIterator, newCamion);
        Solution newSolution = Solution.builder().routes(newRoutes).fitness(Utils.getFitness(newRoutes)).build();
        if (!result.contains(newSolution)) {
            result.add(newSolution);
        }
    }
}
