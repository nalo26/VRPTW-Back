package com.polypote.vrptwback.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polypote.vrptwback.model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class RandomSolutionGeneratorTest {

    @Test
    public void should_return_the_correct_distance() {
        LinkedList<Client> clientList = given_a_client_list_with_two_clients();
        RandomSolutionGenerator generator = new RandomSolutionGenerator();

        int distance = generator.getDistance(clientList);

        assertThat(distance).isEqualTo(14);
    }

    @Test
    public void should_return_a_fitness_of_40() {
        Root root = given_a_root_from_file();
        RandomSolutionGenerator generator = new RandomSolutionGenerator();

        Solution solution = generator.generate(root);

        assertThat(solution.fitness()).usingRecursiveComparison().isEqualTo(50);
    }

    @Test
    public void should_generate_a_solution() {
        Root root = given_a_root_with_two_clients();
        Solution expectedSolution = given_a_solution_with_two_clients();
        RandomSolutionGenerator generator = new RandomSolutionGenerator();

        Solution solution = generator.generate(root);

        assertThat(solution).usingRecursiveComparison().isEqualTo(expectedSolution);
    }

    private Solution given_a_solution_with_two_clients() {
        return Solution.builder()
                .routes(List.of(new Camion(new LinkedList<>() {{
                    Client.builder().id_name("c1")
                            .x(0)
                            .y(10)
                            .ready_time(161)
                            .due_time(171)
                            .demand(10)
                            .service(10)
                            .build();
                    Client.builder()
                            .id_name("c2")
                            .x(0)
                            .y(20)
                            .ready_time(50)
                            .due_time(60)
                            .demand(7)
                            .service(10).build();
                }}, 40)))
                .fitness(40)
                .build();
    }

    private Root given_a_root_from_file() {
        try {

            return new ObjectMapper().readValue(new File("src/test/resources/test_data.json"), Root.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Root given_a_root_with_two_clients() {
        return Root.builder()
                .headers((Headers.builder()
                        .name("data101.vrp")
                        .comment("")
                        .type("vrptw")
                        .coordinates("cartesian")
                        .nbDepots(1)
                        .nbClients(100)
                        .maxQuantity(200)
                        .build()))
                .clients(asList(
                        Client.builder()
                                .id_name("d1")
                                .x(0)
                                .y(0)
                                .ready_time(0)
                                .due_time(230)
                                .build(),
                        Client.builder()
                                .id_name("c1")
                                .x(0)
                                .y(10)
                                .ready_time(161)
                                .due_time(171)
                                .demand(10)
                                .service(10)
                                .build(),
                        Client.builder()
                                .id_name("c2")
                                .x(0)
                                .y(20)
                                .ready_time(50)
                                .due_time(60)
                                .demand(7)
                                .service(10)
                                .build()
                ))
                .build();
    }

    private LinkedList<Client> given_a_client_list_with_two_clients() {
        return new LinkedList<>(List.of(Client.builder().x(10).y(10).build(), Client.builder().x(20).y(20).build()));
    }
}