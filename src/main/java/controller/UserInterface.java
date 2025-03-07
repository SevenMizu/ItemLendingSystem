package controller;

import model.DataHandler;
import model.DayCounter;
import view.ViewContract;
import view.ViewItem;
import view.ViewMember;
import view.ViewMenu;

/**
 * Main scenario controller for the app.
 */
public class UserInterface {
  DayCounter day;
  ViewMenu viewMenu;
  MemberController memberc;
  ItemController itemc;
  ContractController contractc;
  

  private enum MainMenuOptions {
    MEMBER_MANAGEMENT,
    CONTRACT_MANAGEMENT,
    ITEM_MANAGEMENT,
    ADVANCE_TIME,
    EXIT;
  }

  /**
   * The constructor for the UserInterface.
   *
   * @param a The menu view.
   * @param b The member view.
   * @param c The item view.
   * @param d The contract view.
   * @param e The day counter.
   * @param f The data handler.
   */
  public UserInterface(ViewMenu a, ViewMember b, ViewItem c, ViewContract d, DayCounter e, DataHandler f) {
    memberc = new MemberController(b, this, f);
    contractc = new ContractController(d, this, f);
    itemc = new ItemController(c, this, f, e);
    day = e;
    viewMenu = a;
  }

  /**
   * Main menu options.
   */
  public boolean mainMenu() {
    viewMenu.timeShowDisplay();
    viewMenu.menuDisplay();

    MainMenuOptions option = displayMainMenuOptions();

    switch (option) {
      case MEMBER_MANAGEMENT:
        memberc.memberManagementHandler();
        break;
      case ITEM_MANAGEMENT:
        itemc.itemManangementHandler();
        break;
      case CONTRACT_MANAGEMENT:
        contractc.handleContractManagement();
        break;
      case ADVANCE_TIME:
        day.advanceDate();
        mainMenu();
        break;
      case EXIT:
        break;
      default:
        throw new IllegalArgumentException("Wrong user input! ");
    }

    return option != MainMenuOptions.EXIT;
  }

  /**
   * Handling displayal of user options.
   * return The chosen option.
   */
  public MainMenuOptions displayMainMenuOptions() {
    while (true) {
      int selectedOption = viewMenu.integerCheck();
      switch (selectedOption) {
        case 1:
          return MainMenuOptions.MEMBER_MANAGEMENT;
        case 2:
          return MainMenuOptions.ITEM_MANAGEMENT;
        case 3:
          return MainMenuOptions.CONTRACT_MANAGEMENT;
        case 4:
          return MainMenuOptions.ADVANCE_TIME;
        case 5:
          return MainMenuOptions.EXIT;
        default:
          System.out.println("Invalid choice.");
          viewMenu.menuDisplay(); 
      }
    }
  }

}
