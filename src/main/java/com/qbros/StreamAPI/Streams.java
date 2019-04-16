package com.qbros.StreamAPI;

import com.qbros.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.StrictMath.sqrt;
import static java.util.stream.LongStream.rangeClosed;

/**
 * Created by V.Ghasemi
 * on 4/14/2019.
 */
public class Streams {


    public static void main(String[] args) {

        /*
         * NOTE that you cant do concise form here  Stream.of({"d"}) //exception
         * concise form is only allowed at array init time
         * private int[] values1 = new int[]{1,2,3,4};
         * private int[] values2 = {1,2,3,4}; // short form is allowed only at variable initialization
         * */
        Stream.of(new String[] {"d"}).forEach(System.out::println);

        /*
        * you can only convert an array of Objs not primitives to Stream
        * int[] values = { 1, 4, 9, 16 };
        * but you can do this ; Stream.of(new int[]{1,2,3})
        * Stream<Integer> ints = Stream.of(values);
        * */
        //streams work with objs not primitives
        Stream.of("","df","dd","","f","").forEach(System.out::println);

        //NOTE: it will see the whole array as one obj
        Stream.of(new int[]{1,2,3});

        //iw will generate 3 separate obj in the stream
        Stream.of(new Integer[]{1,2,3});

        //it will use autoboxing to convert premitives to wrapper types
        Stream.of(1,2,3);

        /*
        * Stream of primitives vs Primitive Wrapper types stream (e.g Stream.of(1,2,3) vs IntStream.of(1,2,3))
        *
        * 1* WrapperTypeStreams have special methods: summaryStatistics
        *
        * 2* WrapperTypeStreams do all their middle operations unboxed an therefore it takes less time (https://stackoverflow.com/a/35215907/3593084)
        *
        * */

        //Random streams
        Random random = new Random();
        /*
        * This will create an infinit stream of (numbers form 1 to 10).
        * and because there is no short circut operation to consume the stream, it will go for ever.
        * findAny: is a short circuit operator and it will end if it finds
        *
        * */
       // random.ints(1,10+1).forEach(System.out::println);

        /* can create the random of all primitives
        random.doubles();
        random.longs();
        random.ints();
        */

        random.ints(1,10+1).limit(6).forEach(System.out::println);

        //----------------------------------Array to Collections----------------------------------------------
        Person[] personArray = new Person[5];

        for (int i = 0; i <personArray.length ; i++) {
            personArray[i] = new Person(i,"name"+i);
        }

        List<Person> personList = Arrays.stream(personArray).collect(Collectors.toList());


        //----------------------------------Stream from collections--------------------------------------------
        /*
        * NOTE: this will create an stream of one obj which is the whole array
        * */
        Stream.of(personList).forEach(System.out::println);
        //correct way:
        personList.stream().forEach(System.out::println);

        //----------------------------------Parallel Streams---------------------------------------------------
        //does parallel streams block eachother https://dzone.com/articles/think-twice-using-java-8

        countPrimes();
    }


    private static long countPrimes() {
        return IntStream.range(1,7).parallel().map(Streams::possibleLongTask).count();
    }


    private static int possibleLongTask(int n) {
        if (n==5) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return n;
    }
}
