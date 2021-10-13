import java.util.Locale;
import java.util.Scanner;

public class DentalRecords {
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        int numPeople;
        char menuOption = 'P';
        char[][][] dentalRecord = new char[5][2][10];
        String[] names = new String[5];

        welcomeMessage();
        numPeople = getPeople();
        for (int i = 0; i < numPeople; i++) {
            System.out.print("Please enter the name for family member " + (i + 1) + "  : ");
            names[i] = keyboard.next();
            dentalRecord = inputData(dentalRecord, names, i);
        }

        //Create a Method for the menu option (message, getting input, making sure their input matches)
        //This switch reads the menu option then runs the respective method
        switch (menuOption) {
            case 'P':
                break;
            case 'E':
                break;
            case 'R':
                break;
            case 'X':
                System.out.print("Exiting the Floridian Tooth Records :-)");
                break;
            default:
                System.out.print("Exiting the Floridian Tooth Records :-)");
        }


        //This is just for testing purposes
        for (int i = 0; i < numPeople; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 10; k++) {
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
                while(!validTeeth(teeth)){
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
                while(!validTeeth(teeth)){
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
    private static boolean validTeeth(String teeth) {

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
}


