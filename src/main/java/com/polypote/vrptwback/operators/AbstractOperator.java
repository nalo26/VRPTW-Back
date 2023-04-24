package com.polypote.vrptwback.operators;

import com.polypote.vrptwback.model.Solution;

import java.util.List;
import java.util.Random;

public abstract class AbstractOperator {

    protected Random random = new Random();

    public abstract List<Solution> getNeighbours(Solution solution);
}
