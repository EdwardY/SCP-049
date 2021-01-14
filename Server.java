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
     * Sends a message to both users to start the game
     */
    private void startGame(){
        this.gameThread = new Thread(new GameHandler());
        this.town.sendMessage("<s>");
        this.town.sendMessage("t");
        this.town.sendMessage(this.scp.getUsername());
        this.town.sendMessage("" + game.getMoney());
        this.town.sendMessage("" + game.getFood());
        this.scp.sendMessage("<s>");
        this.scp.sendMessage("s");
        this.scp.sendMessage(this.town.getUsername());
        this.scp.sendMessage("" + game.getHume());
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
            serverSock.setSoTimeout(100000); //TODO: take off timeout later
            while(running){
                client = serverSock.accept();
                System.out.println("Client");
                if((this.scp == null) && (this.town == null)){
                    //assign at random
                    double rand = Math.random();
                    if(rand > 0.5){
                        this.scp = new ClientHandler(client);
                        this.scpThread = new Thread(this.scp);
                        this.scpThread.start();
                        this.scp.sendMessage("<w>");
                        System.out.println("SCP");
                    }else{
                        this.town = new ClientHandler(client);
                        this.townThread = new Thread(this.scp);
                        this.townThread.start();
                        this.town.sendMessage("<w>");
                        System.out.println("Town");
                    }
                }else if(this.scp == null){
                    //assigns scp
                    this.scp = new ClientHandler(client);
                    this.scpThread = new Thread(this.scp);
                    this.scpThread.start();
                    startGame();
                    System.out.println("SCP");
                }else{
                    //assigns town
                    this.town = new ClientHandler(client);
                    this.townThread = new Thread(this.scp);
                    this.townThread.start();
                    startGame();
                    System.out.println("Town");
                }
            }
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
                            boolean success = false;
                            String buildingType = input.readLine();
                            String coords = input.readLine();
                            int x = Integer.parseInt(coords.split(" ")[0]);
                            int y = Integer.parseInt(coords.split(" ")[1]);
                            success = buildBuilding(buildingType, x, y);
                            reportTransactionStatus(success);
                        }else if(prefix.equals("<e>")){
                            boolean success = false;
                            String eventType = input.readLine();
                            int level = Integer.parseInt(input.readLine());
                            if(!isWholeGameEvent(eventType)){
                                String coords = input.readLine();
                                int x = Integer.parseInt(coords.split(" ")[0]);
                                int y = Integer.parseInt(coords.split(" ")[1]);
                                success = createEvent(eventType, level, x, y);
                            }else{
                                success = createEvent(eventType, level);
                            }
                            reportTransactionStatus(success);
                        }else if(prefix.equals("<u>")){
                            boolean success = false;
                            String coords = input.readLine();
                            int x = Integer.parseInt(coords.split(" ")[0]);
                            int y = Integer.parseInt(coords.split(" ")[1]);
                            success = upgrade(x, y);
                            reportTransactionStatus(success);
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
         * Sends a message to the client about whether or not a certain task was sucessful
         * @param success if the task was successful or not
         */
        private void reportTransactionStatus(boolean success){
            if(success){
                sendMessage("<st>");
            }else{
                sendMessage("<f>");
            }
        }

        /**
         * Checks for if the type of event is a whole game event
         * @param eventType the string for the type of event
         * @return true if it's a whole game event, false otherwise
         */
        private boolean isWholeGameEvent(String eventType){
            return eventType.equals("Stonks") || eventType.equals("Riot") || eventType.equals("Mutate") || eventType.equals("WarpReality");
        }

        /**
         * <p>
         * Tries to create the {@code Building} based on the type. If the building got created add it to the game's master list of 
         * {@code Building} objects. Send a message to the client to show how much of the resource got spent. return true if 
         * successful.
         * </p>
         * @param type the type of {@code Building}
         * @param x top left x coordinate 
         * @param y top left y coordinate
         * @return boolean sucess depending on if it worked or not
         */
        private boolean buildBuilding(String type, int x, int y){
            boolean success = false;
            int price = Integer.MAX_VALUE;
            Building building = null;
            if(type.equals("Bank")){
                building = new Bank(Bank.INITIAL_PRICE, Bank.INITIAL_HEALTH, Bank.INITIAL_HEALTH, x, y);
                price = Bank.INITIAL_PRICE;
            }else if(type.equals("FoodBuilding")){
                building = new FoodBuilding(FoodBuilding.INITIAL_PRICE, FoodBuilding.INITIAL_HEALTH, FoodBuilding.INITIAL_HEALTH, x, y);
                price = FoodBuilding.INITIAL_PRICE;
            }else if(type.equals("Hospital")){
                building = new Hospital(Hospital.INITIAL_PRICE, Hospital.INITIAL_HEALTH, Hospital.INITIAL_HEALTH, x, y, Hospital.INITIAL_MAX_CAP);
                price = Hospital.INITIAL_PRICE;
            }else if(type.equals("MilitaryBase")){
                building = new MilitaryBase(MilitaryBase.INITIAL_PRICE, MilitaryBase.INITIAL_HEALTH, MilitaryBase.INITIAL_HEALTH, x, y);
                price = MilitaryBase.INITIAL_PRICE;
            }else if(type.equals("ResearchLab")){
                building = new ResearchLab(ResearchLab.INITIAL_PRICE, ResearchLab.INITIAL_HEALTH, ResearchLab.INITIAL_HEALTH, x, y);
                price = ResearchLab.INITIAL_PRICE;
            }else if(type.equals("Residency")){
                building = new Residency(Residency.INITIAL_PRICE, Residency.INITIAL_HEALTH, Residency.INITIAL_HEALTH, x, y, Residency.INITIAL_MAX_CAP);
                price = Residency.INITIAL_PRICE;
            }
            if((building != null) && (game.getMoney() >= price)){
                game.addBuilding(building);
                success = true;
                sendMessage("<r>");
                sendMessage("Money");
                sendMessage("" + (price * -1));
            }
            return success;
        }

        /**
         * <p>
         * Creates an {@code Event} based on the type if the player has sufficient funds and the data given is not incorrect. 
         * Returns true if successfully created, false otherwise. {@code Stonks} does not get created by the user and functions 
         * differently.
         * </p>
         * @param type the type of {@code Event}
         * @param level the level of the {@code Event}
         * @param x the x coordinates for the middle of the {@code Event}
         * @param y the y coordinates for the middle of the {@code Event}
         * @return boolean success, if the creation was successful or not
         */
        private boolean createEvent(String type, int level, int x, int y){
            boolean success = false;
            Event event = null;
            int price = Integer.MAX_VALUE;
            if(type.equals("Earthquake")){
                event = new Earthquake(level, x, y);
                price = Earthquake.getCostByLevel(level);
            }else if(type.equals("Fire")){
                event = new Fire(level, x, y);
                price = Fire.getCostByLevel(level);
            }else if(type.equals("Infect")){
                event = new Infect(level, x, y);
                price = Infect.getCostByLevel(level);
            }else if(type.equals("Thunderstorm")){
                event = new Thunderstorm(level, x, y);
                price = Thunderstorm.getCostByLevel(level);
            }else if(type.equals("Tornado")){
                event = new Tornado(level, x, y);
                price = Tornado.getCostByLevel(level);
            }
            if((event != null) && (game.getHume() >= price)){
                game.startEvent(event);
                sendMessage("<r>");
                sendMessage("Hume");
                sendMessage("" + (price * -1));
            }
            return success;
        }

        /**
         * <p>
         * Creates an {@code Event} based on the type if the player has sufficient funds and the data given is not incorrect. 
         * Returns true if successfully created, false otherwise. {@code Stonks} does not get created by the user and functions 
         * differently.
         * </p>
         * @param type the type of event to create
         * @return boolean success, if the event succeeded
         */
        private boolean createEvent(String type, int level){
            boolean success = false;
            Event event = null;
            int price = Integer.MAX_VALUE;
            if(type.equals("Stonks")){
                game.startEvent(new Stonks(level));
                return true;
            }else if(type.equals("Riot")){
                event = new Riot(level);
                price = Riot.getCostByLevel(level);
            }else if(type.equals("Mutate")){
                event = new Mutate(level);
                price = Mutate.getCostByLevel(level);
            }else if(type.equals("WarpReality")){
                event = new WarpReality(level);
                price = WarpReality.getCostByLevel(level);
            }
            if((event != null) && (game.getHume() >= price)){
                game.startEvent(event);
                sendMessage("<r>");
                sendMessage("Hume");
                sendMessage("" + (price * -1));
            }
            return success;
        }

        /**
         * <p>
         * Loops through all {@code Buildings} in the game. If the coordinates of the {@code Building} match the coordinates passed 
         * in as parameters, check if the player has sufficient funds. If so, upgrade. Return success in the end for whether or not 
         * the operation was a success.
         * </p>
         * @param x the x coordinate of the {@code Building} to upgrade
         * @param y the y coordinate of the {@code Building} to upgrade
         * @return boolean success, whether or not the operation was a success
         */
        private boolean upgrade(int x, int y){
            boolean success = false;
            ArrayList<Building> buildings = game.getBuildings();
            for(Building building:buildings){
                if((building.getX() == x) && (building.getY() == y)){
                    
                    if(building.getUpgradePrice() <= game.getMoney()){
                        building.upgrade();
                        success = true;
                    }
                }
            }
            return success;
        }
    }

    /**
     * [GameHandler.java]
     * Will update turns for the game
     * @author Damon Ma, Edward Yang, Vivian Dai
     * @version 1.0 on January 9, 2021
     */
    private class GameHandler implements Runnable{
        /** one minute but in milliseconds */
        private static final int ONE_MINUTE = 60000;
        /** last update time */
        private long lastTime;
        /** current system time */
        private long curTime;
        /** boolean for if a turn is ongoing or waiting for next turn */
        private boolean turnGoing;
        /** All users in the game */
        private ClientHandler[] allUsers;

        /** 
         * Constructor for the {@code GameHandler} class
         */
        GameHandler(){
            this.turnGoing = false;
            this.allUsers = new ClientHandler[]{scp, town};
        }

        /**
         * Sends a message to a specific {@code ClientHandlers}
         * @param ch the array of {@code ClientHandlers} to send to
         * @param msg the message to send
         */
        private void sendTo(ClientHandler[] ch, String msg){
            for(int i = 0;i < ch.length;i++){
                ch[i].sendMessage(msg);
            }
        }

        /**
         * <p>
         * Loop while still running. If there is currently a turn going on and it's time to update (end turn), the game will 
         * handle the end of turn events. A message will then be sent to the scp side and the town side on new updates for both 
         * sides. The boolean for turn will then flip. If the turn is not going and it's time to update (start turn) a message 
         * will be sent to both sides to start a new turn.
         * </p>
         */
        public void run(){
            while(running){
                curTime = System.currentTimeMillis();
                if(turnGoing){
                    if(curTime - lastTime >= ONE_MINUTE){
                        lastTime = curTime;
                        game.doTurn();
                        sendTo(allUsers, "<te>");
                        //scps
                        sendTo(allUsers, "" + SCP0492.level);
                        sendTo(allUsers, "" + game.getScps().size());
                        for(int i = 0;i < game.getScps().size();i++){
                            SCP0492 curScp = game.getScps().get(i);
                            sendTo(allUsers, curScp.getHealth() + " " + curScp.getMaxHealth() + " " + curScp.getX() + " " + curScp.getY() + " " + curScp.getAttackDamage());
                        }

                        //events
                        sendTo(allUsers, "" + game.getEvents().size());
                        for(int i = 0;i < game.getEvents().size();i++){
                            Event curEvent = game.getEvents().get(i);
                            if(curEvent instanceof WholeGameEvent){
                                sendTo(allUsers, curEvent.getClass().getSimpleName() + " " + curEvent.getLevel() + " " + curEvent.getTimeLeft());
                            }else{
                                sendTo(allUsers, curEvent.getClass().getSimpleName() + " " + curEvent.getLevel() + " " + curEvent.getTimeLeft() + " " + (int)(((AoeEvent)curEvent).getAoe().getX()) + " " + (int)(((AoeEvent)curEvent).getAoe().getY()) + " " + (int)(((AoeEvent)curEvent).getAoe().getWidth())  + " " + (int)(((AoeEvent)curEvent).getAoe().getHeight()));
                            }
                        }

                        //buildings
                        sendTo(allUsers, "" + game.getBuildings().size());
                        for(int i = 0;i < game.getBuildings().size();i++){
                            Building curBuilding = game.getBuildings().get(i);
                            sendTo(allUsers, curBuilding.getClass().getSimpleName() + " " + curBuilding.getMaxHealth() + " " + curBuilding.getHealth() + " " + curBuilding.getX() + " " + curBuilding.getY());
                        }

                        //humans
                        sendTo(allUsers, "" + game.getHumans().size());
                        for(int i = 0;i < game.getHumans().size();i++){
                            Human curHuman = game.getHumans().get(i);
                            sendTo(allUsers, curHuman.getClass().getSimpleName() + " " + curHuman.getAge() + " " + curHuman.getHealth() + " " + curHuman.getMaxHealth() + " " + curHuman.getX() + " " + curHuman.getY());
                        }

                        //town food supply
                        town.sendMessage("<r>");
                        town.sendMessage("Food");
                        if(game.getFood() > game.getHumans().size()){
                            town.sendMessage("" + game.getHumans().size());
                        }
                        
                        //intel gathering
                        if(game.gotIntel()){
                            town.sendMessage("<i>");
                            town.sendMessage("" + game.getHume());
                        }

                        turnGoing = !turnGoing;
                    }
                }else{
                    if(curTime - lastTime >= ONE_MINUTE/2){
                        lastTime = curTime;
                        sendTo(allUsers, "<ts>");
                        turnGoing = !turnGoing;
                    }
                }
            }
        }
    }
}
