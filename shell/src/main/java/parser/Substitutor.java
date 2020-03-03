package parser;

import service.Environment;

import java.util.List;

public class Substitutor {
    Environment environment = Environment.getInstance();

    public void substitution(List<Lexem> lexems) {
        for (Lexem lexem : lexems) {
            if (lexem.type == LexemType.WORD_VARIABLE) {
                String subs = lexem.word.substring(lexem.word.indexOf("$") + 1);
                lexem.word = lexem.word.replace("$" + subs, environment.getVariable(subs));
            }

            if (lexem.type == LexemType.WORD_IN_DOUBLE_QUOTE) {
                if (lexem.word.contains("$")) {
                    StringBuilder subs = new StringBuilder();
                    subs.append(lexem.word.substring(lexem.word.indexOf("$") + 1));
                    subs.deleteCharAt(lexem.word.length() - 3);
                    lexem.word = lexem.word.replace("$" + subs.toString(),
                                                    environment.getVariable(subs.toString()));
                }
            }
        }
    }
}
