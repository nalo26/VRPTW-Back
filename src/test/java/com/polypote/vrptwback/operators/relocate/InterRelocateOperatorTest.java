package com.polypote.vrptwback.operators.relocate;

import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class InterRelocateOperatorTest {

    @Test
    void should_inter_relocate_two_camions_with_one_client_in_each() {
        Solution givenSolution = given_a_solution_with_two_camions_with_one_client_in_each();
        List<Solution> expectedResult = given_a_list_of_relocated_solutions();

        InterRelocateOperator interRelocateOperator = new InterRelocateOperator();
        List<Solution> result = interRelocateOperator.getNeighbours(givenSolution);

        assertThat(result).usingRecursiveComparison().isEqualTo(expectedResult);
    }

    private List<Solution> given_a_list_of_relocated_solutions() {
        return asList(
                Solution.builder()
                        .routes(asList(
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build(),
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(0)
                                        .build(),
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
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(40)
                                        .build()
                        ))
                        .fitness(40)
                        .build(),
                Solution.builder()
                        .routes(asList(
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build(),
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(0)
                                        .build(),
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
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(40)
                                        .build()
                        ))
                        .fitness(40)
                        .build(),
                Solution.builder()
                        .routes(asList(
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
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(40)
                                        .build(),
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build(),
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(0)
                                        .build()
                        ))
                        .fitness(40)
                        .build(),
                Solution.builder()
                        .routes(asList(
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
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(40)
                                        .build(),
                                Camion.builder()
                                        .route(new LinkedList<>(asList(
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build(),
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )))
                                        .distance(0)
                                        .build()
                        ))
                        .fitness(40)
                        .build()
        );
    }

    private Solution given_a_solution_with_two_camions_with_one_client_in_each() {
        return Solution.builder()
                .routes(asList(
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
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )))
                                .distance(20)
                                .build(),
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
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )))
                                .distance(40)
                                .build()
                ))
                .fitness(60)
                .build();
    }
}