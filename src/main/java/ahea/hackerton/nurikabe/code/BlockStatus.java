package ahea.hackerton.nurikabe.code;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BlockStatus {
    WALL(-1),
    CHECKED(-2),
    UNCHECKED(0);

    private int code;

    BlockStatus(int code) {
        this.code = code;
    }

    public static BlockStatus fromCode(int code) {
        return Arrays.stream(BlockStatus.values())
                .filter(src -> src.code == code)
                .findAny()
                .orElse(null);
    }
}
