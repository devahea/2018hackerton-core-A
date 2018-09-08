package ahea.hackerton.nurikabe.service;

import ahea.hackerton.nurikabe.dto.Block;
import ahea.hackerton.nurikabe.dto.Position;

import java.util.List;

public interface SolveService {
    List<Block> level1(int[][] problem);
    List<List<Position>> level2(List<Block> blocks);
    List<List<Position>> level3(List<Block> blocks, List<List<Position>> positionsList);
}
