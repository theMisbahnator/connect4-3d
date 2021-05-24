import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * Given a set of coordinates on a 3d plane (x, y, z),
 * this class returns all the valid search paths to check
 * for a connect 4. This limits unnecessary recursive calls
 * increasing efficiency.
 *
 * I used Minecraft to help visualize the coordinates'
 * valid paths and recorded the results on a google
 * sheets here:
 */
public class SearchLookUpTable implements CONNECT_CONSTANTS{

    private ArrayList<Direction> validPaths;

    public SearchLookUpTable() {
        validPaths = new ArrayList<>();
    }

    public SearchLookUpTable(int row, int col, int height) {
        this();
        if(col < MID_INDEX + height && col >= height) {
            validPaths.add(new Direction(0,1, 1));
        }
        if (col > MID_INDEX - height && col < PLANE_SIZE - height) {
            validPaths.add(new Direction(0,-1, 1));
        }
        if (row < MID_INDEX + height && row >= height) {
            validPaths.add(new Direction(1, 0, 1));
        }
        if (row > MID_INDEX - height && row < PLANE_SIZE - height) {
            validPaths.add(new Direction(-1, 0, 1));
        }
        if (col < MID_INDEX + height && row < MID_INDEX + height && col >= height && row >= height) {
            validPaths.add(new Direction(1, 1, 1));
        }
        if ((col > MID_INDEX - height && row < MID_INDEX + height) && col < PLANE_SIZE - height && row >= height) {
            validPaths.add(new Direction(1, -1, 1));
        }
        if ((col > MID_INDEX - height && row > MID_INDEX - height) && (col < PLANE_SIZE - height && row < PLANE_SIZE - height)) {
            validPaths.add(new Direction(-1, -1, 1));
        }
        if (col < MID_INDEX + height && row > MID_INDEX - height && col >= height && row < PLANE_SIZE - height) {
            validPaths.add(new Direction(-1, 1, 1));
        }
        if (height == HEIGHT) {
            validPaths.add(new Direction(0, 0, -1));
        }
    }

    public boolean equals(Object other) {
        if (!(other instanceof SearchLookUpTable)) {
            return false;
        }
        return ((SearchLookUpTable) other).validPaths.equals(this.validPaths);
    }

    public boolean contains(Object set) {
        if (!(set instanceof SearchLookUpTable)) {
            return false;
        }
        for (Direction s : validPaths) {
            if (s.equals(set)) {
                return true;
            }
        }
        return false;
    }

    public boolean add(Direction set) {
        boolean add = !validPaths.contains(set);
        if (add) {
            validPaths.add(set);
        }
        return add;
    }

    public boolean combine(SearchLookUpTable other) {
        int origSize = validPaths.size();
        for (Direction i : other.validPaths) {
            this.add(i);
        }
        return origSize != validPaths.size();
    }

    public Iterator<Direction> iterator() {
        return validPaths.iterator();
    }

    public String toString() {
        return validPaths.toString();
    }
}