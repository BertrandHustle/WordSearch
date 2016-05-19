import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

/**
 * Describes puzzle and its attributes
 */
public class Puzzle {

    //todo: set Puzzle properties to user constraints when grid is generated

    int Width;
    int Height;
    int numberOfWords;
    int minWordLength;
    int maxWordLength;
    ArrayList<Capability> capabilities;

    Random random = new Random();

    //generates grid
    public String[][] GenerateGrid(int width, int height){

        String[][] grid = new String[width][height];

        for (int x = 0; x < width; x++){

            for (int y = 0; y < height; y++){

                grid[x][y] = "_";

            }
        }

        return grid;

    }

    //this inscribes word into puzzle grid
    public String[][] inscribeWord (String word, Capability capability, String[][] grid){

        /**
         * This is will check for boundaries in the following way:
         * Checks "home coordinate" [x][y] in matrix and length of word against capability
         * If home coordinate + length or width > length of row or column in matrix, word does not print
         */

        //todo: X and Y need to be flipped
        //these describe the dimensions of the grid
        int xBound = grid[0].length;
        int yBound = grid.length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;


        //todo: catch out of bounds errors IF STATEMENT

        //ghostwriter

        boolean hitAWall = false;

        for (int i = 0; i <= word.length(); i++) {

            //try/catch block for hitting a wall
            try {
                //sets characters in word to spaces in grid
                String dummy = grid[randomX][(randomY + i)];
            } catch (IndexOutOfBoundsException ioe) {
                hitAWall = true;
                break;
            }
        }

        if (hitAWall == false) {

            for (int i = 0; i <= word.length(); i++) {

                /**
                 * for horizontal + i to randomY,
                 * for vertical + i to randomX,
                 * for diag-up - i to randomX, + i to randomY,
                 * for diag-down add i to randomX, add i from randomY
                 */

                //try/catch block for hitting a wall
                try {
                    //sets characters in word to spaces in grid
                    grid[(randomX)][randomY+i] = Character.toString(word.charAt(i));
                } catch (IndexOutOfBoundsException ioe) {
                    //todo: put reversal method here?
                    break;
                }

            }
        }

        return grid;

    }

    //this fills empty spaces in puzzle grid w/random letters
    public void FillLetters(String[][] grid){

        int width = grid.length;
        int height = grid[0].length;

        for (int x = 0; x < width; x++){

            for (int y = 0; y < height; y++){

                //check if space is already filled
                if (grid[x][y].equals("_")) {
                    char c = (char) (random.nextInt(26) + 'a');
                    grid[x][y] = Character.toString(c);
                }
            }
        }

    }

    //todo: change this to return single words

    //this fills an arraylist with random words from the dictionary
    public ArrayList<String> wordList(int numberOfWords, int minWordLength, int maxWordLength) throws IOException {

        ArrayList<String> wordList = new ArrayList<>();

        //main loop for generating word list

        while (wordList.size() < numberOfWords) {

            //generates random int

            int randomNumber = random.nextInt(235887);

            //exception handling for number 0
            if (randomNumber == 0){
                randomNumber = 1;
            }

            //gets random word and adds to list
            String randomWord = Files.readAllLines(Paths.get("Dictionary.txt")).get(randomNumber);

            //checks to see if word fits min/max constraints, decrements i if not
            //also checks if word is already in list
            if ((randomWord.length() <= maxWordLength)
                && (randomWord.length() >= minWordLength)
                && !wordList.contains(randomWord)) {

                wordList.add(randomWord.toUpperCase());
            }

        }

        return wordList;
    }

    //Getters and setters

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public void setMinWordLength(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }

    public void setMaxWordLength(int maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public ArrayList<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ArrayList<Capability> capabilities) {
        this.capabilities = capabilities;
    }
}
