import java.io.*;
import java.net.*;
/*
 * Tommy Morgan
 */
public class ServerStart
{
    static int PORT = 7788;


    public static void main(String[] args) throws IOException
    {
    	ServerSocket socket = null;
    	
		try 
    	{
	        socket = new ServerSocket(PORT);
	        while(true)
	        {
	        	Socket connection = socket.accept(); 
	        	new Thread(new ServerGame(connection) ).start();
//	        	{   
////			        BufferedReader clientInStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////			        DataOutputStream serverOutStream = new DataOutputStream(connection.getOutputStream());
////			        ServerGame game = new ServerGame(clientInStream, serverOutStream);
////	
////			        game.begin();
//	        	}
	        }

    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	finally
    	{
    		
            if(socket != null) socket.close();
    	}   
    }
}
