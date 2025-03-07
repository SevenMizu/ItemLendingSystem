package model;

import java.util.Random;

/**
 * This class focuses on handling contract information.
 */
public class Contract {
  private String contractId;
  private int startDate;
  private int endDate;
  private Member borrower;
  private Member lender;
  private Item item;
  private double totalCost;
  private Random random = new Random();
  
  /**
   * Constructor for Contract.
   *
   * @param newStartDate The start date of the contract.
   * @param newEndDate The end date of the contract.
   * @param newBorrower The borrower of the contract.
   * @param newLender The lender of the contract.
   * @param newItem The item involved in the contract.
   */
  public Contract(int newStartDate, int newEndDate, Member newBorrower, Member newLender, Item newItem) {
    this.contractId = generateContractId();
    this.startDate = newStartDate;
    this.endDate = newEndDate;
    this.borrower = new Member(newLender);
    this.lender = new Member(newBorrower);
    this.item = new Item(newItem);
    this.totalCost = calcTotalCost();
  }

  /**
   * Copy constructor for the Contract.
   *
   * @param other The contract to copy from.
   */
  public Contract(Contract other) {
    this.contractId = other.contractId;
    this.startDate = other.startDate;
    this.endDate = other.endDate;
    this.borrower = other.borrower;
    this.lender = other.lender;
    this.item = other.item;
    this.totalCost = other.totalCost;
  }

  private String generateContractId() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    char[] contractId = new char[6];
    for (int i = 0; i < 6; i++) {
      int randomIndex = random.nextInt(characters.length());
      contractId[i] = characters.charAt(randomIndex);
    }
    return new String(contractId);
  }

  public String getContractId() {
    return contractId;
  }

  /**
   * Check if the contract is active.
   *
   * @param currentDate The current date.
   * @return True if the contract is active, false otherwise.
   */
  public boolean isActive(int currentDate) {
    if (startDate <= currentDate && endDate >= currentDate) {
      return true;
    }
    return false;
  }

  /**
   * Calculate the total cost of the contract.
   *
   * @return The total cost of the contract.
   */
  public double calcTotalCost() {
    int dayCounts = endDate - startDate;
    double costPerDay = item.getCost();
    return costPerDay * dayCounts;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public Item getItem() {
    return new Item(this.item);
  }

  public Member getLender() {
    return new Member(this.lender);
  }

  public Member getBorrower() {
    return new Member(this.borrower);
  }

  public void setStartDate(int currentDate) {
    this.startDate = currentDate;
  }

  public int getStartDate() {
    return startDate;
  }

  public void setEndDate(int currentDate) {
    this.endDate = currentDate;
  }

  public int getEndDate() {
    return endDate;
  }
}
