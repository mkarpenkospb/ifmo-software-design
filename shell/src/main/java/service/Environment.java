package service;

import commands.Command;
import commands.impl.*;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private static String currentPath = System.getProperty("user.dir");
    private static Map<String, Command> commands = new HashMap<>();
    private static Map<String, String> variables = new HashMap<>();

    static {
        commands.put("echo", new CommandEcho());
        commands.put("exit", new CommandExit());
        commands.put("cat", new CommandCat());
        commands.put("pwd", new CommandPwd());
        commands.put("wc", new CommandWc());
    }

    public static class SingletonHolder {
        public static final Environment HOLDER_INSTANCE = new Environment();
    }

    public static Environment getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public Command getCommand(String commandName) {
        return commands.getOrDefault(commandName, new CommandDefault());
    }

    public void putVariable(String argumentName, String argumentValue) {
        variables.put(argumentName, argumentValue);
        System.out.println("DEBUG --- " + "VARS > " + variables);
    }

    public String getVariable(String variableName) {
        System.out.println("DEBUG --- " + "VARS > " + variables);
        return variables.get(variableName);
    }

    public static String getCurrentPath() {
        return currentPath;
    }
}
