package opdracht3;

import java.util.Random;

/**
 *
 * @author Jeff
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("ADS opdracht 3");
        System.out.println("Jeff Huijsmans");
        System.out.println("Mauricio Kruijer\n");

        Main main = new Main();
        main.puzzle();
    }

    public void puzzle() {
        int amountOfNumbers = 4;
        
        int[] numbers = generateRandomNumbers(amountOfNumbers);
        char[] operators = generateRandomOperators(amountOfNumbers - 1);

        //for(int i = 0; i < amountOfNumbers - 1; i++) {
        //    System.out.println(operators[i]);
        //}
        //System.out.println(operators[0]);

        String finalString = "";
        String calculateString = "";
        for (int i = 0; i < amountOfNumbers; i++) {
            System.out.println(i);
            finalString += numbers[i];
            calculateString += numbers[i];
            finalString += " ";
            if(i != amountOfNumbers - 1) {
                finalString += operators[i];
                calculateString += operators[i];
                finalString += " ";
            }
        }
        int answer = calculateAnswerFromString(calculateString);
        
        
        System.out.println(finalString + " = ");

    }

    public int[] generateRandomNumbers(int amount) {
        amount++;
        int[] array = new int[amount];

        Random rnd = new Random();
        for (int i = 0; i < amount; i++) {
            // Generate random number under 10
            array[i] = rnd.nextInt(10);
        }
        return array;
    }

    public char[] generateRandomOperators(int amount) {
        char[] array = new char[amount];

        Random rnd = new Random();
        for (int i = 0; i < amount; i++) {
            // Generate random operators
            switch (rnd.nextInt(3)) {
                case 0:
                    array[i] = 'x';
                    break;
                case 1:
                    array[i] = '+';
                    break;
                case 2:
                    array[i] = '-';
                    break;
            }
        }

        return array;
    }

    private int calculateAnswerFromString(String calc) {
        for(int i = 0; i < calc.length(); i++) {
            if(isNumber(calc.charAt(i))) {
                System.out.println(calc.charAt(i) + " is a number");
            } else {
                System.out.println(calc.charAt(i) + " is no number");
            }
        }
        return 1;
    }
    
    private boolean isNumber(char c) {
        for(int i = 0; i < 10; i++) {
            // ASCII conversion (char 0 = 48, so subtract 48)
            if((int)c - 48 == i) {
                return true;
            }
        }
        return false;
    }
}
