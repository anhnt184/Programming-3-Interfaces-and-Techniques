public class Sudoku {
    private char[][] grid;

    public Sudoku() {
        grid = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void set(int i, int j, char c) {
        if (i < 0 || i > 8 || j < 0 || j > 8) {
            System.out.println("Trying to access illegal cell (" + i + ", " + j + ")!");
            return;
        }
        if (c != ' ' && (c < '1' || c > '9')) {
            System.out.println("Trying to set illegal character " + c + " to (" + i + ", " + j + ")!");
            return;
        }
        grid[i][j] = c;
    }

    public boolean check() {
        //Check
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (grid[i][j] == grid[i][k] && grid[i][j] != ' ') {
                        System.out.println("Row " + i + " has multiple " + grid[i][j] + "'s!");
                        return false;
                    }
                }
            }
        }
        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                for (int k = i + 1; k < 9; k++) {
                    if (grid[i][j] == grid[k][j] && grid[i][j] != ' ') {
                        System.out.println("Column " + j + " has multiple " + grid[i][j] + "'s!");
                        return false;
                    }
                }
            }
        }
        for (int x = 0; x < 9; x += 3) {
            for (int y = 0; y < 9; y += 3) {
                for (int i = x; i < x + 3; i++) {
                    for (int j = y; j < y + 3; j++) {
                        for (int k = x; k < x + 3; k++) {
                            for (int l = y; l < y + 3; l++) {
                                if (i != k || j != l) {
                                    if (grid[i][j] == grid[k][l] && grid[i][j] != ' ') {
                                        System.out.println("Block at (" + x + ", " + y + ") has multiple " + grid[i][j] + "'s!");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public void print() {
        String outerBorder = "";
        outerBorder = "#".repeat(37);
        String innerBorder = "#---+---+---#---+---+---#---+---+---#";
        System.out.println(outerBorder);
        for (int i = 0; i < 9; i++) {
            System.out.println("# " + grid[i][0] + " | " + grid[i][1] + " | " + grid[i][2] + " # " + grid[i][3] + " | " + grid[i][4] + " | " + grid[i][5] + " # " + grid[i][6] + " | " + grid[i][7] + " | " + grid[i][8] + " #");
            System.out.println(innerBorder);
        }
        System.out.println(outerBorder);
    }
}
