public class Direction {
    private final int row;
    private final int col;
    private final int height;

    public Direction(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
    }

    public Direction getOtherDir () {
        return new Direction(-row, -col, -height);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getHeight() {
        return height;
    }

    public String toString() {
        return "[row: " + row + ", " +
                "col: " + col + ", " +
                "Height: " + height + "]";
    }
}