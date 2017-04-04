import java.io.*;
import java.net.Socket;
import java.util.*;
/*
 * Tommy Morgan
 */
public class ServerGame implements Runnable
{
    private TicTacToe ticTacToe;
    private Scanner scanner;
    public BufferedReader input; 
    public DataOutputStream output;
    private boolean running = true;
    private Socket connection = null;

    ServerGame(BufferedReader input, DataOutputStream output)
    {
        this.input = input;
        this.output = output;
    }
    
    ServerGame(Socket connection)
    {
    	this.connection = connection;
    }
    
	@Override
	public void run() {
		try
    	{
    		BufferedReader   in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter   out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
            
            ticTacToe = new TicTacToe(in, out);
            ticTacToe.playGame();
            
            while(running)
            {
            	String clientIn = in.readLine();
            	
            	if(clientIn.equalsIgnoreCase("q"))
            	{
            		running = false;
            		
            		System.out.println("Quiting...");
            	}
            }
    	}
    	catch(Exception e)
    	{
    		
    	}
		
		scanner = new Scanner(input);

        
		
	}

}