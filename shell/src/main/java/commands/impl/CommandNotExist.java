package commands.impl;

import commands.Command;

public class CommandNotExist implements Command {
    private String name = "";

    @Override
    public String name() {
        return "";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(String arguments, String options) {
        return "command not found: " + name;
    }
}
