import java.util.Random;

public class GameBoard {

    private final int gridSize;
    private final int noOfMines;
    private final GameCell[][] grid;
    private final Random randomIndex = new Random();

    public GameBoard(int gridSize, int noOfMines) {
        this.gridSize = gridSize;
        this.noOfMines = noOfMines;
        this.grid = new GameCell[gridSize][gridSize];
        initGameBoard();
    }

    private void initGameBoard() {
        for (int row = 0 ; row < gridSize ; row ++) {
            for (int column = 0; column < gridSize ; column ++) {
                grid[row][column] = new GameCell();
            }
        }
        placeMines();
        calculateAdjacent();
    }

    private void placeMines() {
        int placed = 0 ;
        while (placed < noOfMines) {
            int row = randomIndex.nextInt(gridSize), column=randomIndex.nextInt(gridSize);
            if(!grid[row][column].isMineFound()) {
                grid[row][column].setMineFound(true);
                placed++;
            }
        }
    }

    private void calculateAdjacent() {
        int[] deltaRow = {-1, -1, -1, 0,0, 1, 1, 1};
        int[] deltaCol = {-1, 0, 1, -1, 1, -1, 0, 1};
        for (int row = 0; row < gridSize ; row ++) {
            for (int col = 0; col < gridSize ; col ++) {
                if(!grid[row][col].isMineFound()) {
                    int count = 0;
                    for(int i = 0; i < 8 ; i++) {
                        int newRow = row + deltaRow[i], newCol = col + deltaCol[i];
                        if(inBounds(newRow, newCol) && grid[newRow][newCol].isMineFound()) {
                            count ++;
                        }
                        grid[row][col].setAdjacentMines(count);
                    }
                }
            }
        }
    }

    private boolean inBounds(int row, int column) {
        return row >= 0 && row < gridSize && column >= 0 && column < gridSize;
    }

    public void reveal(int row, int col) {
        if (!inBounds(row, col) || grid[row][col].isRevealed()) return;

        grid[row][col].setRevealed(true);
        if (grid[row][col].getAdjacentMines() == 0 && !grid[row][col].isMineFound()) {
            int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < 8; i++)
                reveal(row + dr[i], col + dc[i]);
        }
    }

    public boolean isMine(int row, int col) { return grid[row][col].isMineFound(); }
    public boolean isRevealed(int row, int col) { return grid[row][col].isRevealed(); }
    public int getAdjacentMines(int row, int col) { return grid[row][col].getAdjacentMines(); }
    public int getSize() { return gridSize; }

    public boolean allSafeCellsRevealed() {
        for (int rowIndex = 0; rowIndex < gridSize; rowIndex++)
            for (int columnIndex = 0; columnIndex < gridSize; columnIndex++)
                if (!grid[rowIndex][columnIndex].isMineFound() && !grid[rowIndex][columnIndex].isRevealed())
                    return false;
        return true;
    }

    public void revealAllMines() {
        for (int r = 0; r < gridSize; r++)
            for (int c = 0; c < gridSize; c++)
                if (grid[r][c].isMineFound())
                    grid[r][c].isRevealed();
    }


    public String getDisplayGrid() {
        StringBuilder stringBuilder = new StringBuilder("  ");
        for (int i = 1; i <= gridSize; i++) {
            stringBuilder.append(i).append(" ");
        }
        stringBuilder.append("\n");
        for (int rowIndex = 0; rowIndex < gridSize; rowIndex++) {
            stringBuilder.append((char) ('A' + rowIndex)).append(" ");
            for (int columnIndex = 0; columnIndex < gridSize; columnIndex++) {
                if (grid[rowIndex][columnIndex].isRevealed()) {
                    if (grid[rowIndex][columnIndex].isMineFound()) {
                        stringBuilder.append("M ");
                    } else {
                        int count = grid[rowIndex][columnIndex].getAdjacentMines();
                        if (count == 0) {
                            stringBuilder.append("0 "); // or "  " for blank space
                        } else {
                            stringBuilder.append(count).append(" ");
                        }
                    }
                } else {
                    stringBuilder.append("_ ");
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


}
