import sun.jvm.hotspot.memory.Dictionary;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
    ArrayList<String> capabilities;

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

    //returns single random word
    public String getRandomWord(int minWordLength, int maxWordLength) throws IOException{

        //scanner for dictionary
        File dict = new File("/usr/share/dict/words");
        Scanner scanner = new Scanner(dict);
        scanner.useDelimiter("\\Z");

        //builds list of words in dictionary
        String[] allWords = scanner.next().split("\n");
        List<String> wordList = Arrays.asList(allWords);

        List<String> filteredWords = wordList.stream()
                .filter(word -> word.length() >= minWordLength && word.length() <= maxWordLength)
                .collect(Collectors.toList());

        return filteredWords.get(new Random().nextInt(filteredWords.size()-1)).toUpperCase();

        /*
        //generates random int

        boolean success = false;
        String randomWord = "";

        while(!success){

        int randomNumber = random.nextInt(235887);

        //exception handling for number 0
        if (randomNumber == 0){
            randomNumber = 1;
        }

        //gets random word
        randomWord = Files.readAllLines(Paths.get("Dictionary.txt")).get(randomNumber).toUpperCase();

        if ((randomWord.length() <= maxWordLength) && (randomWord.length() >= minWordLength)){
                success = true;
            }
        }

        return randomWord;
        */

    }

    public static void printPuzzle (String[][] grid){

        int xBound = grid.length;
        int yBound = grid[0].length;

        for (int x = 0; x < xBound; x++) {
            for (int y = 0; y < yBound; y++) {
                System.out.print(grid[x][y] + " ");
            }
            System.out.print("\n");
        }

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

    public ArrayList<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ArrayList<String> capabilities) {
        this.capabilities = capabilities;
    }
}
