package commands.impl;

import commands.Command;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class CommandCdTest {
    private final Command commandLs = new CommandLs();
    private final Command commandCd = new CommandCd();
    private final Command commandPwd = new CommandPwd();
    private final String pathToTestResources = "src/test/";
    List<String> input;
    String where;
    String actual;


    @Before
    public void setUp() {
        List<String> input = Arrays.asList("test-cd-ls");
        actual = commandCd.run(input);
        assertThat(actual).isEqualTo("");
    }

    public static String right(String value, int length) {
        return value.substring(value.length() - length);
    }


    @Test
    public void pwdCdDirTest() {
        input = Arrays.asList("");
        where = commandPwd.run(input);

        assertThat(right(where, 11)).isEqualTo("/test-cd-ls");

        input = Arrays.asList("dir01");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11 + 6)).isEqualTo("/test-cd-ls/dir01");

        input = Arrays.asList("..");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11)).isEqualTo("/test-cd-ls");

        input = Arrays.asList("dir01/dir11");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11 + 6 + 6)).isEqualTo("/test-cd-ls/dir01/dir11");

        input = Arrays.asList("../../dir02");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11 + 6)).isEqualTo("/test-cd-ls/dir02");

    }

}
