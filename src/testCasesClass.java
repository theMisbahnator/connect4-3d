import java.util.Scanner;

public class testCasesClass {
    public static void main(String[] args) {
        // testPlaneClass();
        testLookUpTable();
        // testDirection();
        // testBoardClass();
    }

    private static void testDirection() {
        Direction p = new Direction(0, 0, 0);
        Direction q = null;
        int testNum = 1;
        testCaseOutput(false, p.equals(q), testNum++, "Equals on null", p.equals(q));
        q = new Direction(0, 0, 0);
        testCaseOutput(true, p.equals(q), testNum++, "Equal set", p.equals(q));
        testCaseOutput(true, q.equals(p), testNum++, "Testing reverse", q.equals(p));
        for (int i = 0; i < 20; i++) {
            int M = (int) (Math.random() * 5);
            p = new Direction(i + M, i * M, i - M);
            q = new Direction(i + M, i * M, i - M);
            boolean act = i % 2 == 0 ? p.equals(q) : q.equals(p);
            testCaseOutput(true, act, testNum++, "Testing random: equal", act);
        }
        for (int i = 0; i < 20; i++) {
            int M = (int) (Math.random() * 5);
            p = new Direction(i + M + 1, i * M, i - M);
            q = new Direction(i + M, i * M + 1, i - M);
            boolean act = i % 2 == 0 ? p.equals(q) : q.equals(p);
            testCaseOutput(false, act, testNum++, "Testing random: unequal", act);
        }
    }

    private static void testLookUpTable() {
        int testNum = 1;
        SearchLookUpTable t = new SearchLookUpTable();
        t.add(new Direction(1,3,4));
        Direction s = new Direction(1, 3, 4);
        boolean p = t.contains(s);
        testCaseOutput(true, p, testNum, "Testing contains", t.toString());


        for (int i = 0; i < CONNECT_CONSTANTS.MID_INDEX; i++) {
            for (int j = 0; j < CONNECT_CONSTANTS.PLANE_SIZE; j++) {
                Direction set = new Direction(0, 1, 1);
                SearchLookUpTable act = new SearchLookUpTable(j, i, 0);
                boolean result = act.contains(set);
                testCaseOutput(true, result, testNum++, "First Layer, Left Half", "Row: " + j + ", " +
                        "Col: " + i + "\nEXP: " + "\nACT: " + act + "\n");
            }
        }

    }

