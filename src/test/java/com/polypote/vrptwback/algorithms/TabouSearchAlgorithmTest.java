package com.polypote.vrptwback.algorithms;

import com.polypote.vrptwback.model.Solution;
import com.polypote.vrptwback.operators.relocate.IntraRelocateOperator;
import org.junit.jupiter.api.Test;

import static com.polypote.vrptwback.operators.OperatorsTestUtils.createClient;
import static com.polypote.vrptwback.operators.OperatorsTestUtils.createIntraSolution;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class TabouSearchAlgorithmTest {

    @Test
    void should_retrieve_the_best_solution_from_tabou_search() {
        Solution initialSolution = given_a_solution_with_five_clients();
        Solution expectedResult = given_a_solution_after_tabou_search();
        TabouSearchAlgorithm algorithm = new TabouSearchAlgorithm(10, singletonList(new IntraRelocateOperator()), 4);

        Solution result = algorithm.resolve(initialSolution);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    private Solution given_a_solution_after_tabou_search() {
        return createIntraSolution(asList(
                createClient("d1", 0, 0),
                createClient("c1", 10, 20),
                createClient("c2", 20, 20),
                createClient("c5", 20, 10),
                createClient("c4", 15, 5),
                createClient("c3", 5, 2),
                createClient("d1", 0, 0)
        ), 60);
    }

    private Solution given_a_solution_with_five_clients() {
        return createIntraSolution(asList(
                createClient("d1", 0, 0),
                createClient("c1", 10, 20),
                createClient("c5", 20, 10),
                createClient("c4", 15, 5),
                createClient("c2", 20, 20),
                createClient("c3", 5, 2),
                createClient("d1", 0, 0)
        ), 60);
    }

}