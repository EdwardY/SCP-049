import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
public class TempReportWindows {
    public abstract class ReportWindow{
        /**The JFrame of the window. */
        private JFrame window;
        /**The JPanel in the window. */
        private JPanel mainPanel;
        /**The Textbox the report will be written in. */
        private JTextArea reportBox;

        /**
         * Constructor for the report window.
         */
        public ReportWindow(){
            this.window = new JFrame("End of turn report:");
            this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.window.setSize(500, 900);
            this.mainPanel = new JPanel();
            this.mainPanel.setBounds(10, 10, 450, 850);
            this.window.add(mainPanel);
            this.reportBox = new JTextArea();
            this.reportBox.setLineWrap(true);
            this.reportBox.setEditable(false);
            this.reportBox.setBounds(5, 0, 445, 845);
            this.window.add(reportBox);
            this.window.setVisible(true);

        }//end of constructor


        public JFrame getWindow(){
            return this.window;
        }

        public JPanel getMainPanel(){
            return this.mainPanel;
        }

        /**
         * Gets the textbox for the end of turn report
         * @return
         */
        public JTextArea getReportBox(){
            return this.reportBox;
        }



    }//end of class



    public class SCPReportWindow extends ReportWindow{

        /**
         * Generates and displays and end-of-turn report to the player
         * @param scps The list of the player's SCP0492 NPCs.
         * @param events The list of SCP events.
         * @param hume The amount of hume points (SCP currency) that the player has at the start of the turn.
         */
        public void generateReport(ArrayList<SCP0492> scps, ArrayList<Event> events, int hume){
            //the total amount of SCP game objects
            int scpNum = scps.size();
            int eventNum = events.size();


            //used to count the amount of each event
            int infectCounter = 0;
            int earthquakeCounter = 0;
            int fireCounter = 0;
            int mutateCounter = 0;
            int riotCounter = 0;
            int thunderstormCounter = 0;
            int tornadoCounter = 0;
            int warpRealityCounter = 0;


            //count the amount of each event
            for(int i = 0; i < events.size(); i ++){
                if(events.get(i) instanceof Infect){
                    infectCounter ++;
                }else if (events.get(i) instanceof Earthquake){
                    earthquakeCounter++;
                }else if (events.get(i) instanceof Fire){
                    fireCounter ++;
                }else if(events.get(i) instanceof Mutate){
                    mutateCounter++;
                }else if(events.get(i) instanceof Riot){
                    riotCounter++;
                }else if (events.get(i) instanceof Thunderstorm){
                    thunderstormCounter++;
                }else if(events.get(i) instanceof Tornado){
                    tornadoCounter++;
                }else if(events.get(i) instanceof WarpReality){
                    warpRealityCounter++;
                }//end of block if statements

            }//end of for loop

            String report = "Hume points: " + hume + "\nTotal SCP-049-2: " + scpNum + "\nTotal events: " + eventNum + "\n\nAll events:";
            report += "\n\nEarthquakes: " + earthquakeCounter + "\nFires: " + fireCounter + "\nInfections: " + infectCounter + "\nMutations: " + mutateCounter + "\nRiots: " + riotCounter;
            report += "\nThunderstorms: " + thunderstormCounter + "\nTornadoes: " + tornadoCounter + "Warped Realities: " + warpRealityCounter; 

            //update the report window
            this.getReportBox().setText(report);
            this.getReportBox().repaint();
            this.getReportBox().revalidate();

            this.getWindow().repaint();


        }//end of method

    }//end of class


    public class TownReportWindow extends ReportWindow{
        /**
         * Generates and displays and end-of-turn report to the player
         * @param humans The list of the player's human NPCs.
         * @param buildings The list of buildings.
         * @param duberCoins The amount of duberCoins (Town currency) that the player has at the start of the turn.
         */
        public void generateReport(ArrayList<Human> humans, ArrayList<Building> buildings, int duberCoins){
            //the total amount of SCP game objects
            int humanNum = humans.size();
            int buildingNum = buildings.size();


            //used to count the amount of each human NPC
            int cadetCounter = 0;
            int citizenCounter = 0;
            int doctorCounter = 0;
            int researcherCounter = 0;
            int soldierCounter = 0;
            int spyCounter = 0;




            //used to count the amount of buildings
            int bankCounter = 0;
            int foodBuildingCounter = 0;
            int hospitalCounter = 0;
            int militaryBaseCounter = 0;
            int researchLabCounter = 0;
            int residencyCounter = 0;


            //count the amount of each human NPC
            for(int i = 0; i < humans.size(); i ++){
                if(humans.get(i) instanceof Citizen){
                    citizenCounter ++;
                }else if (humans.get(i) instanceof Cadet){
                    cadetCounter++;
                }else if (humans.get(i) instanceof Doctor){
                    doctorCounter ++;
                }else if(humans.get(i) instanceof Researcher){
                    researcherCounter++;
                }else if(humans.get(i) instanceof Soldier){
                    soldierCounter++;
                }else if (humans.get(i) instanceof Spy){
                    spyCounter++;
                }//end of block if statements

            }//end of for loop


            //count the amount of each human NPC
            for(int i = 0; i < buildings.size(); i ++){
                if(buildings.get(i) instanceof Bank){
                    bankCounter ++;
                }else if (buildings.get(i) instanceof FoodBuilding){
                    foodBuildingCounter++;
                }else if (buildings.get(i) instanceof Hospital){
                    hospitalCounter ++;
                }else if(buildings.get(i) instanceof MilitaryBase){
                    militaryBaseCounter++;
                }else if(buildings.get(i) instanceof ResearchLab){
                    researchLabCounter++;
                }else if (buildings.get(i) instanceof Residency){
                    residencyCounter++;
                }//end of block if statements
            
            }//end of for loop

            String report = "DuberCoins: " + duberCoins + "\nTotal population: " + humanNum + "\nBuildings: " + buildingNum + "\n\nAll humans:";
            report += "\n\nCitizens: " + citizenCounter + "\nCadets: " + cadetCounter + "\nDoctors: " + doctorCounter + "\nResearchers: " + researcherCounter;
            report += "\nSoldiers: " + soldierCounter + "\nSpies: " + spyCounter + "\n\nAll Buildings:\n\nBanks: " + bankCounter + "\nFood Buildings: " + foodBuildingCounter;
            report += "\nHospitals: " + hospitalCounter + "\nMilitary Bases: " + militaryBaseCounter + "\nResearch Labs: " + researchLabCounter  + "Reseidencies: " + residencyCounter;

            //update the report window
            this.getReportBox().setText(report);
            this.getReportBox().repaint();
            this.getReportBox().revalidate();

            this.getWindow().repaint();


        }//end of method

    }//end of class
    

}
