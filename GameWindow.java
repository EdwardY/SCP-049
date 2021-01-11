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
   /**The JPanel displaying all game objects on the game grid.*/
   private JPanel grid;
   /**The JPanel displaying all statistics about the player.*/
   private JPanel infoBar;

    public abstract void update();


    /**
     * Inner class for mouse input.
     */
    public class DuberMouseHandler implements MouseMotionListener, MouseListener{
        /**The x-value of the mouse.*/
        private int x;
        /**The y-value of the mouse.*/
        private int y;

        /**
         * When the mouse's left click is pressed.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mousePressed(MouseEvent e){
            System.out.println("pressed");
        }

        /**
         * When the mouse's left click is released.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseReleased(MouseEvent e){
            System.out.println("released");
        }

        /**
         * When the mouse entered.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseEntered(MouseEvent e){
            System.out.println("entered");
        }


        /**
         * When the mouse exited.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseEntered(MouseEvent e){
            System.out.println("exited");
        }


        /**
         * When the mouse is left-clicked.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseEntered(MouseEvent e){
            System.out.println("clicked");
        }

        /**
         * When the mouse Moved.
         * @param MouseEvent The action performed by the mouse.
         */
        public void mouseEntered(MouseEvent e){
            System.out.println("moved");
        }



        /**
         * Returns the x-value of the mouse's position.
         * @return THe x-value of the mouse's position.
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