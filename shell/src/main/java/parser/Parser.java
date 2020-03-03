package parser;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Lexem> parseLexem(String in) {
        int idx = 0;

        List<Lexem> lexems = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        while (idx < in.length()) {
            if (in.charAt(idx) == '"') {
                StringBuilder wordInDoubleQuote = new StringBuilder();
                wordInDoubleQuote.append("\"");
                idx++;
                while (in.charAt(idx) != '"') {
                    wordInDoubleQuote.append(in.charAt(idx));
                    idx++;
                }
                idx++;
                wordInDoubleQuote.append("\"");
                lexems.add(Lexem.builder()
                                .word(wordInDoubleQuote.toString())
                                .type(LexemType.WORD_IN_DOUBLE_QUOTE)
                                .build());
                while (idx < in.length() && (in.charAt(idx) == ' ')) idx++;
                continue;
            } else if (in.charAt(idx) == '\'') {
                StringBuilder wordInSingleQuote = new StringBuilder();
                wordInSingleQuote.append("'");
                idx++;
                while (in.charAt(idx) != '\'') {
                    wordInSingleQuote.append(in.charAt(idx));
                    idx++;
                }
                idx++;
                wordInSingleQuote.append("'");
                lexems.add(Lexem.builder()
                                .word(wordInSingleQuote.toString())
                                .type(LexemType.WORD_IN_SINGLE_QUOTE)
                                .build());
                while (idx < in.length() && in.charAt(idx) == ' ') idx++;
                continue;
            } else if (in.charAt(idx) == '$') {
                StringBuilder wordVariable = new StringBuilder();
                while (idx < in.length() && in.charAt(idx) != ' ') {
                    wordVariable.append(in.charAt(idx));
                    idx++;
                }
                idx++;
                lexems.add(Lexem.builder()
                                .word(wordVariable.toString())
                                .type(LexemType.WORD_VARIABLE)
                                .build());
                while (idx < in.length() && in.charAt(idx) == ' ') idx++;
                continue;
            } else if (in.charAt(idx) == '=') {
                while (idx < in.length() && in.charAt(idx) != ' ') {
                    word.append(in.charAt(idx));
                    idx++;
                }
                lexems.add(Lexem.builder()
                                .word(word.toString())
                                .type(LexemType.WORD_ASSIGMENT)
                                .build());
                word = new StringBuilder();
                continue;
            } else if (in.charAt(idx) == ' ') {
                if (word.length() > 0) {
                    lexems.add(Lexem.builder()
                                    .word(word.toString())
                                    .type(LexemType.WORD)
                                    .build());
                    word = new StringBuilder();
                }
                while (idx < in.length() && in.charAt(idx) == ' ') idx++;
                continue;
            }
            word.append(in.charAt(idx));
            idx++;
        }

        if (word.length() > 0) {
            lexems.add(Lexem.builder()
                            .word(word.toString())
                            .type(LexemType.WORD)
                            .build());
        }

        return lexems;
    }
}
