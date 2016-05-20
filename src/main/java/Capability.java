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
    public String[][] generateWord (String word, String[][] grid, String direction) {

        int xBound = grid.length;
        int yBound = grid[0].length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;


            /*
            //conditional for criss-cross letters
            if (!grid[(randomX)][randomY + i].equals(Character.toString(word.charAt(i)))
                && !grid[(randomX)][randomY + i].equals("_")){
                break;
            }
            */

            for (int i = 0; i < word.length(); i++) {

                //sets characters in word to spaces in grid
                grid[randomX][randomY] = Character.toString(word.charAt(i));

                switch (direction) {
                    case "horizontal": randomY++;
                        break;
                    case "vertical": randomX++;
                        break;
                    case "diagonal-up": randomX-- ; randomY++;
                        break;
                    case "diagonal-down": randomX++; randomY++;
                        break;
                }

            }
        return grid;
    }

}
