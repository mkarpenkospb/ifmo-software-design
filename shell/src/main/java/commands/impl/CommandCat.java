package commands.impl;

import commands.Command;
import service.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CommandCat implements Command {
    private String name = "";

    @Override
    public String name() {
        return "cat";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(String arguments, String options) {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(
                Paths.get(Environment.getCurrentPath(), arguments.trim()))) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.getMessage();
        }

        System.out.println(contentBuilder.toString());
        return contentBuilder.toString();
    }
}
