import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

class PlaneTest {

    @ParameterizedTest
    @MethodSource("getSquareParam")
    void getSquare(int r, int c, int[][] square, int exp) {
        boolean isEmpty = exp == 0;
        boolean whatTeam = exp == 1;
        Plane p = new Plane(square);

         // test for empty squares
         assertEquals(p.getSquare(r,c).isEmpty(), isEmpty);

        // it not empty, tests for correct team instantiation
        if(!isEmpty) {
            assertEquals(whatTeam, p.getSquare(r,c).getTeam());
        }
    }

    private static Stream<Arguments> getSquareParam() {
        int[][] square = {{0, 1, 1, 1, 1},
                {0, -1, 0, 0, 1},
                {0, 0, 0, -1, 0},
                {0, 1, 0, -1, 0},
                {0, 0, 0, 1, 0},};
        ArrayList<Arguments> ts = new ArrayList<>();
        for(int r = 0; r < square.length; r++) {
            for(int c = 0; c < square[r].length; c++) {
                ts.add(Arguments.of(r, c, square, square[r][c]));
            }
        }
        return ts.stream();
    }


    @ParameterizedTest
    @MethodSource("setSquareParam")
    void setSquare(int r, int c, boolean changedTeam) {
        // expected plane
        int[][] afterArr = {{1, -1, -1, -1, -1},
                {-1, 1, -1, -1, -1},
                {1, 1, 1, 1, 1},
                {-1, -1, -1, 1, -1},
                {1, 1, 1, -1, 1},};
        Plane afterPlane = new Plane(afterArr);

        assertEquals(afterPlane.getSquare(r,c).getTeam(), changedTeam);
    }

    private static Stream<Arguments> setSquareParam() {
        // changes values to either -1, or 1
        int[][] beforeArr = {{0, 1, 1, 1, 1},
                {0, -1, 0, 0, 1},
                {0, 0, 0, -1, 0},
                {0, 1, 0, -1, 0},
                {0, 0, 0, 1, 0},};

        ArrayList<Arguments> ts = new ArrayList<>();
        Plane before = new Plane(beforeArr);
        for(int r = 0; r < beforeArr.length; r++) {
            for(int c = 0; c < beforeArr[r].length; c++) {
                // for the sake of testing set, we will change all 1's to -1 and vice versa
                // for empty squares, 1 if the row is even, -1 if the row is odd
                int val = beforeArr[r][c] == 0 ? r % 2 == 0 ? 1 : -1 :
                        beforeArr[r][c] == 1 ? -1 : 1;
                before.setSquare(r, c, 1 == val);
                ts.add(Arguments.of(r, c, before.getSquare(r,c).getTeam()));
            }
        }
        return ts.stream();
    }

    @ParameterizedTest
    @MethodSource("isDone")
    void isGameDone(Plane p, boolean exp) {
        // should be passed an argument stream containing the parameters
        // - a plane board
        // - an exp boolean indicating weather the game is done

        assertEquals(p.isGameDone(), exp);

    }

    private static Stream<Arguments> isDone() {
        // changes values to either -1, or 1
        int[][] beforeArr = {{0, 1, 1, 1, 1},
                {0, -1, 0, 0, 1},
                {0, 0, 0, -1, 0},
                {0, 1, 0, -1, 0},
                {0, 0, 0, 1, 0},};

        ArrayList<Arguments> ts = new ArrayList<>();
        Plane before = new Plane(beforeArr);
        for(int r = 0; r < beforeArr.length; r++) {
            for(int c = 0; c < beforeArr[r].length; c++) {
                // for the sake of testing set, we will change all 1's to -1 and vice versa
                // for empty squares, 1 if the row is even, -1 if the row is odd
                int val = beforeArr[r][c] == 0 ? r % 2 == 0 ? 1 : -1 :
                        beforeArr[r][c] == 1 ? -1 : 1;
                before.setSquare(r, c, 1 == val);
                ts.add(Arguments.of(r, c, before.getSquare(r,c).getTeam()));
            }
        }
        return ts.stream();
    }

    @Test
    void testIsGameDone() {
    }

    @Test
    void testIsGameDone1() {
    }

    @Test
    void iterateBoard() {
    }

    @Test
    void testToString() {
    }
}