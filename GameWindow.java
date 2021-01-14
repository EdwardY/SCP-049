import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


/**
 * [GameWindow.java]
 * An abstract class to create the main game window.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 On January 11, 2021
 */
public abstract class GameWindow{
    /**The window that the game will use. */
   private JFrame window;
   /**The DuberPanel displaying all game objects on the game grid.*/
   private DuberPanel grid;
   /**The DuberPanel displaying all statistics about the player.*/
   private DuberPanel infoBar;


   public GameWindow(){
       this.window = new JFrame("Code-049");
       this.grid = new DuberPanel();
       this.infoBar = new DuberPanel();
   }

    /**
     * Updates the main game window.
     */
    public abstract void update();


    /**
     * Starts the animation of the {@code GameWindow}
     */
    public void start(){
        while(true){
            this.window.repaint();
            try  {Thread.sleep(20);} catch(Exception e){}
        }
    }

    /**
    * Gets the JFrame of the game window.
    * @return The JFrame of the game window.
    */
    public JFrame getWindow(){
        return this.window;
    }


    /**
    * Gets the grid DuberPanel of the game window.
    * @return The grid DuberPanel of the game window.
    */
    public DuberPanel getGrid(){
        return this.grid;
    }

    /**
    * Gets the stats bar DuberPanel of the game window.
    * @return The stats bar DuberPanel of the game window.
    */
    public DuberPanel getInfoBar(){
        return this.infoBar;
    }

    /**
     * [DuberPanel.java]
     * Custom {@code JPanel} class to be able to repaint things
     * @author Damon Ma, Edward Yang, Vivian Dai
     * @version 1.0 on January 14, 2021
     */
    public class DuberPanel extends JPanel{

        /**
         * Constructor for the {@code DuberPanel}
         */
        public DuberPanel(){
            setFocusable(true);
            requestFocusInWindow();
        }

        /**
         * Repaints the pannel
         * @param g the {@code Graphics} to draw on
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            //TODO: draw in everything else :'D
        }

    }

    /**
     * [DuberMouseHandler.java]
     * Inner class for mouse input.
     * @author Damon Ma, Edward Yang, Vivian Dai
     * @version 1.0 on January 11, 2021
     */
    public class DuberMouseHandler implements MouseMotionListener, MouseListener{
        /**The x-value of the mouse.*/
        private int x;
        /**The y-value of the mouse.*/
        private int y;

        /**
         * When the mouse's click button is pressed.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mousePressed(MouseEvent e){
        }

        /**
         * When the mouse's click button is released.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseReleased(MouseEvent e){
        }

        /**
         * When the mouse enters a component.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseEntered(MouseEvent e){
            if((x <= 100) && (x >= 0) && (y <= 100) && (y >= 0)){
                System.out.println("your button works.");
            }
        }


        /**
         * When the mouse exits a component.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseExited(MouseEvent e){
        }


        /**
         * When the mouse's button is pressed and released.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseClicked(MouseEvent e){
        }

        /**
         * When the mouse Moved. Updates mouse coordinates
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseMoved(MouseEvent e){
            this.x = e.getX();
            this.y = e.getY();
        }

        /**
         * When the mouse is moved while its button is pressed. Updates mouse coordinates
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseDragged(MouseEvent e){
            this.x = e.getX();
            this.y = e.getY();
        }


        /**
         * Returns the x-value of the mouse's position.
         * @return The x-value of the mouse's position.
         */
        public int getMouseX(){
            return this.x;
        }

        /**
         * Returns the y-value of the mouse's position.
         * @return THe y-value of the mouse's position.
         */
        public int getMouseY(){
            return this.y;
        }

    }//end of inner class

}//end of class