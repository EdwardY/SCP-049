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
            this.window.setSize(500, 700);
            this.mainPanel = new JPanel();
            this.mainPanel.setBounds(10, 10, 450, 650);
            this.window.add(mainPanel);
            this.reportBox = new JTextArea();
            this.reportBox.setLineWrap(true);
            this.reportBox.setEditable(false);
            this.reportBox.setBounds(5, 0, 445, 645);
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
            report += "\n\nEarthquakes: " + earthquakeCounter + "\nFires: " + fireCounter + "\nMutations: " + mutateCounter + "\nRiots: " + riotCounter;
            report += "\n Thunderstorms: " + thunderstormCounter + "\nTornadoes: " + tornadoCounter + "Warped Realities: " + warpRealityCounter; 

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
            report += "\n\nEarthquakes: " + earthquakeCounter + "\nFires: " + fireCounter + "\nMutations: " + mutateCounter + "\nRiots: " + riotCounter;
            report += "\n Thunderstorms: " + thunderstormCounter + "\nTornadoes: " + tornadoCounter + "Warped Realities: " + warpRealityCounter; 

            //update the report window
            this.getReportBox().setText(report);
            this.getReportBox().repaint();
            this.getReportBox().revalidate();

            this.getWindow().repaint();


        }//end of method

    }//end of class
    }

}
