import java.util.Locale;
import java.util.Scanner;

public class DentalRecords {
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args){
        int numPeople;
        char[][][] dentalRecord = new char[5][2][10];
        String[] names = new String[5];

        welcomeMessage();
        numPeople = getPeople();
        //Change this to a loop, where I go to two methods one for names and the other for teeth
        dentalRecord = inputData(numPeople, names);
        //Here I make a menu option and also a switch to choose the next method


        //This is just for testing purposes
        for(int i = 0; i < numPeople; i++) {
            for (int j = 0; j < 2; j++) {
                for(int k = 0; k < 10; k++){
                    System.out.print(dentalRecord[i][j][k]);
                }
            }
        }


    }

    // Welcome Message
    private static void welcomeMessage() {
        System.out.println("Welcome to the Floridian Tooth Records");
        System.out.println("--------------------------------------");
    }

    //Getting all the  inputs: number of people, their names, and their teeth
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

    private static char[][][] inputData(int numPeople, String[] names) {
        char[][][] dentalRecord = new char[5][2][10];
        String teeth;

        //Spruce this up with improvements found in experiment class
        for(int i = 0; i < numPeople; i++) {
            System.out.print("Please enter the name for family member     : ");
            names[i] = keyboard.next();
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    System.out.print("Please enter the uppers for " + names[i] + ": ");
                    teeth = keyboard.next().toUpperCase(Locale.ROOT);
                    while(!validTeeth(teeth)){
                        System.out.print("Try again: ");
                        teeth = keyboard.next().toUpperCase(Locale.ROOT);
                    }
                    for (int k = 0; k < teeth.length(); k++) {
                        dentalRecord[i][j][k] = teeth.charAt(k);
                    }
                }
                else{
                    System.out.print("Please enter the lowers for " + names[i] + ": ");
                    teeth = keyboard.next();
                    for(int k = 0; k < teeth.length(); k++){
                        dentalRecord[i][j][k] = teeth.charAt(k);
                    }
                }
            }
        }
        return dentalRecord;
    }
    private static boolean validTeeth(String teeth) {

        if (teeth.length() > 10) {
            System.out.print("Too much teeth, ");
            return false;
        }
        else {
            for (int i = 0; i < teeth.length(); i++) {
                if (teeth.charAt(i) != 'B' && teeth.charAt(i) != 'C' && teeth.charAt(i) != 'M') {
                    System.out.print("Invalid Teeth, ");
                    return false;
                }
            }
            return true;
        }
    }
}
}

