import java.util.ArrayList;

/**
 * hello
 */
public class Plane implements CONNECT_CONSTANTS{
    private EmptySquare[][] boardPlane;

    public Plane() {
        boardPlane = new EmptySquare[PLANE_SIZE][PLANE_SIZE];
        for (int i = 0; i < PLANE_SIZE; i++) {
            for (int j = 0; j < PLANE_SIZE; j++) {
                boardPlane[i][j] = new EmptySquare();
            }
        }
    }

    public Plane(int[][] square) {
        boardPlane = new EmptySquare[square.length][square[0].length];
        for (int i = 0; i < square.length; i++) {
            for (int j = 0; j < square.length; j++) {
                boardPlane[i][j] = new EmptySquare();
                if (square[i][j] == 1) {
                    boardPlane[i][j] = new Piece(true);
                } else if (square[i][j] == -1) {
                    boardPlane[i][j] = new Piece(false);
                }
            }
        }
    }

    public EmptySquare getSquare(int row, int col) {
        if (row == PLANE_SIZE || col == PLANE_SIZE) {
            throw new IllegalArgumentException("Violation: IOB error accessing pieces on plane.");
        }
        return boardPlane[row][col];
    }

    public void setSquare(int row, int col, Piece p1) {
        boardPlane[row][col] = p1;
    }

    public boolean isGameDone() {
        return isGameDone(new Piece(true)) || isGameDone(new Piece(false));
    }
    public boolean isGameDone(Piece player) {
        return iterateBoard(MID_INDEX, PLANE_SIZE, player) || iterateBoard(PLANE_SIZE, MID_INDEX, player);
    }

    public boolean isGameDone(int row, int col, Piece player) {
        return checkConnect4(row, col, player);
    }

    public boolean iterateBoard(int rowLimit, int colLimit, Piece player) {
        for (int i = 0; i < rowLimit; i++) {
            for (int j = 0; j < colLimit; j++) {
                if (!boardPlane[i][j].isEmpty() && boardPlane[i][j].getTeam() == player.getTeam()) {
                    if (checkConnect4(i, j, player)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static class Direction {
        private final int xDirection;
        private final int yDirection;

        Direction (int x, int y) {
            xDirection = x;
            yDirection = y;
        }
        public String toString() {
            return ("(dx, dy): " + "(" + xDirection + ", " + yDirection + ")");
        }
    }
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

        for (Direction set : dir) {
            if (checkDirForFour(row, col, set, player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDirForFour(int row, int col, Direction set, Piece player) {
        int doneSearch = 0;
        int xIndex = row + set.xDirection;
        int yIndex = col + set.yDirection;
        while(doneSearch < 3) {
            if (!boardPlane[xIndex][yIndex].isEmpty() &&
                    boardPlane[xIndex][yIndex].getTeam() == player.getTeam()) {
                xIndex += set.xDirection;
                yIndex += set.yDirection;
            } else {
                return false;
            }
            doneSearch++;
        }
        return true;
    }

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
