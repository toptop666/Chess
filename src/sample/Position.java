package sample;

import java.util.Objects;

import static sample.Main.NUMBER_OF_CELLS;


public class Position implements Cloneable{

    private int maxCell = NUMBER_OF_CELLS;
    private int height = -1;
    private int width = -1;

    public Position(int height, int width) {
        if(height<0 || height>=maxCell || width<0 || width>=maxCell) {
            return;
        }
        this.height = height;
        this.width = width;
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

    @Override
    public String toString() {
        return "(" + this.height + " ," + this.width + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return height == position.height && width == position.width;
    }

    public Object clone() throws CloneNotSupportedException {
        Position position = (Position) super.clone();
        return position;
    }


}
