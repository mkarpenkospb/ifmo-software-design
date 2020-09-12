package commands.impl;

import commands.Command;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Комманда позволяет менять текущую директорию
 */
public class CommandCd implements Command {
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

        //  если просто cd, возвращаемся домой
        if (arguments.size() == 0) {
            System.setProperty("user.dir", System.getProperty("user.home"));
            return "";
        }

        Path targetName = getAbsolutePathToArgument(arguments.get(0));
        if (Files.exists(targetName)) {
            System.setProperty("user.dir", targetName.normalize().toString());
            return "";
        } else {
            return "No such file or directory";
        }
    }

    // package-private
    static Path getAbsolutePathToArgument(String path) {
        if (path.equals("..")) {
            return (new File(System.getProperty("user.dir"))).getParentFile().toPath();
        }

        if (!isAbsolutePath(path)) {
            return Paths.get(System.getProperty("user.dir"), path);
        }

        return Paths.get(path);
    }

    private static boolean isAbsolutePath(String path) {
        return path.charAt(0) == '/';
    }
}
