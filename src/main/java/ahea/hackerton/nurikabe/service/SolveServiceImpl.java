package ahea.hackerton.nurikabe.service;

import ahea.hackerton.nurikabe.dto.Block;
import ahea.hackerton.nurikabe.dto.Position;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class SolveServiceImpl implements SolveService {
    @Override
    public List<List<Position>> solveProblem(int[][] problem) {
        List<Block> blocks = level1(problem);

        List<List<List<Position>>> level2Result = new ArrayList<>();
        for(Block block:blocks) {
            List<List<Position>> result = level2(block);
            level2Result.add(result);
        }

        List<List<List<Position>>> level3Result = new ArrayList<>();
        for(List<List<Position>> positions:level2Result) {
            List<List<Position>> result = level3(blocks, positions);
            level3Result.add(result);
        }

        List<List<Position>> resultPosition = level3Result.get(0);
        for (int i = 1; i < level3Result.size(); i++) {
            resultPosition = level4(resultPosition, level3Result.get(i));
        }

        return null;
    }

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
    public List<List<Position>> level2(Block block) {
        return null;
    }


    @Override
    public List<List<Position>> level3(List<Block> blockList, List<List<Position>> positionsList) {

        List<List<Position>> result = positionsList;
        List<Block> blocks = blockList;
        int deleteRawIndex =0;
        List<Integer> deleteRaw = new ArrayList<>();
        for(List<Position> positions : result){
            int index = 0;
            for(Position position : positions){
                if(index != 0) {
                    for (Block block : blocks) {
                        if (position.getX() == (block.getPosition().getX())
                                && position.getY() == (block.getPosition().getY())) {
                            deleteRaw.add(deleteRawIndex);
                        }
                    }
                }
                index ++;
            }
            deleteRawIndex ++;
        }
        Descending descending = new Descending();
        Collections.sort(deleteRaw, descending);

        for(int deleteRawNum : deleteRaw){
            result.remove(deleteRawNum);
        }
        return result;
    }

    class Descending implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
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
