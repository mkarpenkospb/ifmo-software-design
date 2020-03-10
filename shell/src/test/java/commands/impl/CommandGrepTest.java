package commands.impl;

import commands.Command;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.Assert.*;

public class CommandGrepTest {

    private final Command commandGrep = new CommandGrep();
    private final String pathToTestResources = "src/test/";

    @Test
    public void testGrepWithFile() {
        // Arrange
        String pathToFile = "resources/Example.txt";
        List<String> input = Arrays.asList("lo", pathToTestResources + pathToFile);

        // Act
        String actual = commandGrep.run(input);

        // Assert
        assertThat(actual).isEqualTo("Hello, World!");
    }

    @Test
    public void testGrepWithEmptyFile() {
        // Arrange
        String pathToFile = "resources/Empty.txt";
        List<String> input = Arrays.asList("hello", pathToTestResources + pathToFile);

        // Act
        String actual = commandGrep.run(input);

        // Assert
        assertThat(actual).isEqualTo("");
    }

    @Test
    public void testGrepWithKeyA() {
        // Arrange
        List<String> input = Arrays.asList(
                "abc", "-A 2",
                "abc", "qwe", "abc", "qwe", "abc", "qwe", "abc", "abc", "abc");

        // Act
        String actual = commandGrep.run(input);

        // Assert
        assertThat(actual).isEqualTo("abc abc");
    }

    @Test
    public void testGrepWithKeyW() {
        // Arrange
        List<String> input = Arrays.asList(
                "abc", "-w",
                "ABC", "a", "ab", "ABC", "abczxc", "abc", "abc", "ABCabc");

        // Act
        String actual = commandGrep.run(input);

        // Assert
        assertThat(actual).isEqualTo("abc abc");
    }
}
