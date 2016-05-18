import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

    //this enscribes word into puzzle grid
    public void enscribeWord (String word, Capability capability){

        /**
         * This is will check for boundaries in the following way:
         * Checks "home coordinate" [x][y] in matrix and length of word against capability
         * If home coordinate + length or width > length of row or column in matrix, word does not print
         */



    }

    //this fills empty spaces in puzzle grid w/random letters
    public void FillLetters(String[][] grid){

        int width = grid.length;
        int height = grid[0].length;

        Random letter = new Random();

        for (int x = 0; x < width; x++){

            for (int y = 0; y < height; y++){

                //check if space is already filled
                if (grid[x][y].equals("_")) {
                    char c = (char) (letter.nextInt(26) + 'A');
                    grid[x][y] = Character.toString(c);
                }
            }
        }

    }

    //this fills an arraylist with random words from the dictionary
    public ArrayList<String> wordList(int numberOfWords, int minWordLength, int maxWordLength) throws IOException {

        ArrayList<String> wordList = new ArrayList<>();

        //main loop for generating word list

        while (wordList.size() < numberOfWords) {

            //generates random int

            Random random = new Random();

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

                wordList.add(randomWord);
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
