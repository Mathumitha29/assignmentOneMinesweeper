import org.junit.Test;
import static org.junit.Assert.*;

public class MinesweeperAppTest {

    @Test
    public void testWinCondition() {
        Game game = new Game(2, 1);
        // Find a non-mine cell and reveal it twice
        for (char row = 'A'; row <= 'B'; row++) {
            for (int col = 1; col <= 2; col++) {
                String input = "" + row + col;
                if (!game.getBoardView().contains("M")) {
                    game.reveal(input);
                    game.reveal(input);
                }
            }
        }
        assertTrue(game.isGameOver());
    }

    @Test
    public void testRevealMineEndsGame() {
        Game game = new Game(2, 1);
        boolean mineFound = false;
        for (char row = 'A'; row <= 'B'; row++) {
            for (int col = 1; col <= 2; col++) {
                String input = "" + row + col;
                if (game.getBoardView().contains("_")) {
                    String response = game.reveal(input);
                    if (response.contains("detonated")) {
                        mineFound = true;
                        assertTrue(game.isGameOver());
                        return;
                    }
                }
            }
        }
        assertTrue(mineFound);
    }
}
