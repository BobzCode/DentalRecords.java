import java.util.Locale;
import java.util.Scanner;


public class DentalRecords {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int MAX_FAMILY = 5;
    private static final int JAWLINES = 2;
    private static final int MAX_TEETH = 10;

    public static void main(String[] args) {
        int numPeople;
        char menuOption;
        char[][][] dentalRecord = new char[MAX_FAMILY][JAWLINES][MAX_TEETH];
        String[] names = new String[MAX_FAMILY];

        welcomeMessage();
        numPeople = getPeople();
        inputData(dentalRecord, names, numPeople);
        do{
            menuOption = getMenuOption();
            menuChoices(menuOption, dentalRecord, names, numPeople);
        }while (menuOption != 'X');

    }

    // Welcome Message
    private static void welcomeMessage() {
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");
    }

    //Getting how many people
    private static int getPeople(){
        int numPeople ;

        System.out.print("Please enter number of people in the family : ");

        do{
            numPeople = keyboard.nextInt();
            if((numPeople <= 0) || (numPeople > MAX_FAMILY)){
                System.out.print("Invalid number of people, try again         : ");
            }
        } while ((numPeople <= 0) || (numPeople > MAX_FAMILY));

        return numPeople;
    }

    //Gets the string of teeth
    private static void inputData(char[][][] dentalRecord, String[] names, int numPeople) {
        String teeth;

        for (int i = 0; i < numPeople; i++) {
            System.out.printf("Please enter the name for family member %-4d: ", i + 1);
            names[i] = keyboard.next();
            names[i] = names[i].substring(0,1).toUpperCase(Locale.ROOT) + names[i].substring(1);

            for (int j = 0; j < JAWLINES; j++) {
                if (j == 0) {
                    System.out.printf("Please enter the uppers for %-16s: ", names[i]);
                    teeth = keyboard.next().toUpperCase(Locale.ROOT);
                    while (!validTeethType(teeth)) {
                        teeth = keyboard.next().toUpperCase(Locale.ROOT);
                    }
                    for (int k = 0; k < teeth.length(); k++) {
                        dentalRecord[i][j][k] = teeth.charAt(k);
                    }
                } else {
                    System.out.printf("Please enter the lowers for %-16s: ", names[i]);
                    teeth = keyboard.next().toUpperCase(Locale.ROOT);
                    while (!validTeethType(teeth)) {
                        teeth = keyboard.next().toUpperCase(Locale.ROOT);
                    }
                    for (int k = 0; k < teeth.length(); k++) {
                        dentalRecord[i][j][k] = teeth.charAt(k);
                    }
                }
            }
        }
    }

    //This checks to make sure the input for string of teeth makes sense
    private static boolean validTeethType(String teeth) {

        if (teeth.length() > MAX_TEETH) {
            System.out.print("Too many teeth, try again                   : ");
            return false;
        }
        else {
            for (int i = 0; i < teeth.length(); i++) {
                if (teeth.charAt(i) != 'B' && teeth.charAt(i) != 'C' && teeth.charAt(i) != 'M') {
                    System.out.print("Invalid teeth types, try again              : ");
                    return false;
                }
            }
            return true;
        }
    }

    //Presents a Menu and gets their choice
    private static char getMenuOption(){
        char menuOption;
        System.out.print("(P)rint, (E)xtract, (R)oot, e(X)it          : ");
        do{
            menuOption = keyboard.next().toUpperCase(Locale.ROOT).charAt(0);
            if (menuOption != 'P' && menuOption != 'E' && menuOption != 'R' && menuOption != 'X'){
                System.out.print("Invalid menu option, try again              : ");
            }
        }while(menuOption != 'P' && menuOption != 'E' && menuOption != 'R' && menuOption != 'X');

        return menuOption;
    }

    //The Menu of Methods
    private static void menuChoices(char menuOption, char[][][] dentalRecord, String[] names, int numPeople){
        switch (menuOption) {
            case 'P':
                printOption(dentalRecord, names, numPeople);
                break;
            case 'E':
                extractTeeth(dentalRecord, names, numPeople);
                break;
            case 'R':
                findRoots(dentalRecord, numPeople);
                break;
            case 'X':
                System.out.print("Exiting the Floridian Tooth Records :-)");
                break;
            default:
                System.out.print("How did this happen?");
        }
    }

