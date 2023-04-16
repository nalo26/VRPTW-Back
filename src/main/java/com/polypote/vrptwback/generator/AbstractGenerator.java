package com.polypote.vrptwback.generator;

import com.polypote.vrptwback.client.DataSender;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;

public abstract class AbstractGenerator {

    abstract void generate(Root root);

    public void sendData(Solution solution) {
        DataSender.senDataToFront(solution);
    }
}
