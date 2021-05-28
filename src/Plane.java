import java.util.ArrayList;

/**
 * Deals with tasks at the 2-D level including
 * placing pieces and figuring out if a connect four
 * exists.
 */
public class Plane implements CONNECT_CONSTANTS{

    private Square[][] boardPlane;

    /**
     * Creates an empty 2-D plane with dimensions
     * of 5 by 5 filled with EmptySquares.
     */
    public Plane() {
        boardPlane = new EmptySquare[PLANE_SIZE][PLANE_SIZE];
        for (int i = 0; i < PLANE_SIZE; i++) {
            for (int j = 0; j < PLANE_SIZE; j++) {
                boardPlane[i][j] = new EmptySquare();
            }
        }
    }

    /**
     * FOR TESTING PURPOSES ONLY: Given an
     * already modified int[] board, the constructor
     * creates a corresponding game board.
     * 1 --- Player One
     * -1 --- player Two
     * 0 --- Empty
     * @param square int[] array containing a model of a
     *               board that is translated into a proper
     *               board.
     */
    public Plane(int[][] square) {
        boardPlane = new EmptySquare[square.length][square[0].length];
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                boardPlane[i][j] = new EmptySquare();
                // if 1 or -1, create a player piece
                if (square[i][j] == 1) {
                    boardPlane[i][j] = new Piece(true);
                } else if (square[i][j] == -1) {
                    boardPlane[i][j] = new Piece(false);
                }
            }
        }
    }

    /**
     * Gets a Square from the 2-D plane
     * pre: 0 <= row < PLANE_SIZE
     *      0 <= col < PLANE_SIZE
     *
     * @param row the row index
     * @param col the column index
     * @return a Square piece with either an empty
     * square or game piece
     */
    public Square getSquare(int row, int col) {
        if (row == PLANE_SIZE || col == PLANE_SIZE) {
            throw new IllegalArgumentException("Violation: IOB error accessing pieces on plane.");
        }
        return boardPlane[row][col];
    }

    /**
     * Changes a given Square to another
     * Square type.
     * pre: p != null.
     *      0 <= row < PLANE_SIZE
     *      0 <= col < PLANE_SIZE
     *
     * @param row the row index
     * @param col the col index
     * @param p the desired piece for a
     *           piece to be changed to
     */
    public void setSquare(int row, int col, Square p) {
        if (p == null || row < 0 || col < 0 || row >= PLANE_SIZE || col >= PLANE_SIZE) {
            throw new IllegalArgumentException("Violation: SetSquare(), p is null " +
                    "or row/col are invalid.");
        }
        boardPlane[row][col] = p;
    }

    /**
     * Without given a piece type, determines if there
     * is any winner on a given 2-D Plane.
     *
     * @return true if there is a connect for of any type
     */
    public boolean isGameDone() {
        return isGameDone(new Piece(true)) || isGameDone(new Piece(false));
    }

    /**
     * Check if the piece type given has
     * an existent connect four on the board.
     * pre: player != null
     *
     * @param player piece type being used to determine
     *               if there is a connect four.
     * @return true if there is a connect four from a
     * given piece type.
     */
    public boolean isGameDone(Piece player) {
        return iterateBoard(MID_INDEX, PLANE_SIZE, player) || iterateBoard(PLANE_SIZE, MID_INDEX, player);
    }

    /**
     * NOT FINISHED
     * Determines if the game is done given a
     * row, col, and piece type. Only searches
     * surroundings of this piece to check for
     * a connect four
     *
     * @param row row index
     * @param col column index
     * @param player piece type being looked at to
     *               check for a connect four.
     * @return true if there is a connection of four
     * from a given piece.
     */
    public boolean isGameDone(int row, int col, Piece player) {
        return checkConnect4(row, col, player);
    }

    /**
     * Iterates over all 25 squares of the plane
     * only checking around piece with the specified
     * type.
     *
     * @param rowLimit iterates all rows until the limit
     * @param colLimit iterates all cols until the limit
     * @param player piece type being used to check for a
     *               connect four.
     * @return true if there is a connect four.
     */
    public boolean iterateBoard(int rowLimit, int colLimit, Piece player) {
        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < colLimit; j++) {
                if (!boardPlane[i][j].isEmpty() &&
                        boardPlane[i][j].getTeam() == player.getTeam()) {
                    if (checkConnect4(i, j, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Assembles a list of directions from a
     * given point to traverse in in order to
     * check for a connect four.
     *
     * @param row row index of point
     * @param col column index of point
     * @param player piece type being used to
     *               check for a connect four.
     * @return true if there is a connect four.
     */
    private boolean checkConnect4(int row, int col, Piece player) {
        ArrayList<Direction> dir = new ArrayList<>();

        // checks if vertical or horizontal square
        if (col < MID_INDEX) {
            dir.add(new Direction(0, 1));
        } if (row < MID_INDEX) {
            dir.add(new Direction(1, 0));
        }

        // checks if a diagonal square
        if (row < MID_INDEX && col >= PLANE_SIZE - MID_INDEX) {
            dir.add(new Direction(1, -1));
        } else if (row < MID_INDEX && col < MID_INDEX) {
            dir.add(new Direction(1, 1));
        }

        // iterates from all valid direction assembled from a given point
        for (Direction set : dir) {
            if (checkDirForFour(row, col, set, player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Iterated over a point given a direction
     * path and piece type to look for.
     *
     * @param row starting row index
     * @param col starting column index
     * @param set change in row and col to
     *            get to next point
     * @param player piece type being checked for
     *               connect four.
     * @return true if there is a connect four from
     * current path.
     */
    private boolean checkDirForFour(int row, int col, Direction set, Piece player) {
        int doneSearch = 0;
        // iterates over already identified piece
        row += set.getRowROC();
        col += set.getColROC();
        // must be 3 in a row of same piece, otherwise game isn't done
        while(doneSearch < THRESHOLD - 1) {
            if (!boardPlane[row][col].isEmpty() &&
                    boardPlane[row][col].getTeam() == player.getTeam()) {
                row += set.getRowROC();
                col += set.getColROC();
            } else {
                return false;
            }
            doneSearch++;
        }
        return true;
    }

    /**
     * Prints out the 2-D plane.
     *
     * @return a string representing the current
     * game board.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < PLANE_SIZE; i++) {
            sb.append("[");
            for (int j = 0; j < PLANE_SIZE - 1; j++) {
                sb.append(boardPlane[i][j]).append(" ");
            }
            sb.append(boardPlane[i][PLANE_SIZE - 1]).append("]\n");
        }
        return sb.toString();
    }
}