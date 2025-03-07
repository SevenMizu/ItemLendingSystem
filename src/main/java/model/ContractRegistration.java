package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class handles contract management between members and items.
 * It provides methods to create and retrieve contracts.
 */
public class ContractRegistration {
  private Map<String, Contract> contractRegistry;  // A map to store contracts, keyed by contract ID
  private DayCounter dateTracker;  // Tracks the current day for the system

  /**
   * Constructs a new ContractRegistration with the specified DayCounter for handling time-based operations.
   *
   * @param dateTracker The DayCounter object for managing the system date.
   */
  public ContractRegistration(DayCounter dateTracker) {
    this.dateTracker = dateTracker;
    this.contractRegistry = new HashMap<>();
  }

  /**
   * Creates a new contract between a borrower and a lender for a specific item.
   *
   * @param borrower The member borrowing the item.
   * @param lender The member lending the item.
   * @param item The item being borrowed.
   * @param contractEndDate The end date of the contract.
   * @return The created Contract object if successful, or null if the contract could not be created.
   */
  public Contract createNewContract(Member borrower, Member lender, Item item, int contractEndDate) {
    int contractStartDate = dateTracker.getCurrentDate();
    // Check if the lender has enough credits to cover the contract duration
    int contractDuration = contractEndDate - contractStartDate;
    int totalCost = contractDuration * item.getCost();
    if (lender.getCredits() < totalCost) {
      System.out.println("Error: Lender does not have enough credits to create this contract.");
      return null;
    }
    // Verify if the item is available during the specified time period
    if (!item.isAvailable(dateTracker.getCurrentDate())) {
      System.out.println("Error: The item is not available for the requested period.");
      return null;
    }
    // Update lender's and borrower's credits
    lender.deductCredits(totalCost);
    borrower.addCredits(totalCost);
    // Create the contract and update the item's contract history
    Contract newContract = new Contract(contractStartDate, contractEndDate, borrower, lender, item);
    item.addContract(newContract);
    contractRegistry.put(newContract.getContractId(), newContract);
    return newContract;
  }

  /**
   * Retrieves a list of all active contracts in the system.
   * Inactive contracts are automatically removed.
   *
   * @return A list of active Contract objects.
   */
  public List<Contract> getActiveContracts() {
    List<Contract> activeContractsList = new ArrayList<>();
    Iterator<Map.Entry<String, Contract>> contractIterator = contractRegistry.entrySet().iterator();
    // Iterate over all contracts and filter out inactive ones
    while (contractIterator.hasNext()) {
      Map.Entry<String, Contract> contractEntry = contractIterator.next();
      Contract currentContract = contractEntry.getValue();
      if (currentContract.isActive(dateTracker.getCurrentDate())) {
        activeContractsList.add(currentContract);
      } else {
        System.out.println("Removing inactive contract with ID: " + currentContract.getContractId());
        contractIterator.remove();  // Remove inactive contracts from the registry
      }
    }
    return activeContractsList;
  }
}
