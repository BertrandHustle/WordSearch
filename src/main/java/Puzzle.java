import java.util.ArrayList;
import java.util.Random;

/**
 * Describes puzzle and its attributes
 */
public class Puzzle {

    int Width;
    int Height;
    int numberOfWords;
    int minWordLength;
    int maxWordLength;
    ArrayList<Capability> capabilities;

    public String[][] GenerateGrid(int width, int height){

        String[][] grid = new String[width][height];

        for (int x = 0; x < width; x++){

            for (int y = 0; y < height; y++){

                grid[x][y] = "_";

            }
        }

        return grid;

    }

    public void GenerateWord(String word, Capability capability){

    }

    public void FillLetters(String[][] grid){

        //String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int width = grid.length;
        int height = grid[0].length;

        Random letter = new Random();

        for (int x = 0; x < width; x++){

            for (int y = 0; y < height; y++){

                char c = (char)(letter.nextInt(26) + 'A');
                grid[x][y] = Character.toString(c);

            }
        }

    }

    public ArrayList<String> wordList(int numberOfWords, int minWordLength, int maxWordLength){

        ArrayList<String> wordList = new ArrayList<>();


        return wordList;
    }

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
