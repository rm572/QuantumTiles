public class TileQ {
    private int isOn;

    public TileQ(int on) {
        this.isOn = on;  // Tiles start in the "off" state
    }

    public int isOn() {
        return isOn;
    }

    public void toggle() {
        this.isOn = (this.isOn+1)% 2;  // Toggle the state
    }

    @Override
    public String toString() {
        return ""+isOn;  // Represent "on" as 1 and "off" as 0
    }
}

// public class Tile {
//     private boolean isOn;

//     public Tile() {
//         this.isOn = false;  // Tiles start in the "off" state
//     }

//     public boolean isOn() {
//         return isOn;
//     }

//     public void toggle() {
//         this.isOn = !this.isOn;  // Toggle the state
//     }

//     @Override
//     public String toString() {
//         return isOn ? "1" : "0";  // Represent "on" as 1 and "off" as 0
//     }
// }