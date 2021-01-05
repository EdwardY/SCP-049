import java.io.PrintWriter;
import java.io.BufferedReader;


/**
 * [Client.java]
 * The client program used by the players of the game.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 4, 2020 
 */


public class Client {
    /**The port that the client has connected to.*/
    private int port;
    /**The IP address of the server that the client connected to.*/
    private String ipAddress;
    /**Used to write messages to the server.*/
    private PrintWriter output;
    /**Used to receive messages from the server.*/
    private BufferedReader input;
    /**Holds relevant information about the player's game objects and stats.*/
    //private Player player;
    //no player class yet


    /**
     * The main method of the program to run.
     * @param args Java command-line arguments
     */
    public static void main(String[] args){
        new Client().login(); //initiate login process
    }

    /**
     * The login process for the client.
     */
    public void login(){
        System.out.println("Hello");
    }



}
