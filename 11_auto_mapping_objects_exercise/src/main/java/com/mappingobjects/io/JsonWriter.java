package com.mappingobjects.io;

import org.springframework.stereotype.Component;

@Component(value = "JsonWriter")
public class JsonWriter implements Writer {

    @Override
    public void write(String line) {
        System.out.println("Hi I am Json");
    }
}
