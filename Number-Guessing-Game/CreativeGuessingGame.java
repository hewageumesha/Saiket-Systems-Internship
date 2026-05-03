import java.util.Random;
import java.util.Scanner;

public class CreativeGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // creative elements
        String[] encouragements = {"Keep going!", "You're getting warmer!", "Almost there!", "Don't give up!"};
        int maxAttempts = 7;
        int numberToGuess = random.nextInt(100) + 1;
        int attemptsTaken = 0;

        System.out.println("----------------------------------");
        System.out.println("  THE ULTIMATE NUMBER CHALLENGE   ");
        System.out.println(" I'm thinking of a number (1-100) ");
        System.out.println("   You have " + maxAttempts + " attempts to win! ");
        System.out.println("----------------------------------");

        while (attemptsTaken < maxAttempts) {
            int remaining = maxAttempts - attemptsTaken;
            System.out.println("[" + "".repeat(remaining) + "] Guess: ");

            if (!scanner.hasNextInt()) {
                System.out.println("That's not a number! Try again.");
                scanner.next();
                continue;
            }

            int guess = scanner.nextInt();
            attemptsTaken++;

            if (guess == numberToGuess) {
                System.out.println("\n ***** EPIC WIN! *****");
                System.out.println("The number was indeed " + numberToGuess + ".");
                System.out.println("Your rank: " + getRank(attemptsTaken));
                return;
            } else {
                String hint = (guess < numberToGuess) ? "HIGHER" : "LOWER";
                String cheer = encouragements[random.nextInt(encouragements.length)];
                System.out.println(hint + " | " + cheer);
            }
        }

        System.out.println("\n ***** GAME OVER *****");
        System.out.println("The number was: " + numberToGuess);
        System.out.println("Better luck next time, intern!");
        scanner.close();
    }

    private static String getRank(int tries) {
        if (tries <= 2) return  "Mind Reader (S-Tier)";
        if (tries <= 5) return  "Expert Scout (A-Tier)";
        return "Lucky Human (B-Tier)";
    }
}
