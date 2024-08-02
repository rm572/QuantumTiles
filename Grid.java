public class Grid {
    private TileQ[][] tiles;
    private int size;

    public Grid(int size, int[][] board) {
        this.size = size;
        this.tiles = new TileQ[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tiles[i][j] = new TileQ(board[i][j]);
            }
        }
    }

    public void toggleTile(int row, int col) {
        if (isValidPosition(row, col)) {
            tiles[row][col].toggle();
            // Propagate the change to adjacent tiles
            if (isValidPosition(row - 1, col)) tiles[row - 1][col].toggle();  // Up
            if (isValidPosition(row + 1, col)) tiles[row + 1][col].toggle();  // Down
            if (isValidPosition(row, col - 1)) tiles[row][col - 1].toggle();  // Left
            if (isValidPosition(row, col + 1)) tiles[row][col + 1].toggle();  // Right
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public boolean checkGoal() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j].isOn() != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(tiles[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[] getHint(int[][] goalPattern) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Create a copy of the current state
                int[][] testPattern = new int[size][size];
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        testPattern[k][l] = tiles[k][l].isOn();
                    }
                }
                // Toggle the current tile and adjacent tiles
                testPattern[i][j] = (testPattern[i][j]+1)%2;
                if (isValidPosition(i - 1, j)) testPattern[i - 1][j] = (testPattern[i-1][j]+1)%2;
                if (isValidPosition(i + 1, j)) testPattern[i + 1][j] = (testPattern[i+1][j]+1)%2;
                if (isValidPosition(i, j - 1)) testPattern[i][j - 1] = (testPattern[i][j-1]+1)%2;
                if (isValidPosition(i, j + 1)) testPattern[i][j + 1] = (testPattern[i][j+1]+1)%2;

                // Check if this toggle brings us closer to the goal
                int currentDifference = countDifferences(goalPattern);
                int newDifference = countDifferences(testPattern, goalPattern);
                if (newDifference < currentDifference) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // No hint found
    }

    private int countDifferences(int[][] pattern) {
        int differences = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j].isOn() != pattern[i][j]) {
                    differences++;
                }
            }
        }
        return differences;
    }

    private int countDifferences(int[][] currentPattern, int[][] goalPattern) {
        int differences = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (currentPattern[i][j] != goalPattern[i][j]) {
                    differences++;
                }
            }
        }
        return differences;
    }
}

// public class Grid {
//     private Tile[][] tiles;
//     private int size;

//     public Grid(int size) {
//         this.size = size;
//         this.tiles = new Tile[size][size];
//         for (int i = 0; i < size; i++) {
//             for (int j = 0; j < size; j++) {
//                 tiles[i][j] = new Tile();
//             }
//         }
//     }

//     public void toggleTile(int row, int col) {
//         if (isValidPosition(row, col)) {
//             tiles[row][col].toggle();
//             // Propagate the change to adjacent tiles
//             if (isValidPosition(row - 1, col)) tiles[row - 1][col].toggle();  // Up
//             if (isValidPosition(row + 1, col)) tiles[row + 1][col].toggle();  // Down
//             if (isValidPosition(row, col - 1)) tiles[row][col - 1].toggle();  // Left
//             if (isValidPosition(row, col + 1)) tiles[row][col + 1].toggle();  // Right
//         }
//     }

//     private boolean isValidPosition(int row, int col) {
//         return row >= 0 && row < size && col >= 0 && col < size;
//     }

//     public boolean checkGoal(boolean[][] goalPattern) {
//         for (int i = 0; i < size; i++) {
//             for (int j = 0; j < size; j++) {
//                 if (tiles[i][j].isOn() != goalPattern[i][j]) {
//                     return false;
//                 }
//             }
//         }
//         return true;
//     }

//     public void displayGrid() {
//         for (int i = 0; i < size; i++) {
//             for (int j = 0; j < size; j++) {
//                 System.out.print(tiles[i][j] + " ");
//             }
//             System.out.println();
//         }
//     }

//     public int[] getHint(boolean[][] goalPattern) {
//         for (int i = 0; i < size; i++) {
//             for (int j = 0; j < size; j++) {
//                 // Create a copy of the current state
//                 boolean[][] testPattern = new boolean[size][size];
//                 for (int k = 0; k < size; k++) {
//                     for (int l = 0; l < size; l++) {
//                         testPattern[k][l] = tiles[k][l].isOn();
//                     }
//                 }
//                 // Toggle the current tile and adjacent tiles
//                 testPattern[i][j] = !testPattern[i][j];
//                 if (isValidPosition(i - 1, j)) testPattern[i - 1][j] = !testPattern[i - 1][j];
//                 if (isValidPosition(i + 1, j)) testPattern[i + 1][j] = !testPattern[i + 1][j];
//                 if (isValidPosition(i, j - 1)) testPattern[i][j - 1] = !testPattern[i][j - 1];
//                 if (isValidPosition(i, j + 1)) testPattern[i][j + 1] = !testPattern[i][j + 1];

//                 // Check if this toggle brings us closer to the goal
//                 int currentDifference = countDifferences(goalPattern);
//                 int newDifference = countDifferences(testPattern, goalPattern);
//                 if (newDifference < currentDifference) {
//                     return new int[]{i, j};
//                 }
//             }
//         }
//         return null; // No hint found
//     }

//     private int countDifferences(boolean[][] pattern) {
//         int differences = 0;
//         for (int i = 0; i < size; i++) {
//             for (int j = 0; j < size; j++) {
//                 if (tiles[i][j].isOn() != pattern[i][j]) {
//                     differences++;
//                 }
//             }
//         }
//         return differences;
//     }

//     private int countDifferences(boolean[][] currentPattern, boolean[][] goalPattern) {
//         int differences = 0;
//         for (int i = 0; i < size; i++) {
//             for (int j = 0; j < size; j++) {
//                 if (currentPattern[i][j] != goalPattern[i][j]) {
//                     differences++;
//                 }
//             }
//         }
//         return differences;
//     }
// }