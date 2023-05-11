package com.polypote.vrptwback.operators.twoOpt;

import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.polypote.vrptwback.operators.OperatorsTestUtils.createIntraSolution;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class IntraTwoOptOperatorTest {

    @Test
    void should_apply_two_opt_operator_on_a_route_with_four_clients() {
        Solution solutionWithFourClients = given_a_solution_with_four_clients();
        List<Solution> expectedResult = given_a_solution_with_the_two_opt_applied();
        IntraTwoOptOperator twoOptOperator = new IntraTwoOptOperator();
        List<Solution> result = twoOptOperator.getNeighbours(solutionWithFourClients);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void should_apply_two_opt_operator_on_a_route_with_four_clients_reversed_order() {
        Solution solutionWithFourClientsReversed = given_a_solution_with_four_clients_reversed();
        List<Solution> expectedResult = given_a_solution_with_the_two_opt_applied_reversed();

        IntraTwoOptOperator twoOptOperator = new IntraTwoOptOperator();
        List<Solution> result = twoOptOperator.getNeighbours(solutionWithFourClientsReversed);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    private List<Solution> given_a_solution_with_the_two_opt_applied_reversed() {
        return singletonList(createIntraSolution(asList(
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build(),
                Client.builder()
                        .id_name("c4")
                        .x(10)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("c3")
                        .x(10)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c2")
                        .x(0)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c1")
                        .x(0)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 54));
    }

    private Solution given_a_solution_with_four_clients_reversed() {
        return createIntraSolution(asList(
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build(),
                Client.builder()
                        .id_name("c4")
                        .x(10)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("c2")
                        .x(0)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c3")
                        .x(10)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c1")
                        .x(0)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 40);
    }

    private List<Solution> given_a_solution_with_the_two_opt_applied() {
        return singletonList(createIntraSolution(asList(
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build(),
                Client.builder()
                        .id_name("c1")
                        .x(0)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("c2")
                        .x(0)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c3")
                        .x(10)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c4")
                        .x(10)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 54));
    }

    private Solution given_a_solution_with_four_clients() {
        return createIntraSolution(asList(
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build(),
                Client.builder()
                        .id_name("c1")
                        .x(0)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("c3")
                        .x(10)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c2")
                        .x(0)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c4")
                        .x(10)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 40);
    }

}