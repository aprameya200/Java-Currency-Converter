/* 
* 	Application Programming Assesment Part 2
*
* 	Name: Aprameya Neopane
*	Roll Number: 77261139	
*	Section: E 
*	Level 5

	CurrencyPanel.java
*/

// importing java.text.DecimalFormat class to perform rounding off 
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.Flow;
/*
 	importing awt(Abstract Window Toolkit) package to 
	use Color and Dimension classes 
*/
import java.awt.Color;
import java.awt.Dimension;

/* 
	importing awt.event package to use 
	the following classes and interfaces
*/
import java.awt.event.*;

/* 
	importing javax.swing package 
	to use the following classes 
	and interfaces
*/
import java.awt.*;
import javax.swing.*;

import java.io.*;
import java.lang.reflect.Array;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Aprameya Neopane
 */

/*
 * CurrencyPanel class inheriting all the methods of the JPanel class
 * This class contains all the main actions to create of our application
 */
public class CurrencyPanel extends JPanel {

	// Declaring all the necessary variables
	private static String[] list = {};

	private JTextField textField;
	private JLabel label, counter, inputLabel, description;
	private JComboBox<String> currencyList;
	private JButton convertButton, Reset;
	private String[] symbols = {};

	private double[] factors = {};

	// variable to check if has been loaded
	public boolean fileLoaded = false;

	// variable to count the number of conversions done by the user
	private int conversionCounter = 0;

	// creating a checkbox that allowes the user to perform reverse conversion
	private JCheckBox reverseConversion;

	public File UserLoaded;

	// creating object of JMenu to add Load item to menu bar
	JMenuItem loadOption = new JMenuItem("Load");

