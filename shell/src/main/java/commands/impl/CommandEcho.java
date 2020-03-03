package commands.impl;

import commands.Command;

import java.util.List;

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
    public String run(List<String> arguments) {
        StringBuilder result = new StringBuilder();

        for (String word : arguments) {
            if ((word.startsWith("\"") && word.endsWith("\"")) ||
                (word.startsWith("'") && word.endsWith("'"))) {
                word = word.substring(1, word.length() - 1);
            }
            result.append(word).append(" ");
        }

        return result.toString();
    }
}
