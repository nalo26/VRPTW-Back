package com.polypote.vrptwback.model;

import lombok.Builder;

import java.util.List;


@Builder
public record Solution(List<Camion> routes, int fitness) {

    @Override
    public String toString() {
        return "Solution{" +
                "routes=" + routes +
                ", fitness=" + fitness +
                '}';
    }
}