    private static void testBoardClass() {
        Board b1 = new Board();
        int testNum = 1;
        boolean act;
        for (int i = 0; i < 3; i++) {
            act = b1.addUserPiece(new Piece(true), 1, 1);
            testCaseOutput(false, act, testNum++, "Vertical Connect 4, Placing the " + i + "th piece.", b1.toString());
        }
        act = b1.addUserPiece(new Piece(true), 1, 1);
        testCaseOutput(true, act, testNum++, "Vertical Connect 4, Placing the " + 4 + "th piece.", b1.toString());

        int[][] Layer0 = {
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, -1, 0},
                {0, 0, 0, 0, 0},};
        int[][] Layer1 = {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, -1, 0, 0},
                {0, 0, 0, -1, 0},
                {0, 0, 0, 0, 0},};
        int[][] Layer2 = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, -1, 0},
                {0, 0, 0, 0, 0},};
        int[][] Layer3 = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},};
        b1 = new Board(Layer0, Layer1, Layer2, Layer3);
        act = b1.isGameDone(new Piece(true));
        testCaseOutput(false, act, testNum++, "Diagonal Connect 4, The piece before the game is done.", b1.toString());
        act = b1.addUserPiece(new Piece(true), 3, 3);
        testCaseOutput(true, act, testNum++, "Diagonal Connect 4, Game should be done.", b1.toString());

    }

    private static void testPlaneClass() {
        Plane ps = new Plane();
        String output = ps.toString();
        int testNum = 1;
        for (int i = 0; i < 4; i++) {
            testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++, "Placing " + i + "th piece", output);
            ps.setSquare(0, i, new Piece(true));
            output = ps.toString();
        }
        testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++, "Placing " + 4 + "th piece", output);

        int[][] arr = {{0, 1, 1, 1, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++, "horizontal on edge, p1 win", output);
        testCaseOutput(false, ps.isGameDone(new Piece(false)), testNum++, "horizontal on edge, p2 loss", output);

        arr = new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, 0, -1},
                {0, 0, 0, 0, -1},
                {0, 0, 0, 0, -1},
                {0, 0, 0, 0, -1},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++, "vertical on edge, p2 win", output);
        testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++, "vertical on edge, p1 loss", output);

        arr = new int[][]{{1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++, "left diagonal, p1 win", output);
        testCaseOutput(false, ps.isGameDone(new Piece(false)), testNum++, "left diagonal, p2 loss", output);

        arr = new int[][]{{0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++, "left (lower) diagonal, p1 win", output);
        testCaseOutput(false, ps.isGameDone(new Piece(false)), testNum++, "left (lower) diagonal, p2 loss", output);

        arr = new int[][]{{0, 0, 0, 0, 0},
                {0, -1, 0, 0, 0},
                {0, 0, -1, 0, 0},
                {0, 0, 0, -1, 0},
                {0, 0, 0, 0, -1},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++, "left (lower) diagonal, p1 loss", output);
        testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++, "left (lower) diagonal, p2 win", output);

        arr = new int[][]{{0, 0, 0, 0, -1},
                {0, 0, 0, -1, 0},
                {0, 0, -1, 0, 0},
                {0, -1, 0, 0, 0},
                {0, 0, 0, 0, 0},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++, "Right diagonal, p2 win", output);
        testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++, "Right diagonal, p1 loss", output);

        arr = new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, -1, 0},
                {0, 0, -1, 0, 0},
                {0, -1, 0, 0, 0},
                {-1, 0, 0, 0, 0},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++, "Right (lower) diagonal, p2 win", output);
        testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++, "Right (lower) diagonal, p1 loss", output);

        arr = new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(false, ps.isGameDone(), testNum++, "Small 3x3 bottom right corner, no win", output);

        arr = new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {1, 1, 1, 0, -1},
                {1, 1, 1, 0, -1},
                {1, 1, 1, 0, -1},};
        ps = new Plane(arr);
        output = ps.toString();
        testCaseOutput(false, ps.isGameDone(), testNum++, "Small 3x3 bottom left corner, no win", output);

        for (int i = 0; i < arr.length; i++) {
            arr = new int[][]{{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},};
            ps = new Plane(arr);
            for (int j = 0; j < arr.length - 1; j++) {
                testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++, "Testing rows unfinished", ps.toString());
                ps.setSquare(i, j, new Piece(true));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++, "Testing row " + i, ps.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            arr = new int[][]{{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},};
            ps = new Plane(arr);
            for (int j = 1; j < arr.length; j++) {
                testCaseOutput(false, ps.isGameDone(new Piece(false)), testNum++, "Testing rows unfinished", ps.toString());
                ps.setSquare(i, j, new Piece(false));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++, "Testing row " + i, ps.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            arr = new int[][]{{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},};
            ps = new Plane(arr);
            for (int j = 0; j < arr.length - 1; j++) {
                testCaseOutput(false, ps.isGameDone(new Piece(false)), testNum++, "Testing cols unfinished", ps.toString());
                ps.setSquare(j, i, new Piece(false));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++, "Testing col " + i, ps.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            arr = new int[][]{{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},};
            ps = new Plane(arr);
            for (int j = 1; j < arr.length; j++) {
                testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++, "Testing cols unfinished", ps.toString());
                ps.setSquare(j, i, new Piece(true));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++, "Testing col " + i, ps.toString());
        }
    }

    public static void testCaseOutput(boolean exp, boolean act, int testNum, String Description, Object output) {
        if (exp == act) {
            System.out.println("passed test " + testNum + ", " + Description);
        } else {
            System.out.println("FAILED test " + testNum + ", " + Description);
//            System.out.println("BOARD");
            System.out.println(output);
        }
    }
}
