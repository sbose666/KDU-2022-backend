package com.example.spring;

public class C {
    C() {
        System.out.println("In C's constructor");
//        A a = new A();
        // if we uncomment the above line, we encounter a cyclic dependency.
    }

    public void say() {
        System.out.println("Hi, I am C");
    }
}
