package parser;

import service.Environment;

import java.util.List;

public class Substitutor {
    Environment environment = Environment.getInstance();


    public void substitution(List<Lexem> lexems) {
        for (Lexem lexem : lexems) {
//            if (lexem.type == LexemType.WORD_ASSIGMENT) {
//                String[] attrAndValue = lexem.word.split("=");
//                environment.putVariable(attrAndValue[0], attrAndValue[1]);
//            }
            if (lexem.type == LexemType.WORD_VARIABLE) {
                String subs = lexem.word.substring(lexem.word.indexOf("$") + 1);
                lexem.word = lexem.word.replace("$" + subs, environment.getVariable(subs));
            }

            if (lexem.type == LexemType.WORD_IN_DOUBLE_QUOTE) {
                if (lexem.word.contains("$")) {
                    StringBuilder subs = new StringBuilder();
                    subs.append(lexem.word.substring(lexem.word.indexOf("$") + 1));
                    subs.deleteCharAt(lexem.word.length() - 3);
//                    String subs = lexem.word.substring(lexem.word.indexOf("$") + 1);
                    lexem.word = lexem.word.replace("$" + subs.toString(),
                                                    environment.getVariable(subs.toString()));
                    //                lexem.word = newWord.deleteCharAt(newWord.length() - 1).toString();
                }
            }

//            if (lexem.type == LexemType.WORD || lexem.type == LexemType.WORD_IN_DOUBLE_QUOTE) {
//                String[] line = lexem.word.split(" ");
//                StringBuilder newWord = new StringBuilder();
//                for (String word : line) {
//                    if (word.startsWith("$")) {
//                        String subs = word.substring(word.indexOf("$") + 1);
//                        word = word.replace("$" + subs, environment.getVariable(subs));
//                    }
//                    newWord.append(word).append(" ");
//                }
//                lexem.word = newWord.deleteCharAt(newWord.length() - 1).toString();
//            }
        }
    }
}
