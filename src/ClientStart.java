import java.util.ArrayList;
/*
 * Tommy Morgan
 */
public class ClientStart
{
    
    private static int threadCount = 4;

    public static void main(String[] args) throws InterruptedException
    {
        ArrayList<Thread> threads = new ArrayList<Thread>();
        
        for(int i = 0; i < threadCount; i++)
        {
        	threads.add(new Thread(new GameBoard()));
        }
        
        for(int i = 0; i < threads.size(); i++)
        {
        	threads.get(i).run();
        	System.out.println("Player " + (i + 1));
        }
    }
}