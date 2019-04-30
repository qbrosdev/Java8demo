package com.qbros.StreamAPI;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

/**
 * Created by V.Ghasemi
 * on 4/30/2019.
 */
public class ConcurrentModification {
    void method(){
        /*
         * Integer intx = new Integer(12);
         * IntStream.range(0, 1000).parallel().forEach(i ->intx++);
         * you cant do this cause variables passed to lambdas need to be (effectively) final.
         * */

        /*
         * To protect thread safety of sharared vars we can use Atomic variables or AtomicReferences.
         * For reading and writing, the locking methanisem is applied (Not very efficient)
         * NOTE: we can have the same effect by applying synchronised methods on volatile variables
         * */
        AtomicInteger atomicInt = new AtomicInteger(0);

        /*
        * In Double/LongAdder each thread (Parallel stream) gets it own copy of the variable and manipulate it
        * the reader will the the summation of all the local thread values, so the locking on write is removes
        * (better performance)
        * */
        LongAdder longAdder = new LongAdder();
        //longAdder.add(); longAdder.sum();

        /*
        * Long/DoubleAccumulator, is a generic version of Adder, in which we can define accumulation funtions other than
        * Simple summation. */
        LongAccumulator longAccumulator = new LongAccumulator((val1, val2) -> val1 + val2,0);
        //longAccumulator.accumulate(); longAccumulator.get();
    }
}
