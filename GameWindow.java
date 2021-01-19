//graphic stuff
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//swing java graphic components
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * [GameWindow.java]
 * An abstract class to create the main game window.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 On January 11, 2021
 */
public abstract class GameWindow{
    /**The window that the game will use. */
   private JFrame window;

   public GameWindow(){
       this.window = new JFrame("Code-049");

       //setting the JFrame
       window.setSize(1920,1080); 
       window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

   }



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
     * [GridPanel.java]
     * Custom {@code JPanel} class to be able to repaint game grids
     * @author Damon Ma, Edward Yang, Vivian Dai
     * @version 1.0 on January 15, 2021
     */
    public abstract class GridPanel extends JPanel{

        /** The size of the road */
        public static final int ROAD_SIZE = 30;
        /** The number oc rows of buildings. */
        public static final int GRID_SIZE_BUILDING_WIDTH = 8;
        /**The number of columns of buildings */
        public static final int GRID_SIZE_BUILDING_LENGTH = 6;
        /** The length of the grid in pixels. */
        public static final int GRID_SIZE_LENGTH = 1080;
        /**The width of the grid in pizels.*/
        public static final int GRID_SIZE_WIDTH = 1420;
        /**
         * Constructor for the {@code DuberPanel}
         */
        public GridPanel(){
            setFocusable(true);
            requestFocusInWindow();
            this.setBackground(Color.GRAY);;
        }

        /**
         * Repaints the panel
         * @param g the {@code Graphics} to draw on
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);
        }
    }

    /**
     * [DuberMouseHandler.java]
     * Inner class for mouse input.
     * @author Damon Ma, Edward Yang, Vivian Dai
     * @version 1.0 on January 11, 2021
     */
    public abstract class DuberMouseHandler implements MouseMotionListener, MouseListener{
        /**The x-value of the mouse.*/
        private int x;
        /**The y-value of the mouse.*/
        private int y;

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