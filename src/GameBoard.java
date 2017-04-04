import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
/*
 * Tommy Morgan
 */
public class GameBoard implements Runnable {
    private char[][] gameBoard;
    private boolean gameOnGoing = true;
    private final String SERVER_IP    = "localhost";
    private final int    SERVER_PORT  = 7788;

    public GameBoard()
    {
        gameBoard = new char [3][3];
        
        for (int row=0; row<gameBoard.length; row++)
        {
            Arrays.fill(gameBoard[row], ' ');
        }
    } 

    public void displayBoard()
    {
        for (int row=0; row<gameBoard.length; row++){
            for (int col=0; col<gameBoard[0].length; col++ ){
                
            	System.out.print("\t" + gameBoard[row][col]);
                
                if (col < 2)
                    System.out.print("|");
                
            }
            if(row < 2)
            	System.out.print("\n---------------------------\n");
        }
        System.out.println();
    } 

    public boolean gameActive()
    {
        return gameOnGoing;
    }

    public void askPlayer(char player)
    {
        @SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
        int row, col;
        do
        {
            System.out.printf("Player %s please enter a row (0-2): ", player);
            row = keyboard.nextInt();
            
            System.out.printf("Player %s please enter a column: (0-2)", player);
            col = keyboard.nextInt(); 
            
        }while(notValid(row,col));
        
        makeMove(player, row, col);
    } 

    public boolean checkForWinner()
    {
        for(int row=0; row<gameBoard.length; row++)
        {
            if((gameBoard[row][0] == 'O' || gameBoard[row][0] == 'X') &&
            	gameBoard[row][0] == gameBoard[row][1] && 
	            gameBoard[row][1] == gameBoard[row][2] && 
	            gameBoard[row][0] != ' ')
            {
                System.out.print("The winner " + gameBoard[row][0]);
                gameOnGoing = false;
            }
        }

        for(int col=0; col<gameBoard.length; col++)
        {
            if((gameBoard[0][col] == 'O' || gameBoard[0][col] == 'X') &&
            	gameBoard[0][col] == gameBoard[1][col] && 
	            gameBoard[1][col] == gameBoard[2][col] && 
	            gameBoard[0][col] != ' ')
            {
                System.out.print("The winner " + gameBoard[0][col]);
                gameOnGoing = false;
            }
        }
        
        if((gameBoard[0][0] == 'O' || gameBoard[2][0] == 'X') &&
        	gameBoard[0][0] == gameBoard[1][1] && 
	        gameBoard[1][1] == gameBoard[2][2] &&
	        gameBoard[0][0] != ' ')
            {
                System.out.print("The winner " + gameBoard[0][0]);
                gameOnGoing = false;
            }
        
        if(	(gameBoard[2][0] == 'O' || gameBoard[2][0] == 'X') &&
        	gameBoard[2][0] == gameBoard[1][1] && 
	        gameBoard[1][1] == gameBoard[0][2] &&
	        gameBoard[0][0] != ' ')
            {
                System.out.print("The winner is " + gameBoard[1][1]);
                gameOnGoing = false;
            }
        
        return false;
    }
    
    public boolean notValid(int row, int col)
    {
        if (row > 2 || row < 0 || col > 2 || col < 0 || !isEmpty(row,col))
            return true; 
        else
            return false;
    }
    
    public boolean isEmpty(int row, int col)
    {
        if (gameBoard[row][col] == ' ')
            return true;
        else
            System.out.printf("That position is taken.\n");
            return false;
    }
    
    public boolean makeMove(char player,int row,int col)
    {
        if(row>=0 && row<=2 && col>=0 && col <=2)
        {
            if(gameBoard[row][col] != ' ')
                return false;
            else
            {
                gameBoard[row][col] = player;
                return true;
            }
        }
        else
            return false;
    }

	public void doComputerMove(char computer) {
		int row = ThreadLocalRandom.current().nextInt(0, 3);
		int col = ThreadLocalRandom.current().nextInt(0, 3);
		
		makeMove(computer, row, col);
		
	}

	public void run()
    {
    	try
        {
    		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
    		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		
        }

        catch(UnknownHostException e)
        {
            System.err.println("Server not found.");
            System.exit(-1);
        }

        catch(IOException e)
        {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        System.out.println("Client has connected to the server.");
        
    } 
}