import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Math;

/**
 * [QuadTree.java]
 * A Quadtree used to check if an NPC is within range to attack another NPC.
 * @author Damon Ma
 * @version 1.0 on January 14, 2020
 */


public class QuadTree {
    /**The maximum amount of target's that can be held in this quadtree. */
    private final int MAXTARGETS = 10;
    /**The maximum amount of subdivisions that the quadtree can make. */
    private final int MAXDEPTH = 5;
    /**Counts the amount of times that the quadtree has been subdivided */
    private int depth;
    /**The boundary of the quadtree. */
    private Boundary boundary;
    /**All NPC's that can attack in the quadtree. */
    private ArrayList<NPC> attackerList;
    /**All NPC's that can receive damage from the attackers in the quadtree.*/
    private ArrayList<NPC> targetList;
    /**Boolean stores whether or not this quadtree is a leaf. */
    private boolean isLeaf = true;
    /**The leaves of this quadtree that is created when this quadtree is subdivided. */
    private QuadTree[] leaves = new QuadTree[4];


    /**
    * Constructor for the quadtree
    * @param length The length or height of the quadtree's boundary.
    * @param width The width of the quadtree's boundary.
    * @param xPos The x-value of the boundary's midpoint.
    * @param yPos The y-value of the boundary's midpoint.
    * @param depth The amount of subdivisions of the quadtree.
    */
    public QuadTree(int length, int width, int xPos, int yPos, int depth){
        this.boundary = new Boundary(length, width, xPos, yPos);  //make the boundary for the quadtree
        //define the rest of the quadtree's attributes
        this.attackerList = new ArrayList<NPC>();
        this.targetList = new ArrayList<NPC>();
        this.depth = depth;
    } //end of constructor


    /**
   * Inserts an attacker into the quadtree.
   * @param newAttacker The new attacking NPC that will be put into quadtree.
   */
  public void insertAttacker(NPC attacker){
    //get attacker position
    int attackerXPos = attacker.getX();
    int attackerYPos = attacker.getY();

    //get the boundaries of this quadtree (up, down, left and right)
    int leftBoundary = this.boundary.getXPos() - (this.boundary.getWidth()/2);
    int rightBoundary = this.boundary.getXPos() + (this.boundary.getWidth()/2);
    int topBoundary = this.boundary.getYPos() - (this.boundary.getLength()/2);
    int bottomBoundary = this.boundary.getYPos() +   (this.boundary.getLength()/2);

    if((attackerXPos > rightBoundary)  && (attackerXPos < leftBoundary)  &&  (attackerYPos < topBoundary) && (attackerYPos  > bottomBoundary) ){ //check if NPC is out of bounds
      
      return; //end method, since it doesn't belong in this quadtree
    }else if(this.isLeaf == false){ //if the quadtree has already been divided
      
      this.leaves[findLeaf(attacker)].insertAttacker(attacker); //find which leaf the attacker belongs in and insert it
    }else{ //if it doesn't belong in a leaf, add here, subdivision is based on number of targets.
      this.attackerList.add(attacker);
    }

  } //end of inserting attacker method

  /**
   * Inserts a potential target into the quadtree.
   * @param target The target to be inserted.
   */
  public void insertTarget(NPC target){
    int targetXPos = target.getX();
    int targetYPos = target.getY();

    //get the boundaries of this quadtree (up, down, left and right)
    int leftBoundary = this.boundary.getXPos() - (this.boundary.getWidth()/2);
    int rightBoundary = this.boundary.getXPos() + (this.boundary.getWidth()/2);
    int topBoundary = this.boundary.getYPos() - (this.boundary.getLength()/2);
    int bottomBoundary = this.boundary.getYPos() +   (this.boundary.getLength()/2);

    if((targetXPos > rightBoundary)  && (targetXPos < leftBoundary)  &&  (targetYPos < topBoundary) && (targetYPos  > bottomBoundary) ){ //if the target is out of bounds
      
      return; //doesn't belong here, don't add

    }else if(isLeaf == false){ //if the quadtree has already been divided
      
      leaves[findLeaf(target)].insertTarget(target); //find which child or quadrant the ball belongs in and insert it there
      
    }else if((this.targetList.size() < MAXTARGETS) || (this.depth >= MAXDEPTH)){ //if max subdivisions have been made
      
      targetList.add(target);  //insert the target into this quadtree
      
    }else{ //if the quadtree needs to be divided
      
      targetList.add(target);  //add object to the list of targets for now
      this.divide(); //divide the quadtree into 4 smaller quadtrees and put the targets where they belong
      this.isLeaf = false; //indicate that this quadtree is no longer a leaf      
    }


  }//end of method


  /**
   * Clears the quadtree.
   */
  public void clear(){
    //reset all of the variables
    this.leaves = null;
    this.attackerList = null;
    this.targetList = null;
    this.boundary = null;
    this.depth = 0;
    this.isLeaf = true;
  }

