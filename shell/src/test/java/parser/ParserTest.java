package parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void testParseLexemWithWordInDoubleQuote() {
        // Arrange
        String input = "\"   hello   world   \"";
        List<Lexem> excpected = Collections.singletonList(
                Lexem.builder()
                     .word(input)
                     .type(LexemType.WORD_IN_DOUBLE_QUOTE)
                     .build());

        // Act
        List<Lexem> actual = parser.parseLexem(input);

        // Assert
        assertThat(actual).isEqualTo(excpected);
    }


    @Test
    public void testParseLexemWithWordInSingleQuote() {
        // Arrange
        String input = "'  hello   world  '";
        List<Lexem> excpected = Collections.singletonList(
                Lexem.builder()
                     .word(input)
                     .type(LexemType.WORD_IN_SINGLE_QUOTE)
                     .build());

        // Act
        List<Lexem> actual = parser.parseLexem(input);

        // Assert
        assertThat(actual).isEqualTo(excpected);
    }

    @Test
    public void testParseLexemWithWordVariable() {
        // Arrange
        String input = "$task";
        List<Lexem> excpected = Collections.singletonList(
                Lexem.builder()
                     .word(input)
                     .type(LexemType.WORD_VARIABLE)
                     .build());

        // Act
        List<Lexem> actual = parser.parseLexem(input);

        // Assert
        assertThat(actual).isEqualTo(excpected);
    }

    @Test
    public void testParseLexemWithWordAssigment() {
        // Arrange
        String input = "y=10";
        List<Lexem> excpected = Collections.singletonList(
                Lexem.builder()
                     .word(input)
                     .type(LexemType.WORD_ASSIGMENT)
                     .build());

        // Act
        List<Lexem> actual = parser.parseLexem(input);

        // Assert
        assertThat(actual).isEqualTo(excpected);
    }

    @Test
    public void testParseLexem() {
        // Arrange
        String input = "Hello World \"    Software  Design\"  $bash 'hi' ";
        Lexem lexem1 = Lexem.builder().word("Hello").type(LexemType.WORD).build();
        Lexem lexem2 = Lexem.builder().word("World").type(LexemType.WORD).build();
        Lexem lexem3 = Lexem.builder().word("\"    Software  Design\"").type(LexemType.WORD_IN_DOUBLE_QUOTE).build();
        Lexem lexem4 = Lexem.builder().word("$bash").type(LexemType.WORD_VARIABLE).build();
        Lexem lexem5 = Lexem.builder().word("'hi'").type(LexemType.WORD_IN_SINGLE_QUOTE).build();
        List<Lexem> excpected = Arrays.asList(lexem1, lexem2, lexem3, lexem4, lexem5);

        // Act
        List<Lexem> actual = parser.parseLexem(input);

        // Assert
        assertThat(actual).isEqualTo(excpected);
    }
}
