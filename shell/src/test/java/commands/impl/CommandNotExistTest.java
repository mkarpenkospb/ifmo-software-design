package commands.impl;

import commands.Command;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandNotExistTest {
    private final Command command = new CommandNotExist();

    @Test
    public void testRunCommandNotExist() {
        String actual = command.run(Collections.emptyList());
        assertThat(actual).isEqualTo("Command not found: ");
    }
}
