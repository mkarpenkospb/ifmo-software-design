package commands;

import service.Environment;

public class CommandExecutor {

    Environment environment = Environment.getInstance();

    public String execute(String commandName, String args) {
        Command command = environment.getCommand(commandName);
        command.setName(commandName);

        return command.run(args, " ");
    }
}
