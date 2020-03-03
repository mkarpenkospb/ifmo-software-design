package commands.impl;

import commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandDefault implements Command {
    private String name = "";

    @Override
    public String name() {
        return "default";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String run(String arguments, String options) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(name, arguments, options);

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
                System.exit(0);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }
}
