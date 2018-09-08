package ahea.hackerton.nurikabe.service;

import ahea.hackerton.nurikabe.dto.Block;
import ahea.hackerton.nurikabe.dto.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolveServiceTest {

    private int[][] problem;
    private List<Block> blocks;
    private List<List<Position>> positionsList;
    List<Position> movePositions;

    @Autowired
    private SolveService solveService;

    @Before
    public void setUp() throws Exception {
        int[][] problem = {{1,0,0,0,1},
                        {0,0,0,0,0},
                        {0,3,0,1,0},
                        {0,0,0,0,0},
                        {3,0,0,0,1}};

        this.problem = problem;
        setBlock();
        setData();

        this.movePositions = Arrays.asList(
                new Position(1,0)
                , new Position(-1,0)
                , new Position(0,1)
                , new Position(0,-1)
        );
    }

    private static List<Block> setBlock() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block();
        Position position = new Position();
        position.setX(2);
        position.setY(0);
        Block block1 = new Block();
        Position position1 = new Position();
        position1.setX(4);
        position1.setY(2);
        Block block2 = new Block();
        Position position2 = new Position();
        position2.setX(4);
        position2.setY(4);

        block.setPosition(position);
        block1.setPosition(position1);
        block2.setPosition(position2);
        blocks.add(block);
        blocks.add(block1);
        blocks.add(block2);

        System.out.println(blocks);
        return blocks;
    }

    private static List<List<Position>> setData() {
        List<List<Position>> result = new ArrayList<>();
        List<Position> positions = new ArrayList<>();
        Position position1 = new Position();
        position1.setX(2);
        position1.setY(0);
        Position position2 = new Position();
        position2.setX(3);
        position2.setY(0);
        Position position3 = new Position();
        position3.setX(4);
        position3.setY(0);
        positions.add(position3);
        positions.add(position1);
        positions.add(position2);
        result.add(positions);

        position1 = new Position();
        position2 = new Position();
        position3 = new Position();
        positions = new ArrayList<>();
        position1.setX(4);
        position1.setY(0);
        position2.setX(3);
        position2.setY(0);
        position3.setX(3);
        position3.setY(1);
        positions.add(position1);
        positions.add(position2);
        positions.add(position3);
        result.add(positions);

        position1 = new Position();
        position2 = new Position();
        position3 = new Position();
        positions = new ArrayList<>();
        position1.setX(4);
        position1.setY(1);
        position2.setX(3);
        position2.setY(1);
        position3.setX(4);
        position3.setY(0);
        positions.add(position3);
        positions.add(position1);
        positions.add(position2);
        result.add(positions);


        position1 = new Position();
        position2 = new Position();
        position3 = new Position();
        positions = new ArrayList<>();
        position1.setX(4);
        position1.setY(2);
        position2.setX(4);
        position2.setY(1);
        position3.setX(4);
        position3.setY(0);
        positions.add(position3);
        positions.add(position1);
        positions.add(position2);
        result.add(positions);



        return result;
    }

    private static void printPosition(List<List<Position>> result) {
        for(List<Position> ss : result){
            System.out.println(ss.toString());
        }
    }

    @Test
    public void level1() {
        blocks = solveService.level1(problem);
        blocks.forEach(src -> {
            assertTrue(src.getNumber() == problem[src.getPosition().getX()][src.getPosition().getY()]);
        });
    }
    @Test
    public void level3() {
        List<List<Position>> result = solveService.level3(setBlock(),setData());
        printPosition(result);
    }

    @Test
    public void level2() {
        SolveService solveService = new SolveService() {
            @Override
            public List<List<Position>> solveProblem(int[][] problem) {
                return null;
            }

            @Override
            public List<Block> level1(int[][] problem) {
                return null;
            }

            @Override
            public List<List<Position>> level2(Block block) {
                List<List<Position>> positionListList = new ArrayList<>();

                for (Position movePosition : movePositions) {
                    List<Position> positions = new ArrayList<>();
                    positions.add(block.getPosition());
                    positions.add(calcSumPosition(positions.get(0), movePosition));
                    positionListList.add(nextPosition(positions, block.getNumber()));
                }

                return positionListList;
            }

            @Override
            public List<List<Position>> level3(List<Block> blocks, List<List<Position>> positionsList) {
                return null;
            }

            @Override
            public List<List<Position>> level4(List<List<Position>> p1, List<List<Position>> p2) {
                return null;
            }

            private List<Position> nextPosition(List<Position> positions, int nextCount) {
                for (Position movePosition : movePositions) {
                    Position calcSumPosition = calcSumPosition(positions.get(positions.size() - 1), movePosition);
                    if (isOverPosition(calcSumPosition)
                            && problem[calcSumPosition.getX()][calcSumPosition.getY()] == 0
                    )
                        positions.add(calcSumPosition);
                }
                nextCount -= 1;

                if (nextCount >= 0)
                    return nextPosition(positions, nextCount);
                else
                    return null;
            }

            private Position calcSumPosition(Position arg1, Position arg2) {
                return new Position(arg1.getX() + arg2.getX(), arg1.getY() + arg2.getY());
            }

            private boolean isOverPosition(Position position) {
                if (position.getX() < 0 || position.getY() < 0)
                    return false;

                if (position.getX() >= problem.length || position.getY() >= problem.length)
                    return false;

                return true;
            }

        };

        System.out.println(solveService.level2(new Block(new Position(1,2), 3)));
    }
}