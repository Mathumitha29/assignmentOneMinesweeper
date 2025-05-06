import java.util.Scanner;

public class MinesweeperApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Minesweeper!");

            int gridSize = 0;
            while (true) {
                System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
                if (scanner.hasNextInt()) {
                    gridSize = scanner.nextInt();
                    if (gridSize >= 2) {
                        break;
                    } else {
                        System.out.println("Invalid input. Grid size must be at least 2.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // discard invalid input
                }
            }

            int maxMines = (int) (gridSize * gridSize * 0.35);
            int noOfMines = 0;
            while (true) {
                System.out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
                if (scanner.hasNextInt()) {
                    noOfMines = scanner.nextInt();
                    if (noOfMines >= 1 && noOfMines <= maxMines) {
                        break;
                    } else {
                        System.out.printf("Invalid input. Number of mines must be between 1 and %d.%n", maxMines);
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // discard invalid input
                }
            }

            Game game = new Game(gridSize, noOfMines);

            while (!game.isGameOver()) {
                System.out.println(game.getBoardView());
                System.out.print("Select a square to reveal (e.g., A1): ");
                String inputCellName = scanner.next().toUpperCase();
                System.out.println(game.reveal(inputCellName));
            }

            System.out.print("Press any key to play again or type 'exit' to quit: ");
            String next = scanner.next();
            if (next.equalsIgnoreCase("exit")) {
                break;
            }
        }

    }
}
