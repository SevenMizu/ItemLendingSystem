package controller;

import java.util.List;
import model.Contract;
import model.DataHandler;
import model.DayCounter;
import model.Item;
import model.ItemCategories;
import model.Member;
import view.ViewItem;

/**
 * Class to handle Item methods.
 */
public class ItemController {
  ViewItem viewItem;
  UserInterface userInterface;
  DataHandler dataHandler;
  DayCounter dayCounter;

  /**
   * The constructor for item controller.
   *
   * @param viewItem The item view.
   * @param userintface The user interface.
   * @param dataHandler The data handler.
   * @param dayCounter The day counter.
   */
  public ItemController(ViewItem viewItem, UserInterface userintface, DataHandler dataHandler, DayCounter dayCounter) {
    this.viewItem = viewItem;
    this.userInterface = userintface;
    this.dataHandler = dataHandler;
    this.dayCounter = dayCounter;
  }

  private enum ItemOptions {
    CREATE_ITEM,
    LIST_ITEMS,
    DELETE,
    CHANGE,
    VIEW,
    BACK;
  }

  private enum ChangeItemInfo {
    NAME,
    DESC,
    CATEGORY,
    COST,
    BACK;
  }

  /**
   * Method to handle user options.
   */
  public void itemManangementHandler() {
    boolean continueManagingItems = true;

    while (continueManagingItems) {
      viewItem.itemMenuDisplay();
      ItemOptions option = itemManagementMenuDisplay();

      switch (option) {
        case CREATE_ITEM:
          String email = viewItem.ownerEmailDisplay();
          Member owner = dataHandler.getMember().findMemberByEmail(email);
          if (owner != null) {
            ItemCategories category = viewItem.chooseCategory();
            String name = viewItem.itemNameDisplay();
            String description = viewItem.itemDescriptionDisplay();
            int costPerDay = viewItem.itemCostDisplay();
            Item newItem = dataHandler.getItem().registerNewItem(category, name, description, costPerDay, owner);
            dataHandler.getMember().updateMember(owner);
            continueManagingItems = viewItem.itemCreateDisplay(newItem);
          } else {
            System.out.println("Member cannot be found in this Member ID.");
          }
          break;
        case LIST_ITEMS:
          List<String> iteList = dataHandler.getItem().getEveryItems();
          viewItem.itemViewDisplay(iteList);
          break;
        case DELETE:
          String deleteId = viewItem.itemIdDisplay();
          Item deleteItem = dataHandler.getItem().findItemById(deleteId);
          Member itemOwner = deleteItem.getOwner();
          dataHandler.getItem().removeItemById(deleteId, itemOwner);
          viewItem.itemDeleteDisplay(deleteItem);
          break;
        case CHANGE:
          changeItemInformation();
          break;
        case VIEW:
          String itemId = viewItem.itemIdDisplay();
          Item item = dataHandler.getItem().findItemById(itemId);
          viewItem.basicInformationDisplay(item, itemId);
          List<Contract> contracts = item.getContract(dayCounter.getCurrentDate());
          viewItem.furtherInformationDisplay(item, itemId, contracts);
          break;
        case BACK:
          continueManagingItems = false;
          userInterface.mainMenu();
          break;
        default:
          throw new IllegalArgumentException("User input incorrect!");
      }
    }
  }

  private ItemOptions itemManagementMenuDisplay() {
    while (true) {
      int selectedOption = viewItem.integerCheck();

      switch (selectedOption) {
        case 1:
          return ItemOptions.CREATE_ITEM;
        case 2:
          return ItemOptions.LIST_ITEMS;
        case 3:
          return ItemOptions.DELETE;
        case 4:
          return ItemOptions.CHANGE;
        case 5:
          return ItemOptions.VIEW;
        case 6:
          return ItemOptions.BACK;
        default:
          System.out.println("Invalid option selected. Please enter 1, 2, 3, 4, 5 or 6.");
          viewItem.itemMenuDisplay();
      }

    }

  }

  private void changeItemInformation() {
    viewItem.changeInformationDisplay();
    ChangeItemInfo option = itemInfoChangeDisplay();
    String changeId = viewItem.itemIdDisplay();

    switch (option) {
      case NAME:
        String newName = viewItem.changeNameDisplay(changeId);
        dataHandler.getItem().updateNewName(changeId, newName);
        break;
      case DESC:
        String newDesc = viewItem.changeDescriptionDisplay(changeId);
        dataHandler.getItem().updateNewDescription(changeId, newDesc);
        break;
      case CATEGORY:
        ItemCategories newCategory = viewItem.changeCategoryDisplay(changeId);
        dataHandler.getItem().updateNewCategory(changeId, newCategory);
        break;
      case COST:
        int newCost = viewItem.changeCostDisplay(changeId);
        dataHandler.getItem().updateNewCost(changeId, newCost);
        break;
      case BACK:
        itemManangementHandler();
        break;
      default:
        throw new IllegalArgumentException("Invalid intput! ");
    }
  }

  private ChangeItemInfo itemInfoChangeDisplay() {
    int selectedOption = viewItem.integerCheck();

    switch (selectedOption) {
      case 1:
        return ChangeItemInfo.NAME;
      case 2:
        return ChangeItemInfo.DESC;
      case 3:
        return ChangeItemInfo.CATEGORY;
      case 4:
        return ChangeItemInfo.COST;
      case 5:
        return ChangeItemInfo.BACK;
      default:
        throw new IllegalArgumentException("Wrong input! ");
    }
  }
}
