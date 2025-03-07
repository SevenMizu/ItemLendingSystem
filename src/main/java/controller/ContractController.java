package controller;

import java.util.List;
import model.Contract;
import model.DataHandler;
import model.Item;
import model.Member;
import view.ViewContract;

/**
 * Class handling state of the contract menu.
 *
 * @param c Contract viewContract.
 * @param d Data.
 * @param ui User interface.
 */
public class ContractController {
  private final ViewContract viewContract;
  private final DataHandler dataHandler;
  private final UserInterface userInterface;

  /**
   * Constructor.
   *
   * @param viewContract The viewContract.
   * @param userInterface The userInterface.
   * @param dataHandler The dataHandler.
   */
  public ContractController(ViewContract viewContract, UserInterface userInterface, DataHandler dataHandler) {
    this.viewContract = viewContract;
    this.userInterface = userInterface;
    this.dataHandler = dataHandler;
  }

  private enum ContractOptions {
    CREATE,
    LIST,
    BACK;
  }

  /**
   * Method to handle user options.
   */
  public void handleContractManagement() {
    boolean continueManagingContracts = true;

    while (continueManagingContracts) {
      viewContract.contractMenuDisplay();
      ContractOptions option = displayContractManagementMenu();

      switch (option) {
        case CREATE:
          continueManagingContracts = createNewContract();
          break;
        case LIST:
          List<Contract> contracts = dataHandler.getContract().getActiveContracts();
          viewContract.contractListDisplay(contracts);
          break;
        case BACK:
          continueManagingContracts = false;
          userInterface.mainMenu();
          break;
        default:
          throw new IllegalArgumentException("Invalid input! ");
      }
    }
  }

  private ContractOptions displayContractManagementMenu() {
    while (true) {
      int selectedOption = viewContract.integerCheck();
      switch (selectedOption) {
        case 1:
          return ContractOptions.CREATE;
        case 2:
          return ContractOptions.LIST;
        case 3:
          return ContractOptions.BACK;
        default:
          System.out.println("Invalid choice. Please try agian");
          viewContract.contractMenuDisplay(); 
      }
    }
  }

  private boolean createNewContract() {
    String borrowerId = viewContract.addBorrowerDisplay();
    Member borrower = dataHandler.getMember().findMemberByEmail(borrowerId);

    if (borrower != null) {
      String itemId = viewContract.addItemDisplay();
      int returnDay = viewContract.returnDateDisplay();
      Item item = dataHandler.getItem().findItemById(itemId);
      if (item != null) {
        Member lender = item.getOwner();
        Contract newContract = dataHandler.getContract().createNewContract(borrower, lender, item, returnDay);
        if (newContract != null) {
          System.out.println("Contract created. Contract ID =  " + newContract.getContractId());
        }
      } else {
        System.out.println("Item not found. Please try again");
      }
    } else {
      System.out.println("Borrower not found. Please try agaiin");
    }
    return true;
  }
}
