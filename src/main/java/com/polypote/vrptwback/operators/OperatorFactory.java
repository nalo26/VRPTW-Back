package com.polypote.vrptwback.operators;


import com.polypote.vrptwback.operators.exchange.InterExchangeOperator;
import com.polypote.vrptwback.operators.exchange.IntraExchangeOperator;
import com.polypote.vrptwback.operators.relocate.IntraRelocateOperator;
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
        return operatorMap.get(new ImmutablePair<>(operatorName, type));
    }

    @PostConstruct
    public void fillMap() {
        operatorMap = new HashMap<>() {{
            put(new ImmutablePair<>("exchange", "intra"), new IntraExchangeOperator());
            put(new ImmutablePair<>("exchange", "inter"), new InterExchangeOperator());
            put(new ImmutablePair<>("relocate", "intra"), new IntraRelocateOperator());
        }};

    }
}
