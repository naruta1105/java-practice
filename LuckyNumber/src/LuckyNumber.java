import java.lang.Math;
import java.util.Scanner;
import java.util.InputMismatchException; // this exception for input string for nextInt();

public class LuckyNumber {
    // init static to get rid of warning using scanner.close()
    private static final Scanner console = new Scanner(System.in);
    // Initialize constant min, max
    private static final int min = 0, max = 100;

    // Result
    private static int totalGame = 0;
    private static int totalGuesses = 0;
    private static double guessPerGame = 0.0; //guess avg
    private static int bestGuess = Integer.MAX_VALUE; // min of guess

    public static void main (String[] args) {
        boolean isContinued;
        do {
            isContinued = false; // that will make the game not play again
            totalGame++;
            play();
            System.out.print("Do you want to play again? ");
            String answer = console.next();
            answer = answer.toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                isContinued = true;
            }
            System.out.println();
        } while (isContinued);
        guessPerGame = (double)totalGuesses/totalGame;
        report();
    }

    private static void play() {
        /*
        We will initialize the random value with range(min,max) and play guess here;
        */
        // init variable
        int randNumber = (int) (Math.random()*(max-min+1) + min) ; //+1 because max is exclusive
        int guessCount = 0; // count how many guesses are made
        boolean isCorrect = false, isGuessValid = true;
        int guessNumber = 0;

        System.out.printf("I'm thinking of a number between %d and %d...\n",min,max);

        do {
            do {
                isGuessValid = true;
                try {
                    System.out.print("Your guess? ");
                    guessNumber = console.nextInt();
                } catch (InputMismatchException e){ // this exception for input string for nextInt();
                    isGuessValid = false;
                    console.next(); // must use to make new input to prevent Infinite loop
                }
            } while (!isGuessValid);

            guessCount++;
            if (guessNumber<randNumber) {
                System.out.println("It's higher.");
            } else if (guessNumber>randNumber) {
                System.out.println("It's lower.");
            } else {
                System.out.printf("You got it right in %d guess%s!\n", guessCount,(guessCount>1)?"es":"");
                isCorrect = true;
            }
        } while (!isCorrect);
        totalGuesses+=guessCount;
        bestGuess = Math.min(bestGuess,guessCount); // bestGuess will get min of guessCount
    }

    private static void report() {
        System.out.println("Overall results: ");
        System.out.printf("%-14s = %d\n","Total game",totalGame);
        System.out.printf("%-14s = %d\n","Total guesses",totalGuesses);
        System.out.printf("%-14s = %.1f\n","Guesses/game",guessPerGame);
        System.out.printf("%-14s = %d\n","Best game",bestGuess);
    }
}