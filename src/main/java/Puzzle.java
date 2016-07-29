import com.google.gson.annotations.Expose;

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

    //todo: set Puzzle properties to user constraints when puzzle is generated

    private int height;
    private int width;
    private int numberOfWords;
    private int minWordLength;
    private int maxWordLength;
    private ArrayList<String>requestCapabilities;
    private ArrayList<Capability> capabilities;

    @Expose
    private String[][] puzzle;

    @Expose
    ArrayList<Word>words = new ArrayList<>();
    private Random random = new Random();

    public Puzzle(ArrayList<String> requestCapabilities, int maxWordLength, int minWordLength, int numberOfWords, int width, int height) {
        this.requestCapabilities = requestCapabilities;
        this.maxWordLength = maxWordLength;
        this.minWordLength = minWordLength;
        this.numberOfWords = numberOfWords;
        this.width = width;
        this.height = height;
    }

    public Puzzle(int height, int width, int numberOfWords, int minWordLength, int maxWordLength, ArrayList<String> requestCapabilities, ArrayList<Capability> capabilities) {
        this.height = height;
        this.width = width;
        this.numberOfWords = numberOfWords;
        this.minWordLength = minWordLength;
        this.maxWordLength = maxWordLength;
        this.requestCapabilities = requestCapabilities;
        this.capabilities = capabilities;
    }


    public Puzzle() {
    }

    //generates puzzle grid
    public String[][] GenerateGrid(int width, int height){

        String[][] grid = new String[width][height];

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                grid[x][y] = "_";
            }
        }
        return grid;
    }



    //this fills empty spaces in puzzle puzzle w/random letters
    public void FillLetters(String[][] grid){

        int width = grid.length;
        int height = grid[0].length;

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                //check if space is already filled
                if (grid[x][y].equals("_")) {
                    //fill space with random letter
                    char c = (char) (random.nextInt(26) + 'A');
                    grid[x][y] = Character.toString(c);
                }
            }
        }

    }


    //todo: get rid of this, it's useless
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
        File dict = new File("Dictionary.txt");
        Scanner scanner = new Scanner(dict);
        scanner.useDelimiter("\\Z");

        //builds list of words in dictionary
        String[] allWords = scanner.next().split("\n");
        List<String> wordList = Arrays.asList(allWords);

        List<String> filteredWords = wordList.stream()
                .filter(word -> word.length() >= minWordLength && word.length() <= maxWordLength)
                .collect(Collectors.toList());

        return filteredWords.get(new Random().nextInt(filteredWords.size()-1)).toUpperCase();

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

    public static ArrayList<Capability>makeCapabilitiesList(Puzzle puzzle){
        ArrayList<Capability> capabilitiesList = new ArrayList<>();

        for(String s : puzzle.getRequestCapabilities()){
            Capability newCapability = new Capability();
            newCapability.setName(s);
            newCapability.setKeyword(s);
            newCapability.setDescription("Adds words at a "+s+" angle in the puzzle");

            puzzle.capabilities.add(newCapability);
        }

        return capabilitiesList;
    }

    //Getters and setters

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public String[][] getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String[][] puzzle) {
        this.puzzle = puzzle;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public ArrayList<String> getRequestCapabilities() {
        return requestCapabilities;
    }

    public void setRequestCapabilities(ArrayList<String> requestCapabilities) {
        this.requestCapabilities = requestCapabilities;
    }
}
