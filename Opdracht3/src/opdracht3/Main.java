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

    }

    public int[] generateRandomNumbers(int amount) {
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
                    array[i] = '*';
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
}
