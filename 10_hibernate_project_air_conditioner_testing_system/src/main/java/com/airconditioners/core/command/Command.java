package com.airconditioners.core.command;

public abstract class Command {

    private String[] tokens;

    protected Command(String[] tokens) {
        this.tokens = tokens;
    }

    public abstract String execute();

}
