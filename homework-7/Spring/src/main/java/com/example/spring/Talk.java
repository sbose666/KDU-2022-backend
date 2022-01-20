package com.example.spring;

public class Talk {
    private A a;
    private B b;
    private C c;
    private boolean propertyValue;

    Talk(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void speak() {
        a.say();
        b.say();
        c.say();
    }

    public void init() {
        System.out.println("In init block");
    }

    public void setPropertyValue(boolean propertyValue) {
        System.out.println("Inside property value setter");
        this.propertyValue = propertyValue;
        System.out.println("Property Value: " + propertyValue);
    }
}
