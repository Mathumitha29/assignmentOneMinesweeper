public class Game {
    private final GameBoard gameBoard;
    private boolean isGameOver;

    public Game(int gridSize, int noOfMines) {
        gameBoard = new GameBoard(gridSize, noOfMines);
        isGameOver = false;
    }

    public String getBoardView() {
        return gameBoard.getDisplayGrid();
    }

    public  boolean isGameOver() {
        return isGameOver;
    }

    public String reveal(String input) {
        if (input == null || input.length() < 2) {
            return "Invalid input. Please use format like A1.";
        }

        int row = input.charAt(0) - 'A';
        int col;

        try {
            col = Integer.parseInt(input.substring(1)) - 1;
        } catch (NumberFormatException e) {
            return "Invalid input. Column must be a number (e.g., A1, B3).";
        }

        // Check if row and col are within bounds
        if (row < 0 || row >= gameBoard.getSize() || col < 0 || col >= gameBoard.getSize()) {
            return "Invalid input. Row or column out of bounds.";
        }

        if (gameBoard.isMine(row, col)) {
            gameBoard.revealAllMines();
            isGameOver = true;
            return "Oh no, you detonated a mine! Game over.";
        }

        if (gameBoard.isRevealed(row, col)) {
            return "This square is already revealed. Please choose another.";
        }

        gameBoard.reveal(row, col);
        if (gameBoard.allSafeCellsRevealed()) {
            isGameOver = true;
            return "Congratulations, you have won the game!";
        }

        return "This square contains " + gameBoard.getAdjacentMines(row, col) + " adjacent mines.";
    }

}
