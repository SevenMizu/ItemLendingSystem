package view;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import model.DayCounter;

/**
 * This class handles menu display.
 */
public class ViewMenu {
  private Scanner scanner;
  private DayCounter dayCounter;

  public ViewMenu(DayCounter dayCounter) {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    this.dayCounter = dayCounter;
  }

  /**
   * Displays the start of the main menu.
   */
  public void startDisplay() {
    System.out.println("Welcome to the Stuff Lending System!");
    //System.out.println("Current date: " + dayCounter.getCurrentDate());
  }

  /**
   * Displays the main menu options.
   */
  public void menuDisplay() {
    System.out.println("Main Menu:");
    System.out.println("1. Member Manage");
    System.out.println("2. Item Manage");
    System.out.println("3. Contract Manage");
    System.out.println("4. Advance Time");
    System.out.println("\nEnter your choice: ");
  }

  /**
   * Checks if the input is an integer.
   *
   * @return the integer input.
   */
  public int integerCheck() {
    while (true) {
      try {
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
          System.out.println("Empty Input. Please enter a number.");
          continue;
        }
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        System.out.println("Input invalid. Please enter a number.");
      }
    }
  }
  
  /**
   * Displays the current date.
   */
  public void timeShowDisplay() {
    System.out.println();
    System.out.println("Current date: " + dayCounter.getCurrentDate());
  }

  /**
   * Displays the system exit.
   */
  public void systemExitDisplay() {
    System.out.println("Exiting the Stuff Lending System...");
    scanner.close();
    System.exit(0);
  }
}
