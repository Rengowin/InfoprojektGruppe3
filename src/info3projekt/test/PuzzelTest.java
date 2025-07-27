package info3projekt.test;
import info3projekt.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuzzelTest {

    private NormalPuzzel normalPuzzel;
    private FinalPuzzel finalPuzzel;

    // beforeEach open before each test to create the puzzles for test purposes
    @BeforeEach
    void setUp() {
        // Create a NormalPuzzel
        normalPuzzel = new NormalPuzzel(5);
        // Create a FinalPuzzel
        finalPuzzel = new FinalPuzzel(1);
    }

    @Test
    void testNormalPuzzelCreation() {
        assertEquals(5, normalPuzzel.getPuzzelTyp());
        assertNotNull(normalPuzzel.getPuzzelInfo());
        assertNotNull(normalPuzzel.getSolution());
    }

    @Test
    void testFinalPuzzelCreation() {
        assertEquals(1, finalPuzzel.getPuzzelTyp());
    }

    @Test
    void testFinalPuzzelSolution() {
        Object solution = finalPuzzel.getSolution();
        assertTrue(solution.toString().contains("no way to get the solution"));
    }

    @Test
    void testNormalPuzzelSolution() {
        Object solution = normalPuzzel.getSolution();
        assertNotNull(solution);
        assertEquals(1800, solution);
    }

    @Test
    void testDifferentPuzzleTypes() {
        NormalPuzzel puzzle1 = new NormalPuzzel(1);
        NormalPuzzel puzzle2 = new NormalPuzzel(8);
        NormalPuzzel puzzle3 = new NormalPuzzel(15); // Unknown type, will become -1

        assertEquals(1, puzzle1.getPuzzelTyp());
        assertEquals(8, puzzle2.getPuzzelTyp());
        assertEquals(-1, puzzle3.getPuzzelTyp()); // Unknown types are converted to -1

        assertNotNull(puzzle1.getPuzzelInfo());
        assertNotNull(puzzle2.getPuzzelInfo());
        assertNotNull(puzzle3.getPuzzelInfo());

        assertEquals(80, puzzle1.getSolution());
        assertEquals(11, puzzle2.getSolution());
    }

    @Test
    void testEdgeCasePuzzleTypes() {
        NormalPuzzel puzzleZero = new NormalPuzzel(0); // Unknown type, becomes -1
        NormalPuzzel puzzleNegative = new NormalPuzzel(-1); // Valid type -1

        //-1 is unkown puzzelTyp
        assertEquals(-1, puzzleZero.getPuzzelTyp()); // 0 is unknown, becomes -1
        assertEquals(-1, puzzleNegative.getPuzzelTyp()); // -1 stays -1

        assertNotNull(puzzleZero.getPuzzelInfo());
        assertNotNull(puzzleNegative.getPuzzelInfo());
    }
}
