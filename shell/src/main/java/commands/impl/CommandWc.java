package commands.impl;

import commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CommandWc implements Command {
    private String name = "";

    @Override
    public String name() {
        return "wc";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(String arguments, String options) {
        File file = new File(arguments);

        if (file.isDirectory())
            return "This path is directory";

        // TODO: добавить на строку
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            return "No such file";
        }

        int words = 0;
        int lines = 0;
        int bytes = 0;

        while (Objects.requireNonNull(scanner).hasNextLine()) {
            List<String> line = Arrays.asList(scanner.nextLine().split(" "));
            bytes += line.stream().mapToInt(String::length).sum();
            words += line.size();
            lines++;
        }
        scanner.close();
//        System.out.println(lines + " " + words + " " + bytes);

        return lines + " " + words + " " + bytes;
    }
}
