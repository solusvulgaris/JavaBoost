package com.ak.inheritance.implement_one_from_two_announced;

public class SimpleCWithAObject {
    public SimpleCWithAObject() {
        //AbstractATwoMethods A = new AbstractATwoMethods(); - error;
        //"AbstractATwoMethods' is abstract; cannot be instantiated"
        //TODO: why?

        AbstractATwoMethods A = new AbstractATwoMethods() {
            @Override
            public void method1() {

            }

            @Override
            public void method2() {

            }
        };
    }
}
