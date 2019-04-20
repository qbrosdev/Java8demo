package com.qbros.FunctionalIntfAndLambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Created by V.Ghasemi
 * on 4/16/2019.
 *
 * Lambdas is like referencing a method in places you need a method
 */
public class LambdasTest {

//    https://stackoverflow.com/questions/22444731/java-8-maptoint-and-tointfunction-examples
//    https://stackoverflow.com/questions/36253973/comparator-comparingint


    public LambdasTest() {
    }

    public LambdasTest(String input) {
    }

    /**
     * Lambdas expressions are just anonymous methods which are defined inline
     * We can refrence to methods by name whenever we need a lamdas expression.
     * Method type arguments are inferred by JRE at runtime from the context it is defined.
     *
     * */

    public static int staticMethodWhichCanBeReferencedInPlaceOfLambdasExpression(int a, int b){
       return a+b;
    }

    public int referenceMethodWhichCanBeReferencedInPlaceOfLamdasExpression(int a, int b){
        return a+b;
    }


    /**
     * Method reference
     * Types of Method References
     * Static method reference
     * Instance method reference of a particular object
     * Instance method reference of an arbitrary object of a particular type
     * Constructor reference
     */

    public static void main(String[] args) {
        //Static method reference
        int summationResult = IntStream.of(1,2,3).reduce(0,LambdasTest::staticMethodWhichCanBeReferencedInPlaceOfLambdasExpression);
        System.out.println(summationResult);

        //Instance method reference
        LambdasTest lambdasTest = new LambdasTest();
        int summationResults = IntStream.of(1,2,3).reduce(0,lambdasTest::referenceMethodWhichCanBeReferencedInPlaceOfLamdasExpression);
        System.out.println(summationResult);

        //Instance Method reference of an arbitrary obj ??
        //https://www.google.com/search?q=Instance+Method+Reference+of+an+Arbitrary+Object&oq=Instance+Method+Reference+of+an+Arbitrary+Object&aqs=chrome..69i57&sourceid=chrome&ie=UTF-8
        Comparator<String> stringIgnoreCase = String::compareToIgnoreCase;

        //constructor reference
        Supplier<LambdasTest> jobCreator = LambdasTest::new;
        //note that we used "supplier" in this case, cause we did not have any arguments to the function (constructor)
        //https://stackoverflow.com/a/40153253/3593084
        Function<String,LambdasTest> jobCreatorWtihStringArgs = LambdasTest::new;

        //------------------------------------------------------------------------------------------------------------

    }

}




