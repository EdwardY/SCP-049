
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * [ReportWindow.java]
 * An abstract class for an end-of-turn report window.
 * @author Vivian Dai, Damon Ma, and Edward Yang
 * @version 1.0 on January 18, 2021.
 */
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
