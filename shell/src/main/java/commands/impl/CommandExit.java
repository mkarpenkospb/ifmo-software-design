package commands.impl;

import commands.Command;

import java.util.List;

public class CommandExit implements Command {
    private String name;

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(List<String> arguments) {
        System.exit(0);
        return null;
    }
}
