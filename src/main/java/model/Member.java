package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class focuses on handling member information.
 */
public class Member {
  private String name;
  private String email;
  private String phoneNumber;
  private String memberId;
  private int credits;
  private List<Item> ownedItems;
  private Random random = new Random();

  /**
   * Constructor for the member.
   * That has a new name, new email and new phone number.
   */

  public Member(String newName, String newEmail, String newPhoneNumber) {
    this.name = newName;
    this.email = newEmail;
    this.phoneNumber = newPhoneNumber;
    this.memberId = generateMemberId();
    this.ownedItems = new ArrayList<>();
    this.credits = 0;
  }

  /**
   * This is the constuctor copy.
   */

  public Member(Member other) {
    this.name = other.name;
    this.email = other.email;
    this.phoneNumber = other.phoneNumber;
    this.memberId = other.memberId;
    this.ownedItems = new ArrayList<>(other.ownedItems);
    this.credits = other.credits;
  }

  public String getName() {
    return name;
  }

  public void updateName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void updateEmail(String newEmail) {
    this.email = newEmail;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void updatePhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  private String generateMemberId() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    char[] memberId = new char[6];

    for (int i = 0; i < 6; i++) {
      int randomIndex = random.nextInt(characters.length());
      memberId[i] = characters.charAt(randomIndex);
    }

    return new String(memberId);
  }

  public String getMemberId() {
    return memberId;
  }

  public void addCredits(int amount) {
    credits += amount;
  }

  public void deductCredits(int amount) {
    credits -= amount;
  }

  public int getCredits() {
    return credits;
  }

  /**
   * This connects the item that the member owns back to them.
   *
   */

  public void addItem(Item item) {
    ownedItems.add(item);
    this.credits += 100;
  }

  public List<Item> getOwnedItems() {
    return new ArrayList<>(ownedItems);
  }

  public int getOwnedItemCount() {
    return ownedItems.size();
  }

  public void removeOwnedItem(Item item) {
    ownedItems.remove(item);
  }
}
