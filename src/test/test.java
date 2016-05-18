import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Scott on 5/18/16.
 */
public class test {

    Puzzle testPuzzle = new Puzzle();

    /**
     * Given a width of 5 and height of 5
     * When grid generator is executed
     * Then prints 5x5
     */

    @Test
    public void whenWidth5AndHeight5ThenOutputsGrid(){

        //arrange

        //act
        String[][]testGrid = testPuzzle.GenerateGrid(5, 5);

        //assert
        assertThat(testGrid.length * testGrid[0].length, is(25));

    }

    /**
     * Given a grid and a specific coordinate
     * When fill number method is executed 3 times
     * Then the coordinate contains a different letter each time
     */

    @Test
    public void whenFilled3TimesThenSpaceHasDifferentLetterEachTime(){

        //arrange
        String[][] testGrid = testPuzzle.GenerateGrid(4, 5);

        //act
        testPuzzle.FillLetters(testGrid);
        String test1 = testGrid[3][3];

        testPuzzle.FillLetters(testGrid);
        String test2 = testGrid[3][3];

        testPuzzle.FillLetters(testGrid);
        String test3 = testGrid[3][3];

        //assert
        assertThat(test1.equals(test2) && (test2.equals(test3)) && (test1.equals(test3)), is(false));

    }

}
