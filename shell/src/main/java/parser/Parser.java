package parser;

import org.jetbrains.annotations.NotNull;
import commands.CommandExecutor;
import commands.CommandType;
import service.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    Environment environment = Environment.getInstance();
    CommandExecutor commandExecutor = new CommandExecutor();

    public CommandType getType(@NotNull String input) {
        if (input.contains("=")) {
            return CommandType.Assign;
        }

        if (input.contains("|")) {
            return CommandType.Pipes;
        }

        return CommandType.Command;
    }

    public String parse(String input) {
        switch (getType(input)) {
            case Assign: {
                if (input.contains(" ")) {
                    return " ";
                }

                String[] attrAndValue = input.split("=");
                environment.putVariable(attrAndValue[0], attrAndValue[1]);
                break;
            }
            case Pipes: {
                input = substitution(input);
                List<String> commands = Arrays.asList(input.split("\\|"));
                String[] cmdAndArgs = commands.get(0).split(" ", 2);
                String output = cmdAndArgs[1];
                for (String command : commands) {
                    String[] cm = command.trim().split(" ", 2);
                    output = commandExecutor.execute(cm[0], output);
                }

                return output;
            }
            case Command: {
                input = substitution(input);
                String[] cmdAndArgs = input.split(" ", 2);

                if (cmdAndArgs.length < 2) {
                    return commandExecutor.execute(cmdAndArgs[0], "");
                }

                return commandExecutor.execute(cmdAndArgs[0], cmdAndArgs[1]);
            }
            default: {
                break;
            }
        }

        return " ";
    }

    String substitution(String input) {
        String[] line = input.split(" ");
        boolean isReplace = true;

        StringBuilder subsInput = new StringBuilder();
        for (String word : line) {
            if (word.contains("'")) {
                isReplace = !isReplace;
            }

            if (word.startsWith("$")) {
                if (isReplace) {
                    String repl = word.substring(word.indexOf("$") + 1);
                    word = word.replace("$" + repl, environment.getVariable(repl));
                }
            }
            subsInput.append(word).append(" ");
        }

        return subsInput.deleteCharAt(subsInput.length() - 1).toString();
    }

//    String substitution2(List<Lexem> lexems) {
//        for (Lexem lexem : lexems) {
//            if (lexem.type == LexemType.WORD_ASSIGMENT) {
//                String[] attrAndValue = lexem.word.split("=");
//                environment.putVariable(attrAndValue[0], attrAndValue[1]);
//            } else if (lexem.type == LexemType.WORD_VARIABLE) {
//
//                String repl = lexem.word.substring(lexem.word.indexOf("$") + 1);
//                word = word.replace("$" + repl, environment.getVariable(repl));
//            }


//        for (String word : line) {
//            if (word.contains("'")) {
//                isReplace = !isReplace;
//            }
//
//            if (word.startsWith("$")) {
//                if (isReplace) {
//                    String repl = word.substring(word.indexOf("$") + 1);
//                    word = word.replace("$" + repl, environment.getVariable(repl));
//                }
//            }
//            subsInput.append(word).append(" ");
//        }
//
//        return subsInput.deleteCharAt(subsInput.length() - 1).toString();
//    }

    public List<Lexem> parseLexem(String in) {
        int idx = 0;

        List<Lexem> lexems = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        while (idx < in.length()) {
            if (in.charAt(idx) == '"') {
                StringBuilder wordInDoubleQuote = new StringBuilder();
                wordInDoubleQuote.append("\"");
                idx++;
                while (in.charAt(idx) != '"') {
                    wordInDoubleQuote.append(in.charAt(idx));
                    idx++;
                }
                idx++;
                wordInDoubleQuote.append("\"");
                lexems.add(Lexem.builder()
                                .word(wordInDoubleQuote.toString())
                                .type(LexemType.WORD_IN_DOUBLE_QUOTE)
                                .build());
                while (idx < in.length() && (in.charAt(idx) == ' ')) idx++;
                continue;
            } else if (in.charAt(idx) == '\'') {
                StringBuilder wordInSingleQuote = new StringBuilder();
                wordInSingleQuote.append("'");
                idx++;
                while (in.charAt(idx) != '\'') {
                    wordInSingleQuote.append(in.charAt(idx));
                    idx++;
                }
                idx++;
                wordInSingleQuote.append("'");
                lexems.add(Lexem.builder()
                                .word(wordInSingleQuote.toString())
                                .type(LexemType.WORD_IN_SINGLE_QUOTE)
                                .build());
                while (idx < in.length() && in.charAt(idx) == ' ') idx++;
                continue;
            } else if (in.charAt(idx) == '$') {
                StringBuilder wordVariable = new StringBuilder();
                while (idx < in.length() && in.charAt(idx) != ' ') {
                    wordVariable.append(in.charAt(idx));
                    idx++;
                }
                idx++;
                lexems.add(Lexem.builder()
                                .word(wordVariable.toString())
                                .type(LexemType.WORD_VARIABLE)
                                .build());
                while (idx < in.length() && in.charAt(idx) == ' ') idx++;
                continue;
            } else if (in.charAt(idx) == ' ') {
                if (word.length() > 0) {
                    lexems.add(Lexem.builder()
                                    .word(word.toString())
                                    .type(LexemType.WORD)
                                    .build());
                    word = new StringBuilder();
                }
                while (idx < in.length() && in.charAt(idx) == ' ') idx++;
                continue;
            }
            word.append(in.charAt(idx));
            idx++;
        }

        if (word.length() > 0) {
            lexems.add(Lexem.builder()
                            .word(word.toString())
                            .type(LexemType.WORD)
                            .build());
        }

        return lexems;
    }
}
