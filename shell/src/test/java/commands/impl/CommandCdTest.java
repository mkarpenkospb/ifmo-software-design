package commands.impl;

import commands.Command;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandCdTest {
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

    public static String right(String value, int length) {
        return value.substring(value.length() - length);
    }


    @Test
    public void pwdCdDirTest() {
        input = Collections.singletonList("");
        where = commandPwd.run(input);

        assertThat(right(where, 11)).isEqualTo("/test-cd-ls");

        input = Collections.singletonList("dir01");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11 + 6)).isEqualTo("/test-cd-ls/dir01");

        input = Collections.singletonList("..");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11)).isEqualTo("/test-cd-ls");

        input = Collections.singletonList("dir01/dir11");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11 + 6 + 6)).isEqualTo("/test-cd-ls/dir01/dir11");

        input = Collections.singletonList("../../dir02");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        where = commandPwd.run(input);

        assertThat(right(where, 11 + 6)).isEqualTo("/test-cd-ls/dir02");
    }
}
