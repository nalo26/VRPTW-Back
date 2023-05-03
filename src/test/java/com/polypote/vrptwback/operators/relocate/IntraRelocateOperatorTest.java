package com.polypote.vrptwback.operators.relocate;

import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
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
                Solution.builder()
                        .routes(singletonList(
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
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
                                        )))
                                        .distance(80)
                                        .build()
                        ))
                        .fitness(80)
                        .build(),
                Solution.builder()
                        .routes(singletonList(
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
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
                                        )))
                                        .distance(60)
                                        .build()
                        ))
                        .fitness(60)
                        .build(),
                Solution.builder()
                        .routes(singletonList(
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
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
                                        )))
                                        .distance(60)
                                        .build()
                        ))
                        .fitness(60)
                        .build(),
                Solution.builder()
                        .routes(singletonList(
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
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
                                        )))
                                        .distance(80)
                                        .build()
                        ))
                        .fitness(80)
                        .build()
        );
    }

    private Solution given_a_solution_with_three_client() {
        return Solution.builder()
                .routes(singletonList(
                        Camion.builder()
                                .route(new LinkedList<>(asList(
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
                                )))
                                .distance(60)
                                .build()
                ))
                .fitness(60)
                .build();
    }
}