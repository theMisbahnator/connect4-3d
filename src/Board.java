import java.util.Arrays;
import java.util.Iterator;

/**
 * The playing board that holds the connect 4 pieces.
 * The structure is a cube with PLANE_SIZE amount of
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
     * FOR TESTING PURPOSES: Takes in 4 already filled
     * layers and creates a valid board. Assume
     * the board is correct.
     *
     * @param layer0 the first layer of the board
     * @param layer1 the second layer of the board
     * @param layer2 the third layer of the board
     * @param layer3 the fourth layer of the board
     */
    public Board(int[][] layer0, int[][] layer1, int[][] layer2, int[][] layer3) {
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
     * @param row row position to place desired piece
     * @param col column position to place desired
     * @return true if the given row and column pos isn't
     * filled.
     */
    public EmptySquare getSquare(int row, int col, int height) {
        return CONNECT_BOARD[height].getSquare(row, col);
    }

    public EmptySquare getOpenSquare(int row, int col) {
        for (Plane e : CONNECT_BOARD) {
            if (!e.getSquare(row, col).isEmpty()) {
                return e.getSquare(row, col);
            }
        }
        return null;
    }

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


    public boolean isGameDone(int row, int col, int height, Piece Player) {
        // diagonal check, four diagonals from a given piece
        return CONNECT_BOARD[height].isGameDone(Player) || checkMultiDimensions(row, col, height, Player);
    }

    public boolean isGameDone(Piece Player) {
        for (int i = 0; i < CONNECT_BOARD.length; i++) {
            for (int r = 0; r < PLANE_SIZE; r++) {
                for (int c = 0; c < PLANE_SIZE; c++) {
                    EmptySquare s = CONNECT_BOARD[i].getSquare(r, c);
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

    private boolean checkMultiDimensions(int row, int col, int height, Piece player) {
        SearchLookUpTable validPaths = new SearchLookUpTable(row, col, height);
        Iterator<Direction> paths = validPaths.iterator();
        while (paths.hasNext()) {
            Direction path = paths.next();
            if (checkConnectFour(row, col, height, player, path)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkConnectFour(int row, int col, int height, Piece player, Direction path) {
        Direction inversePath = path.getOtherDir();
        return inARow(row, col, height, player, inversePath) + inARow(row + path.getRowROC(),
                col + path.getColROC(), height + path.getHeightROC(), player, path) >= 4;
    }

    private int inARow(int row, int col, int height, Piece player, Direction path) {
        if (row < 0 || col < 0 || height < 0 || row >= PLANE_SIZE || col >= PLANE_SIZE || height > HEIGHT) {
            return 0;
        }
        EmptySquare square = CONNECT_BOARD[height].getSquare(row, col);
        if (square.isEmpty() || square.getTeam() != player.getTeam()) {
            return 0;
        }
        return 1 + inARow(row + path.getRowROC(), col + path.getColROC(), height + path.getHeightROC(), player, path);
    }

    private int checkHeight(int changeInHeight, int height, int row, int col, Piece player) {
        if (height >= PLANE_SIZE || height < 0) {
            return 0;
        }
        boolean sameTeam = !getSquare(row, col, height).isEmpty() &&
                (player.getTeam() == getSquare(row, col, height).getTeam());
        return !sameTeam ? 0 : 1 + checkHeight(changeInHeight,
                height + changeInHeight, row, col, player);
    }

    public boolean addUserPiece(Piece addedPiece, int row, int col) {
        if (!isSquareAvailable(row, col)) {
            throw new IllegalStateException("Square already taken");
        }
        int height = getOpenSquareHeight(row, col);
        CONNECT_BOARD[height].setSquare(row, col, addedPiece);
        return isGameDone(row, col, height, addedPiece) || CONNECT_BOARD[height].isGameDone(addedPiece);
    }

    @Override
    public String toString() {
        return "Board{" +
                "CONNECT_BOARD=" + Arrays.toString(CONNECT_BOARD) +
                '}';
    }
}
