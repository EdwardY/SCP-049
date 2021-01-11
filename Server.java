//graphics imports
import java.awt.Image;
import java.awt.Toolkit;

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
    /** Thread for the game */
    private Thread gameThread;
    /** Game info storage */
    private Game game;

    public static void main(String[] args){
        new Server(5000).go();
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
     * newly joined player gets the not taken role. After both players have connected, the game thread will start running and a 
     * message will be sent to both players indicating which role they have and their opponent's username
     * </p>
     */
    public void go(){
        Socket client = null;
        try{
            serverSock = new ServerSocket(this.port);
            //serverSock.setSoTimeout(1000);
            while((this.scp == null) || (this.town == null)){
                client = serverSock.accept();
                if((this.scp == null) && (this.town == null)){
                    //assign at random
                    double rand = Math.random();
                    if(rand > 0.5){
                        this.scp = new ClientHandler(client);
                        this.scpThread = new Thread(this.scp);
                        this.scp.sendMessage("<w>");
                    }else{
                        this.town = new ClientHandler(client);
                        this.townThread = new Thread(this.scp);
                        this.town.sendMessage("<w>");
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
            this.gameThread = new Thread(new GameHandler());
            this.town.sendMessage("<s>");
            this.town.sendMessage("t");
            this.town.sendMessage(this.scp.getUsername());
            this.scp.sendMessage("<s>");
            this.scp.sendMessage("s");
            this.scp.sendMessage(this.town.getUsername());
        }catch(Exception e){ 
            System.out.println("Error accepting connection");
        }
        try{
          client.close();
        }catch (Exception e){ 
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
        /** The username of the player connected */
        private String username;

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
         * Gets the player's username
         * @return the username of the player
         */
        public String getUsername(){
            return this.username;
        }

        /**
         * <p>
         * Accepts messages from the clients and deals with them properly while the server is still running. Once the server is 
         * no longer running, the socket will attempt to close itself
         * </p>
         */
        public void run(){
            while(running){
                String prefix = "";
                try{
                    if(input.ready()){
                        prefix = input.readLine();
                        if(prefix.equals("<c>")){
                            this.username = input.readLine();
                        }else if(prefix.equals("<b>")){
                            String buildingType = input.readLine();
                            String coords = input.readLine();
                            int x = Integer.parseInt(coords.split(" ")[0]);
                            int y = Integer.parseInt(coords.split(" ")[1]);
                            buildBuilding(buildingType, x, y);
                        }else if(prefix.equals("<e>")){
                            String eventType = input.readLine();
                            int level = Integer.parseInt(input.readLine());
                            if(!isWholeGameEvent(eventType)){
                                String coords = input.readLine();
                                int x = Integer.parseInt(coords.split(" ")[0]);
                                int y = Integer.parseInt(coords.split(" ")[1]);
                            }else{

                            }
                        }
                    }
                }catch(IOException e){
                    System.out.println("Something broke, server maintenance time");
                    e.printStackTrace();
                }
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

        /**
         * Checks for if the type of event is a whole game event
         * @param eventType the string for the type of event
         * @return true if it's a whole game event, false otherwise
         */
        private boolean isWholeGameEvent(String eventType){
            return eventType.equals("stonks") || eventType.equals("riot") || eventType.equals("mutate") || eventType.equals("warpreality");
        }

        /**
         * Creates the {@code Building} then adds it to the game
         * @param type the type of {@code Building}
         * @param x top left x coordinate 
         * @param y top left y coordinate
         */
        private void buildBuilding(String type, int x, int y){
            Image sprite = Toolkit.getDefaultToolkit().getImage("./assets/" + type + ".png");
            int price = Integer.MAX_VALUE;
            Building building = null;
            if(type.equals("bank")){
                building = new Bank(Bank.INITIAL_PRICE, Bank.INITIAL_HEALTH, Bank.INITIAL_HEALTH, sprite, x, y);
                price = Bank.INITIAL_PRICE;
            }else if(type.equals("foodbuilding")){
                building = new FoodBuilding(FoodBuilding.INITIAL_PRICE, FoodBuilding.INITIAL_HEALTH, FoodBuilding.INITIAL_HEALTH, sprite, x, y);
                price = FoodBuilding.INITIAL_PRICE;
            }else if(type.equals("hospital")){
                building = new Hospital(Hospital.INITIAL_PRICE, Hospital.INITIAL_HEALTH, Hospital.INITIAL_HEALTH, sprite, x, y, Hospital.INITIAL_MAX_CAP);
                price = Hospital.INITIAL_PRICE;
            }else if(type.equals("militarybase")){
                building = new MilitaryBase(MilitaryBase.INITIAL_PRICE, MilitaryBase.INITIAL_HEALTH, MilitaryBase.INITIAL_HEALTH, sprite, x, y);
                price = MilitaryBase.INITIAL_PRICE;
            }else if(type.equals("researchlab")){
                building = new ResearchLab(ResearchLab.INITIAL_PRICE, ResearchLab.INITIAL_HEALTH, ResearchLab.INITIAL_HEALTH, sprite, x, y);
                price = ResearchLab.INITIAL_PRICE;
            }else if(type.equals("residency")){
                building = new Residency(Residency.INITIAL_PRICE, Residency.INITIAL_HEALTH, Residency.INITIAL_HEALTH, sprite, x, y, Residency.INITIAL_MAX_CAP);
                price = Residency.INITIAL_PRICE;
            }
            if(building != null){
                if(game.getMoney() >= price){
                    game.addBuilding(building);
                }
            }
        }

        /**
         * Creates an {@code Event}
         * @param type the type of event to create
         */
        private boolean createEvent(String type, int level){
            //TODO: finish this up
            if(type.equals("stonks")){
                game.startEvent(new Stonks(level));
            }
            return false;
        }
    }

    /**
     * [GameHandler.java]
     * Will update turns for the game
     * @author Damon Ma, Edward Yang, Vivian Dai
     * @version 1.0 on January 9, 2021
     */
    private class GameHandler implements Runnable{
        private long lastTime;
        private long curTime;
        private static final int ONE_MINUTE = 60000;
        /**
         * 
         */
        //TODO: javadocs
        public void run(){
            while(running){
                curTime = System.currentTimeMillis();
                if(curTime - lastTime >= ONE_MINUTE){
                    game.doTurn();
                }
            }//TODO: thread.sleep, send info to both sides
        }
    }
}
