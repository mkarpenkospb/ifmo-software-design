package service;

import commands.CommandEntity;
import commands.CommandExecutor;
import parser.Lexem;
import parser.LexemType;
import parser.Parser;
import parser.Substitutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {
    private Parser parser = new Parser();
    private Environment environment = new Environment();
    private Substitutor substitutor = new Substitutor();
    private CommandExecutor commandExecutor = new CommandExecutor();

    public String execute(String input) {
        if (input.contains("|")) {
            List<CommandEntity> commands = new ArrayList<>();

            String[] cmds = input.split("\\|");

            for (String cmd : cmds) {
                List<Lexem> lexems = parser.parseLexem(cmd);
                substitutor.substitution(lexems);

                commands.add(
                        CommandEntity.builder()
                                     .name(lexems.get(0).getWord())
                                     .arguments(lexems.stream()
                                                      .filter(lexem -> lexems.indexOf(lexem) != 0)
                                                      .map(Lexem::getWord)
                                                      .collect(Collectors.joining(" ")))
                                     .build());
            }

            String output = commandExecutor.execute(commands.get(0).getName(),
                                                    commands.get(0).getArguments());
            commands.remove(0);
            for (CommandEntity command : commands) {
                output = commandExecutor.execute(command.getName(), output);
            }

            return output;
        }

        List<Lexem> lexems = parser.parseLexem(input);
        substitutor.substitution(lexems);

        for (Lexem lexem : lexems) {
            if (lexem.getType() == LexemType.WORD_ASSIGMENT) {
                String[] attrAndValue = input.split("=");
                environment.putVariable(attrAndValue[0], attrAndValue[1]);
                return attrAndValue[0] + attrAndValue[1];
            }

            CommandEntity command =
                    CommandEntity.builder()
                                 .name(lexems.get(0).getWord())
                                 .arguments(lexems.stream()
                                                  .filter(l -> lexems.indexOf(l) != 0)
                                                  .map(Lexem::getWord)
                                                  .collect(Collectors.joining(" ")))
                                 .build();
            return commandExecutor.execute(command.getName(), command.getArguments());
        }

        return "";
    }
}
