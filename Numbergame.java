import java.util.Random;
import java.util.Scanner;

public class Numbergame {
    
private static final int MIN_NUM = 1;
    private static final int MAX_NUM = 100;
    private static final int MAX_ATTEMPTS = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        
        while (true) {
            int number = generateNumber(random);
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            System.out.println("\nNew Round! Try to guess the number between " + MIN_NUM + " and " + MAX_NUM + ".");
            
            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;
                
                if (guess < number) {
                    System.out.println("Too low!");
                } else if (guess > number) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! The number was " + number + ".");
                    score++;
                    guessedCorrectly = true;
                    break;
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all attempts. The number was " + number + ".");
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().trim().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }
        
        System.out.println("\nGame Over! Your score: " + score);
        scanner.close();
    }
    
    private static int generateNumber(Random random) {
        return random.nextInt(MAX_NUM - MIN_NUM + 1) + MIN_NUM;
    }
}
