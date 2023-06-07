package com.polypote.vrptwback.algorithms;

import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.Operator;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import static com.polypote.vrptwback.Utils.getBestSolution;

@AllArgsConstructor
public class TabouSearchAlgorithm extends AbstractAlgorithm {

    private final int maxIter;
    private List<Operator> operatorsToUse;
    private int tabouListSize;

    @Override
    public Solution resolve(Solution x0) {
        Random random = new Random();
        Solution xMin = x0;
        int fMin = xMin.fitness();
        Solution xI = x0;
        Queue<Solution> tabouList = new LinkedList<>();
        for (int iterator = 0; iterator < maxIter; iterator++) {
            List<Solution> neighbours = operatorsToUse.get(random.nextInt(operatorsToUse.size())).getNeighbours(xI);
            List<Solution> solutionsWithoutTabous = CollectionUtils.subtract(neighbours, tabouList, substractPredicate(tabouList)).stream().toList();
            Solution bestSolution = getBestSolution(solutionsWithoutTabous);
            if (bestSolution.fitness() - xI.fitness() > 0) {
                if (tabouList.size() == tabouListSize) {
                    tabouList.poll();
                }
                tabouList.add(bestSolution);
            }
            if (bestSolution.fitness() < fMin) {
                xMin = bestSolution;
                fMin = bestSolution.fitness();
            }
            xI = bestSolution;
        }
        return xMin;
    }

    private Predicate<Solution> substractPredicate(Queue<Solution> tabouList) {
        return solution -> tabouList.stream().anyMatch(currentTabou -> currentTabou.routes().get(0).getRoute().equals(solution.routes().get(0).getRoute()));
    }
}
