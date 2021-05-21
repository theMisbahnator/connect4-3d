public class testCasesClass {
    public static void main(String[] args) {
        // testPlaneClass();
        // testLookUpTable();
        // testBoardClass();
    }

    private static void testLookUpTable() {
        int row = 0;
        int col = 0;
        int height = 3;
        SearchLookUpTable ls = new SearchLookUpTable(row, col, height);
        System.out.println(ls);
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
        act = b1.addUserPiece(new Piece(true), 3,3);
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
                testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++,"Testing rows unfinished" , ps.toString());
                ps.setSquare(i , j, new Piece(true));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++,"Testing row " + i , ps.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            arr = new int[][]{{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},};
            ps = new Plane(arr);
            for (int j = 1; j < arr.length; j++) {
                testCaseOutput(false, ps.isGameDone(new Piece(false)), testNum++,"Testing rows unfinished" , ps.toString());
                ps.setSquare(i , j, new Piece(false));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++,"Testing row " + i , ps.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            arr = new int[][]{{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},};
            ps = new Plane(arr);
            for (int j = 0; j < arr.length - 1; j++) {
                testCaseOutput(false, ps.isGameDone(new Piece(false)), testNum++,"Testing cols unfinished" , ps.toString());
                ps.setSquare(j , i, new Piece(false));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(false)), testNum++,"Testing col " + i , ps.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            arr = new int[][]{{0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0},};
            ps = new Plane(arr);
            for (int j = 1; j < arr.length; j++) {
                testCaseOutput(false, ps.isGameDone(new Piece(true)), testNum++,"Testing cols unfinished" , ps.toString());
                ps.setSquare(j , i, new Piece(true));
            }
            testCaseOutput(true, ps.isGameDone(new Piece(true)), testNum++,"Testing col " + i , ps.toString());
        }
    }

    public static void testCaseOutput (boolean exp, boolean act, int testNum, String Description, String output) {
        if (exp == act) {
            System.out.println("passed test " + testNum + ", " + Description);
        } else {
            System.out.println("FAILED test " + testNum + ", " + Description);
            System.out.println("BOARD");
            System.out.println(output);
        }
    }
}
