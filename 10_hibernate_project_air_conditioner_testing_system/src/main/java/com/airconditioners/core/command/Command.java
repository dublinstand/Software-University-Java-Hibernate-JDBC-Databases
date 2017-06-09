package com.airconditioners.core.command;

public abstract class Command {

    private String[] tokens;

    protected Command(String[] tokens) {
        this.tokens = tokens;
    }

    protected String[] getTokens() {
        return tokens;
    }

    protected void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public abstract String execute();
}
