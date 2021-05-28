import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class PlaneTest {

    @Test
    void getSquare() {
        int[][] square =
                {{0, 1, 1, 1, 1},
                {0, -1, 0, 0, 0},
                {0, 0, 0, -1, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},};
        Plane p = new Plane(square);
        assertTrue(p.getSquare(0, 0).isEmpty());
        assertTrue(!p.getSquare(0, 1).isEmpty()
                && p.getSquare(0,1).getTeam());
        assertTrue(!p.getSquare(1, 1).isEmpty()
                && !p.getSquare(1,1).getTeam());
    }

//    @Test
//    void setSquare() {
//    }
//
//    @Test
//    void isGameDone() {
//    }
//
//    @Test
//    void testIsGameDone() {
//    }
//
//    @Test
//    void testIsGameDone1() {
//    }
//
//    @Test
//    void iterateBoard() {
//    }
//
//    @Test
//    void testToString() {
//    }
}