package com.polypote.vrptwback.operators.exchange;

import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

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
        return asList(Solution.builder()
                        .routes(asList(
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
                                                        .y(50)
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
                                        )
                                        ))
                                        .distance(100)
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
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )
                                        ))
                                        .distance(20)
                                        .build()
                        ))
                        .fitness(120)
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
                                                        .id_name("c3")
                                                        .x(0)
                                                        .y(50)
                                                        .build(),
                                                Client.builder()
                                                        .id_name("d1")
                                                        .x(0)
                                                        .y(0)
                                                        .build()
                                        )
                                        ))
                                        .distance(100)
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
                                        )
                                        ))
                                        .distance(40)
                                        .build()
                        ))
                        .fitness(140)
                        .build());
    }

    private Solution given_a_solution_with_three_clients_and_two_camions() {
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
                                                .id_name("c2")
                                                .x(0)
                                                .y(20)
                                                .build(),
                                        Client.builder()
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )
                                ))
                                .distance(60)
                                .build(),
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
                                                .y(50)
                                                .build(),
                                        Client.builder()
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )
                                ))
                                .distance(100)
                                .build()
                ))
                .fitness(160)
                .build();
    }

    private List<Solution> given_a_solution_with_two_routes_and_two_clients_reversed() {
        return singletonList(Solution.builder()
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
                                                .y(10)
                                                .build(),
                                        Client.builder()
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )
                                ))
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
                                                .id_name("c1")
                                                .x(10)
                                                .y(0)
                                                .build(),
                                        Client.builder()
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )
                                ))
                                .distance(20)
                                .build()
                ))
                .fitness(40)
                .build());
    }

    public Solution given_a_solution_with_two_routes_and_two_clients() {
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
                                                .x(10)
                                                .y(0)
                                                .build(),
                                        Client.builder()
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )
                                ))
                                .distance(10)
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
                                                .y(10)
                                                .build(),
                                        Client.builder()
                                                .id_name("d1")
                                                .x(0)
                                                .y(0)
                                                .build()
                                )
                                ))
                                .distance(10)
                                .build()
                ))
                .fitness(20)
                .build();
    }

}