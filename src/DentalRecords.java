import java.util.Locale;
import java.util.Scanner;

public class DentalRecords {
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        int numPeople;
        char menuOption;
        char[][][] dentalRecord = new char[5][2][10];
        String[] names = new String[5];

        welcomeMessage();
        numPeople = getPeople();
        for (int i = 0; i < numPeople; i++) {
            System.out.print("Please enter the name for family member " + (i + 1) + "  : ");
            names[i] = keyboard.next();
            dentalRecord = inputData(dentalRecord, names, i);
        }
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
            if((numPeople <= 0) || (numPeople > 5)){
                System.out.print("Invalid number of people, try again         : ");
            }
        } while ((numPeople <= 0) || (numPeople > 5));

        return numPeople;
    }

    //Gets the string of teeth
    private static char[][][] inputData(char[][][] dentalRecord, String[] names, int i) {
        String teeth;

        for (int j = 0; j < 2; j++) {
            if (j == 0) {
                System.out.print("Please enter the uppers for " + names[i] + "     : ");
                teeth = keyboard.next().toUpperCase(Locale.ROOT);
                while(!validTeethType(teeth)){
                    System.out.print("try again              : ");
                    teeth = keyboard.next().toUpperCase(Locale.ROOT);
                }
                for (int k = 0; k < teeth.length(); k++) {
                    dentalRecord[i][j][k] = teeth.charAt(k);
                }
            }
            else{
                System.out.print("Please enter the lowers for " + names[i] + "     : ");
                teeth = keyboard.next().toUpperCase(Locale.ROOT);
                while(!validTeethType(teeth)){
                    System.out.print("try again              : ");
                    teeth = keyboard.next().toUpperCase(Locale.ROOT);
                }
                for(int k = 0; k < teeth.length(); k++){
                    dentalRecord[i][j][k] = teeth.charAt(k);
                }
            }
        }


        return dentalRecord;
    }

    //This checks to make sure the input for string of teeth makes sense
    private static boolean validTeethType(String teeth) {

        if (teeth.length() > 10) {
            System.out.print("Too many teeth, ");
            return false;
        }
        else {
            for (int i = 0; i < teeth.length(); i++) {
                if (teeth.charAt(i) != 'B' && teeth.charAt(i) != 'C' && teeth.charAt(i) != 'M') {
                    System.out.print("Invalid teeth types, ");
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
                dentalRecord = extractTeeth(dentalRecord, names, numPeople);
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

    //Printing the inputs
    private static void printOption(char[][][] dentalRecord, String[] names, int numPeople){

        for (int i = 0; i < numPeople; i++) {
            System.out.println(names[i]);
            for (int j = 0; j < 2; j++) {
                if (j ==0){
                    System.out.print("Uppers:");
                }
                else{
                    System.out.print("Lowers:");
                }
                for (int k = 0; k < 10; k++) {
                    if(dentalRecord[i][j][k] == 'B' || dentalRecord[i][j][k] == 'C' || dentalRecord[i][j][k] == 'M'){
                        System.out.print("  " + (k + 1) + ":" + dentalRecord[i][j][k]);
                    }
                }
                System.out.println();
            }
        }
    }

    //Teeth Extraction, i.e., replacing a chosen B or C with an M
    private static char[][][] extractTeeth(char[][][] dentalRecord, String[] names, int numPeople){
        String firstName;
        int familyNum = 6, jawNum, toothNum;
        char toothLayer;

        //Get family member
        System.out.print("Which family member                         : ");
        do{
            firstName = keyboard.next();
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
            System.out.print("try again              : ");
            toothNum = (keyboard.nextInt() - 1);
            }

        dentalRecord[familyNum][jawNum][toothNum] = 'M';

        return dentalRecord;
    }

    //Makes sure the user chooses a removable tooth
    private static boolean validToothNum(int toothNum, char[][][] dentalRecord, int familyNum, int jawNum){
        if (toothNum > 9){
            System.out.print("Invalid tooth number, ");
            return false;
        }
        else if(dentalRecord[familyNum][jawNum][toothNum] != 'B' && dentalRecord[familyNum][jawNum][toothNum] != 'C'){
            System.out.print("Missing tooth, ");
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
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 10; k++) {
                 if(dentalRecord[i][j][k] == 'B'){
                     ++bCount;
                 }
                 else if(dentalRecord[i][j][k] == 'C'){
                     ++cCount;
                 }
                 else if(dentalRecord[i][j][k] == 'M'){
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
            System.out.println("One root canal at     " + root1);
            System.out.println("Another root canal at " + root2);
        }
        else if (discriminant == 0){
            System.out.println("One root canal at     " + root1);
        }
        else{
            System.out.println("No root canals");
        }

    }
}


