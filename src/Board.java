import java.util.Arrays;
import java.util.Iterator;

/**
 * The Game board that holds the pieces.
 * The structure is a cube with PLANE_SIZE (4) amount of
 * Planes. When inserting a piece into the board
 * given a row and column, the piece slots down
 * to the lowest available position within the cube.
 */
public class Board implements CONNECT_CONSTANTS {

    private Plane[] CONNECT_BOARD;

    /**
     * Instantiates a plane for every layer in the
     * board.
     */
    public Board() {
        CONNECT_BOARD = new Plane[HEIGHT + 1];
        for (int i = 0; i <= HEIGHT; i++) {
            CONNECT_BOARD[i] = new Plane();
        }
    }

    /**
     * FOR TESTING PURPOSES ONLY: Takes in 4 already filled
     * planes and creates a board. Assume
     * the board's construction is correct.
     *
     * @param layer0 the first layer of the board
     * @param layer1 the second layer of the board
     * @param layer2 the third layer of the board
     * @param layer3 the fourth layer of the board
     */
    public Board(int[][] layer0, int[][] layer1, int[][] layer2, int[][] layer3) {
        if (layer0 == null || layer1 == null || layer2 == null || layer3 == null) {
            throw new IllegalArgumentException("Violation: Parameters for the " +
                    "board tester constructor are null.");
        }
        // tests only optimized for a board with 4 layers
        CONNECT_BOARD = new Plane[HEIGHT + 1];
        CONNECT_BOARD[0] = new Plane(layer0);
        CONNECT_BOARD[1] = new Plane(layer1);
        CONNECT_BOARD[2] = new Plane(layer2);
        CONNECT_BOARD[3] = new Plane(layer3);
    }

