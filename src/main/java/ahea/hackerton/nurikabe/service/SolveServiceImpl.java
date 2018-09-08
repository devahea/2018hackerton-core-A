package ahea.hackerton.nurikabe.service;

import ahea.hackerton.nurikabe.dto.Block;
import ahea.hackerton.nurikabe.dto.Position;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SolveServiceImpl implements SolveService {
    @Override
    public List<List<Position>> solveProblem(int[][] problem) {
        List<Block> blocks = level1(problem);

        List<List<List<Position>>> level2Result = new ArrayList<>();
        for(Block block:blocks) {
            List<List<Position>> result = level2(problem, block);
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

        return resultPosition;
    }

    @Override
    public List<Block> level1(int[][] problem) {
        List<Block> blocks = new ArrayList<>();

        for (int y = 0; y < problem.length; y++) {
            for (int x = 0; x < problem[y].length; x++) {
                if(problem[y][x] > 0) {
                    Block block = new Block(new Position(x, y), problem[y][x]);
                    blocks.add(block);
                }
            }
        }

        return blocks;
    }

    @Override
    public List<List<Position>> level2(int[][] problem, Block block) {
        if(block.getNumber() == 0) {
//                    return
            System.out.println("------" + block);
        }

        List<List<Position>> positionListList = new ArrayList<>();

        List<Position> firstPosition = new ArrayList<>();

//                firstPosition.add(block.getPosition());

        System.out.println();System.out.println();System.out.println();

        nextPosition(positionListList, firstPosition, block.getPosition(), block.getNumber()-1,block);


        System.out.println();System.out.println();System.out.println();

        System.out.println("positionListList " + positionListList);

        return positionListList;
    }

    private void nextPosition(List<List<Position>> positionListList, List<Position> positionTrace, Position thisPosition, int hasCount,Block block) {

        System.out.println(thisPosition + " ==== " + hasCount + "  ==== " + block);
        //todo change
        int height = 5;
        int width = 5;

        positionTrace.add(thisPosition);
        if(hasCount == 0) {

            System.out.println( block + " positionTrace Add " + positionTrace);

            positionListList.add(positionTrace);

            return;
        }

        if(thisPosition.getX() !=0) {
            System.out.println("x -1 call");
            Position position = new Position();
            position.setX(thisPosition.getX()-1);
            position.setY(thisPosition.getY());
            nextPosition(positionListList, deepCopyAsList(positionTrace), position, hasCount-1,block);
        }
        if(thisPosition.getY() !=0) {
            System.out.println("y -1 call");
            Position position = new Position();
            position.setX(thisPosition.getX());
            position.setY(thisPosition.getY()-1);
            nextPosition(positionListList, deepCopyAsList(positionTrace), position, hasCount-1,block);
        }
        if(thisPosition.getX() != width) {
            System.out.println("x +1 call");
            Position position = new Position();
            position.setX(thisPosition.getX()+1);
            position.setY(thisPosition.getY());
            nextPosition(positionListList, deepCopyAsList(positionTrace), position, hasCount-1,block);
        }
        if(thisPosition.getY() != height) {
            System.out.println("y +1 call");
            Position position = new Position();
            position.setX(thisPosition.getX());
            position.setY(thisPosition.getY()+1);
            nextPosition(positionListList, deepCopyAsList(positionTrace), position, hasCount-1,block);
        }

    }

    private  List<Position> deepCopyAsList(List<Position>  list) {

        List<Position> result = new ArrayList<>();

        for(Position position : list) {
            result.add(position);
        }

        return result;
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
