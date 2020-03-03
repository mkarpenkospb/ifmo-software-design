package commands.impl;

import commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    // TODO: добавить на строку
    @Override
    public String run(String arguments, String options) {
        int totalWords = 0;
        int totalLines = 0;
        int totalBytes = 0;

        List<String> output = new ArrayList<>();
        String[] files = arguments.split(" ");
        for (String fileName : files) {
            File file = new File(fileName);

            if (file.isDirectory())
                return "This path is directory";

            Scanner scanner;
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
            totalBytes += bytes;
            totalLines += lines;
            totalWords += words;
            scanner.close();

            output.add(lines + " " + words + " " + bytes + " " + file.getPath() + "\n");
        }

        if (files.length > 1) {
            output.add(totalLines + " " + totalWords + " " + totalBytes + " " + "total" + "\n");
        }

        return String.join("", output);
    }
}