	// Creating menubar for the application
	JMenuBar setupMenu() {

		// creating object of JMenuBar to create menu bar
		JMenuBar menuBar = new JMenuBar();

		// creating object of JMenu to add File item to menu bar
		JMenu fileMenu = new JMenu("File");
		fileMenu.setToolTipText("Select File");

		loadOption.setToolTipText("Load file into application");
		// setting icon for load option in menubar
		loadOption.setIcon(new ImageIcon("icons/load.png"));

		// Mnemonic to load file into application (ALT + L)
		loadOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_MASK));

		// creating object of JMenuItem to add Exit option to File item in menu bar
		JMenuItem exitOption = new JMenuItem("Exit");
		exitOption.setToolTipText("Exit from application");

		// creating object of JMenu to add Help item to menu bar
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setToolTipText("See help options");

		// creating object of JMenuItem to add About option to Help item in menu bar
		JMenuItem aboutOption = new JMenuItem("About");
		aboutOption.setToolTipText("View description of app");

		/*
		 * Creating ExitOption class implementing ActionListener interface
		 * to exit application when we press Exit option in menu bar
		 */
		class ExitOption implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}

		// Adding icon to exit option in menu bar
		exitOption.setIcon(new ImageIcon("icons/exitButton.png"));

		// Mnemonic to exit application (ALT + X)
		exitOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.ALT_MASK));

		// closes the application when we press exit button
		exitOption.addActionListener(new ExitOption());

		// Adding icon to about option in menu bar
		aboutOption.setIcon(new ImageIcon("icons/about.png"));

		// Mnemonic to see about option of application (ALT + A)
		aboutOption.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));

		/*
		 * Creating AboutOption class implementing ActionListener interface
		 * to show about information when about option is chosen
		 */
		class AboutOption implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// popup window opens and shows about information
				JOptionPane.showMessageDialog(null,
						"Welcome to My Currency Converter !\nThis application can "
								+ "perform conversion between British Pounds (GBP) to currencies\n " +
								"that are present in the default text file and the text file loaded by user\n" +
								"Author: Aprameya Neopane\n\u00a9 2021");
			}
		}

		// opens about option popup when it is selected
		aboutOption.addActionListener(new AboutOption());

		fileMenu.add(loadOption);

		// adding exitOption to fileMenu
		fileMenu.add(exitOption);

		// adding aboutOption to helpMenu
		helpMenu.add(aboutOption);

		// adding fileMenu to the main panel (JPanel)
		menuBar.add(fileMenu);

		// adding helpMenu to the main panel (JPanel)
		menuBar.add(helpMenu);

		return menuBar;
	}

	// constructor of class CurrencyPanel
	CurrencyPanel() throws IOException {

		// Reference variable for Object of Currency class
		Currency d = new Currency();

		// saving items of currencyName[] array in lists[] array ussing getter method
		list = d.getCurrencyName();

		// saving items of currencySymbol[] array in symbols[] array ussing getter
		// method
		symbols = d.getCurrencySymbols();

		// saving items of currencyFactors[] array in factors[] array ussing getter
		// method
		factors = d.getCurrencyFactor();

		// creating combobox and adding items of list array
		currencyList = new JComboBox<String>(list);

		/*
		 * Creating LoadOption class implementing ActionListener interface
		 * to load new text file containing other currency informations into the
		 * application
		 */
		class LoadOption implements ActionListener {
			public void actionPerformed(ActionEvent e) {

				// Creating object of JFileChooser to open file chooser option
				JFileChooser fileChooser = new JFileChooser();

				/*
				 * saving the condition of loaded file as option. If the file is not loaded
				 * through JFileChooser then error messege is shown
				 */
				int response = fileChooser.showOpenDialog(null);

				// If we get correct response then new file is loaded
				if (response == JFileChooser.APPROVE_OPTION) {

					// Saving the file chosen by the user to UserLoaded object of File
					UserLoaded = new File(fileChooser.getSelectedFile().getAbsolutePath());

					fileLoaded = true;

					// Creating instance of Currency class
					Currency defaultFile;
					try {
						defaultFile = new Currency(UserLoaded);

						// saving items of currencyName[] array in loadList[] array ussing getter
						// method
						String loadedList[] = defaultFile.getCurrencyName();

						// saving items of currencySymbol[] array in symbols[] array ussing getter
						// method
						symbols = defaultFile.getCurrencySymbols();

						// saving items of currencyfactor[] array in factors[] array ussing getter
						// method
						factors = defaultFile.getCurrencyFactor();

						// removing items from combobox to add new items
						currencyList.removeAllItems();

						// for loop to add new items and remove null values from combobox
						for (int i = 0; i < loadedList.length; i++) {

							currencyList.addItem(loadedList[i]);
							currencyList.removeItem(Collections.singleton(null));

						}

						// if erros are found in the file then the user receives pop up window
						if (defaultFile.error1 && defaultFile.error2 && defaultFile.error3) {

							JOptionPane.showMessageDialog(null,
									"Any errors found in the selected text file have been sorted");
						}
					} catch (Exception errors) {
						JOptionPane.showMessageDialog(null, "Please select valid currency");
					}
				} else {
					JOptionPane.showMessageDialog(null, "File was unable to load");
				}
			}
		}

		// adding action listner to the convert button to perform conversion
		loadOption.addActionListener(new LoadOption());

		// creating instance of ConvertListener created below
		ActionListener listener = new ConvertListener();

		// setting setToolTipText when cursor is hovered
		currencyList.setToolTipText("Change conversion unit");
		currencyList.setFont(new Font("Tahoma", Font.BOLD, 20));

		// creating object of JLabel class showing asking user to enter value
		inputLabel = new JLabel("Enter value: ");
		// setting setToolTipText when cursor is hovered
		inputLabel.setToolTipText("Enter value here");
		inputLabel.setFont(new Font("Tahoma", Font.BOLD, 20));

		// creating object of JButton to create Convert button
		convertButton = new JButton("Convert");
		// setting setToolTipText when cursor is hovered
		convertButton.setToolTipText("Click to enter value");
		convertButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		convertButton.setPreferredSize(new Dimension(200, 50));

		// creating object of JLabel class to output converted value
		label = new JLabel("---");
		// setting setToolTipText when cursor is hovered
		label.setToolTipText("New/Converted value");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));

		// create textfield for user to input number
		textField = new JTextField(5);
		// setting setToolTipText when cursor is hovered
		textField.setToolTipText("Enter value here");
		textField.setFont(new Font("Tahoma", Font.BOLD, 20));

		// create textfield for user to input number
		description = new JLabel("Converting from British Pounds (GBP)");
		// setting setToolTipText when cursor is hovered
		description.setFont(new Font("Tahoma", Font.BOLD, 20));

		/*
		 * using addKeyListener() method to create KeyEvent
		 * when click Convert user presses return button on
		 * the keyboard
		 */
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// using keypressed() method to trigger Convert button
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					/*
					 * fetches virtual keycode of of the button pressed by the use.r
					 * If it is equal to keycode of RETURN key then Convert action
					 * is triggered
					 */
					convertButton.doClick();
					// clicks convert button when RETURN is pressed in the keyboard
				}
			}
		});

		// creating object of JButton to make the Reset button
		Reset = new JButton("Reset");
		// setting setToolTipText when cursor is hovered
		Reset.setToolTipText("Reset values");
		Reset.setFont(new Font("Tahoma", Font.BOLD, 20));

		// creating object of JLabel output counter number
		counter = new JLabel("Count: " + String.valueOf(conversionCounter));
		// setting setToolTipText when cursor is hovered
		counter.setToolTipText("Times conversion has been performed");
		counter.setFont(new Font("Tahoma", Font.BOLD, 20));

		/*
		 * creating object of JCheckBox to create
		 * checkbox to perform reverse conversion
		 */
		reverseConversion = new JCheckBox("Reverse Conversion");
		// setting background of checkbox same as main panel
		reverseConversion.setBackground((Color.decode("#bad1ab")));
		// setting setToolTipText when cursor is hovered
		reverseConversion.setToolTipText("Reverse conversion units");
		reverseConversion.setFont(new Font("Tahoma", Font.BOLD, 20));

		// adding action listner to the convert button to perform conversion
		convertButton.addActionListener(listener); // convert values when pressed

		// adding action listner to the Reset button to Reset input textfield and
		// counter
		Reset.addActionListener(listener);
		currencyList.addActionListener(listener); // convert values when option

		GridLayout experimentLayout = new GridLayout(0, 1, 10, 10);

		setLayout(experimentLayout);

		// adding currencyList to the main panel (JPanel)
		add(currencyList);

		// adding inputLabel to the main panel (JPanel)
		add(inputLabel);

		// adding textField to the main panel (JPanel)
		add(textField);

		// adding convertButton to the main panel (JPanel)
		add(convertButton);

		// adding Reset to the main panel (JPanel)
		add(Reset);

		// adding label to the main panel (JPanel)
		add(label);

		// adding reverseConversion to the main panel (JPanel)
		add(reverseConversion);

		// adding counter to the main panel (JPanel)
		add(counter);

		// adding conversion description to the main panel (JPanel)
		add(description);

		// setting size of JPanel
		setMaximumSize(new Dimension(700, 700));

		// setting background color of the application to sky blue
		setBackground(Color.decode("#bad1ab"));

	}

	// ConvertListener class that implements ActionListener interface
	private class ConvertListener implements ActionListener {

		// using actionPerformed() method of ActionListener to create click event
		@Override
		public void actionPerformed(ActionEvent clickEvent) {

			Object obj = clickEvent.getSource();
			/*
			 * creating an object that saves the
			 * return value the object on which the event occurred
			 */

			// string to store the rounded off value upto 2 decimal places
			String roundOffValues;

			// using try-catch statement
			try {
				// if obj i.e clickEvent is equal to the convertButton object
				if (obj == convertButton) {
					// stored value entered by the user
					String text = textField.getText();

					// if the user has enterd a value
					if (text.isEmpty() == false) {

						// double vaiable to save the result
						double result;
						String symbol = "GBP";
						boolean descriptionTake = true;

						// double variable to store the value entered by the user
						double value = Double.parseDouble(text);

						// factor applied during the conversion
						double factor = 0;

						int index = currencyList.getSelectedIndex();

						factor = factors[index];
						symbol = symbols[index];

						if (reverseConversion.isSelected()) {

							/*
							 * if reverse conversion checkbox is selected
							 * then this formula is used
							 */
							// inputLabel.setText("Enter value in " + list[currencyList.getSelectedIndex()]
							// + " " + symbols[currencyList.getSelectedIndex()]);
							result = value / factor;
							symbol = "\u00A3";
							descriptionTake = false;

						} else {
							/*
							 * if reverse conversion checkbox is not selected
							 * then this formula is used
							 */
							result = factor * value;
							descriptionTake = true;
							// inputLabel.setText("Enter value in British Pounds (GBP): \u00A3");

						}

						// storing the result value upto two decimal places
						String roundOffValue = new DecimalFormat("0.00").format(result);

						String currencyValue = symbol + " " + roundOffValue;

						// increamenting conversion counter
						conversionCounter++;

						// setting the new value of the counter
						counter.setText("Counter: " + conversionCounter);

						// setting the label to converted value
						label.setText(currencyValue);

						if (descriptionTake) {

							if (list[index].equals("United Arab Emirates Dirham (AED)")) {
								description.setText(
										"£" + value + " is equal to "
												+ "د.إ" +
												roundOffValue);
							} else {
								description.setText("\u00A3" + value + " is equal to " + currencyValue);

							}
						}

						else {
							if (list[index].equals("United Arab Emirates Dirham (AED)")) {
								description.setText(
										currencyValue + " is equal to " + "د.إ" +
												value);
							} else {
								description.setText(
										symbols[currencyList.getSelectedIndex()] + value + " is equal to " +
												currencyValue);
							}

						}

					} else if (text.isEmpty() == true) {
						// if the user does not input a value
						JOptionPane.showMessageDialog(null, "Please enter a number");
						// popup window opens to tell message to the user
					}
				}
				if (obj == Reset) {
					// if the user clicks Reset then we set all the values to null or zero
					textField.setText(null);
					label.setText(null);
					conversionCounter = 0;

					currencyList.setSelectedItem(list[0]);

					description.setText("Converting from British Pounds (GBP)");

					counter.setText("Counter: " + String.valueOf(conversionCounter));

					reverseConversion.setSelected(false); // deselects the checkbox
					// currencyList.s
				}
			} catch (NumberFormatException nonNumeric) {
				// if the user enters non numeric value
				JOptionPane.showMessageDialog(null, "Please enter numbers only");
				// popup window opens to tell message to the user

				textField.setText(null);
				label.setText(null);
				// we set all the values to null or zero
			} catch (Exception ee2) {
				JOptionPane.showMessageDialog(null, "Please select valid currency");
			}

		}
	}

}

// End of program
