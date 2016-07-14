
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Spark;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Main {

  public static void main(String[] args) throws IOException{

      Puzzle puzzle = new Puzzle();
      ArrayList<Capability>capabilities = new ArrayList<>();
      puzzle.setCapabilities(capabilities);
      Random random = new Random();
      Capability capability = new Capability();

      //represents all possible capabilities
      ArrayList<Capability>possibleCapabilities = new ArrayList<>();
      ArrayList<String>possibleCapabilityStrings = new ArrayList<>();
      Puzzle possiblePuzzle = new Puzzle(1, 1, 1, 1, 1, possibleCapabilityStrings, possibleCapabilities);

      possibleCapabilityStrings.add("horizontal");
      possibleCapabilityStrings.add("vertical");
      possibleCapabilityStrings.add("diagonal-up");
      possibleCapabilityStrings.add("diagonal-down");

      possiblePuzzle.setRequestCapabilities(possibleCapabilityStrings);
      possibleCapabilities = Puzzle.makeCapabilitiesList(possiblePuzzle);

    //disable this for local testing
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

      //capabilities
      Spark.get(
              "/capabilities",
              (request, response) -> {

                  Gson gson = new GsonBuilder().create();
                  String json = gson.toJson(possiblePuzzle.getCapabilities());

                  return json;
              }
      );

      //this holds details about the puzzle
      Spark.post(
              "/puzzle",
              (request, response) -> {

                  long startTime = System.currentTimeMillis();

                  //parses puzzle request
                  String jsonRequest = request.body();
                  Gson gsonRequest = new GsonBuilder().create();
                  Puzzle puzzleRequest = gsonRequest.fromJson(jsonRequest, Puzzle.class);

                  //init
                  ArrayList<String>capabilitiesList = new ArrayList<>();
                  int maxWordLength = puzzleRequest.getMaxWordLength();
                  int minWordLength = puzzleRequest.getMinWordLength();
                  int width = puzzleRequest.getWidth();
                  int height = puzzleRequest.getHeight();


                  //sets capabilities to actually BE Capabilities (call capability list maker here)
                  ArrayList<Capability>requestCapabilities = new ArrayList<Capability>();

                  Puzzle puzzleResponse = new Puzzle(width, height, puzzleRequest.getNumberOfWords(),  minWordLength, maxWordLength, puzzleRequest.getRequestCapabilities(), requestCapabilities);

                  //Puzzle.makeCapabilitiesList(puzzleResponse);
                  requestCapabilities = Puzzle.makeCapabilitiesList(puzzleResponse);


                  ArrayList<Word>words = new ArrayList<>();

                  //generate grid
                  String[][] grid = puzzleRequest.GenerateGrid(width, height);

                  for (int i = 0 ; i < puzzleRequest.getNumberOfWords();) {

                      //this is the word we'll be passing in
                      Word word = new Word();
                      word.setWord(puzzle.getRandomWord(minWordLength, maxWordLength));

                      int capabilitySelection = random.nextInt(puzzleResponse.getRequestCapabilities().size());

                      try {
                          capability.generateWord(word, grid, puzzleResponse.getRequestCapabilities().get(capabilitySelection));
                          words.add(word);
                          i++;
                      } catch (IndexOutOfBoundsException ioe){
                          int x = 0;
                      }

                  }

                  puzzle.FillLetters(grid);

                  puzzle.printPuzzle(grid);

                  int i = 0;

                  Puzzle gsonPuzzle = new Puzzle();
                  gsonPuzzle.setPuzzle(grid);

                  Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                  for (Word word : words){
                      gsonPuzzle.words.add(word);
                  }
                  String json = gson.toJson(gsonPuzzle);


                  //String jsonWords = gson.toJson(words);

                  System.out.println(gsonPuzzle);
                  System.out.println((System.currentTimeMillis() - startTime));

                  //System.out.println(words);

                  return json;
              }
      );

  }

}
