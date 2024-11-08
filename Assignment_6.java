//Author: Sarthak Somavanshi
import java.util.Scanner;

public class ArrayExceptionHandling {
    public static void main(String[] args) {
        // Sample array
        int[] numbers = {10, 20, 30, 40, 50};

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an index to access the array (0 to " + (numbers.length - 1) + "): ");

        try {
            // Get user input
            int index = scanner.nextInt();

            // Attempt to access the array element at the specified index
            System.out.println("Value at index " + index + ": " + numbers[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // Handle the exception
            System.out.println("Error: Index " + e.getMessage() + " is out of bounds for the array.");
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the scanner
            scanner.close();
        }
    }
}