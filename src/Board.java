
import java.util.Arrays;
import java.util.Iterator;

public class Board implements CONNECT_CONSTANTS {
    private Plane[] CONNECT_BOARD;

    public Board () {
        CONNECT_BOARD = new Plane[HEIGHT + 1];
        for (int i = 0; i <= HEIGHT; i++) {
            CONNECT_BOARD[i] = new Plane();
        }
    }

    public boolean isSquareAvailable(int row, int col) {
        for (Plane e : CONNECT_BOARD) {
            if(e.getSquare(row, col).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public EmptySquare getSquare(int row, int col, int height) {
        return CONNECT_BOARD[height].getSquare(row, col);
    }

    public EmptySquare getOpenSquare(int row, int col) {
        for (Plane e : CONNECT_BOARD) {
            if(!e.getSquare(row, col).isEmpty()) {
                return e.getSquare(row, col);
            }
        }
        return null;
    }

    public int getOpenSquareHeight(int row, int col) {
        int height = 0;
        for (Plane e : CONNECT_BOARD) {
            if(e.getSquare(row, col).isEmpty()) {
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
        return inARow(row, col, height, player, inversePath) + inARow(row + path.getRow(),
                col + path.getCol(), height + path.getHeight(), player, path) >= 4;
    }

    private int inARow(int row, int col, int height, Piece player, Direction path) {
        if (row < 0 || col < 0 || height < 0 || row >= PLANE_SIZE || col >= PLANE_SIZE || height > HEIGHT) {
            return 0;
        }
        EmptySquare square = CONNECT_BOARD[height].getSquare(row, col);
        if (square.isEmpty() || square.getTeam() != player.getTeam()) {
            return 0;
        }
        return 1 + inARow(row + path.getRow(), col + path.getCol(), height + path.getHeight(), player, path);
    }

    private int checkHeight(int changeInHeight, int height, int row, int col, Piece player) {
        if(height >= PLANE_SIZE || height < 0) {
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
