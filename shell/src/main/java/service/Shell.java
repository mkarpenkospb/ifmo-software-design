package service;

import java.util.Scanner;

public class Shell {
    static Scanner scanner = new Scanner(System.in);
    static Executor executor = new Executor();

    public static void run() {
        String input;
        String output;

        while (scanner.hasNextLine()) {
            System.out.print("SHELL >> ");

            input = scanner.nextLine();
            output = executor.execute(input);

            System.out.println(output);
        }
    }
}
