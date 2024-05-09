import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        Random random = new Random();
        
        // Specify the range for the random number
        int min = 1;
        int max = 100;
        
        // Generate a random number within the specified range
        int randomNumber = random.nextInt(max - min + 1) + min;
        
        // Number of attempts allowed
        int attempts = 5;
        int attempt = 0;
        
        System.out.println("Welcome to Guess Number Game!");
        System.out.println("You Will Be Asked To Guess a Number To Win The Game.");
        System.out.println("You have Maximum 5 Attemps.");
        System.out.println("Enter a Guess Number between " +min +" to " +max);
        
        // Game loop
        while (attempt < attempts) {
            attempt++;
            System.out.print("Attempt " + attempt + ": Enter your guess: ");
            int guess = scanner.nextInt();
            
            // Compare the guess with the random number
            if (guess < randomNumber) {
                System.out.println("Your guess number is Smaller.");
            } else if (guess > randomNumber) {
                System.out.println("Your guess number is Greater.");
            } else {
                System.out.println("Oohoo!Your number is correct.You win the game.");
                return;
            }
        }
        
        // If the player runs out of attempts
        System.out.println("Sorry, you ran out of attempts. The number was " + randomNumber + ".");
    }
}
