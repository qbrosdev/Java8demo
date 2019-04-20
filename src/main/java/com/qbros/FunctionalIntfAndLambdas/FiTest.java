package com.qbros.FunctionalIntfAndLambdas;

import com.qbros.Person;

import java.util.function.*;

/**
 * Created by V.Ghasemi
 * on 4/15/2019.
 */
public class FiTest {
    public static void main(String[] args) {
        /**
         * Common functional interfaces:
         *
         */

        //---------------------Consumers: they have arguments but dont return anything
        ObjIntConsumer<Person> personObjIntConsumer = (person, value) -> person.setAge(value);
        Consumer<Person> personConsumer = person -> System.out.println("eating person" + person);
        //Some consumers for primitives are already defined
        //IntConsumer,LongConsumer,....

        //--------------------Suppliers: they produce objs of certain types
        IntSupplier intSupplier = () -> 12;
        Supplier<Person> personSupplier = Person::new;
        //Some suppliers for primitives are already defined
        //IntSupplier, LongSupplier,...


        //--------------------Function: has specific input and output
        Function<Person, String> personToStringFunction = person -> person.getName();
        BiFunction<Person, Person, Integer> towPersonToIntegerBiFunction = (person, person2) -> person.getAge() + person2.getAge();
        //Some functions on primitives are already defined
        //DoubleToIntFunction, DoubleToLongFunction, IntToLongFunction, ....

        //-------------------Operators: are especial kind of functions which peroform
        // an operation on operands and produce results of the same type like operations in math: e.g plus operator opr1 + opr2 = result
        UnaryOperator<Person> personAgeMaximizerUnaryOperator = person -> {
            person.setAge(100);
            return person;
        };
        BinaryOperator<Person> personMergerBinaryOperator = (person, person2) ->
                new Person(person.getAge() + person2.getAge(), person.getName() + person2.getName());
        //Some operators on primitives are already defined
        //DoubleBinaryOperator,IntUnaryOperator

        //--------------------Predicate: defines a functions that performs some cheking on the input and returns true or false
        Predicate<Person> personIsInfantPredicate = person -> person.getAge() < 2;
        BiPredicate<Person, String> personHasThisNameBiPredicate = (person, s) -> s.equalsIgnoreCase(person.getName());
        //Some prediactes for primitives are already defined
        //IntPredicate, ...


        //------------------Converter functions: convert input(s) to a particular primitive type
        // ToIntFunction<T>, ToIntBiFunction<T,U>,
        //There are also some predefined converters for primitive to premitive conversion:
        //DoubleToIntFunction, IntToLongFunction, ...
        //NOTE: why dont we have ToObjFunction<T>? because that will be a map functions :)

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

