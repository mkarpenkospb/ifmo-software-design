package commands;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommandEntity {
    private String name;
    //    private String arguments;
    private List<String> arguments;
}
