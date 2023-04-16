package com.polypote.vrptwback.generator;

import com.polypote.vrptwback.controller.MainController;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;

public abstract class AbstractGenerator {

    abstract void generate(Root root);

    public void sendData(Solution solution) {
        MainController controller = new MainController();
        controller.sendDataToFront(solution);
    }
}
