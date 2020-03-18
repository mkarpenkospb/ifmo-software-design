package commands;

import java.io.IOException;
import java.util.List;

public interface Command {
    String name();

    String run(List<String> arguments);

    void setName(String name);
}
