package commands;

import service.Environment;

import java.io.IOException;

public class CommandExecutor {
    Environment environment = Environment.getInstance();

    public String execute(CommandEntity commandEntity) {
        Command command = environment.getCommand(commandEntity.getName());
        command.setName(commandEntity.getName());

        return command.run(commandEntity.getArguments());
    }
}
