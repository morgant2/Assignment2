import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
/*
 * Tommy Morgan
 */
public class TicTacToe {

	private static char player = 'O';
	private static char computer = 'X';
	
    private BufferedReader input; 
    private PrintWriter output;
    private Scanner scanner;
	
    public TicTacToe(BufferedReader in, PrintWriter out)
    {
        this.input = in;
        this.output = out;
    }
    
	

	public void playGame() {
		Random rand = new Random();
		
		scanner = new Scanner(input);
		
		GameBoard game = new GameBoard();
		game.displayBoard();
		
		int counter = rand.nextInt(1);
		
		while(game.gameActive())
		{
			if (counter % 2 == 0)
			{
				game.askPlayer(player);
			}
			else
			{
				game.doComputerMove(computer);
			}
			
			counter++;
			System.out.println("\n");
			game.displayBoard();
			game.checkForWinner();
			
			if(counter == 10)
			{
				System.out.println("Draw!");
			}			
		}
	}

	

}
