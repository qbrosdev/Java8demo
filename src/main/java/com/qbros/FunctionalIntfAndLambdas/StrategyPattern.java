package com.qbros.FunctionalIntfAndLambdas;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by V.Ghasemi
 * on 6/1/2019.
 */
public class StrategyPattern {

    public void PerformOperationOnCollection(){

    }

    public int totalValues (List<Integer> list, Predicate<Integer> selector){
        return list.stream()
                .filter(selector)
                .reduce(0,(c, e) -> c + e);
    }
}
