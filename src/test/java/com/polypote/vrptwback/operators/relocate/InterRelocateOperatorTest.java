package com.polypote.vrptwback.operators.relocate;

import com.polypote.vrptwback.model.Solution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.polypote.vrptwback.operators.OperatorsTestUtils.*;
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
                createInterSolution(asList(
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("d1", 0, 0)
                        ), 0),
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("c1", 0, 10),
                                createClient("c2", 0, 20),
                                createClient("d1", 0, 0)
                        ), 40)
                ), 40),
                createInterSolution(asList(
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("d1", 0, 0)
                        ), 0),
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("c2", 0, 20),
                                createClient("c1", 0, 10),
                                createClient("d1", 0, 0)
                        ), 40)
                ), 40),
                createInterSolution(asList(
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("c2", 0, 20),
                                createClient("c1", 0, 10),
                                createClient("d1", 0, 0)
                        ), 40),
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("d1", 0, 0)
                        ), 0)
                ), 40),
                createInterSolution(asList(
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("c1", 0, 10),
                                createClient("c2", 0, 20),

                                createClient("d1", 0, 0)
                        ), 40),
                        createCamion(asList(
                                createClient("d1", 0, 0),
                                createClient("d1", 0, 0)
                        ), 0)
                ), 40)
        );
    }

    private Solution given_a_solution_with_two_camions_with_one_client_in_each() {
        return createInterSolution(asList(
                createCamion(asList(
                        createClient("d1", 0, 0),
                        createClient("c1", 0, 10),
                        createClient("d1", 0, 0)
                ), 20),
                createCamion(asList(
                        createClient("d1", 0, 0),
                        createClient("c2", 0, 20),
                        createClient("d1", 0, 0)
                ), 40)
        ), 60);
    }
}