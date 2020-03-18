package commands.impl;

import commands.Command;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CommandCd implements Command {

    String name;

    @Override
    public String name() {
        return "cd";
    }

    @Override
    public String run(List<String> arguments) {
        String currDirName = System.getProperty("user.dir");
        File currDir = new File(currDirName);
        if (arguments.size() != 1)
            return "Wrong number of arguments!";
        String targetName = arguments.get(0);
        String innerName = currDirName + "/" + arguments.get(0);
        File targetDirFull = new File(targetName);
        File innerDir = new File(innerName);
        Path fullPath;
        if (targetName.charAt(0) == '/' && targetDirFull.exists()) {
            fullPath = Paths.get(targetName).toAbsolutePath().normalize();
        } else if (innerDir.exists()) {
            fullPath = Paths.get(innerName).toAbsolutePath().normalize();
        } else {
            return "No such file or directory";
        }
        System.setProperty("user.dir", fullPath.toString());

        return "";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
