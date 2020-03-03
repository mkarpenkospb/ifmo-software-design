package commands.impl;

import commands.Command;

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
    public String run(String arguments, String options) {
        return System.getProperty("user.dir");
    }
}
