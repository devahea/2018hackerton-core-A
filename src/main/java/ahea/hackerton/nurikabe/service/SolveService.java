package ahea.hackerton.nurikabe.service;

import ahea.hackerton.nurikabe.dto.Block;
import ahea.hackerton.nurikabe.dto.Position;

import java.util.List;

public interface SolveService {
    List<List<Position>> solveProblem(int[][] problem);
    List<Block> level1(int[][] problem);
    List<List<Position>> level2(int[][] problem, Block block);
    List<List<Position>> level3(List<Block> blocks, List<List<Position>> positionsList);
    List<List<Position>> level4(List<List<Position>> p1, List<List<Position>> p2);
}
