package com.polypote.vrptwback.operators.exchange;

import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.polypote.vrptwback.operators.OperatorsTestUtils.createIntraSolution;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;


class IntraExchangeOperatorTest {

    @Test
    void should_exchange_a_solution_with_two_clients() {
        Solution givenSolution = given_a_solution_with_two_clients();
        List<Solution> expectedResult = get_expected_result_for_two_clients();
        IntraExchangeOperator operator = new IntraExchangeOperator();

        List<Solution> result = operator.getNeighbours(givenSolution);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    @Test
    void should_exchange_a_solution_with_three_clients() {
        Solution givenSolution = given_a_solution_with_three_clients();
        List<Solution> expectedResult = get_expected_result_for_three_clients();

        IntraExchangeOperator operator = new IntraExchangeOperator();

        List<Solution> result = operator.getNeighbours(givenSolution);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    private List<Solution> get_expected_result_for_three_clients() {

        return asList(
                createIntraSolution(asList(
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build(),
                        Client.builder()
                                .id_name("c2")
                                .x(0)
                                .y(6)
                                .build(),
                        Client.builder()
                                .id_name("c1")
                                .x(0)
                                .y(3)
                                .build(),
                        Client.builder()
                                .id_name("c3")
                                .x(4)
                                .y(3)
                                .build(),
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build()
                ), 18),
                createIntraSolution(asList(
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build(),
                        Client.builder()
                                .id_name("c3")
                                .x(4)
                                .y(3)
                                .build(),
                        Client.builder()
                                .id_name("c2")
                                .x(0)
                                .y(6)
                                .build(),
                        Client.builder()
                                .id_name("c1")
                                .x(0)
                                .y(3)
                                .build(),

                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build()
                ), 16),
                createIntraSolution(asList(
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build(),
                        Client.builder()
                                .id_name("c1")
                                .x(0)
                                .y(3)
                                .build(),
                        Client.builder()
                                .id_name("c3")
                                .x(4)
                                .y(3)
                                .build(),
                        Client.builder()
                                .id_name("c2")
                                .x(0)
                                .y(6)
                                .build(),
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build()
                ), 18)
        );
    }

    private Solution given_a_solution_with_three_clients() {
        return createIntraSolution(asList(Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build(),
                Client.builder()
                        .id_name("c1")
                        .x(0)
                        .y(3)
                        .build(),
                Client.builder()
                        .id_name("c2")
                        .x(0)
                        .y(6)
                        .build(),
                Client.builder()
                        .id_name("c3")
                        .x(4)
                        .y(3)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 0);
    }

    private List<Solution> get_expected_result_for_two_clients() {
        return singletonList(createIntraSolution(asList(
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build(),
                Client.builder()
                        .id_name("c2")
                        .x(20)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c1")
                        .x(10)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 56));
    }

    private Solution given_a_solution_with_two_clients() {
        return createIntraSolution(asList(
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build(),
                Client.builder()
                        .id_name("c1")
                        .x(10)
                        .y(10)
                        .build(),
                Client.builder()
                        .id_name("c2")
                        .x(20)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 0);
    }
}