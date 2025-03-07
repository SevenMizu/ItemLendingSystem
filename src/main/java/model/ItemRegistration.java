package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles item management.
 */
public class ItemRegistration {
  private Map<String, Item> items = new HashMap<>();
  private DayCounter dateTracker;

  public ItemRegistration(DayCounter dateTracker) {
    this.dateTracker = dateTracker;
  }

  /**
   * Registers a new item.
   *
   * @param category Category of the item.
   * @param name Name of the item.
   * @param description Description of the item.
   * @param cost Cost of the item.
   * @param owner Owner of the item.
   * @return The created Item object if successful, or null if the item could not be created.
   */
  public Item registerNewItem(ItemCategories category, String name, String description, int cost, Member owner) {
    if (owner != null) {
      Item newItem = new Item(category, name, description, cost, owner);
      owner.addItem(newItem);
      items.put(newItem.getItemId(), newItem);
      int currentDate = dateTracker.getCurrentDate();
      System.out.println("Item: " + newItem.getName() + " registered on day " + currentDate);
      return newItem;
    } else {
      System.out.println("Error: Owner not found.");
      return null;
    }
  }

  /**
   * Finds an item by its ID.
   *
   * @param itemId The ID of the item.
   * @return The item if found, or null if not found.
   */
  public Item findItemById(String itemId) {
    return items.get(itemId);
  }
  
  /**
   * Update the category of an item.
   *
   * @param itemId The ID of the item.
   * @param newCategory The new category.
   * @return True if the item was found and updated, false otherwise.
   */
  public boolean updateNewCategory(String itemId, ItemCategories newCategory) {
    Item item = findItemById(itemId);
    if (item != null) {
      item.updateCategoery(newCategory);
      items.put(item.getItemId(), item);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Update the name of an item.
   *
   * @param itemId The ID of the item.
   * @param newName The new name.
   * @return True if the item was found and updated, false otherwise.
   */
  public boolean updateNewName(String itemId, String newName) {
    Item item = findItemById(itemId);
    if (item != null) {
      item.updateName(newName);
      items.put(item.getItemId(), item);
      return true;
    } else { 
      return false;
    }
  }

  /**
   * Update the description of an item.
   *
   * @param itemId The ID of the item.
   * @param newDescription The new description.
   * @return True if the item was found and updated, false otherwise.
   */
  public boolean updateNewDescription(String itemId, String newDescription) {
    Item item = findItemById(itemId);
    if (item != null) {
      item.updateDescription(newDescription);
      items.put(item.getItemId(), item);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Update the cost of an item.
   *
   * @param itemId The ID of the item.
   * @param newCost The new cost.
   * @return True if the item was found and updated, false otherwise.
   */
  public boolean updateNewCost(String itemId, int newCost) {
    Item item = findItemById(itemId);
    if (item != null) {
      item.updateCost(newCost);
      items.put(item.getItemId(), item);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Removes an item based on its ID and owner.
   *
   * @param itemId The ID of the item.
   * @param owner The owner of the item.
   * @return True if the item was found and removed, false otherwise.
   */
  public boolean removeItemById(String itemId, Member owner) {
    System.out.println(owner.getName());
    Item item = findItemById(itemId);
    if (item != null) {
      owner.removeOwnedItem(item);
      items.remove(item.getItemId());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Get all items.
   *
   * @return A list of all items.
   */
  public List<String> getEveryItems() {
    List<String> itemsList = new ArrayList<>();
    for (Item item : items.values()) {
      String itemdetail = "Item: " + item.getName() + ", Item ID: " + item.getItemId() + ",  Owner(ID): " 
          + item.getOwner().getMemberId();
      itemsList.add(itemdetail);
    }
    return itemsList;
  }
}
