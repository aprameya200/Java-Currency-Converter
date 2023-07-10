
/* 
* 	Application Programming Assesment Part 2
*
* 	Name: Aprameya Neopane
*	Roll Number: 77261139	
*	Section: E 
*	Level 5
*
*	CurrencyPanel.java
*/

// importing java.io package to handle file input
import java.io.*;

import javax.swing.JOptionPane;

public class Currency {

    // Declaring the neccesary variables for the class

    public static String singleLettters[] = new String[1];
    public static String currencyName[] = new String[1];
    public static double currencyFactor[] = new double[1];
    public static String currencySymbols[] = new String[1];

    boolean error1 = false;
    boolean error2 = false;
    boolean error3 = false;

    public int count = 0;
    public int sizeofarray = 1;

    public int currencyCount = 0;

    public int sizeOfCurrencyArray = 1;

    public int factorCount = 0;
    public int sizeOfFactorArray = 1;

    public int symbolCount = 0;
    public int sizeOfSymbolArray = 1;

    String allFromFile[] = new String[1];

    // getter and setter methods for the above created variables

    // getter method for singleLetters
    public static String[] getSingleLetters() {
        return singleLettters;
    }

    // getter method for currencyName
    public static String[] getCurrencyName() {
        return currencyName;
    }

    // setter method for currencyName
    public static void setCurrencyName(String[] currencyName) {
        Currency.currencyName = currencyName;
    }

    // getter method for currencyFactors
    public static double[] getCurrencyFactor() {
        return currencyFactor;
    }

    // setter method for currencyFactor
    public static void setCurrencyFactor(double[] currencyFactor) {
        Currency.currencyFactor = currencyFactor;
    }

    // getter method for currencysymbol
    public static String[] getCurrencySymbols() {
        return currencySymbols;
    }

    // setter method for currencysymbol
    public static void setCurrencySymbols(String[] currencySymbols) {
        Currency.currencySymbols = currencySymbols;
    }

    // Method to add item into singleLettters[] array
    public void addElement(String a) {
        if (count == sizeofarray) {
            growSize();
        }
        singleLettters[count] = a;
        count++;
    }

    // Method to grow size of singleLettters[] array
    public void growSize() {
        String temp[] = null;
        if (count == sizeofarray) {
            temp = new String[sizeofarray * 2];
            {
                for (int i = 0; i < sizeofarray; i++) {
                    temp[i] = singleLettters[i];
                }
            }
        }
        singleLettters = temp;
        sizeofarray = sizeofarray * 2;
    }

    // Method to add item into currencyName[] array
    public void addElementToCurrency(String a) {
        if (currencyCount == sizeOfCurrencyArray) {
            growCurrencySize();
        }
        currencyName[currencyCount] = a;
        currencyCount++;
    }

    // Method to grow size of currencyName[] array
    public void growCurrencySize() {
        String temp[] = null;
        if (currencyCount == sizeOfCurrencyArray) {
            temp = new String[sizeOfCurrencyArray * 2];
            {
                for (int i = 0; i < sizeOfCurrencyArray; i++) {
                    temp[i] = currencyName[i];
                }
            }
        }
        currencyName = temp;
        sizeOfCurrencyArray = sizeOfCurrencyArray * 2;
    }

    // Method to add item into currencyFactor[] array
    public void addElementToFactor(double a) {
        if (factorCount == sizeOfFactorArray) {
            growFactorSize();
        }
        currencyFactor[factorCount] = a;
        factorCount++;
    }

    // Method to grow size of currencyFactor[] array
    public void growFactorSize() {
        double temp[] = null;
        if (factorCount == sizeOfFactorArray) {
            temp = new double[sizeOfFactorArray * 2];
            {
                for (int i = 0; i < sizeOfFactorArray; i++) {
                    temp[i] = currencyFactor[i];
                }
            }
        }
        currencyFactor = temp;
        sizeOfFactorArray = sizeOfFactorArray * 2;
    }

    // Method to add item into currencySymbols[] array
    public void addElementToSymbol(String a) {
        if (symbolCount == sizeOfSymbolArray) {
            growSymbolSize();
        }
        currencySymbols[symbolCount] = a;
        symbolCount++;
    }

