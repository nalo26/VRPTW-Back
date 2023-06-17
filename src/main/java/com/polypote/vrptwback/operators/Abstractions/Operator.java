package com.polypote.vrptwback.operators.Abstractions;

import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Solution;

import java.util.List;

public interface Operator {

    List<Solution> getNeighbours(Solution solution);

    default boolean checkRoute(Camion newCamion) {
        for (int iterator = 1; iterator < newCamion.getRoute().size() - 1; iterator++) {
            final Client currentClient = newCamion.getRoute().get(iterator);
            if (newCamion.getCurrentCamionTime() < currentClient.getReady_time()) {
                newCamion.setCurrentCamionTime(currentClient.getReady_time());
            }
            if (!newCamion.checkTimeWindow(currentClient)) {
                return false;
            }
            newCamion.setCurrentCamionTime(newCamion.getCurrentCamionTime() + currentClient.getService());
        }
        newCamion.setCurrentCamionTime(0);
        return true;
    }


}
