//io imports
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

//network communication imports
import java.net.Socket;
import java.net.ServerSocket;

//data structures needed
import java.util.ArrayList;

/**
 * [Server.java]
 * Server for the SCP game
 * @author Damon Ma, Edward Yang, Vivian Dai
 * @version 1.0 on January 5, 2021
 */

class Server {
    /** A boolean for if the server is still running or not */
    private boolean running;
    /** The {@code ServerSocket} for where all the networking will be run */
    private ServerSocket serverSock;
    /** The port to connect to */
    private int port;
    /** Handles town player actions */
    private ClientHandler town;
    /** Handles SCP player actions */
    private ClientHandler scp;
    /** Thread for the town player */
    private Thread townThread;
    /** Thread for the SCP player */
    private Thread scpThread;
    /** Game info storage */
    private Game game;

    public static void main(String[] args){
        new Server(5000);
    }

    /**
     * Creates the {@code Server}
     * @param port the port to use
     */
    public Server(int port){
        running = true;
        this.port = port;
        this.game = new Game();
        this.scp = null;
        this.town = null;
    }

    /**
     * <p>
     * Starts looking for connections. Will keep looking for connections as long as there isn't the requirement of two players. 
     * If there are no players in the game, SCP or town role will be assigned randomly. If there already is a player then the 
     * newly joined player gets the not taken role. 
     * </p>
     */
    public void go(){
        Socket client = null;
        try{
            serverSock = new ServerSocket(this.port);
            while((this.scp == null) || (this.town == null)){
                client = serverSock.accept();
                if((this.scp == null) && (this.town == null)){
                    //assign at random
                    double rand = Math.random();
                    if(rand > 0.5){
                        this.scp = new ClientHandler(client);
                        this.scpThread = new Thread(this.scp);
                    }else{
                        this.town = new ClientHandler(client);
                        this.townThread = new Thread(this.scp);
                    }
                }else if(this.scp == null){
                    //assigns scp
                    this.scp = new ClientHandler(client);
                    this.scpThread = new Thread(this.scp);
                }else{
                    //assigns town
                    this.town = new ClientHandler(client);
                    this.townThread = new Thread(this.scp);
                }
            }
        }catch(Exception e) { 
            System.out.println("Error accepting connection");
        }
        try {
          client.close();
        }catch (Exception e1) { 
            System.out.println("Failed to close socket");
        }
        System.exit(-1);
    }

    /**
     * [ClientHandler.java]
     * Accepts clients and handles requests
     * @author Damon Ma, Edward Yang, and Vivian Dai
     * @version 1.0 on January 5, 2021
     */
    private class ClientHandler implements Runnable{
        /** The {@code PrintWriter} used to send stuff over to the client's computer */
        private PrintWriter output;
        /** The stream for network input */
        private BufferedReader input;
        /** {@code Socket} which the client uses */
        private Socket client;

        /**
         * Constructor for the {@code ClientHandler}
         * @param s the {@code Socket} the client is connecting to
         */
        private ClientHandler(Socket s){
            this.client = s;
            try {
                this.output = new PrintWriter(client.getOutputStream());
                InputStreamReader stream = new InputStreamReader(client.getInputStream());
                this.input = new BufferedReader(stream);
            }catch(IOException e) {
                e.printStackTrace();        
            }
        }

        /**
         * <p>
         * Accepts messages from the clients and deals with them properly while the server is still running. Once the server is 
         * no longer running, the socket will attempt to close itself
         * </p>
         */
        public void run(){
            while(running){
                //TODO: accept messages and deal with
            }
            try{
                input.close();
                output.close();
                client.close();
            }catch(Exception e){
                System.out.println("Error closing socket");
                e.printStackTrace();
            }
        }

        /**
         * Sends a message through a {@code PrintWriter}, in this case it gets sent over the network
         * @param msg the message to send
         */
        private void sendMessage(String msg){
            this.output.println(msg);
            this.output.flush();
        }
    }
}
