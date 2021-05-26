import java.util.ArrayList;
import java.util.Iterator;

/**
 * Given a set of coordinates on a 3d plane (x, y, z),
 * this class returns all the valid search paths to check
 * for a connect 4. This limits unnecessary recursive calls
 * increasing efficiency.
 */
public class SearchLookUpTable implements CONNECT_CONSTANTS {

    private final ArrayList<Direction> VALID_PATHS;

    /**
     * Creates a bare set of valid paths used
     * for testing purposes.
     */
    public SearchLookUpTable() {
        VALID_PATHS = new ArrayList<>();
    }

    /**
     * Given a coordinate on a 3-D plane, there are
     * 9 possible directions to traverse in 3-D space.
     * However, traversing in certain directions are
     * pointless if there isn't enough space to check
     * four spaces. Therefore a set of possible paths
     * from a point to create a connect 4 are assembled
     * to reduce traversing in irrelevant directions.
     *
     * @param row    starting row index, 0 <= row < PLANE_SIZE
     * @param col    starting column index, 0 <= col < PLANE_SIZE
     * @param height starting height index, 0 <= height < HEIGHT
     */
    public SearchLookUpTable(int row, int col, int height) {
        this();
        // precondition
        if (row < 0 || col < 0 || height < 0 || row >= PLANE_SIZE || col >= PLANE_SIZE
                || height > HEIGHT) {
            throw new IllegalArgumentException("Violation: param when creating search table are " +
                    "invalid.");
        }
        // leftward diagonal
        if (col < MID_INDEX + height && col >= height) {
            VALID_PATHS.add(new Direction(0, 1, 1));
        }
        // rightward diagonal
        if (col > MID_INDEX - height && col < PLANE_SIZE - height) {
            VALID_PATHS.add(new Direction(0, -1, 1));
        }
        // downward diagonal
        if (row < MID_INDEX + height && row >= height) {
            VALID_PATHS.add(new Direction(1, 0, 1));
        }
        // upward diagonal
        if (row > MID_INDEX - height && row < PLANE_SIZE - height) {
            VALID_PATHS.add(new Direction(-1, 0, 1));
        }
        // downward + rightward diagonal
        if (col < MID_INDEX + height && row < MID_INDEX + height &&
                col >= height && row >= height) {
            VALID_PATHS.add(new Direction(1, 1, 1));
        }
        // downward + leftward diagonal
        if (col > MID_INDEX - height && row < MID_INDEX + height &&
                col < PLANE_SIZE - height && row >= height) {
            VALID_PATHS.add(new Direction(1, -1, 1));
        }
        // upward + rightward diagonal
        if ((col > MID_INDEX - height && row > MID_INDEX - height) &&
                (col < PLANE_SIZE - height && row < PLANE_SIZE - height)) {
            VALID_PATHS.add(new Direction(-1, -1, 1));
        }
        // upward + leftward diagonal
        if (col < MID_INDEX + height && row > MID_INDEX - height &&
                col >= height && row < PLANE_SIZE - height) {
            VALID_PATHS.add(new Direction(-1, 1, 1));
        }
        // vertical only added on max height level
        if (height == HEIGHT) {
            VALID_PATHS.add(new Direction(0, 0, -1));
        }
    }

    /**
     * Determines if two Search Tables are
     * equivalent. Order is irrelevant.
     *
     * @param other other search table
     * @return true if other and this search table
     * contain the same set of directions.
     */
    public boolean equals(Object other) {
        if (!(other instanceof SearchLookUpTable)) {
            return false;
        }
        return ((SearchLookUpTable) other).VALID_PATHS.equals(this.VALID_PATHS);
    }

    /**
     * Determines if a direction is within this
     * search table.
     *
     * @param set direction obj being searched
     *            in this list
     * @return true if set is found in the
     * search table
     */
    public boolean contains(Object set) {
        if (!(set instanceof Direction)) {
            return false;
        }
        for (Direction s : VALID_PATHS) {
            if (s.equals(set)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a direction to the search table.
     * Do not allow same elements in table.
     *
     * @param set direction being added, set != null.
     * @return true if the element can be added.
     */
    public boolean add(Direction set) {
        if (set == null) {
            throw new IllegalArgumentException("Violation: adding null elem to search table.");
        }
        boolean add = !VALID_PATHS.contains(set);
        if (add) {
            VALID_PATHS.add(set);
        }
        return add;
    }

    /**
     * Iterator used to allow the traversal of
     * directions within the search table.
     *
     * @return a iterator used to access elements
     */
    public Iterator<Direction> iterator() {
        return VALID_PATHS.iterator();
    }

    /**
     * Prints out the direction sets for a point.
     *
     * @return a string revealing the contents of
     * the search table.
     */
    public String toString() {
        return VALID_PATHS.toString();
    }
}