import java.util.Random;

/**
 * Describes capabilities of word generation (e.g. horizontal or diagonal-up)
 */

public class Capability {

    String name;
    String description;
    String keyword;

    Random random = new Random();

    /**
     * for horizontal + i to randomY,
     * for vertical + i to randomX,
     * for diag-up - i to randomX, + i to randomY,
     * for diag-down + i to randomX, + i to randomY
     */

    //generates horizontal word
    public String[][] generateHorizontal (String word, String[][] grid) {

        int xBound = grid.length;
        int yBound = grid[0].length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;

        //ghostwriter, checks if word will hit wall

        boolean hitAWall = false;

        for (int i = 0; i <= word.length(); i++) {

            //try/catch block for hitting a wall
            try {
                //sets characters in word to spaces in grid
                String dummy = grid[randomX][(randomY + i)];
            } catch (IndexOutOfBoundsException ioe) {
                hitAWall = true;
                break;
            }
        }

        if (hitAWall == false) {

            for (int i = 0; i <= word.length(); i++) {

                //try/catch block for hitting a wall
                try {
                    //sets characters in word to spaces in grid
                    grid[(randomX)][randomY + i] = Character.toString(word.charAt(i));
                } catch (IndexOutOfBoundsException ioe) {
                    //todo: put reversal method here?
                    break;
                }

            }
        }
        return grid;
    }


    //generates vertical words
    public String[][] generateVertical (String word, String[][] grid){
        int xBound = grid.length;
        int yBound = grid[0].length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;

        //ghostwriter, checks if word will hit wall

        boolean hitAWall = false;

        for (int i = 0; i <= word.length(); i++) {

            //try/catch block for hitting a wall
            try {
                //sets characters in word to spaces in grid
                String dummy = grid[randomX+i][(randomY)];
            } catch (IndexOutOfBoundsException ioe) {
                hitAWall = true;
                break;
            }
        }

        if (hitAWall == false) {

            for (int i = 0; i <= word.length(); i++) {

                //try/catch block for hitting a wall
                try {
                    //sets characters in word to spaces in grid
                    grid[(randomX+i)][randomY] = Character.toString(word.charAt(i));
                } catch (IndexOutOfBoundsException ioe) {
                    //todo: put reversal method here?
                    break;
                }

            }
        }
        return grid;
    }

    //generates diagonal-up words
    public String[][] generateDiagonalUp (String word, String[][] grid){
        int xBound = grid.length;
        int yBound = grid[0].length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;

        //ghostwriter, checks if word will hit wall

        boolean hitAWall = false;

        for (int i = 0; i <= word.length(); i++) {

            //try/catch block for hitting a wall
            try {
                //sets characters in word to spaces in grid
                String dummy = grid[randomX-i][(randomY+i)];
            } catch (IndexOutOfBoundsException ioe) {
                hitAWall = true;
                break;
            }
        }

        if (hitAWall == false) {

            for (int i = 0; i <= word.length(); i++) {

                //try/catch block for hitting a wall
                try {
                    //sets characters in word to spaces in grid
                    grid[(randomX-i)][randomY + i] = Character.toString(word.charAt(i));
                } catch (IndexOutOfBoundsException ioe) {
                    //todo: put reversal method here?
                    break;
                }

            }
        }
        return grid;
    }

    //generates words diagonal-down
    public String[][] generateDiagonalDown (String word, String[][] grid){
        int xBound = grid.length;
        int yBound = grid[0].length;

        //these get random x,y coordinates in the grid, adding one to account for 0 inclusive/bound exclusive
        int randomX = random.nextInt(xBound) + 1;
        int randomY = random.nextInt(yBound) + 1;

        //ghostwriter, checks if word will hit wall

        boolean hitAWall = false;

        for (int i = 0; i <= word.length(); i++) {

            //try/catch block for hitting a wall
            try {
                //sets characters in word to spaces in grid
                String dummy = grid[randomX+i][(randomY+i)];
            } catch (IndexOutOfBoundsException ioe) {
                hitAWall = true;
                break;
            }
        }

        if (hitAWall == false) {

            for (int i = 0; i <= word.length(); i++) {

                //try/catch block for hitting a wall
                try {
                    //sets characters in word to spaces in grid
                    grid[(randomX+i)][randomY + i] = Character.toString(word.charAt(i));
                } catch (IndexOutOfBoundsException ioe) {
                    //todo: put reversal method here?
                    break;
                }

            }
        }
        return grid;
    }

}
