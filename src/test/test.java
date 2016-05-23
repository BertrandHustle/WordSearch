import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Scott on 5/18/16.
 */
public class test {

    Puzzle testPuzzle = new Puzzle();
    Capability testCapability = new Capability();

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
        String[][] testGrid2 = testPuzzle.GenerateGrid(4, 5);
        String[][] testGrid3 = testPuzzle.GenerateGrid(4, 5);

        //act
        testPuzzle.FillLetters(testGrid);
        String test1 = testGrid[3][3];

        testPuzzle.FillLetters(testGrid);
        String test2 = testGrid2[3][3];

        testPuzzle.FillLetters(testGrid);
        String test3 = testGrid3[3][3];

        //assert
        assertThat(test1.equals(test2) && (test2.equals(test3)) && (test1.equals(test3)), is(false));

    }

    /**
     * Given a dictionary API
     * When method is called with min/max params (2, 10)
     * Then word at index[2] is different each time and adheres to params
     */

    @Test
    public void whenGivenDictionaryThenIndex2IsRandomWord() throws IOException{

        //arrange

        //act
        ArrayList<String> testWordList = testPuzzle.wordList(5, 2, 10);
        ArrayList<String> testWordList2 = testPuzzle.wordList(5, 2, 10);
        ArrayList<String> testWordList3 = testPuzzle.wordList(5, 2, 10);

        String testWord = testWordList.get(2);
        String testWord2 = testWordList2.get(2);
        String testWord3 = testWordList3.get(2);

        //assert
        assertThat(testWordList.size(), is(5));
        assertThat(testWord.equals(testWord2) &&
        testWord2.equals(testWord3) &&
        testWord.equals(testWord3), is(false));

    }

    /**
     * Given a word too big for puzzle
     * When word is output into puzzle
     * Then word prints if it wouldn't hit a wall
     */

    @Test
    public void whenGivenBigWordThenWordNotInscribedInPuzzleGrid() throws IOException{

        //arrange
        Word word = new Word();
        word.setWord("PARSIMONIOUS");

        String[][] grid = testPuzzle.GenerateGrid(10, 10);
        boolean foundLetter = false;

        //act
        String[][]testGrid = testCapability.generateWord(word, grid, "horizontal");

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
               System.out.println(grid[x][y]);
               if (!grid[x][y].equals("_")){
                   foundLetter = true;
               }
            }
        }

        //assert
        assertThat(foundLetter, is(false));

    }

    /**
     * Given a capability
     * When word inscription is run
     * Then word is inscribed in correct direction
     */

    @Test
    public void whenGivenCapabilityThenWordDirectionIsCorrect(){

        //arrange
        String[][]grid = testPuzzle.GenerateGrid(1, 3);
        Word word = new Word();
        word.setWord("HAM");
        String [][] resultGrid;

        //act

        boolean b = true;
        while (true) {
            try {
                resultGrid = testCapability.generateWord(word, grid, "vertical");
                b = false;
            } catch (IndexOutOfBoundsException ioe) {
                int x = 0;
            }
        }

        //assert
        //assertThat((resultGrid[1][1].equals("H")) && (resultGrid[1][2].equals("A")) && (resultGrid[1][3].equals("M")), is(true));


    }

}
