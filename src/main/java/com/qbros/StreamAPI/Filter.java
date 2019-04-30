package com.qbros.StreamAPI;

import java.util.stream.Stream;

/**
 * Created by V.Ghasemi
 * on 4/14/2019.
 */
public class Filter {

//    https://www.logicbig.com/tutorials/core-java-tutorial/java-util-stream/lazy-evaluation.html
    public static void main(String[] args) {
        //filter does not change the type of the stream
        Stream.of(1,2,3).filter(i -> i>2).forEach(System.out::println);
        //map function may change the type of stream
        Stream.of(1,2,3).map(i -> "Mapped value:"+ String.valueOf(i)).forEach(System.out::println);
        Stream.of(1,2,3).sorted().forEach(System.out::println);
    }
}