    //Printing the inputs, goes through each index with some nice formatting
    private static void printOption(char[][][] dentalRecord, String[] names, int numPeople){

        for (int i = 0; i < numPeople; i++) {
            System.out.println(names[i]);
            for (int j = 0; j < JAWLINES; j++) {
                if (j ==0){
                    System.out.print("Uppers:");
                }
                else{
                    System.out.print("Lowers:");
                }
                for (int k = 0; k < MAX_TEETH; k++) {
                    if(dentalRecord[i][j][k] == 'B' || dentalRecord[i][j][k] == 'C'
                            || dentalRecord[i][j][k] == 'M'){
                        System.out.print("  " + (k + 1) + ":" + dentalRecord[i][j][k]);
                    }
                }
                System.out.println();
            }
        }
    }

    //Teeth Extraction, i.e., replacing a chosen B or C with an M
    private static void extractTeeth(char[][][] dentalRecord, String[] names, int numPeople){
        String firstName;
        int familyNum = 6, jawNum, toothNum;
        char toothLayer;

        //Get family member, matching input with element in names array
        System.out.print("Which family member                         : ");
        do{
            firstName = keyboard.next();
            firstName = firstName.substring(0,1).toUpperCase(Locale.ROOT) + firstName.substring(1);

            for(int i = 0; i < numPeople; i++){
                if (names[i].equals(firstName)){
                    familyNum = i;
                }
            }
            if(familyNum == 6){
                System.out.print("Invalid family member, try again            : ");
            }
        } while (familyNum == 6);

        //Get tooth layer
        System.out.print("Which tooth layer (U)pper or (L)ower        : ");
        do{
            toothLayer = keyboard.next().toUpperCase(Locale.ROOT).charAt(0);
            if(toothLayer != 'U' && toothLayer != 'L'){
                System.out.print("Invalid layer, try again                    : ");
            }
        } while(toothLayer != 'U' && toothLayer != 'L');

        //Converting the char into the number used for the index
        if (toothLayer == 'U'){
            jawNum = 0;
        }
        else{
            jawNum = 1;
        }

        //Get tooth to extract
        System.out.print("Which tooth number                          : ");
        toothNum = (keyboard.nextInt() - 1);
        while(!validToothNum(toothNum, dentalRecord, familyNum, jawNum)){
            toothNum = (keyboard.nextInt() - 1);
            }

        dentalRecord[familyNum][jawNum][toothNum] = 'M';

    }

    //Makes sure the user chooses a removable tooth
    private static boolean validToothNum(int toothNum, char[][][] dentalRecord, int familyNum, int jawNum){
        if (toothNum > 9 || toothNum < 0){
            System.out.print("Invalid tooth number, try again             : ");
            return false;
        }
        else if(dentalRecord[familyNum][jawNum][toothNum] != 'B'
                && dentalRecord[familyNum][jawNum][toothNum] != 'C'){
            System.out.print("Missing tooth, try again                    : ");
            return false;
        }
        else{
            return true;
        }
    }

    //Finds the square roots based on number of Bs, Cs, and Ms
    private static void findRoots(char[][][] dentalRecord, int numPeople){
        double bCount = 0, cCount = 0, mCount = 0, root1, root2, discriminant;

        for (int i = 0; i < numPeople; i++) {
            for (int j = 0; j < JAWLINES; j++) {
                for (int k = 0; k < MAX_TEETH; k++) {
                    if(dentalRecord[i][j][k] == 'B'){
                     ++bCount;
                    }
                    else if(dentalRecord[i][j][k] == 'C'){
                        ++cCount;
                    }
                    else if(dentalRecord[i][j][k] == 'M') {
                        --mCount;
                    }
                }
            }
        }

        //Does the Math
        discriminant = Math.sqrt(Math.pow(cCount, 2) - (4*bCount*mCount));
        root1 = (-cCount + discriminant)/(2*bCount);
        root2 = (-cCount - discriminant)/(2*bCount);

        //Prints the roots, depending on how many are real
        if (discriminant > 0){
            System.out.printf("One root canal at %.2f\n", root1);
            System.out.printf("Another root canal at %.2f\n", root2);
        }
        else if (discriminant == 0){
            System.out.printf("One root canal at %.2f\n", root1);
        }
        else{
            System.out.println("No root canals");
        }

    }
}


