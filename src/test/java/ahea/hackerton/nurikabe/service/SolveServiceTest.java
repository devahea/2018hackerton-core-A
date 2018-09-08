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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolveServiceTest {

    private int[][] problem;
    private List<Block> blocks;
    private List<List<Position>> positionsList;

    @Autowired
    private SolveService solveService;

    @Before
    public void setUp() throws Exception {
        int[][] problem = {{0,0,0,0,0},
                        {0,0,0,2,0},
                        {3,0,0,0,5},
                        {0,1,0,0,0},
                        {0,0,0,0,0}};

        this.problem = problem;
        setBlock();
        setData();
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
}