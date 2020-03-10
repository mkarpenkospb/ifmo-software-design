package commands.impl;

import commands.Command;
import picocli.CommandLine;
import utils.GrepEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CommandGrep implements Command {

    String name;

    @Override
    public String name() {
        return "grep";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(List<String> arguments) {
        String input = String.join(" ", arguments);

        GrepEntity grepEntity = new GrepEntity();
        CommandLine commandLine = new CommandLine(grepEntity);
        commandLine.parseArgs(input.split(" "));

        String searchWord = grepEntity.getSearch();
        List<String> args = grepEntity.getArguments();
        args.remove(0);

        File file = new File(String.join("", args));

        if (file.isDirectory())
            return "This path is directory!";

        if (file.isFile()) {
            Scanner scanner;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                return "No such file";
            }

            args = new ArrayList<>();
            while (Objects.requireNonNull(scanner).hasNextLine()) {
                args.add(scanner.nextLine());
            }
        }

//        if (grepEntity.isRegister()) {
//            args = args.stream().map(String::toLowerCase).collect(Collectors.toList());
////            searchWord = searchWord.toLowerCase();
//        }

//        String finalSearchWord = searchWord;
        Predicate<String> searchPredicate = grepEntity.isFullWord() ?
                                            s -> s.equals(searchWord) :
                                            s -> s.contains(searchWord);
        Predicate<String> registerPredicate = grepEntity.isRegister() ?
                                              s -> s.toLowerCase().contains(searchWord.toLowerCase()) :
                                              s -> true;

        List<String> result = args.stream()
                                  .filter(searchPredicate)
                                  .filter(registerPredicate)
                                  .collect(Collectors.toList());

        if (grepEntity.getLineCount() != -1) {
            result = result.subList(0, grepEntity.getLineCount());
        }

        return String.join(" ", result);
    }
}
