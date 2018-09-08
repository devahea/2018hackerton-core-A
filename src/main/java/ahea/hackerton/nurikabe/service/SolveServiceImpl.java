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
}