    /**
     * Determines if there is an open spot to place a
     * piece given a row and column.
     *
     * @param row row position to place desired piece
     * @param col column position to place desired
     * @return true if the given row and column pos isn't
     * filled.
     */
    public boolean isSquareAvailable(int row, int col) {
        for (Plane p : CONNECT_BOARD) {
            if (p.getSquare(row, col).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a square within the Board given
     * a row, col, and height. i.e (x, y, z).
     *
     * @param row    row index for desired square
     * @param col    column index for desired square
     * @param height height index for desired square
     * @return a square containing either an empty piece,
     * or a square filled by a game piece.
     */
    public EmptySquare getSquare(int row, int col, int height) {
        return CONNECT_BOARD[height].getSquare(row, col);
    }

    /**
     * Gets the top of a stack given a row and col.
     * if there are no pieces on a given coordinate,
     * return null.
     *
     * @param row desired row index
     * @param col desired column index
     * @return a square with the top most piece or null
     * if there are no pieces.
     */
    public EmptySquare getOpenSquare(int row, int col) {
        EmptySquare openSquare = null;
        for (Plane e : CONNECT_BOARD) {
            if (!e.getSquare(row, col).isEmpty()) {
                openSquare = e.getSquare(row, col);
            }
        }
        return openSquare;
    }

    /**
     * Gets the height of the top most piece
     * on a given position within the board.
     *
     * @param row desired row index
     * @param col desired column index
     * @return a int indicating the height of the
     * top most piece within the desired spot.
     */
    public int getOpenSquareHeight(int row, int col) {
        int height = 0;
        for (Plane e : CONNECT_BOARD) {
            if (e.getSquare(row, col).isEmpty()) {
                return height;
            }
            height++;
        }
        return height;
    }

    /**
     * Adds a piece from a team to the board. Returns
     * a boolean indicating if the piece added led to
     * a connect four.
     * pre: row and col must lead to a placement within
     * the bard that is available.
     *
     * @param addedPiece piece added to the board
     * @param row        desired row index to add piece
     * @param col        desired col index to add piece
     * @return true if the piece add leads to a connect four.
     */
    public boolean addUserPiece(Piece addedPiece, int row, int col) {
        if (!isSquareAvailable(row, col)) {
            throw new IllegalStateException("Square already taken.");
        }
        int height = getOpenSquareHeight(row, col);
        CONNECT_BOARD[height].setSquare(row, col, addedPiece);
        // checks both the 2-D plane and the valid diagonals in 3-D space
        return isGameDone(row, col, height, addedPiece) ||
                CONNECT_BOARD[height].isGameDone(addedPiece);
    }

    /**
     * Determines if the game is completed if there is a
     * connect four based on the previous move made. This is
     * the efficient version to determine a winner since it
     * only examines the valid paths from the previous move
     * versus examining all paths from a given team.
     *
     * @param row    row index from the prev move
     * @param col    column index from the prev move
     * @param height height index from the prev move
     * @param Player the player who made the prev move
     * @return true if the previous move created a connect 4.
     */
    public boolean isGameDone(int row, int col, int height, Piece Player) {
        // checks both the 2-D plane and the valid diagonals in 3-D space
        return CONNECT_BOARD[height].isGameDone(Player) ||
                checkMultiDimensions(row, col, height, Player);
    }

    /**
     * FOR TESTING PURPOSES ONLY: Determines if the game
     * is completed if there is a connect based on checking
     * all the moves from a given team. This is the less
     * efficient method for determining a winner since it
     * checks all moves made from a given player and not
     * the previous one which is all that is needed.
     *
     * @param Player the piece type being used to check for a
     *               connect four.
     * @return true if the previous move created a connect 4.
     */
    public boolean isGameDone(Piece Player) {
        for (int i = 0; i < CONNECT_BOARD.length; i++) {
            // iterates through all squares in plane (25)
            for (int r = 0; r < PLANE_SIZE; r++) {
                for (int c = 0; c < PLANE_SIZE; c++) {
                    EmptySquare s = CONNECT_BOARD[i].getSquare(r, c);
                    // only initiates search algorithm on squares with same team
                    if (!s.isEmpty() && s.getTeam() == Player.getTeam()) {
                        if (isGameDone(r, c, i, Player)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Assembles all valid paths needed to traverse through the board in
     * order to find a connect four given a point. A path contains two parts:
     * - The first half of a diagonal from a given point. Uses the normative
     * List of path created using the search look up table.
     * - The second half of a diagonal from a given point. Uses the negated
     * version of a path that allows a mirrored search from the opposite
     * side of a given point.
     * <p>
     * Each diagonal can range from size 0 to 5, yet given the programs
     * recursive mechanism, the program stops forming a diagonal when
     * the pieces are different from the desired or goes out of bounds.
     * When there is a string of similar pieces that add up to 4, we
     * can determine there is a winner.
     *
     * @param row    row index of prev move
     * @param col    column index of prev move
     * @param height height index of prev move
     * @param player desired piece type when looking for winner
     * @return true if there is a connection of for in 3-D space.
     */
    private boolean checkMultiDimensions(int row, int col, int height, Piece player) {
        // contains all valid search paths for a given point (x, y, z)
        SearchLookUpTable validPaths = new SearchLookUpTable(row, col, height);
        Iterator<Direction> paths = validPaths.iterator();
        while (paths.hasNext()) {
            Direction path = paths.next();
            // since prev point already counts, iterate over that
            int diagonalOne = inARow(row + path.getRowROC(),
                    col + path.getColROC(), height + path.getHeightROC(), player, path);
            int diagonalTwo = inARow(row, col, height, player, path.getOtherDir());
            if (diagonalOne + diagonalTwo >= THRESHOLD) {
                return true;
            }
        }
        return false;
    }

    /**
     * Recursive search algorithm that iterated over pieces in 3-D space.
     * Accumulates a sum for every similar piece connected to last piece
     * placed. Direction of iteration is dictated by path object created
     * by the search table.
     *
     * @param row    current row index in recursive call
     * @param col    current column index in recursive call
     * @param height current height index in recursive call
     * @param player desired piece being checked for a connection of
     *               similar pieces
     * @param path   the rate of change for row, col, and height dictating
     *               the movement from a given point in the recursive call.
     * @return an int for every similar piece connected to the last piece placed.
     */
    private int inARow(int row, int col, int height, Piece player, Direction path) {
        // base case: piece goes out of bounds
        if (row < 0 || col < 0 || height < 0 || row >= PLANE_SIZE || col >= PLANE_SIZE
                || height > HEIGHT) {
            return 0;
        }
        EmptySquare square = CONNECT_BOARD[height].getSquare(row, col);
        // base case: current piece in call is different from last piece placed
        if (square.isEmpty() || square.getTeam() != player.getTeam()) {
            return 0;
        }
        // piece is the same type as the desired piece and is connected, continue 3-D iteration
        return 1 + inARow(row + path.getRowROC(), col + path.getColROC(),
                height + path.getHeightROC(), player, path);
    }

    /**
     * Prints out the game board. Prints out each plane in
     * order from top down.
     *
     * @return a string presenting the game board in 2-D fashion.
     */
    public String toString() {
        return "Board{" +
                "CONNECT_BOARD=" + Arrays.toString(CONNECT_BOARD) +
                '}';
    }
}
