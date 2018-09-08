package ahea.hackerton.nurikabe.service;

import ahea.hackerton.nurikabe.dto.Block;
import ahea.hackerton.nurikabe.dto.Position;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolveServiceImpl implements SolveService {
    @Override
    public List<Block> level1(int[][] problem) {
        List<Block> blocks = new ArrayList<>();

        for (int i = 0; i < problem.length; i++) {
            for (int j = 0; j < problem[i].length; j++) {
                if(problem[i][j] > 0) {
                    Block block = new Block(new Position(i, j), problem[i][j]);
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    @Override
    public List<List<Position>> level2(List<Block> blocks) {
        return null;
    }

    @Override
    public List<List<Position>> level3(List<Block> blocks, List<List<Position>> positionsList) {
        return null;
    }
}
