package service;

import parser.Lexem;
import parser.LexemType;
import parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shell {
    static Scanner scanner = new Scanner(System.in);

    public static void run() {
        String input;
        Parser parser = new Parser();

        System.out.print("SHELL >> ");
        while (scanner.hasNextLine()) {
//            System.out.print("SHELL >> ");
//
//            input = scanner.nextLine();
//
//            System.out.println("DEBUG --- " + "IN > " + input); // TODO
//
//            String output = parser.parse(input);
//
//            System.out.println("DEBUG --- " + "OUT > " + output); // TODO
//
//            System.out.println(output);
            /////////////////////////////

            System.out.print("SHELL-TEST >> ");
            input = scanner.nextLine();
            List<Lexem> lexems = parser.parseLexem(input);
            System.out.println(lexems);
        }
    }
}
