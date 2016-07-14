import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Creates dictionary
 */

public class Dictionary {

    public static List<String> createDictionary() throws FileNotFoundException {

        //scanner for dictionary
        File dict = new File("Dictionary.txt");
        Scanner scanner = new Scanner(dict);
        scanner.useDelimiter("\\Z");

        //builds list of words in dictionary
        String[] allWords = scanner.next().split("\n");
        List<String> wordList = Arrays.asList(allWords);
        return wordList;

    }

    //returns single random word
    public static String getRandomWordFromDictionary(int minWordLength, int maxWordLength, List<String>wordList) throws IOException {

        List<String> filteredWords = wordList.stream()
                .filter(word -> word.length() >= minWordLength && word.length() <= maxWordLength)
                .collect(Collectors.toList());

        return filteredWords.get(new Random().nextInt(filteredWords.size()-1)).toUpperCase();

    }

}
