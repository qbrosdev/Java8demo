package com.qbros.Sorting;

import com.qbros.Person;
import com.qbros.Point;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by V.Ghasemi
 * on 4/15/2019.
 */
public class Comperators {

    public static void main(String[] args) {


        //------------------------------------------Arrays sort-------------------------------------------------------
        /*
         * -----partial sort
         * Arrays.sort(int[] a, int fromIndex, int toIndex)
         *
         *-----parallel sort
         * Behind the scenes of parallelSort(), it breaks the array into different sub-arrays
         * (as per granularity in the algorithm of parallelSort). Each sub-array is sorted with Arrays.sort() in
         * different threads so that sort can be executed in parallel fashion and are merged finally as a sorted array.
         *
         * Arrays.parallelSort (int [] a, int fromIndex, int toIndex);
         *
         *
         *
         * -----------------------------------------Collections (List,...)sort ------------------------------------------
         * Collections.sort(list)
         *
         * Set<Integer> integersSet= new HashSet<>();
         * Collections.sort(new ArrayList<>(integersSet));
         *
         * */

        //-------------------------------------------------Comparator static methods---------------------------
        Person[] personArray = new Person[5];

        for (int i = 0; i <personArray.length ; i++) {
            personArray[i] = new Person(i,"name"+i);
        }

        //some static mathods are added (in java 8) to comerator interfaces
        Comparator ageComparator = Comparator.comparing(Person::getAge).reversed();
        //sorting array using ageComparator
        Arrays.sort(personArray,ageComparator);
        Stream.of(personArray).forEach(System.out::println);
        //sorting list using ageComparator
        List<Person> personList = Arrays.stream(personArray).collect(Collectors.toList());
        Collections.sort( personList,ageComparator);
        personList.stream().forEach(System.out::println);
        //you can also define custom comparing function
        Comparator customComparator = Comparator.comparing(Person::getName,(o1, o2) -> o1.compareTo(o2)).reversed();

        //-----------Comparator chaining
        // Comparator.comparing(Person::getAge).thenComparing().thenComparing().reversed();

        //---------------------------------Compare primitives -----------------------------------
        /**
         * @see #main(String[] args)
         * https://corochann.com/javadoc-coding-rule-of-link-linkplain-see-372.html
        * */
        Comparator<Point> cmp = Comparator.comparingInt(Point::getX).thenComparingInt(p -> p.y);
    }

//    https://www.baeldung.com/java-8-comparator-comparing
//    https://www.baeldung.com/java-sorting
}

