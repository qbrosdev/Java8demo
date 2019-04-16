package com.qbros;

/**
 * Created by V.Ghasemi
 * on 4/16/2019.
 */
public class Person {
    int age;
    String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

}