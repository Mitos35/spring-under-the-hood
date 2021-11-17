package com.demo.service;

import com.demo.annotation.Trimmed;
import org.springframework.stereotype.Service;

@Service
@Trimmed
public class HelloService {

    public void printHello(String value) {
        System.out.println(value);
    }

    public String getHello() {
        return "    Hello    ";
    }
}
