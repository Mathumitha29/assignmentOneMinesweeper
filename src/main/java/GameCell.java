public class GameCell {
    private boolean isMineFound = false;
    private boolean isRevealed;
    private int adjacentMines;

    public GameCell() {
        this.isMineFound = false;
        this.isRevealed = false;
        this.adjacentMines = 0;
    }

    public boolean isMineFound() {
        return isMineFound;
    }

    public void setMineFound(boolean mineFound) {
        isMineFound = mineFound;
    }

    public boolean isRevealed() {
        return isRevealed ;
    }

    public void setRevealed(boolean revealed) {
        isRevealed = revealed;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMinesCount) {
        adjacentMines = adjacentMinesCount;
    }
}
