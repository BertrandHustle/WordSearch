import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Scott on 5/18/16.
 */
public class test {
    Random testRandom = new Random();
    Puzzle testPuzzle = new Puzzle();
    Capability testCapability = new Capability();

    /**
     * Given a width of 5 and height of 5
     * When grid generator is executed
     * Then prints 5x5
     */

    @Test
    public void whenWidth5AndHeight5ThenOutputsGrid() {

        //arrange

        //act
        String[][] testGrid = testPuzzle.GenerateGrid(5, 5);

        //assert
        assertThat(testGrid.length * testGrid[0].length, is(25));

    }

    /**
     * Given a grid and a specific coordinate
     * When fill number method is executed 3 times
     * Then the coordinate contains a different letter each time
     */

    @Test
    public void whenFilled3TimesThenSpaceHasDifferentLetterEachTime() {

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
    public void whenGivenDictionaryThenIndex2IsRandomWord() throws IOException {

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
    public void whenGivenBigWordThenWordNotInscribedInPuzzleGrid() throws IOException {

        //arrange
        Word word = new Word();
        word.setWord("PARSIMONIOUS");

        String[][] grid = testPuzzle.GenerateGrid(10, 10);
        boolean foundLetter = false;

        //act
        String[][] testGrid = testCapability.generateWord(word, grid, "horizontal");

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                System.out.println(grid[x][y]);
                if (!grid[x][y].equals("_")) {
                    foundLetter = true;
                }
            }
        }

        //assert
        assertThat(foundLetter, is(false));

    }

    /**
     * Given a json String with the right parameters
     * When puzle generator is run
     * Then outputs a puzzle
     */

    @Test
    public void whenGivenAJsonThenOutputsPuzzle(){
        //arrange
        boolean pass = true;
        String testJson = "{\n" +
                "   \"width\": 20,\n" +
                "   \"height\": 20,\n" +
                "   \"words\": 10,\n" +
                "   \"minLength\": 4,\n" +
                "   \"maxLength\": 8,\n" +
                "   \"capabilities\": [\n" +
                "      \"horizontal\",\n" +
                "      \"vertical\",\n" +
                "      \"diagonal-up\"\n" +
                "   ]\n" +
                "}";
        //act
        Gson gson = new GsonBuilder().create();
        Puzzle testPuzzle = gson.fromJson(testJson, Puzzle.class);
        String[][] testGrid = testPuzzle.getPuzzle();

        for (int x = 0; x < testPuzzle.getHeight(); x++) {
            for (int y = 0; y < testPuzzle.getWidth(); y++) {
                if (testGrid[x][y].equals("_")){
                    pass = false;
                }
            }

        }

        //assert
        assertThat((testPuzzle.getCapabilities().contains("horizontal"))
        &&(testPuzzle.getCapabilities().contains("vertical"))
        &&(testPuzzle.getCapabilities().contains("diagonal-up"))&& pass, is(true));



    }
    /**Given a word
     * When word is output into puzzle
     * Then coordinates of first and last letter matach puzzle coordinates
     */
    @Test
    public void whenWordOutputThenCoordinatesMatchPuzzle() throws IOException {
        //arrange
        String[][] testGrid = testPuzzle.GenerateGrid(20, 40);


        //act
        ArrayList<Capability>capabilities = new ArrayList<>();
        testPuzzle.setCapabilities(capabilities);


        int numOfWords = 10;

        String[][] grid = testPuzzle.GenerateGrid(20, 40);
        testPuzzle.setPuzzle(grid);

        //do exception for list with only one capability
        ArrayList<String> capabilitiesList = new ArrayList<>();

        capabilitiesList.add("vertical");
        capabilitiesList.add("horizontal");
        capabilitiesList.add("diagonal-up");
        capabilitiesList.add("diagonal-down");

        for(String s : capabilitiesList){
            Capability newCapability = new Capability();
            newCapability.setName(s);
            newCapability.setKeyword(s);
            newCapability.setDescription("Adds words at a "+s+" angle in the puzzle");

            testPuzzle.getCapabilities().add(newCapability);
        }

        ArrayList<Word> words = new ArrayList<>();

        for (int i = 0 ; i < numOfWords;) {

            //this is the word we'll be passing in
            Word word = new Word();
            word.setWord(testPuzzle.getRandomWord(4, 10));

            int capabilitySelection = testRandom.nextInt(capabilitiesList.size());

            try {
                testCapability.generateWord(word, grid, capabilitiesList.get(capabilitySelection));
                words.add(word);
                testPuzzle.getWords().add(word);
                i++;
            } catch (IndexOutOfBoundsException ioe){
                int x = 0;
            }

        }

        String testWord = words.get(0).getWord();
        char firstLetter = words.get(0).getWord().charAt(0);
        char lastLetter = testWord.charAt(testWord.length()-1);
        String testString = testPuzzle.getPuzzle()[words.get(0).getY1()][words.get(0).getX1()];
        String testString2 = testPuzzle.getPuzzle()[words.get(0).getY2()][words.get(0).getX2()];
        //assert
        assertThat(lastLetter, is(testString2));
        //assertThat(lastLetter, is(testString2));
    }
}




