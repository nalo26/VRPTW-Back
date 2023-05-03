package com.polypote.vrptwback.operators.Abstractions;

import com.polypote.vrptwback.model.Solution;

import java.util.List;

public interface Operator {

    List<Solution> getNeighbours(Solution solution);
}
