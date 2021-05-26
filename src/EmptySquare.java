public class EmptySquare extends Square{

    public boolean isEmpty() {
        return true;
    }

    public boolean getTeam() {
        return false;
    }

    public String toString() {
        return ".";
    }
}
