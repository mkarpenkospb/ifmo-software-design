package commands;

public interface Command {
    String name();

    String run(String arguments, String options);

    void setName(String name);
}
