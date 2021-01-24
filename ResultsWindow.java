import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;

/**
 * [ResultsWindow.java]
 * A window the opens up at the end of the game to tell them if they won or lost.
 * @author Vivian Dai, Damon Ma, and Edward Yang.
 * @version 1.0 on January 18 2021.
 */
public class ResultsWindow {
    /**The JFrame of the ending window. */
    private JFrame window;
    /**The JPanel of the ending window. */
    private JPanel mainPanel;
    /**The textbox that will display the end-of-game message. */
    private JTextArea resultsBox;


    /**
     * Constructor for the end-of-game window.
     */
    public ResultsWindow(){
        this.window = new JFrame("Thanks for Playing!");
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(500, 500);
        this.window.setBackground(Color.WHITE);
        this.mainPanel = new JPanel();
        this.mainPanel.setBackground(Color.WHITE);
        this.mainPanel.setBounds(10, 10, 450, 450);
        this.mainPanel.setLayout(null);
        this.window.add(mainPanel);
        this.resultsBox = new JTextArea();
        this.resultsBox.setLineWrap(true);
        this.resultsBox.setEditable(false);
        this.resultsBox.setBounds(5, 0, 445, 445);
        this.mainPanel.add(resultsBox);
        this.window.setVisible(true);
    }

    /**
     * Shows the victory, defeat, or tie message to the user.
     * @param victory Determines if the user has won, lost, or tied against the other player.
     * @param isTown String used to identify which side the player is on.
     */
    public void getResults(String victory, boolean isTown){
        String result = "";


        if(victory.equals("win")){
            result += "Congratulations! You won!\n\n";

            if(isTown){
                result += "You successfully defended the town and everyone is safe!";
            }else{
                result += "You successfully invaded the town!";
            }
        }else if(victory.equals("lose")){
            result += "Bitter defeat. Better luck next time...\n\n";

            if(isTown){
                result += "The SCP's invaded your town and took over!";
            }else{
                result += "The town developed a cure and saved themselves from your invasion!";
            }

        }else if(victory.equals("tie")){
            result+= "Stalemate, an eternal war is on its way.\n\nNeither side could beat the other this game.";
        }


        //update the window
        this.resultsBox.setText(result);
        this.resultsBox.repaint();
        this.resultsBox.revalidate();
        this.window.repaint();

    }//end of method



}
