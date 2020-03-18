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


public class CommandLsTest {
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


    @Test
    public void lsDirTest() {
        input = Collections.emptyList();
        actual = commandLs.run(input);
        where = commandPwd.run(input);

        assertThat(actual).isEqualTo("dir01  dir02  file1.txt  file2.py  ");

        input = Collections.singletonList("dir01");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        actual = commandLs.run(input);

        assertThat(actual).isEqualTo("dir11  file3.txt  ");

        input = Collections.singletonList("dir11");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        actual = commandLs.run(input);

        assertThat(actual).isEqualTo("file4.txt  ");

        input = Collections.singletonList("../../dir02");
        actual = commandCd.run(input);
        input = Collections.emptyList();
        actual = commandLs.run(input);

        assertThat(actual).isEqualTo("");
    }

}
