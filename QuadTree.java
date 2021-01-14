import java.util.ArrayList;
import java.lang.Math;

/**
 * [QuadTree.java]
 * A Quadtree used to check if an NPC is within range to attack another NPC.
 * @author Damon Ma
 * @version 1.0 on January 14, 2020
 */


public class QuadTree {
    /**The maximum amount of NPC's that can be held in this quadtree. */
    private final int MAXNPCS = 10;
    /**The maximum amount of subdivisions that the quadtree can make. */
    private final int MAXDEPTH = 5;
    /**Counts the amount of times that the quadtree has been subdivided */
    private int depth;
    /**The boundary of the quadtree. */
    private Boundary boundary;
    /**All NPC's stored in this quadtree */
    private ArrayList<NPC> NPCList;
    /**Boolean stores whether or not this quadtree is a leaf. */
    private boolean isLeaf = false;
    /**The leaves of this quadtree that is created when this quadtree is subdivided. */
    private QuadTree[] leaves = new QuadTree[4];


    /**
    * Constructor for the quadtree
    * @param length The length or height of the quadtree's boundary.
    * @param width The width of the quadtree's boundary.
    * @param xPos The x-value of the boundary's midpoint.
    * @param yPos The y-value of the boundary's midpoint.
    * @param NPCList The linked list that stores all of the quadtree's balls.
    * @param scpNum The amount of scps that the .
    * @param depth The amount of subdivisions of the quadtree.*/
    public QuadTree(int length, int width, int xPos, int yPos, ArrayList<NPC> NPCList, int depth){
        this.boundary = new Boundary(length, width, xPos, yPos);  //make the boundary for the quadtree
        //define the rest of the quadtree's attributes
        this.NPCList = NPCList;
        this.depth = depth;
    } //end of constructor



    



}//end of class
