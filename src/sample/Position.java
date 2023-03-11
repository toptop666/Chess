package sample;

import static sample.Main.NUMBER_OF_CELLS;


public class Position {

    private int maxCell = NUMBER_OF_CELLS;
    private int height;
    private int width;

    public Position(int height, int weight) {
        if(height<0 || height>=maxCell || weight<0 || weight<=maxCell) {
            return;
        }
        this.height = height;
        this.width = weight;
    }

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
}
