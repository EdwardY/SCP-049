/**
 * [GameWindowRunner.java]
 * A class that will repeatedly repaint the game window in another thread so the rest of the game can run simultaneously.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 23, 2021
 */


public class GameWindowRunner implements Runnable{
    /**The game window that will be repainted.*/
    private GameWindow gameWindow;


    /**
     * Constructor for the GameWindowRunner
     * @param gameWindow The game window that will be repainted
     */
    public GameWindowRunner(GameWindow gameWindow){
        this.gameWindow = gameWindow;
    }


    /**
     * Starts the animation of the {@code GameWindow}
     */
    public void run(){
        while(true){
            this.gameWindow.getWindow().repaint();
            try  {Thread.sleep(20);} catch(Exception e){}
        }
    }

}