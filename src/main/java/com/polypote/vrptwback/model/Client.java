package com.polypote.vrptwback.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Client {
    String id_name;
    int x;
    int y;
    int ready_time;
    int due_time;
    int demand;
    int service;

}
