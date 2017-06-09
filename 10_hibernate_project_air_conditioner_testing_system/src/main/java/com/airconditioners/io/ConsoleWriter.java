package com.airconditioners.io;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements Writer{

    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
