import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuantumTilesGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Level> levels = createLevels();

        System.out.println("Welcome to Quantum Tiles!");

        for (Level level : levels) {
            System.out.println("\nStarting new level...");
            Grid grid = new Grid(level.getSize(), level.getStartPattern());
            int[][] startPattern = level.getStartPattern();

            while (true) {
                System.out.println("Current Grid:");
                grid.displayGrid();

                System.out.println("Enter row and column to toggle (or -1 -1 to quit, -2 -2 for hint): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (row == -1 && col == -1) {
                    System.out.println("Exiting game. Goodbye!");
                    scanner.close();
                    return;
                } else if (row == -2 && col == -2) {
                    int[] hint = grid.getHint(startPattern);
                    if (hint != null) {
                        System.out.println("Try toggling the tile at: (" + hint[0] + ", " + hint[1] + ")");
                    } else {
                        System.out.println("No hints available.");
                    }
                } else {
                    grid.toggleTile(row, col);

                    if (grid.checkGoal()) {
                        System.out.println("Congratulations! You've matched the goal pattern!");
                        grid.displayGrid();
                        break;
                    }
                }
            }
        }

        System.out.println("You've completed all levels! Great job!");
        scanner.close();
    }

    private static List<Level> createLevels() {
        List<Level> levels = new ArrayList<>();

        // Level 1: 3x3 grid
        levels.add(new Level(3, new int[][]{
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        }));

        // Level 2: 3x3 grid with different pattern
        levels.add(new Level(3, new int[][]{
            {0, 1, 0},
            {1, 1, 1},
            {0, 1, 0}
        }));


        // Level 3: 4x4 grid
        levels.add(new Level(4, new int[][]{
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        }));

        // Level 3: 4x4 grid
        levels.add(new Level(4, new int[][]{
            {1, 0, 1, 0},
            {0, 1, 0, 1},
            {1, 0, 1, 0},
            {0, 1, 0, 1}
        }));


        // Level 4: 5x5 grid
        levels.add(new Level(5, new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
        }));        

        // Level 4: 5x5 grid
        levels.add(new Level(5, new int[][]{
            {1, 1, 0, 1, 1},
            {1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1},
            {1, 1, 0, 1, 1}
        }));

        // Add more levels as needed
        return levels;
    }
}

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import java.util.ArrayList;
// import java.util.List;

// public class QuantumTilesGame extends Application {
//     private List<Level> levels;
//     private int currentLevelIndex;
//     private Grid currentGrid;
//     private boolean[][] currentGoalPattern;
//     private Label statusLabel;

//     public static void main(String[] args) {
//         launch(args);
//     }

//     @Override
//     public void start(Stage primaryStage) {
//         levels = createLevels();
//         currentLevelIndex = 0;

//         VBox root = new VBox();
//         statusLabel = new Label("Welcome to Quantum Tiles!");
//         root.getChildren().add(statusLabel);

//         GridPane gridPane = new GridPane();
//         root.getChildren().add(gridPane);

//         Scene scene = new Scene(root, 300, 400);
//         primaryStage.setTitle("Quantum Tiles");
//         primaryStage.setScene(scene);
//         primaryStage.show();

//         loadLevel(gridPane);
//     }

//     private void loadLevel(GridPane gridPane) {
//         gridPane.getChildren().clear();

//         if (currentLevelIndex >= levels.size()) {
//             statusLabel.setText("You've completed all levels! Great job!");
//             return;
//         }

//         Level level = levels.get(currentLevelIndex);
//         currentGrid = new Grid(level.getSize());
//         currentGoalPattern = level.getGoalPattern();

//         for (int row = 0; row < level.getSize(); row++) {
//             for (int col = 0; col < level.getSize(); col++) {
//                 Button tileButton = new Button("0");
//                 tileButton.setPrefSize(50, 50);
//                 int finalRow = row;
//                 int finalCol = col;

//                 tileButton.setOnAction(e -> {
//                     currentGrid.toggleTile(finalRow, finalCol);
//                     updateGrid(gridPane);
//                     if (currentGrid.checkGoal(currentGoalPattern)) {
//                         statusLabel.setText("Congratulations! You've matched the goal pattern!");
//                         currentLevelIndex++;
//                         loadLevel(gridPane);
//                     }
//                 });

//                 gridPane.add(tileButton, col, row);
//             }
//         }

//         updateGrid(gridPane);
//     }

//     private void updateGrid(GridPane gridPane) {
//         for (int row = 0; row < currentGrid.getSize(); row++) {
//             for (int col = 0; col < currentGrid.getSize(); col++) {
//                 Button button = (Button) getNodeByRowColumnIndex(row, col, gridPane);
//                 button.setText(currentGrid.getTile(row, col).isOn() ? "1" : "0");
//             }
//         }
//     }

//     private static javafx.scene.Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
//         for (javafx.scene.Node node : gridPane.getChildren()) {
//             if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
//                 return node;
//             }
//         }
//         return null;
//     }

//     private List<Level> createLevels() {
//         List<Level> levels = new ArrayList<>();

//         // Level 1: 3x3 grid
//         levels.add(new Level(3, new boolean[][]{
//             {true, true, true},
//             {true, false, true},
//             {true, true, true}
//         }));

//         // Level 2: 3x3 grid with different pattern
//         levels.add(new Level(3, new boolean[][]{
//             {false, true, false},
//             {true, true, true},
//             {false, true, false}
//         }));

//         // Level 3: 4x4 grid
//         levels.add(new Level(4, new boolean[][]{
//             {true, false, true, false},
//             {false, true, false, true},
//             {true, false, true, false},
//             {false, true, false, true}
//         }));

//         // Level 4: 5x5 grid
//         levels.add(new Level(5, new boolean[][]{
//             {true, true, false, true, true},
//             {true, false, true, false, true},
//             {false, true, false, true, false},
//             {true, false, true, false, true},
//             {true, true, false, true, true}
//         }));

//         return levels;
//     }
// }