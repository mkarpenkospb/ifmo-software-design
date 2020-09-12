package commands.impl;

import commands.Command;
import commands.ConsoleColors;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Комманда выводит список файлов текущей или указанной директории
 */
public class CommandLs implements Command {

    String name;

    @Override
    public String name() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(List<String> arguments) {
        if (arguments.size() > 1)
            return "Wrong number of arguments!";
        List<String> output = new ArrayList<>();
        String[] contents;
        File dir;
        if (arguments.size() == 0) {
            dir = new File(System.getProperty("user.dir"));
        } else {
            dir = new File(String.valueOf(CommandCd.getAbsolutePathToArgument(arguments.get(0))));
        }
        contents = dir.list();
        if (contents != null) {
            Arrays.sort(contents);
            for (String item : contents) {
                output.add(colorIt(item) + "  ");
            }
        }
        return String.join("", output);
    }

    private String colorIt(String file) {
        if (Files.isDirectory(Paths.get(file)))
            return ConsoleColors.BLUE_BOLD + file + ConsoleColors.RESET;
        if (Files.isExecutable(Paths.get(file)))
            return ConsoleColors.GREEN_BOLD + file + ConsoleColors.RESET;
        return file;
    }
}
