public class Level {
    private int size;
    private int[][] startPattern;

    public Level(int size, int[][] goalPattern) {
        this.size = size;
        this.startPattern = goalPattern;
    }

    public int getSize() {
        return size;
    }

    public int[][] getStartPattern() {
        return startPattern;
    }
}

// public class Level {
//     private int size;
//     private boolean[][] goalPattern;

//     public Level(int size, boolean[][] goalPattern) {
//         this.size = size;
//         this.goalPattern = goalPattern;
//     }

//     public int getSize() {
//         return size;
//     }

//     public boolean[][] getGoalPattern() {
//         return goalPattern;
//     }
// }