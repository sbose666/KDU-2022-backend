package com.example.spring;

public class A {
    A() {
        System.out.println("In A's constructor");
        B b = new B();
    }

    public void say() {
        System.out.println("Hi, I am A");
    }

}
