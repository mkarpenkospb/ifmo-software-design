package commands.impl;

import commands.Command;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class CommandLsTest {
    private final Command commandLs = new CommandLs();
    private final Command commandCd = new CommandCd();
    private final Command commandPwd = new CommandPwd();
    List<String> input;
    String where;
    String actual;

    @Before
    public void setUp() {
        List<String> input = Collections.singletonList("test-cd-ls");
        actual = commandCd.run(input);
        assertThat(actual).isEqualTo("");
    }

    private String clearColor(String output) {
        return output.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    @Test
    public void lsDirTest() {
        input = Collections.emptyList();
        actual = commandLs.run(input);
        where = commandPwd.run(input);

        assertThat(clearColor(actual)).isEqualTo("dir01  dir02  file1.txt  file2.py  ");

        input = Collections.singletonList("dir01");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        actual = commandLs.run(input);

        assertThat(clearColor(actual)).isEqualTo("dir11  file3.txt  ");

        input = Collections.singletonList("dir11");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        actual = commandLs.run(input);

        assertThat(clearColor(actual)).isEqualTo("file4.txt  ");

        input = Collections.singletonList("../../dir02");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        actual = commandLs.run(input);

        assertThat(clearColor(actual)).isEqualTo("");

        // go home
        input = Collections.singletonList("..");
        actual = commandCd.run(input);
        input = Collections.singletonList("..");
        actual = commandCd.run(input);
    }

    @Test
    public void lsDirTestWithArg() {
        input = Collections.emptyList();
        actual = commandLs.run(input);
        where = commandPwd.run(input);

        input = Collections.singletonList("dir01");
        actual = commandLs.run(input);

        assertThat(clearColor(actual)).isEqualTo("dir11  file3.txt  ");

        input = Collections.singletonList("dir01/dir11");
        actual = commandLs.run(input);

        assertThat(clearColor(actual)).isEqualTo("file4.txt  ");

        input = Collections.singletonList("../../dir02");
        actual = commandLs.run(input);

        assertThat(clearColor(actual)).isEqualTo("");
    }
}
