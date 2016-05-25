import java.util.HashMap;
import java.util.Random;

/**
 * Describes capabilities of word generation (e.g. horizontal or diagonal-up)
 */

public class Capability {

    String name;
    String description;
    String keyword;

    Random random = new Random();

    //int initializer = 0;

    /**
     * for horizontal + i to randomY,
     * for vertical + i to randomX,
     * for diag-up - i to randomX, + i to randomY,
     * for diag-down + i to randomX, + i to randomY
     */

    public static void directionSwitch(int X, int Y, String direction){

        switch (direction) {
            case "horizontal":
                Y++;
                break;
            case "vertical":
                X++;
                break;
            case "diagonal-up":
                X--;
                Y++;
                break;
            case "diagonal-down":
                X++;
                Y++;
                break;
        }

    }

    //generates word (more accurately: generates updated grid containing word)
    //todo: this needs to take a Word as argument
    public String[][] generateWord(Word passedInWord, String[][] grid, String direction) {

        int xBound = grid.length;
        int yBound = grid[0].length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;

        //gets word
        String word = passedInWord.getWord();

        //ghostwriter (scouts out if word will fit)

        boolean noWallsorWords = true;

        int testRandomX = randomX;
        int testRandomY = randomY;

        for (int i = 0; i < word.length(); i++) {

            String currentPoint = grid[testRandomX][testRandomY];
            String currentChar = Character.toString(word.charAt(i));

            //sets characters in word to spaces in grid

            if (currentPoint.equals(currentChar) || currentPoint.equals("_")) {
                //todo: use (get char at 0 index) and word.length - 1 to track coordinates

                int x = 0;
            } else {
                noWallsorWords = false;
                grid[xBound + 10][yBound + 10] = " ";
            }

            //Capability.directionSwitch(testRandomX, testRandomY, direction);


            switch (direction) {
                case "horizontal":
                    testRandomY++;
                    break;
                case "vertical":
                    testRandomX++;
                    break;
                case "diagonal-up":
                    testRandomX--;
                    testRandomY++;
                    break;
                case "diagonal-down":
                    testRandomX++;
                    testRandomY++;
                    break;
            }

        }


        if (noWallsorWords) {

            for (int i = 0; i < word.length(); i++) {

                String currentPoint = grid[randomX][randomY];
                String currentChar = Character.toString(word.charAt(i));

                //sets characters in word to spaces in grid
                if (currentPoint.equals(currentChar) || currentPoint.equals("_")) {
                    grid[randomX][randomY] = currentChar;
                } else {
                    grid[xBound + 10][yBound + 10] = " ";
                }

                //Capability.directionSwitch(randomX, randomY, direction);


                switch (direction) {
                    case "horizontal":
                        randomY++;
                        break;
                    case "vertical":
                        randomX++;
                        break;
                    case "diagonal-up":
                        randomX--;
                        randomY++;
                        break;
                    case "diagonal-down":
                        randomX++;
                        randomY++;
                        break;
                }

            }
        }

        Puzzle.printPuzzle(grid);
        System.out.println(" ");

        return grid;
    }
}


