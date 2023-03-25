package com.polypote.vrptwback.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Headers {
    @JsonProperty("NAME")
    String name;
    @JsonProperty("COMMENT")
    String comment;
    @JsonProperty("TYPE")
    String type;
    @JsonProperty("COORDINATES")
    String coordinates;
    @JsonProperty("NB_DEPOTS")
    int nbDepots;
    @JsonProperty("NB_CLIENTS")
    int nbClients;
    @JsonProperty("MAX_QUANTITY")
    int maxQuantity;
}
