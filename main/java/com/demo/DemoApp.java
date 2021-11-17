package com.demo;

import com.demo.service.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class DemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.demo");
        HelloService helloService = context.getBean(HelloService.class);
        helloService.printHello("  Hello    ");
        System.out.println(helloService.getHello());
    }
}
