package commands.impl;

import commands.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLs implements Command {

    String name;

    @Override
    public String name() {
        return "ls";
    }

    @Override
    public String run(List<String> arguments) {
        List<String> output = new ArrayList<>();
        String[] contents;
        File dir = new File(System.getProperty("user.dir"));
        contents = dir.list();
        if (contents != null) {
            Arrays.sort(contents);
            for (String item : contents) {
                output.add(item + "  ");
            }
        }
        return String.join("", output);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
