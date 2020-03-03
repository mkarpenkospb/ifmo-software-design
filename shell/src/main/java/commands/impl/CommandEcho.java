package commands.impl;

import commands.Command;

public class CommandEcho implements Command {
    private String name = "";

    @Override
    public String name() {
        return "echo";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(String arguments, String options) {
        return arguments;
    }
}
