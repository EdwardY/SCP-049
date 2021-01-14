/**
 * [Boundary.java]
 * Used to detect game objects within its boundary.
 * @author Damon Ma
 * @version 1.0 on January 14, 2020.
 */




class Boundary{
    /**Stores half the height of the rectangle.*/
    private int length;
    /**Stores half the width of the rectangle.*/
    private int width;
    /**Stores the x-position of the midpoint of the rectangle.*/
    private int xPos;
    /**Stores the y-position of the midpoint of the rectangle.*/
    private int yPos;
  
    
    /**
     * Constructor for the rectangle.
     * @param length Half the height of the rectangle.
     * @param width Half the width of the rectangle.
     * @param xPos The x-position of the rectangle's midpoint.
     * @param yPos The y-position of the rectangle's midpoint.
     */
    public Boundary(int length, int width, int xPos, int yPos){
      this.length = length;
      this.width = width;
      this.xPos = xPos;
      this.yPos = yPos;
    }
    
    
    //start of getters
    
    /**
     * Returns the height of the rectangle.
     * @return The height of the rectangle.
     */
    public int getLength(){
      return this.length;
    }
    
    
    /**
     * Returns the width of the rectangle.
     * @return The width of the rectangle.
     */
    public int getWidth(){
      return this.width;
    }
    
    /**
     * Returns the x-position of the rectangle's midpoint.
     * @return The x-position of the rectangle's midpoint.
     */
    public int getXPos(){
      return this.xPos;
    }
    
    /**
     * Returns the y-position of the rectangle's midpoint.
     * @return The y-position of the rectangle's midpoint.
     */
    public int getYPos(){
      return this.yPos;
    }
    
    //end of getters
    
  }//end of class