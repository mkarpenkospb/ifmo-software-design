package parser;

import org.junit.Test;
import service.Environment;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SubstitutorTest {
    private final Substitutor substitutor = new Substitutor();
    private final Environment environment = Environment.getInstance();

    @Test
    public void testSubstitution() {
        // Arrange
        String input = "Hello World \"    Software  Design\"  $bash 'hi' ";
        Lexem lexem1 = Lexem.builder().word("Hello").type(LexemType.WORD).build();
        Lexem lexem2 = Lexem.builder().word("World").type(LexemType.WORD).build();
        Lexem lexem3 = Lexem.builder().word("\"    Software  Design\"").type(LexemType.WORD_IN_DOUBLE_QUOTE).build();
        Lexem lexem4 = Lexem.builder().word("$bash").type(LexemType.WORD_VARIABLE).build();
        Lexem lexem5 = Lexem.builder().word("'hi'").type(LexemType.WORD_IN_SINGLE_QUOTE).build();
        Lexem lexem6 = Lexem.builder().word("value").type(LexemType.WORD_VARIABLE).build();

        List<Lexem> actual = Arrays.asList(lexem1, lexem2, lexem3, lexem4, lexem5);
        List<Lexem> expected = Arrays.asList(lexem1, lexem2, lexem3, lexem6, lexem5);

        environment.putVariable("bash", "value");

        // Act
        substitutor.substitution(actual);

        // Assert
        assertThat(actual).isEqualTo(expected);
    }
}
