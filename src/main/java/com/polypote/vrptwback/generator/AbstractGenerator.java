package com.polypote.vrptwback.generator;

import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;

public abstract class AbstractGenerator {

    abstract Solution generate(Root root);
}
