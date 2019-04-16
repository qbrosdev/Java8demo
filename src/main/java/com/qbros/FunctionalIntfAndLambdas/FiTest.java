package com.qbros.FunctionalIntfAndLambdas;

/**
 * Created by V.Ghasemi
 * on 4/15/2019.
 */
public class FiTest {
    public static void main(String[] args) {
        //todo
    }
}

/**
 * functional interfaces can't have two abstract methods
 * But they can have default (static/non-static) methods
 */
@FunctionalInterface
interface TestInterface {
    void singleMethodToImplement();

    default void defaultMethod() {
        System.out.println("hello");
        //NOTE: method references can only be used in place of lamdas expressions (not any where else)
        //System.out::println("dfd");
    }

    static void staticDefaultMethod() {
        System.out.println("Blowing horn!!!");
    }
}

//calling default methods of interface
class TestImple implements TestInterface {

    public void print() {
        //calling default method
        TestInterface.super.defaultMethod();
        //calling default static method
        TestInterface.staticDefaultMethod();
    }

    @Override
    public void singleMethodToImplement() {

    }
}

