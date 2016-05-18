import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

    /**
     * Given a dictionary API
     * When method is called with min/max params (2, 10)
     * Then word at index[2] is different each time and adheres to params
     */

    @Test
    public void whenGivenDictionaryThenIndex2IsRandomWord(){

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

}