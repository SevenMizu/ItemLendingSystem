package view;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.Contract;

/**
 * This class handles contract display.
 */
public class ViewContract {
  private Scanner scanner;

  public ViewContract() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  }

  /**
   * Displays the contract menu.
   */
  public void contractMenuDisplay() {
    System.out.println("\nContract Manage Menu:");
    System.out.println("1. Create a new contract");
    System.out.println("2. View all contracts");
    System.out.println("3. Go back");
    System.out.println("\nEnter your choice: ");
  }

  /**
   * Display about entering email to add borrower.
   *
   * @return the borrower.
   */
  public String addBorrowerDisplay() {
    System.out.println("Creating new contract...\n");
    System.out.println("Enter Borrower's email: ");
    String borrower = scanner.nextLine();
    return borrower;
  }

  /**
   * Display about entering the item ID to borrow.
   *
   * @return the item ID.
   */
  public String addItemDisplay() {
    System.out.println("Enter the item ID you want to borrow: ");
    String item = scanner.nextLine();
    return item;
  }

  /**
   * Display about entering the return date.
   *
   * @return the return date.
   */
  public int returnDateDisplay() {
    System.out.println("Enter the return date: ");
    int returnDate = integerCheck();
    return returnDate;
  }

  /**
   * Check if the input is an integer.
   *
   * @return the integer input.
   */
  public int integerCheck() {
    while (true) {
      try {
        String input = scanner.nextLine();
        if (input.trim().isEmpty()) {
          System.out.println("Empty Input. Please enter a number:");
          continue;
        } else {
          return Integer.parseInt(input);
        }
      } catch (NumberFormatException e) {
        System.out.println("Input invalid. Please enter a number:");
      }
    }
  }

  /**
   * Display the contract list.
   *
   * @param contractList the contract list.
   */
  public void contractListDisplay(List<Contract> contractList) {
    if (contractList.isEmpty()) {
      System.out.println("No contract found");
    } else {
      System.out.println("Contract List: ");
      for (Contract contract : contractList) {
        System.out.println("Contract ID: " + contract.getContractId());
        System.out.println("Borrower Name: " + contract.getBorrower().getName());
        System.out.println("Lender Name: " + contract.getLender().getName());
        System.out.println("Item ID: " + contract.getItem().getName());
        System.out.println("Contract Start Date: " + contract.getStartDate());
        System.out.println("Contract End Date: " + contract.getEndDate());
        System.out.println("Total Cost: " + contract.getTotalCost());
        System.out.println();
      }
    }
  }
}
