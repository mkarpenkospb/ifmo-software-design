package commands.impl;

import commands.Command;

import java.util.List;

public class CommandPwd implements Command {
    private String name = "";

    @Override
    public String name() {
        return "pwd";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(List<String> arguments) {
        return System.getProperty("user.dir");
    }
}
