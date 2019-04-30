package com.qbros;

import java.util.stream.IntStream;

/**
 * Created by V.Ghasemi
 * on 4/14/2019.
 */
public class ExamplesInJava8 {

    public static void main(String[] args) {

    }


    public static void fizzbuzz(){
        //fizbuzz example
        IntStream.range(1, 100).mapToObj(i ->{
            String r="";
            if (i% 15 == 0) r="FizzBuzz";
            else if (i % 3 == 0) r="Fizz";
            else if (i % 5 == 0) r="Buzz";
            else r=""+i;
            return r;
        } ).forEach(System.out::println);
    }
}
