package com.qbros.FunctionalIntfAndLambdas;

import java.util.function.Consumer;

/**
 * Created by V.Ghasemi
 * on 6/1/2019.
 */
public class AroundInvoke {

    public static void main(String[] args) {
        ValueAbleResource.useResource(valueAbleResource -> {
            valueAbleResource.useResourceMethod1();
            valueAbleResource.useResourceMethod2(); }
        );
    }
}

class ValueAbleResource {
    // no one from the out-side should be able to manage (create/destroy) this resource
    private ValueAbleResource() {
        System.out.println("creating resource");
    }

    //out side word can use the resource (its methods)
    public void useResourceMethod1() {
        System.out.println("useResourceMethod1");
    }

    public void useResourceMethod2() {
        System.out.println("useResourceMethod2");
    }

    private void close() {
        System.out.println("close resource to free memory,...");
    }

    public static void useResource(Consumer<ValueAbleResource> valuAbleResourceConsumer) {
        ValueAbleResource valuAbleResource = new ValueAbleResource();
        try {
            valuAbleResourceConsumer.accept(valuAbleResource);
        } finally {
            valuAbleResource.close();
        }
    }
}