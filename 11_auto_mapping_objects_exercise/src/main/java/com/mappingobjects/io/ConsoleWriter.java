package com.mappingobjects.io;

import org.springframework.stereotype.Component;

@Component(value = "ConsoleWriter")
public class ConsoleWriter implements Writer{

    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
