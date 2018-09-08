package ahea.hackerton.nurikabe.service;

import ahea.hackerton.nurikabe.dto.Block;
import ahea.hackerton.nurikabe.dto.Position;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolveServiceTest {

    private int[][] problem;
    private List<Block> blocks;
    private List<List<Position>> positionsList;

    @Before
    public void setUp() throws Exception {
        int[][] problem = {{0,0,0,0,0},
                        {0,0,0,2,0},
                        {3,0,0,0,5},
                        {0,1,0,0,0},
                        {0,0,0,0,0}};

        this.problem = problem;
    }

    @Test
    public void level1() {
        System.out.println(problem);
    }
}