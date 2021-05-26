/**
 * Defines and models the common behavior of slots within
 * the game board. Used as the structure of either an
 * empty square within the board or a square
 * inhabited by a piece.
 */
public abstract class Square {

    public abstract boolean isEmpty();

    public abstract boolean getTeam();

    public abstract String toString();
}
