
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.Spark;
import spark.ModelAndView;

import spark.template.mustache.MustacheTemplateEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Main {

  public static void main(String[] args) throws IOException{

      long startTime = System.currentTimeMillis();

      Puzzle puzzle = new Puzzle();
      ArrayList<Capability>capabilities = new ArrayList<>();
      puzzle.setCapabilities(capabilities);
      Random random = new Random();
      Capability capability = new Capability();

      //test for word gen
      int numOfWords = 10;

      String[][] grid = puzzle.GenerateGrid(20, 40);

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

          puzzle.getCapabilities().add(newCapability);
      }

      ArrayList<Word> words = new ArrayList<>();

      for (int i = 0 ; i < numOfWords;) {

          //this is the word we'll be passing in
          Word word = new Word();
          word.setWord(puzzle.getRandomWord(4, 10));

          int capabilitySelection = random.nextInt(capabilitiesList.size());

          try {
              capability.generateWord(word, grid, capabilitiesList.get(capabilitySelection));
              words.add(word);
              i++;
          } catch (IndexOutOfBoundsException ioe){
              int x = 0;
          }

      }

      puzzle.FillLetters(grid);

      puzzle.printPuzzle(grid);

      int x = 0;

      System.out.println((System.currentTimeMillis() - startTime));


    //port(Integer.valueOf(System.getenv("PORT")));
    //staticFileLocation("/public");

      //capabilities
      Spark.get(
              "/capabilities",
              (request, response) -> {

                  Gson gson = new GsonBuilder().create();
                  String json = gson.toJson(puzzle.getCapabilities());

                  return json;
              }
      );

      //this holds details about the puzzle
      Spark.post(
              "/puzzle",
              (request, response) -> {

                  //todo: rename field to "puzzle"
                  //todo: add words list with coordinates

                  Puzzle gsonPuzzle = new Puzzle();
                  gsonPuzzle.setGrid(grid);

                  Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                  String json = gson.toJson(gsonPuzzle);

                  return json;
              }
      );

  }

}
