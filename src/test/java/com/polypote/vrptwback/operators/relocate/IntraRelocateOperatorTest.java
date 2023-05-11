package com.polypote.vrptwback.operators.relocate;

import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.polypote.vrptwback.operators.OperatorsTestUtils.createIntraSolution;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;


class IntraRelocateOperatorTest {

    @Test
    void should_relocate_a_camion_with_three_clients() {
        Solution solutionWithThreeClient = given_a_solution_with_three_client();

        List<Solution> expectedResult = given_a_list_of_relocated_solution();

        IntraRelocateOperator intraRelocateOperator = new IntraRelocateOperator();
        List<Solution> result = intraRelocateOperator.getNeighbours(solutionWithThreeClient);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    private List<Solution> given_a_list_of_relocated_solution() {
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
                                .y(20)
                                .build(),
                        Client.builder()
                                .id_name("c1")
                                .x(0)
                                .y(10)
                                .build(),
                        Client.builder()
                                .id_name("c3")
                                .x(0)
                                .y(30)
                                .build(),
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build()
                ), 80),
                createIntraSolution(asList(
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build(),
                        Client.builder()
                                .id_name("c2")
                                .x(0)
                                .y(20)
                                .build(),
                        Client.builder()
                                .id_name("c3")
                                .x(0)
                                .y(30)
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
                ), 60),
                createIntraSolution(asList(
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
                                .x(0)
                                .y(30)
                                .build(),
                        Client.builder()
                                .id_name("c2")
                                .x(0)
                                .y(20)
                                .build(),
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build()
                ), 60),
                createIntraSolution(asList(
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build(),
                        Client.builder()
                                .id_name("c3")
                                .x(0)
                                .y(30)
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
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .build()
                ), 80)
        );
    }

    private Solution given_a_solution_with_three_client() {
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
                        .id_name("c2")
                        .x(0)
                        .y(20)
                        .build(),
                Client.builder()
                        .id_name("c3")
                        .x(0)
                        .y(30)
                        .build(),
                Client.builder()
                        .id_name("d1")
                        .x(0)
                        .y(0)
                        .build()
        ), 60);
    }
}