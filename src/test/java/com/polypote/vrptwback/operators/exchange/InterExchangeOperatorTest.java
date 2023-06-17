package com.polypote.vrptwback.operators.exchange;

import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.polypote.vrptwback.operators.OperatorsTestUtils.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class InterExchangeOperatorTest {


    @Test
    void should_exchange_the_two_clients_inter_the_two_given_camion() {
        Solution solutionWithTwoCamionAndTwoClients = given_a_solution_with_two_routes_and_two_clients();
        List<Solution> expectedResult = given_a_solution_with_two_routes_and_two_clients_reversed();

        InterExchangeOperator interExchangeOperator = new InterExchangeOperator();

        List<Solution> result = interExchangeOperator.getNeighbours(solutionWithTwoCamionAndTwoClients);


        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void should_exchange_the_three_clients_between_them_with_two_given_camion() {
        Solution solutionWithTwoCamionAndThreeClients = given_a_solution_with_three_clients_and_two_camions();
        List<Solution> expectedResult = given_two_solutions_with_exchanged_clients();

        InterExchangeOperator interExchangeOperator = new InterExchangeOperator();

        List<Solution> result = interExchangeOperator.getNeighbours(solutionWithTwoCamionAndThreeClients);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    private List<Solution> given_two_solutions_with_exchanged_clients() {
        return asList(createInterSolution(asList(
                        createCamion(asList(
                                createClient("d1", 0, 0, 0, 230),
                                createClient("c3", 0, 50, 100, 110),
                                createClient("c2", 0, 20, 30, 40),
                                createClient("d1", 0, 0, 0, 230)
                        ), 100),
                        createCamion(asList(
                                createClient("d1", 0, 0, 0, 230),
                                createClient("c1", 0, 10, 10, 20),
                                createClient("d1", 0, 0, 0, 230)
                        ), 20)
                ), 120),
                createInterSolution(asList(
                        createCamion(asList(
                                createClient("d1", 0, 0, 0, 230),
                                createClient("c1", 0, 10, 10, 20),
                                createClient("c3", 0, 50, 100, 110),
                                createClient("d1", 0, 0, 0, 230)
                        ), 100),
                        createCamion(asList(
                                createClient("d1", 0, 0, 0, 230),
                                createClient("c2", 0, 20, 30, 40),
                                createClient("d1", 0, 0, 0, 230)
                        ), 40)
                ), 140));
    }

    private Solution given_a_solution_with_three_clients_and_two_camions() {
        return createInterSolution(asList(
                createCamion(asList(
                        createClient("d1", 0, 0, 0, 230),
                        createClient("c1", 0, 10, 10, 20),
                        createClient("c2", 0, 20, 30, 40),
                        createClient("d1", 0, 0, 0, 230)

                ), 60),
                createCamion(asList(
                        createClient("d1", 0, 0, 0, 230),
                        createClient("c3", 0, 50, 100, 110),
                        createClient("d1", 0, 0, 0, 230)
                ), 100)
        ), 160);
    }

    private List<Solution> given_a_solution_with_two_routes_and_two_clients_reversed() {
        return singletonList(createInterSolution(asList(
                createCamion(asList(
                        createClient("d1", 0, 0),
                        createClient("c2", 0, 10),
                        createClient("d1", 0, 0)
                ), 20),
                createCamion(asList(
                        createClient("d1", 0, 0),
                        createClient("c1", 10, 0),
                        createClient("d1", 0, 0)
                ), 20)
        ), 40));
    }

    public Solution given_a_solution_with_two_routes_and_two_clients() {
        return createInterSolution(asList(
                createCamion(asList(
                        createClient("d1", 0, 0),
                        createClient("c1", 10, 0),
                        createClient("d1", 0, 0)
                ), 10),
                createCamion(asList(
                        createClient("d1", 0, 0),
                        createClient("c2", 0, 10),
                        createClient("d1", 0, 0)
                ), 10)
        ), 20);
    }

}