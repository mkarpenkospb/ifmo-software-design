package commands.impl;

import commands.Command;

public class CommandExit implements Command {
    private String name = "";

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(String arguments, String options) {
        System.exit(0);
        return null;
    }
}
