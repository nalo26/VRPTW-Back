package com.polypote.vrptwback;

import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.Abstractions.Operator;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.polypote.vrptwback.operators.OperatorFactory.getInstance;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Component
public class Utils {
    public static Integer getFitness(List<Camion> result) {
        return result.stream().map(Camion::getDistance).reduce(0, Integer::sum);
    }

    public static int getDistance(LinkedList<Client> routes) {
        int result = 0;
        for (int clientIterator = 0; clientIterator < routes.size(); clientIterator++) {
            if (clientIterator + 1 < routes.size()) {
                Client current = routes.get(clientIterator);
                Client next = routes.get(clientIterator + 1);
                result += getEuclideanDistance(current.getX(), current.getY(), next.getX(), next.getY());
            }
        }
        return result;
    }

    public static int getEuclideanDistance(int x1, int y1, int x2, int y2) {
        return (int) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
    }

    public static Solution getBestSolution(List<Solution> neighbours) {
        return neighbours.stream().min(Comparator.comparing(Solution::fitness)).orElseThrow(() -> new RuntimeException("Cannot find best solution"));
    }

    public static List<Operator> fromStringList(List<String> methods) {
        return methods.stream()
                .map(method -> method.split(":"))
                .map(split -> getInstance().createOperator(split[0], split[1]))
                .toList();
    }
}
