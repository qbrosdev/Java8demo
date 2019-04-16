package com.qbros.StreamAPI;

import com.qbros.Person;

import java.util.LongSummaryStatistics;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by V.Ghasemi
 * on 4/14/2019.
 */
public class UtillFunctionsOnStreams {

    public static void main(String[] args) {

        //-----------------------------------Sorting------------------------------------------
        /*
        * Sorting: for primitives it will use the default comparator defined
        * in their wrapper class. for custom class objects we should define a comparator
        * */
        Stream.of("","df","dd","","f","").sorted().forEach(System.out::println);

        //Custom comparator
        Stream.of(new Person(123,"aa"),new Person(1,"bb"))
                .sorted((o1, o2) -> o2.getName().compareTo(o1.getName())).forEach(System.out::println);


        //---------------------------------Cheeking a condition---------------------------------------------
        //this is a short circuit operation, it will break if it finds any matches
        boolean answer = Stream.of("","df","dd","","f","").anyMatch(s -> Optional.ofNullable(s).orElse("").length()==3);
        //all elements shoul be cheeked
        boolean answer2 = Stream.of("","df","dd","","f","").allMatch(s -> Optional.ofNullable(s).orElse("").length()==3);
        // also
        boolean answer3 = Stream.of("","df","dd","","f","").noneMatch(s -> Optional.ofNullable(s).orElse("").length()==3);


        //---------------------------------Finding-----------------------------------------------------------
        Optional optionalResult = Stream.of("","df","dd","","f","").findAny();
        Optional optionalResult2 = Stream.of("","df","dd","","f","").findFirst();

        //-----------------------------------Debugging--------------------------------------------------------
        /*peek is for logging it is for watching the values as it is processing
        * Note that peek is a not a terminal operation and because streams are lazily evaluated.
        * Therefore, if we dont add treminal operators then the stream will not start.
        *
        * */
        Stream.of("$$","$df","$dd","$","$f","").peek(System.out::println).count();

        //----------------------------------the Rest
        /*
        * 3 summary statistics classes for Double, Long, and Integer primitive data types.
        * The java.util package contains three classes to summarizing data and return a summary statistics object
        * to get count, min, max, sum, and average value.
        * DoubleSummaryStatistics
        * LongSummaryStatistics
        * IntSummaryStatistics
        * */

        //summery statistics --------------------
        LongSummaryStatistics stats = new LongSummaryStatistics();
        stats.accept(1);
        stats.accept(2);
        stats.accept(3);

        System.out.println("Count:- " + stats.getCount());
        System.out.println("Sum:- " + stats.getSum());
        System.out.println("Min:- " + stats.getMin());
        System.out.println("Max:- " + stats.getMax());
        System.out.println("Average:- " + stats.getAverage());

        //get summery statistics form stream
        Stream.of(new Integer(1));
        IntStream.builder().add(12).add(13).add(9).build().summaryStatistics();
        Stream.builder().add(1).add(2).build();
    }
}



