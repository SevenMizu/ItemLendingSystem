package view;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import model.Contract;
import model.Item;
import model.ItemCategories;

/**
 * This class handles item display.
 */
public class ViewItem {
  private Scanner scanner;

  public ViewItem() {
    this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
  }

  /**
   * Displays the item menu.
   */
  public void itemMenuDisplay() {
    System.out.println("\nItem Manage Menu:");
    System.out.println("1. Create a new item");
    System.out.println("2. View all items");
    System.out.println("3. Delete an item");
    System.out.println("4. Change item information");
    System.out.println("5. View item information");
    System.out.println("6. Go back");
    System.out.println("\nEnter your choice: ");
  }

  /**
   * Displays the item create menu.
   */
  public boolean itemCreateDisplay(Item newItem) {
    System.out.println("New item created successfully");
    System.out.println("Item ID: " + newItem.getItemId());
    return true;
  }

  /**
   * Displays the item list.
   *
   * @param itemList The list of items.
   */
  public void itemViewDisplay(List<String> itemList) {
    for (String item : itemList) {
      System.out.println("Item:" + item);
    }
  }

  /**
   * Displays the item delete.
   *
   * @param item The item being deleted.
   */
  public void itemDeleteDisplay(Item item) {
    System.out.println(item.getName() + "Item deleted successfully");
  }

  /**
   * Displays the owner email.
   */
  public String ownerEmailDisplay() {
    System.out.println("Enter owner email: ");
    String email = scanner.nextLine();
    return email;
  }

  /**
   * Displays the item name.
   *
   * @return The item name.
   */
  public String itemNameDisplay() {
    String name = scanner.nextLine();
    while (name == null || name.isEmpty()) {
      System.out.println("Enter item name: ");
      name = scanner.nextLine().trim();
      if (name.isEmpty()) {
        System.out.println("Error: Item name cannot be blank to register.");
      }
      
    }
    return name;
  }

  /**
   * Displays the item ID.
   *
   * @return The item ID.
   */
  public String itemIdDisplay() {
    System.out.println("Enter item ID: ");
    String id = scanner.nextLine();
    return id;
  }

  /**
   * Displays the item description.
   *
   * @return The item description.
   */
  public String itemDescriptionDisplay() {
    String description = null;
    while (description == null || description.isEmpty()) {
      System.out.println("Enter item description: ");
      description = scanner.nextLine().trim();
      if (description.isEmpty()) {
        System.out.println("Error: Item description cannot be blank to register.");
      }
      
    }
    return description;
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
      // Invalid input; do nothing, the loop will prompt again.
    }
    return input;
  }

  /**
   * Displays the item cost.
   *
   * @return The item cost.
   */
  public int itemCostDisplay() {
    int cost = -1;
    while (cost <= 0) {
      try {
        System.out.println("Enter item cost: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
          System.out.println("Error: Item cost cannot be blank to register.");
          continue;
        }
        cost = Integer.parseInt(input);
        if (cost <= 0) {
          System.out.println("Error: Item cost must be greater than 0.");
        } 
      } catch (NumberFormatException e) {
        System.out.println("Error: Invalid input. Please enter a positive number.");
      }
    }
    return cost;
  }

  /**
   * Displays the item category.
   *
   * @return The choosen item category.
   */
  public ItemCategories chooseCategory() {
    System.out.println("Choose a category: ");
    System.out.println("1. Tool");
    System.out.println("2. Vehicle");
    System.out.println("3. Game");
    System.out.println("4. Toy");
    System.out.println("5. Sport");
    System.out.println("6. Other");
    int choice;
    do {
      System.out.println("Enter your choice: ");
      choice = scanner.nextInt();
    } while (choice < 1 || choice > 6);
    switch (choice) {
      case 1:
        return ItemCategories.TOOL;
      case 2:
        return ItemCategories.VEHICHLE;
      case 3:
        return ItemCategories.GAME;
      case 4:
        return ItemCategories.TOY;
      case 5:
        return ItemCategories.SPORT;
      case 6:
        return ItemCategories.OTHER;
      default:
        throw new IllegalArgumentException("Invalid choice.");
    }
  }

  /**
   * Displays the item change menu.
   */
  public void changeInformationDisplay() {
    System.out.println("\nChange item information options:");
    System.out.println("1. Change item name");
    System.out.println("2. Change item category");
    System.out.println("3. Change item description");
    System.out.println("4. Change item cost per day");
    System.out.println("5. Go back");
    System.out.println("\nEnter your choice: ");
  }

  /**
   * Displays the item change name.
   *
   * @param item The item being changed.
   * @return The new name.
   */
  public String changeNameDisplay(String item) {
    System.out.println("Enter new name: ");
    String newName = scanner.nextLine();
    return newName;
  }

  /**
   * Displays the item change category.
   *
   * @param item The item being changed.
   * @return The new category.
   */
  public ItemCategories changeCategoryDisplay(String item) {
    ItemCategories newCategory = chooseCategory();
    System.out.println("New category: " + newCategory);
    scanner.nextLine();
    return newCategory;
  }

  /**
   * Displays the item change description.
   *
   * @param item The item being changed.
   * @return The new description.
   */
  public String changeDescriptionDisplay(String item) {
    System.out.println("Enter new description: ");
    String newDescription = scanner.nextLine();
    return newDescription;
  }

  /**
   * Displays the item change cost.
   *
   * @param item The item being changed.
   * @return The new cost.
   */
  public int changeCostDisplay(String item) {
    System.out.println("Enter new cost per day: ");
    int newCost = scanner.nextInt();
    scanner.nextLine();
    return newCost;
  }

  /**
   * Displays the basic information of an item.
   *
   * @param item The item being displayed.
   * @param itemId The ID of the displayed item.
   */
  public void basicInformationDisplay(Item item, String itemId) {
    if (item != null) {
      System.out.println("\nItem information:");
      System.out.println("Name: " + item.getName());
      System.out.println("Category: " + item.getCategory());
      System.out.println("Description: " + item.getDescription());
      System.out.println("Category: " + item.getCategory());
      System.out.println("Cost per day: " + item.getCost());
    } else {
      System.out.println("Item with ID " + itemId + " is not found.");
    }
  }

  /**
   * Displays the further information of an item.
   *
   * @param item The item being displayed.
   * @param itemId The ID of the displayed item.
   * @param contracts The list of contracts associated with the item.
   */
  public void furtherInformationDisplay(Item item, String itemId, List<Contract> contracts) {
    if (item != null) {
      basicInformationDisplay(item, itemId);
      if (!contracts.isEmpty()) {
        System.out.println("\nContract for this item: ");
        for (Contract contract : contracts) {
          System.out.println("Contract ID: " + contract.getContractId());
          System.out.println("Borrower: " + contract.getBorrower());
          System.out.println("Lender: " + contract.getLender());
          System.out.println("Start date: " + contract.getStartDate());
          System.out.println("End date: " + contract.getEndDate());
          System.out.println("Total cost: " + contract.getTotalCost());
          System.out.println();
        }
      } else {
        System.out.println("There are no contracts found for this item.");
      }
    } else {
      System.out.println("Item with ID " + itemId + " is not found.");
    }
  }
}
