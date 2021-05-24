/**
 * Given a point on a 3-D cubic plane, this class
 * stores the rate of change that is needed to
 * check for a connect 4 --- (dx, dy, dz).
 * Additionally, used for 2-D movement.
 * <p>
 * This allows movement from a specific point
 * on a 3-D cubic plane.
 */
public class Direction {
    // ROC -> rate of change, of (x, y, z) for a particular point
    private final int rowROC;
    private final int colROC;
    private int heightROC;

    /**
     * Stores parameters indicating the rate of change
     * of a row, column, and height. Used for 3-D movement
     *
     * @param rowROC    the slope at which the row changes
     * @param colROC    the slope at which the column changes
     * @param heightROC the slope at which the height changes
     */
    public Direction(int rowROC, int colROC, int heightROC) {
        this.rowROC = rowROC;
        this.colROC = colROC;
        this.heightROC = heightROC;
    }

    /**
     * Stores parameters indicating the rate of change
     * or a row and column. Used for 2-D movement.
     * @param rowROC
     * @param colROC
     */
    public Direction(int rowROC, int colROC) {
        this.rowROC = rowROC;
        this.colROC = colROC;
    }

    /**
     * Gets the negated slope from the initial given
     * rate of changes. This slope provides the
     * other half of the direction to check from a
     * given point.
     *
     * @return the negated slope from the original
     * rate of change set.
     */
    public Direction getOtherDir() {
        return new Direction(-rowROC, -colROC, -heightROC);
    }

    /**
     * Gets the rate of change of the row.
     *
     * @return an int indicating the ROC for
     * a given points row value.
     */
    public int getRowROC() {
        return rowROC;
    }

    /**
     * Gets the rate of change of the column.
     *
     * @return an int indicating the ROC for
     * a given points column value.
     */
    public int getColROC() {
        return colROC;
    }

    /**
     * Gets the rate of change of the height.
     *
     * @return an int indicating the ROC for
     * a given points height value.
     */
    public int getHeightROC() {
        return heightROC;
    }

    /**
     * Determines if two directions are equal
     * comparing row, col and height.
     *
     * @return true if two Directions
     * are equal
     */
    public boolean equals(Object other) {
        if (!(other instanceof Direction)) {
            return false;
        }
        return ((Direction) other).rowROC == rowROC && ((Direction) other).colROC == colROC
                && ((Direction) other).heightROC == heightROC;
    }

    /**
     * Prints out the Rate of Change values
     *
     * @return a String indicating the ROC values
     * for a row, column, and height.
     */
    public String toString() {
        return "[row: " + rowROC + ", " +
                "col: " + colROC + ", " +
                "Height: " + heightROC + "]";
    }
}