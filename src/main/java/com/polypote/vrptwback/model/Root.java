package com.polypote.vrptwback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Root {
    @JsonProperty("headers")
    Headers headers;
    @JsonProperty("depots")
    List<Depot> depots;

    @JsonProperty("clients")
    List<Client> clients;
}
