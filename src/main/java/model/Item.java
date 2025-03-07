package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class focuses on handling item information.
 */
public class Item {
  private String itemId;
  private String name;
  private String description;
  private ItemCategories category;
  private Member owner;
  private int cost;
  private Random random = new Random();
  private List<Contract> contracts;
  
  /**
   * Constructor for Item.
   *
   * @param newCategory Category of the item.
   * @param newName Name of the item.
   * @param newDescription Description of the item.
   * @param newCost Cost of the item per day.
   * @param owner Owner of the item.
   */
  public Item(ItemCategories newCategory, String newName, String newDescription, int newCost, Member owner) {
    this.category = newCategory;
    this.name = newName;
    this.description = newDescription;
    this.cost = newCost;
    this.owner = new Member(owner);
    this.itemId = generateItemId();
    contracts = new ArrayList<>();
  }

  /**
   * Copy constructor for Item.
   *
   * @param other Item to be copied.
   */
  public Item(Item other) {
    this.category = other.category;
    this.name = other.name;
    this.description = other.description;
    this.cost = other.cost;
    this.owner = other.owner;
    this.itemId = other.itemId;
    this.contracts = new ArrayList<>(other.contracts);
  }

  public void updateCategoery(ItemCategories category) {
    this.category = category;
  }

  public ItemCategories getCategory() {
    return category;
  } 

  public void updateName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void updateDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void updateCost(int cost) {
    this.cost = cost;
  }

  public int getCost() {
    return cost;
  }

  public Member getOwner() {
    return new Member(this.owner);
  }

  public void removeFromOwner(Member owner) {
    owner.removeOwnedItem(this);
  }

  /**
   * Generate an item ID.
   *
   * @return The generated item ID.
   */
  public String generateItemId() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    char[] itemId = new char[6];
    for (int i = 0; i < 6; i++) {
      int randomIndex = random.nextInt(characters.length());
      itemId[i] = characters.charAt(randomIndex);
    }
    return new String(itemId);
  }

  public String getItemId() {
    return itemId;
  }

  public void addContract(Contract contract) {
    contracts.add(contract);
  }

  /**
   * Check if the item is available.
   *
   * @param currentDate The current date.
   * @return True if the item is available, false otherwise.
   */
  public boolean isAvailable(int currentDate) {
    for (Contract contract : contracts) {
      if (contract.isActive(currentDate)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Get the list of contract involved in the item.
   *
   * @param time The current time.
   * @return The list of contract involved in the item.
   */
  public List<Contract> getContract(int time) {
    List<Contract> involvedContract = new ArrayList<>();
    for (Contract contract : contracts) {
      if (contract.isActive(time)) {
        involvedContract.add(contract);
      }
    }
    return involvedContract;
  }     
}
