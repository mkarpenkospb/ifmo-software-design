package service;

import commands.impl.CommandExit;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class EnvironmentTest {
    private final Environment environment = Environment.getInstance();

    @Test
    public void testGetInstance() {
        // Arrange
        Environment env1 = Environment.getInstance();
        Environment env2 = Environment.getInstance();

        // Assert
        assertEquals(env1, env2);
    }

    @Test
    public void testGetCommand() {
        assertThat(environment.getCommand("exit").getClass()).isEqualTo(CommandExit.class);
    }

    @Test
    public void testPutAndGetVariable() {
        // Act
        environment.putVariable("Hello", "World");

        // Assert
        assertThat(environment.getVariable("Hello")).isEqualTo("World");
    }

    @Test
    public void testGetCurrentPath() {
        assertThat(Environment.getCurrentPath()).isEqualTo(System.getProperty("user.dir"));
    }
}
