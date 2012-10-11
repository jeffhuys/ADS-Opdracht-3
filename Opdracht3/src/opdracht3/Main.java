package opdracht3;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.swing.DefaultListModel;

/**
 *
 * @author Jeff
 */
public class Main {

    private int amountOfNumbers;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("ADS opdracht 3");
        System.out.println("Jeff Huijsmans");
        System.out.println("Mauricio Kruijer\n");

        Main main = new Main();
        main.puzzle(false, 10);
    }

    public void puzzle(boolean mayTryAgain, int numberOfQuestions) {
        int questionsCorrect = 0;
        for (int question = 0; question < numberOfQuestions; question++) {
            amountOfNumbers = 4;

            int[] numbers = generateRandomNumbers(amountOfNumbers);
            char[] operators = generateRandomOperators(amountOfNumbers - 1);

            //for(int i = 0; i < amountOfNumbers - 1; i++) {
            //    System.out.println(operators[i]);
            //}
            //System.out.println(operators[0]);

            String finalString = "";
            String calculateString = "";
            for (int i = 0; i < amountOfNumbers; i++) {
                finalString += numbers[i];
                calculateString += numbers[i];
                finalString += " ";
                if (i != amountOfNumbers - 1) {
                    finalString += operators[i];
                    calculateString += operators[i];
                    finalString += " ";
                }
            }
            calculateString = calculateString.replaceAll("x", "*");
            int answer = calculateAnswerFromString(calculateString);

            // Order numbers from int[] numbers and display them
            Arrays.sort(numbers);
            for (int i = 0; i < amountOfNumbers; i++) {
                System.out.print(numbers[i] + " ");
            }
            System.out.print(" ");

            // Print the operators possible
            String operatorsPossible = null;
            for (int i = 0; i < operators.length; i++) {
                operatorsPossible += operators[i];
            }
            if (operatorsPossible.contains("+")) {
                System.out.print("+ ");
            }
            if (operatorsPossible.contains("-")) {
                System.out.print("- ");
            }
            if (operatorsPossible.contains("x")) {
                System.out.print("x ");
            }
            System.out.println(" " + answer);

            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            input = input.trim();

            input.contains("+");
            
            int userAnswer = calculateAnswerFromString(input.replaceAll("x", "*"), numbers, operators);
            if (userAnswer == answer) {
                System.out.println("Correctemente");
            } else {
                System.out.println("Fout");
            }

            //System.out.print(finalString + "= ");
            // Wait for user input
            /*
             Scanner in = new Scanner(System.in);
             int input = 0;
             boolean inputRight = false;
             while (!inputRight) {
             // TODO: Validate input.
             boolean valid = false;
             while (!valid) {
             try {
             if(in.hasNext())
             input = in.nextInt();
                        
             valid = true;
             } catch (java.util.InputMismatchException ex) {
             System.out.println("Please enter a number.");
             in.reset();
             in.close();
             in = new Scanner(System.in);
             }
             }
                
             if (input == answer) {
             System.out.println("You got it! Yeah!");
             questionsCorrect++;
             inputRight = true;
             } else {
             if (mayTryAgain) {
             System.out.print("\nWrong answer. Please try again: ");
             } else {
             System.out.println("Wrong. Next question.");
             inputRight = true;
             }
             }
             }
             */
        }
        System.out.println("You got " + questionsCorrect + "/" + numberOfQuestions + " correct.");
        System.out.println("Grade: " + ((questionsCorrect / numberOfQuestions) * 10));

    }

    public int[] generateRandomNumbers(int amount) {
        int[] array = new int[amount];

        Random rnd = new Random();
        for (int i = 0; i < amount; i++) {
            // Generate random number under 10, above 0
            array[i] = rnd.nextInt(9) + 1;
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

    /**
     * This will calculate an answer from a string generated by the program. I
     * just realized this method of doing things is pretty stupid, because we
     * have access to the arrays themselves. But oh well, I'll continue this
     * method as this is a more fun way of doing it, if not slightly...
     * Expensive.
     *
     * TODO: Fix priorities. The way of calculating as it is now will sometimes
     * calculate the wrong answers.
     *
     * @param calc
     * @return
     */
    private int calculateAnswerFromString(String calc) {
        int answer = 0;
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            String answerTemp = engine.eval(calc).toString();
            answer = Integer.decode(answerTemp.substring(0, answerTemp.length() - 2));
        } catch (ScriptException ex) {
            System.out.println("Error in syntax! JavaScript could not evaluate "
                    + calc);
            System.out.println("Ex: " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Dude, je geeft nu niks mee!");
        }

        /*
         * Old version. This had priorities wrong (ofcourse. Just look at it, hehe)
         * New version is above.
         
         for (int i = 0; i < calc.length(); i++) {
         int isNumber = -1;
         isNumber = isNumber(calc.charAt(i));
         if (isNumber != -1) {
         if (i == 0) {
         answer = isNumber;
         } else {
         switch (calc.charAt(i - 1)) {
         case 'x':
         answer *= isNumber;
         break;
         case '+':
         answer += isNumber;
         break;
         case '-':
         answer -= isNumber;
         break;
         }
         }
         }
         }
         */
        return answer;
    }

    //<editor-fold defaultstate="collapsed" desc="For use with the old (wrong) calculation method">
    private int isNumber(char c) {
        for (int i = 0; i < 10; i++) {
            // ASCII conversion (char 0 = 48, so subtract 48)
            if ((int) c - 48 == i) {
                return i;
            }
        }
        return -1;
    }
    //</editor-fold>

    private int calculateAnswerFromString(String calc, int[] numbers, char[] operators) {
        int answer = 0;
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {

            String inputCleared = calc.trim().replace(" ", "");
            int i;
            int opcount = 0;
            int numcount = 0;
            for (i = 0; i < inputCleared.length(); i++) {
                int[] num = new int[amountOfNumbers];
                String[] op = new String[amountOfNumbers - 1];

                try {
                    int foundInt = Integer.parseInt(String.valueOf(inputCleared.charAt(i)));
                    if (!in_array(numbers, foundInt)) {
                        System.out.println("Ongeldig getal gevonden");
                        return 0;
                    }
                    num[numcount] = foundInt;
                    numcount++;
                } catch (NumberFormatException e) {
                    char foundChar = inputCleared.charAt(i);
                    if(!in_array(operators, foundChar)) {

                        System.out.println("Ongeldige operator opgegeven..");
                        return 0;
                    }
                    op[opcount] = String.valueOf(inputCleared.charAt(i));
                    opcount++;
                    //System.out.println("string gevonden, checken op possible operators"); // a b c e.d mag niet
                }
            }
            if (numcount != amountOfNumbers) {
                System.out.println("Te weinig nummers gekrgen.");
                return 0;
            }
            String answerTemp = engine.eval(calc).toString();
            double answerDouble = Double.parseDouble(answerTemp);
            answer = (int) answerDouble;

        } catch (ScriptException ex) {
            System.out.println("Error in syntax! JavaScript could not evaluate " + calc);
            System.out.println("Ex: " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("Dude, je geeft nu niks mee!" + ex);
        } catch (Exception ex) {
            System.out.println("Uh.. iets anders gevonden wat niet goed is :) " + ex);
        }
        return answer;
    }

    public static boolean in_array(DefaultListModel haystack, String needle) {
        for (int i = 0; i < haystack.size(); i++) {
            if (haystack.get(i).toString().equals(needle)) {
                return true;
            }
        }
        return false;
    }
    public static boolean in_array(char[] haystack, char needle) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i] == needle) {
                return true;
            }
        }
        return false;
    }
    public static boolean in_array(int[] haystack, int needle) {
        for (int i = 0; i < haystack.length; i++) {
            if (haystack[i] == needle) {
                return true;
            }
        }
        return false;
    }
}