  /**
   * Checks if a target is within the range of the attacker and attacks if possible.
   */
  public void startCombat(){
    if(this.isLeaf){ //if this quadtree is a leaf, start comparing
      //set coordinate values of the two NPC's being compared
      int targetX;
      int targetY;
      int attackerX;
      int attackerY;
      int distance;

      for(int i = 0; i < attackerList.size(); i ++){ //run through each attacker seeing if they can attack a target
        //get attacker's position
        attackerX = attackerList.get(i).getX();
        attackerY = attackerList.get(i).getY();

        for(int j = 0; j < targetList.size(); j++){//run through each attacker seeing if it will be attacked by an attacker.
          //get target's position
          targetX = targetList.get(j).getX();
          targetY = targetList.get(j).getY();

          //calculate the distance between these two NPC's
          distance = (int)Math.sqrt(Math.pow(Math.abs(attackerX - targetX), 2) + Math.pow(Math.abs(attackerY - targetY), 2));

          if((this.attackerList.get(i) instanceof SCP0492) && (distance <= SCP0492.RANGE)){ //if the attacker is an SCP0492
              ((SCP0492)attackerList.get(i)).attack(targetList.get(j)); //attack the target
              if(this.targetList.get(j).getHealth() <= 0){ //if the target has no more health
                this.targetList.remove(j); //remove the target from the list of targets since it is now destroyed
                j = targetList.size(); //end inner for loop since scp has already attacked

              }
          }else if((this.attackerList.get(i) instanceof Soldier) && (distance <= Soldier.RANGE)){ //if the attacker is a soldier.
              ((Soldier)attackerList.get(i)).attack(targetList.get(j));  //attack the target
              if(this.targetList.get(j).getHealth() <=0 ){ //if target has no more health
                this.targetList.remove(j); //remove the object since the target is now destroyed
                j = targetList.size(); //end inner for loop since Soldier has already attacked
              }
          }//end of if statement block
        }//end of inner for loop
      }//end of for loop


    }else{ //if there are other leaves, call those leaves to start the comparison
      for(int i = 0; i < leaves.length; i ++){
        leaves[i].startCombat();
      }//end of for loop
    }//end of if block statements
  }//end of method

  /**
   * Subdivides this quadtree into 4 other leaves.
   */
  private void divide(){

    //Get all of the dimensions of this quadtree's boundary
    int newLength = this.boundary.getLength()/2;
    int newWidth = this.boundary.getWidth()/2;
    int currentXPos = this.boundary.getXPos();
    int currentYPos = this.boundary.getYPos();
    //end getting dimensions
    
    //based on this quadtree, split it into four smaller quadtrees, follows the quadrant system where the quadrant number is the index number +1 
    this.leaves[0] = new QuadTree(newLength, newWidth, (currentXPos + (newWidth/2)), (currentYPos - (newLength/2)), this.depth+1);
    this.leaves[1] = new QuadTree(newLength, newWidth, (currentXPos - (newWidth/2)), (currentYPos - (newLength/2)), this.depth+1);
    this.leaves[2] = new QuadTree(newLength, newWidth, (currentXPos - (newWidth/2)), (currentYPos + (newLength/2)), this.depth+1);
    this.leaves[3] = new QuadTree(newLength, newWidth, (currentXPos + (newWidth/2)), (currentYPos + (newLength/2)), this.depth+1);
    //end splitting
    
    for(int i = 0; i  < attackerList.size(); i++){ //move all of the attackers to the subtree that they belong in
      NPC currentAttacker = this.attackerList.get(i); 
      leaves[findLeaf(currentAttacker)].insertAttacker(currentAttacker); //find which subtree it belongs to, and inserts it there
    }

    for(int i = 0; i  < targetList.size(); i++){ //move all of the attackers to the subtree that they belong in
      NPC currentTarget = this.targetList.get(i); 
      leaves[findLeaf(currentTarget)].insertTarget(currentTarget); //find which subtree it belongs to, and inserts it there
    }


    this.targetList.clear(); //clear list of targets since they have been moved
    this.attackerList.clear(); //clears this quadtree's list of attackers since they have all been moved

  }//end of method

  
  /**
   * Finds the sub quadtree where an NPC belongs or where it is supposed to be inserted.
   * @param npc The NPC that will be inserted into the quadtree
   */
  private int findLeaf(NPC npc){
  
    if((npc.getX() >= this.boundary.getXPos())  && (npc.getY()   <= this.boundary.getYPos())){ //if ball belongs in the first quadrant
      return 0;
    }else if((npc.getX() <= this.boundary.getXPos())  && (npc.getY() <= this.boundary.getYPos())){ //if ball belongs in the second quadrant
      return 1;
    }else if((npc.getX() <= this.boundary.getXPos())   &&  (npc.getY() >= this.boundary.getYPos() ) ){ //if ball belongs in the third quadrant
      return 2;
    }else{ //if ball belongs in the fourth quadrant
      return 3;
    }
  } //end of method


  //start of getters
  /**
   * Returns all of the targets in the quadtree
   * @param targets A master arraylist that will store all of the targets
   * @return A linked list storing all of the targets.
   */
  public ArrayList<NPC> getTargets(ArrayList<NPC> targets){    
    
    if(this.isLeaf){ //if this quadtree is a leaf
      for(int i = 0; i < this.targetList.size(); i++){ //for loop will recursively add all of the targets to the master list
        targets.add(this.targetList.get(i));
      }
      
    }else{ //if the quadtree is not a leaf
      for(int i = 0; i < leaves.length; i ++){ //for loop will get all of the targets from the leaves and add them to the master list
        leaves[i].getTargets(targets);
      }
    }
    
    return targets; //once everything has been added, return the master list
    
  } //end of method


   /**
   * Returns all of the attackers in the quadtree
   * @param attackers A master arraylist that will store all of the attackers
   * @return A linked list storing all of the attackers.
   */
  public ArrayList<NPC> getAttackers(ArrayList<NPC> attackers){    
    
    if(this.isLeaf){ //if this quadtree is a leaf
      for(int i = 0; i < this.attackerList.size(); i++){ //for loop will recursively add all of the targets to the master list
        attackers.add(this.attackerList.get(i));
      }
      
    }else{ //if the quadtree is not a leaf
      for(int i = 0; i < leaves.length; i ++){ //for loop will get all of the targets from the leaves and add them to the master list
        leaves[i].getAttackers(attackers);
      }
    }
    
    return attackers; //once everything has been added, return the master list
    
  } //end of method

  //end of getters

}//end of class