    // Method to grow size of currencySymbols[] array
    public void growSymbolSize() {
        String temp[] = null;
        if (symbolCount == sizeOfSymbolArray) {
            temp = new String[sizeOfSymbolArray * 2];
            {
                for (int i = 0; i < sizeOfSymbolArray; i++) {
                    temp[i] = currencySymbols[i];
                }
            }
        }
        currencySymbols = temp;
        sizeOfSymbolArray = sizeOfSymbolArray * 2;
    }

    // Constructor to load default text file (currency.txt) into the application
    Currency() throws IOException {

        /*
         * Reference variabel of BufferedReader class
         * that is used to read from text file
         */
        BufferedReader readFile = null;

        // try-catch block to catch error if file is not found
        try {
            readFile = new BufferedReader(
                    new InputStreamReader(new FileInputStream("Converter/currency.txt"), "UTF8"));
        } catch (FileNotFoundException error) {
            JOptionPane.showMessageDialog(null, "File not found");
        }

        int characters;
        char values;

        // read from FileReader till the end of file
        while ((characters = readFile.read()) != -1) {
            values = (char) characters;
            addElement(String.valueOf(values));
        }

        // int variables to count index of allFromFile[] array
        int fileCounter = 1, indexCounter = 0;

        allFromFile[0] = singleLettters[0];

        /*
         * converting single characters from singleLettters[] as
         * words in allfromFile[] array
         */
        while (fileCounter < singleLettters.length) {
            allFromFile[indexCounter] = allFromFile[indexCounter] + singleLettters[fileCounter];
            fileCounter++;
        }

        // Spliting allFromFile[] array
        String[] splitInput = allFromFile[0].replace("\n", ",").split(",");

        /*
         * Saving elements of splitInput[] into 3 seperate arrays according to Currency
         * name,currency factor and their symbols
         */
        for (int k = 0; k < splitInput.length; k++) {

            if (k == 0 || k % 3 == 0) {
                addElementToCurrency(splitInput[k]);
            } else if (k == 1 || k % 3 == 1) {
                addElementToFactor(Double.parseDouble(splitInput[k]));
            } else if (k == 2 || k % 3 == 2) {
                addElementToSymbol(splitInput[k]);
            }
        }

        // closing the file
        readFile.close();
    }

    /*
     * Constructor to load file selected by the user through Load option in the menu
     */
    Currency(File def) throws IOException {

        /*
         * Reference variabel of BufferedReader class
         * that is used to read from text file
         */
        BufferedReader readFile = null;

        if (def == null) {
            def = new File("currency.txt");
        }

        // try-catch block to catch error if file is not found
        try {
            readFile = new BufferedReader(
                    new InputStreamReader(new FileInputStream(def), "UTF8"));
        } catch (FileNotFoundException error) {
            JOptionPane.showMessageDialog(null, "File not found");
        }

        int characters;
        char values;
        // read from FileReader till the end of file
        while ((characters = readFile.read()) != -1) {
            values = (char) characters;
            addElement(String.valueOf(values));

        }

        // int variables to count index of allFromFile[] array
        int fileCounter = 1, indexCounter = 0;

        allFromFile[0] = singleLettters[0];

        /*
         * converting single characters from singleLettters[] as
         * words in allfromFile[] array
         */
        while (fileCounter < singleLettters.length) {

            allFromFile[indexCounter] = allFromFile[indexCounter] + singleLettters[fileCounter];

            fileCounter++;

        }

        // Spliting allFromFile[] array
        String[] splitInput = allFromFile[0].replace("\n", ",").split(",");

        /*
         * Saving elements of splitInput[] into 3 seperate arrays according to Currency
         * name,currency factor and their symbols
         */
        for (int k = 0; k < splitInput.length; k++) {

            if (splitInput[k].matches("[a-zA-Z()\\s]+") && splitInput[k].length() > 3) {
                addElementToCurrency(splitInput[k].trim());
                error1 = true;
            }

            if (splitInput[k].matches("[0-9.\\s]+")) {
                addElementToFactor(Double.parseDouble(splitInput[k]));
                error2 = true;
            }

            if (splitInput[k].matches("[A-Z$[^\\x00-\\x7F]+\\r\\s]+")) {
                addElementToSymbol(splitInput[k].trim());
                error3 = true;
            }

        }

        // closing the file
        readFile.close();
    }

}

// end of file