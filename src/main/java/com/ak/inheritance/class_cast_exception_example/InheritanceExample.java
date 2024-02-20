package com.ak.inheritance.class_cast_exception_example;

public class InheritanceExample {
    // Build completed successfully
    public static void main(String[] args) {
        A ab = new B();
        A ac = new C();
        C cb = (C) new B();// java.lang.ClassCastException:
        // B cannot be cast to class C
    }
}