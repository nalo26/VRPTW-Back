package com.polypote.vrptwback;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Headers;
import com.polypote.vrptwback.model.Root;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

import static java.util.Arrays.asList;

@SpringBootTest
public class ModelTest {

    @Test
    public void should_read_root_object_from_file() throws IOException {
        final Root expected = given_a_root_object();
        final ObjectMapper objectMapper = new ObjectMapper();
        final Root root = objectMapper.readValue(new File("src/test/resources/test_data.json"), Root.class);

        Assertions.assertThat(root).usingRecursiveComparison().isEqualTo(expected);
    }

    private Root given_a_root_object() {
        return Root.builder()
                .headers(Headers.builder()
                        .name("data101.vrp")
                        .comment("")
                        .type("vrptw")
                        .coordinates("cartesian")
                        .nbDepots(1)
                        .nbClients(100)
                        .maxQuantity(200)
                        .build())
                .clients(asList(
                        Client.builder()
                                .id_name("d1")
                                .x(35)
                                .y(35)
                                .ready_time(0)
                                .due_time(230)
                                .build(),
                        Client.builder()
                                .id_name("c1")
                                .x(41)
                                .y(49)
                                .ready_time(161)
                                .due_time(171)
                                .demand(10)
                                .service(10)
                                .build(),
                        Client.builder()
                                .id_name("c2")
                                .x(35)
                                .y(17)
                                .ready_time(50)
                                .due_time(60)
                                .demand(7)
                                .service(10)
                                .build()
                ))
                .build();
    }
}
