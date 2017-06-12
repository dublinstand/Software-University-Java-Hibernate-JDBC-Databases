package com.airconditioners.domain.entities.dto;

import org.springframework.stereotype.Component;

@Component
public class CommandDto {

    private String commandName;

    public String getCommandName() {
        return this.commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
}
