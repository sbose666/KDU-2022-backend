package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        A a = context.getBean(A.class); // Gets the bean with the provided class
        boolean isAvailable_A = context.containsBean("a"); // checks if a bean with the given id is available
        boolean isAvailable_C = context.containsBean("b");
        boolean isAvailable_Z = context.containsBean("z");
        System.out.println("A is available: " + isAvailable_A);
        System.out.println("C is available: " + isAvailable_C);
        System.out.println("Z is available: " + isAvailable_Z);
        System.out.println("Date: " + context.getStartupDate()); // returns the startup date
        Talk talk1 = (Talk) context.getBean("talk");
        Talk talk2 = (Talk) context.getBean("talk");
        System.out.println("Are both the talk objects same? " + (talk1 == talk2)); // Since the scope is set to prototype, both the references will not reference the same object.
        talk1.speak();
    }
}
