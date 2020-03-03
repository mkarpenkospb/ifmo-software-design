package parser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lexem {
    String word;
    LexemType type;
}
