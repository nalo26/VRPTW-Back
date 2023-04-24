package com.polypote.vrptwback.operators;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExchangeOperator extends AbstractOperator {


    @Override
    public List<Solution> getNeighbours(Solution solution) {
        List<Solution> result = new ArrayList<>();
        for (int solutionIterator = 0; solutionIterator < solution.routes().size(); solutionIterator++) {
            final LinkedList<Camion> newRoutes = new LinkedList<>(solution.routes());
            Camion currentCamion = newRoutes.get(solutionIterator);

            int index1 = random.nextInt(0, currentCamion.clients().size());
            int index2 = random.nextInt(0, currentCamion.clients().size());

            Client client1 = currentCamion.clients().get(index1);
            Client client2 = currentCamion.clients().get(index2);

            currentCamion.clients().set(index1, client2);
            currentCamion.clients().set(index2, client1);


            Camion newCamion = Camion.builder().clients(new LinkedList<>(currentCamion.clients())).distance(Utils.getDistance(currentCamion.clients())).build();
            newRoutes.set(solutionIterator, newCamion);
            Solution newSolution = Solution.builder().routes(newRoutes).fitness(Utils.getFitness(newRoutes)).build();
            result.add(newSolution);
        }
        return result;
    }
}
