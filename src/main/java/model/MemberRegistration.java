package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles member registration.
 */
public class MemberRegistration {
  private Map<String, Member> members = new HashMap<>();
  private DayCounter dayCounter;

  /**
   * Initializes the member registry and day counter.
   *
   * @param dayCounter A reference to track the current date.
   */
  public MemberRegistration(DayCounter dayCounter) {
    this.dayCounter = dayCounter;
  }

  /**
   * Registers a new member if the provided email and phone number are unique.
   *
   * @param name The member's name.
   * @param email The member's email address.
   * @param phoneNumber The member's phone number.
   * @return The newly created member, or null if email or phone number is not unique.
   */
  public Member registerNewMember(String name, String email, String phoneNumber) {
    if (isEmailUnique(email) && isPhoneNumberUnique(phoneNumber)) {
      Member newMember = new Member(name, email, phoneNumber);
      members.put(email, newMember);  // Use email as the unique key for members
      int creationDay = dayCounter.getCurrentDate();
      System.out.println("Member with ID " + newMember.getMemberId() + " registered on day " + creationDay);
      return newMember;
    } else {
      System.out.println("Error: Email or phone number is not unique.");
      return null;
    }
  }

  /**
   * Updates a member's information.
   *
   * @param updatedMember The member object with updated details.
   */
  public void updateMember(Member updatedMember) {
    members.put(updatedMember.getEmail(), updatedMember);  // Overwrite with updated member info
  }

  /**
   * Checks if the provided email is unique.
   *
   * @param email The email to check.
   * @return True if the email is unique, false otherwise.
   */
  private boolean isEmailUnique(String email) {
    return !members.containsKey(email);
  }

  /**
   * Checks if the provided phone number is unique.
   *
   * @param phoneNumber The phone number to check.
   * @return True if the phone number is unique, false otherwise.
   */
  private boolean isPhoneNumberUnique(String phoneNumber) {
    for (Member member : members.values()) {
      if (member.getPhoneNumber().equals(phoneNumber)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Updates a member's name.
   *
   * @param email The email of the member.
   * @param newName The new name to assign to the member.
   * @return True if the update was successful, false otherwise.
   */
  public boolean updateMemberName(String email, String newName) {
    Member member = findMemberByEmail(email);
    if (member != null) {
      member.updateName(newName);
      updateMember(member);
      return true;
    }
    return false;
  }

  /**
   * Updates a member's email.
   *
   * @param currentEmail The current email of the member.
   * @param newEmail The new email to assign to the member.
   * @return True if the update was successful, false otherwise.
   */
  public boolean updateMemberEmail(String currentEmail, String newEmail) {
    Member member = findMemberByEmail(currentEmail);
    if (member != null) {
      member.updateEmail(newEmail);
      updateMember(member);
      return true;
    }
    return false;
  }

  /**
   * Updates a member's phone number.
   *
   * @param email The email of the member.
   * @param newPhoneNumber The new phone number to assign to the member.
   * @return True if the update was successful, false otherwise.
   */
  public boolean updateMemberPhoneNumber(String email, String newPhoneNumber) {
    Member member = findMemberByEmail(email);
    if (member != null) {
      member.updatePhoneNumber(newPhoneNumber);
      updateMember(member);
      return true;
    }
    return false;
  }


  /**
   * Retrieves all registered members.
   *
   * @return A list of all registered members.
   */
  public List<Member> getAllMembers() {
    List<Member> memberCopies = new ArrayList<>();
    for (Member member : members.values()) {
      memberCopies.add(new Member(member));

    }
    return memberCopies;
  }

  /**
   * Removes a member based on their email.
   *
   * @param email The email of the member to remove.
   * @return True if the member was successfully removed, false otherwise.
   */
  public boolean removeMemberByEmail(String email) {
    if (members.remove(email) != null) {
      return true;
    }
    return false;
  }

  /**
   * Finds a member by their email.
   *
   * @param email The email to search for.
   * @return The member object if found, or null if not found.
   */
  public Member findMemberByEmail(String email) {
    Member originalMember = members.get(email);
    return (originalMember != null) ? new Member(originalMember) : null;
    

  }
}
