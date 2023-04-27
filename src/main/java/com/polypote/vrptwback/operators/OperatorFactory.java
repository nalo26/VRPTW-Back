package com.polypote.vrptwback.operators;


import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OperatorFactory {

    private Map<Pair<String, String>, AbstractOperator> operatorMap;

    public AbstractOperator createOperator(String operatorName, String type) {
        return operatorMap.get(new ImmutablePair<>("exchange", "intra"));
    }

    @PostConstruct
    public void fillMap() {
        operatorMap = new HashMap<>() {{
            put(new ImmutablePair<>("exchange", "intra"), new ExchangeOperator());
        }};

    }
}
