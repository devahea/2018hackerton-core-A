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

    @Override
    public List<List<Position>> level4(List<List<Position>> p1, List<List<Position>> p2) {
        List<List<Position>> positionsList = new ArrayList<>();

        p1.stream()
                .forEach(src -> {
                    p2.stream()
                            .filter(src2 -> checkPositionMergeable(src, src2))
                            .forEach(src2 -> positionsList.add(mergePositions(src, src2)));
        });

        return positionsList;
    }

    private boolean checkPositionMergeable(List<Position> positions1, List<Position> positions2) {
        for (Position p1 : positions1) {
            for (Position p2 : positions2) {
                if(p1.isNearby(p2))
                    return false;
            }
        }
        return true;
    }

    private List<Position> mergePositions(List<Position> positions1, List<Position> positions2) {
        List<Position> positions = new ArrayList<>();
        positions.addAll(positions1);
        positions.addAll(positions2);

        return positions;
    }
}
