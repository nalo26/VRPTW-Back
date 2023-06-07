package com.polypote.vrptwback.operators;


import com.polypote.vrptwback.operators.Abstractions.Operator;
import com.polypote.vrptwback.operators.exchange.InterExchangeOperator;
import com.polypote.vrptwback.operators.exchange.IntraExchangeOperator;
import com.polypote.vrptwback.operators.relocate.InterRelocateOperator;
import com.polypote.vrptwback.operators.relocate.IntraRelocateOperator;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OperatorFactory {

    private static OperatorFactory INSTANCE;
    private Map<Pair<String, String>, Operator> operatorMap;

    private OperatorFactory() {
    }

    public static OperatorFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OperatorFactory();
        }
        return INSTANCE;
    }

    public Operator createOperator(String operatorName, String type) {
        return operatorMap.get(new ImmutablePair<>(operatorName, type));
    }

    @PostConstruct
    public void fillMap() {
        operatorMap = new HashMap<>() {{
            put(new ImmutablePair<>("exchange", "intra"), new IntraExchangeOperator());
            put(new ImmutablePair<>("exchange", "inter"), new InterExchangeOperator());
            put(new ImmutablePair<>("relocate", "intra"), new IntraRelocateOperator());
            put(new ImmutablePair<>("relocate", "inter"), new InterRelocateOperator());
        }};

    }
}
