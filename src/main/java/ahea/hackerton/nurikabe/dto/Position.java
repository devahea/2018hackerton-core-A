package ahea.hackerton.nurikabe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    private int x;
    private int y;

    public boolean isNearby(Position position) {
        int x = position.getX() - this.x;
        int y = position.getY() - this.y;

        return Math.pow(x, 2) + Math.pow(y, 2) < 2 ? true : false;
    }
}
