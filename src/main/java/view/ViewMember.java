package view;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.DayCounter;
import model.Item;
import model.Member;

/**
 * This class handles member display.
 */
public class ViewMember {
  private Scanner scanner;
  private DayCounter dayCounter;
  
  public ViewMember(DayCounter dayCounter) {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    this.dayCounter = dayCounter;
  }

  /**
   * Displays the member menu.
   */
  public void memberMenuDisplay() {
    System.out.println("\nMember Manage Menu:");
    System.out.println("1. Create a member");
    System.out.println("2. Delete a member");
    System.out.println("3. View a member's full information");
    System.out.println("4. Change member information");
    System.out.println("5. List all members in a simple way");
    System.out.println("6. List all members in a verbose way");
    System.out.println("7. Go back");
    System.out.println("\nEnter your choice: ");
  }

  /**
   * Displays the member change menu.
   */
  public void memberChangeDisplay() {
    System.out.println("\nChange member information options:");
    System.out.println("1. Change member name");
    System.out.println("2. Change member email");
    System.out.println("3. Change member phone number");
    System.out.println("4. Go back");
    System.out.println("\nEnter your choice: ");
  }

  /**
   * Displays the member create.
   *
   * @param newMember The newly created member.
   * @return True if the member creation is successful.
   */
  public boolean memberCreateDisplay(Member newMember) {
    System.out.println("New member created with ID: " + newMember.getMemberId() + " successfully.");
    return true;
  }

  /**
   * Displays the member name.
   *
   * @return The member name.
   */
  public String memberNameDisplay() {
    String name = null;
    while (name == null || name.isEmpty()) {
      System.out.println("Enter member name: ");
      name = scanner.nextLine().trim();
      if (name.isEmpty()) {
        System.out.println("Member name cannot be blank.");
      }
      
    }
    return name;
  }

  /**
   * Displays the member email.
   *
   * @return The member email.
   */
  public String memberEmailDisplay() {
    String email = null;
    while (email == null || email.isEmpty()) {
      System.out.println("Enter member email: ");
      email = scanner.nextLine().trim();
      if (email.isEmpty()) {
        System.out.println("Member email cannot be blank to register.");
      }
      
    }
    return email;
  }

  /**
   * Checks if the input is an integer.
   *
   * @return the integer input.
   */
  public int integerCheck() {
    int input = -1;
    try {
      input = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      // Invalid input, the loop will prompt again.
    }
    return input;
  }

  /**
   * Displays the member phone number.
   *
   * @return The member phone number.
   */
  public String memberPhoneNumberDisplay() {
    String phoneNumber = null;
    while (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.length() != 10) {
      System.out.println("Enter 10 digit phone number for member: ");
      phoneNumber = scanner.nextLine().trim();
      if (phoneNumber.isEmpty() || phoneNumber.length() != 10) {
        System.out.println("Error: Phone number must be 10 digits.");
      }
      
    }
    return phoneNumber;
  }

  /**
   * Displays the member delete.
   */
  public void memberDeleteDisplay() {
    System.out.println("\nMember delete successfully.");
  }

  /**
   * Displays the member detail.
   *
   * @param member The member to display.
   */
  public void memberDetailDisplay(Member member) {
    System.out.println("Member full information: \n");
    System.out.println("Name: " + member.getName());
    System.out.println("Email: " + member.getEmail());
    System.out.println("Phone Number: " + member.getPhoneNumber());
    System.out.println("Member ID: " + member.getMemberId());
    System.out.println("Owned Item Number: " + member.getOwnedItemCount());
    System.out.println("Currect Credits: " + member.getCredits());
  }

  /**
   * Displays the members in a simple way.
   *
   * @param memberList The list of members.
   */
  public void simpleViewDisplay(List<Member> memberList) {
    for (Member member : memberList) {
      String memberEmail = member.getEmail();
      System.out.println("\nMember infomation with email " + memberEmail + ":");
      memberDetailDisplay(member);
    }
  }

  /**
   * Displays the members in a verbose way.
   *
   * @param memberList The list of members.
   */
  public void verboseViewDisplay(List<Member> memberList) {
    for (Member member : memberList) {
      System.out.println("\nMember infomation:");
      System.out.println("Name: " + member.getName());
      List<Item> ownedItems = member.getOwnedItems();
      if (!ownedItems.isEmpty()) {
        System.out.println("Owned Items: \n");
        for (Item item : ownedItems) {
          System.out.println("Item Name: " + item.getName());
          System.out.println("Item Category: " + item.getCategory());
          System.out.println("Item Description: " + item.getDescription());
          System.out.println("Item Cost Per Day: " + item.getCost());
          List<Contract> contracts = item.getContract(dayCounter.getCurrentDate());
          if (!contracts.isEmpty()) {
            System.out.println("\nContract: \n");
            for (Contract contract : contracts) {
              System.out.println("Borrower: " + contract.getBorrower());
              System.out.println("Start Date: " + contract.getStartDate());
              System.out.println("End Date: " + contract.getEndDate());
            }
          } else {
            System.out.println("This item is currently available for borrowing.");
          }
        } 
      } else {
        System.out.println("This member currently has no owned items.");
      }
    } 
  } 

  /**
   * Displays the member change name.
   *
   * @return The new name.
   */
  public String changeNameDisplay() {
    System.out.println("Enter new name: ");
    String newName = scanner.nextLine();
    return newName;
  }

  /**
   * Displays the member change email.
   *
   * @return The new email.
   */
  public String changeEmailDisplay() {
    System.out.println("Enter new email: ");
    String newEmail = scanner.nextLine();
    return newEmail;
  }

  /**
   * Displays the member change phone number.
   *
   * @return The new phone number.
   */
  public String changePhoneNumberDisplay() {
    System.out.println("Enter new phone number: ");
    String newPhoneNumber = scanner.nextLine();
    scanner.nextLine();
    return newPhoneNumber;
  }
}
