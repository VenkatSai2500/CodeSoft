import java.util.Random;
import java.util.Scanner;
public class RandomNumberGuessingGame
{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange=1;
        int maxRange=100;
        int attemptsLimit=5;
        int score=0;
        boolean playAgain = true;
        while (playAgain){
            int randomNumber=random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts=0;
            boolean guessedCorrectly=false;
            System.out.println("Guess the number between " + minRange + " and " + maxRange + ".");
            while (attempts<attemptsLimit && !guessedCorrectly){
                System.out.print("Attempt " + (attempts + 1) + ": Enter your guess: ");
                int userGuess = scanner.nextInt();
                scanner.nextLine(); 
                if (userGuess == randomNumber){
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                } 
                else if (userGuess < randomNumber){
                    System.out.println("Sorry, your guess is too low.");
                } 
                else{
                    System.out.println("Sorry, your guess is too high.");
                }
                attempts++;
            }
            if (!guessedCorrectly){
                System.out.println("Out of attempts. The correct number was: " + randomNumber);
            }
            score += guessedCorrectly ? 1 : 0;
            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.nextLine();
            playAgain = playChoice.equalsIgnoreCase("yes");
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}
