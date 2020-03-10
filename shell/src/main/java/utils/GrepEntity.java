package utils;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.List;

public class GrepEntity {

    @Parameters
    List<String> arguments;

    @Option(names = {"-i"})
    boolean isRegister;

    @Option(names = "-w")
    boolean isFullWord;

    @Option(names = "-A")
    int lineCount = -1;

    @Parameters(index = "0")
    String search;

    public String getSearch() {
        return search;
    }

    public List<String> getArguments() {
        return arguments;
    }

    public boolean isRegister() {
        return isRegister;
    }

    public boolean isFullWord() {
        return isFullWord;
    }

    public int getLineCount() {
        return lineCount;
    }
}
