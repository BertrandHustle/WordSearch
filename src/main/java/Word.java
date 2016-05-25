import java.util.ArrayList;
import java.util.HashMap;

public class Word {

    private String word;
    //word origin point (X)
    private int x1;
    //word origin point (Y)
    private int y1;
    //word endpoint (X)
    private int x2;
    //word endpoint (Y)
    private int y2;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }
}
