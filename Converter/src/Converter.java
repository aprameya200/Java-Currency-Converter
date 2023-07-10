
/* 
* 	Application Programming Assesment Part 2
*
* 	Name: Aprameya Neopane
*	Roll Number: 77261139	
*	Section: E 
*	Level 5

	Converter.java
*/

import java.io.IOException;

import javax.swing.JFrame;

/**
 * The main driver program for the GUI based conversion program.
 * 
 * @author Aprameya Neopane
 */
public class Converter {
    // Driver class for CurrencyPanel class
    public static void main(String[] args) throws IOException { // main method

        // creating JFrame object to add CurrencyPanel to the frame
        JFrame frame = new JFrame("Converter");

        // close the appkication when we press close button ('X' button)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creating an instance of CurrencyPanel
        CurrencyPanel panel = new CurrencyPanel();

        // adding menubar to JFrame
        frame.setJMenuBar(panel.setupMenu());

        // setting size of the frame
        frame.setSize(400, 600);

        // positioning the frame towards the center of the screen
        frame.setLocationRelativeTo(null);

        /*
         * adding the content pane (all content components) from panel
         * object to the frame
         */
        frame.getContentPane().add(panel);

        /*
         * sizing the frame so that all contents are at their preferred sizes.
         */
        frame.pack();

        // setting the visibility of the panel to true
        frame.setVisible(true);
    }
}

// end of program