package com.polypote.vrptwback.operators;


import com.polypote.vrptwback.operators.exchange.ExchangeOperatorInter;
import com.polypote.vrptwback.operators.exchange.ExchangeOperatorIntra;
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
            put(new ImmutablePair<>("exchange", "intra"), new ExchangeOperatorIntra());
            put(new ImmutablePair<>("exchange", "inter"), new ExchangeOperatorInter());
        }};

    }
}
