package com.example.spring;

public class B {
    B() {
        System.out.println("In B's constructor");
        C c = new C();
    }

    public void say() {
        System.out.println("Hi, I am B");
    }
}
