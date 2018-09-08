package ahea.hackerton.nurikabe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Block {
    private Position position;
    private int number;
}
