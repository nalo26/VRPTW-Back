package com.polypote.vrptwback.generator;

import com.polypote.vrptwback.Utils;
import com.polypote.vrptwback.model.Camion;
import com.polypote.vrptwback.model.Client;
import com.polypote.vrptwback.model.Root;
import com.polypote.vrptwback.model.Solution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class RandomSolutionGenerator extends AbstractGenerator {

    private final Random random = new Random();

    public Solution generate(final Root root) {
        final List<Camion> result = new ArrayList<>();
        final List<Client> clients = new ArrayList<>(root.getClients());
        Client depot = clients.remove(0);
        Collections.shuffle(clients);
        Camion currentCamion = new Camion(depot);

        for (var client : clients) {
            if (!currentCamion.addClient(client, root)) {
                result.add(currentCamion);
                currentCamion = new Camion(depot);
                currentCamion.addClient(client, root);
            }
        }
        result.add(currentCamion);
        return Solution.builder().routes(result).fitness(Utils.getFitness(result)).build();
    }

}
