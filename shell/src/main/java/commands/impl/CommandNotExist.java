package commands.impl;

import commands.Command;

import java.util.List;

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
    public String run(List<String> arguments) {
        return "command not found: " + name;
    }
}
