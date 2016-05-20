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

    //generates horizontal word
    public String[][] generateWord(String word, String[][] grid, String direction) {

        int xBound = grid.length;
        int yBound = grid[0].length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;

        //ghostwriter (scouts out if word will fit)

        boolean noWallsorWords = true;

        for (int i = 0; i < word.length(); i++) {

            String currentPoint = grid[randomX][randomY];
            String currentChar = Character.toString(word.charAt(i));

            //sets characters in word to spaces in grid

            if (currentPoint.equals(currentChar) || currentPoint.equals("_")) {
                int x = 0;
            } else {
                noWallsorWords = false;
                grid[xBound + 10][yBound + 10] = " ";
            }

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


