package controller;

import java.util.List;
import model.DataHandler;
import model.Member;
import view.ViewMember;

/**
 * Controller class for member methods.
 */
public class MemberController {
  ViewMember viewMember;
  UserInterface userInterface;
  DataHandler dataHandler;

  /**
   * The constructor for member controller.
   *
   * @param viewMember The member view.
   * @param userInterface The user interface.
   * @param dataHandler The data handler.
   */
  public MemberController(ViewMember viewMember, UserInterface userInterface, DataHandler dataHandler) {
    this.viewMember = viewMember;
    this.userInterface = userInterface;
    this.dataHandler = dataHandler;
  }

  private enum MemberOptions {
    CREATE_MEMBER,
    DELETE_MEMBER,
    VIEW_MEMBER_INFORMATION,
    CHANGE_MEMBER_INFORMATION,
    LIST_MEMBERS_SIMPLE,
    LIST_MEMBERS_VERBOSE,
    BACK;
  }

  private enum ChangeMemberInfo {
    NAME,
    EMAIL,
    PHONE,
    BACK;
  }

  /**
   * Method to handle user options.
   */
  public void memberManagementHandler() {
    boolean continueManagingMembers = true;

    while (continueManagingMembers) {
      viewMember.memberMenuDisplay();

      MemberOptions selectedOption = memberManagementMenuDisplay();

      switch (selectedOption) {
        case CREATE_MEMBER:
          String name = viewMember.memberNameDisplay();
          String newEmail = viewMember.memberEmailDisplay();
          String phoneNumber = viewMember.memberPhoneNumberDisplay();
          Member newMember = dataHandler.getMember().registerNewMember(name, newEmail, phoneNumber);
          if (newMember != null) {
            continueManagingMembers = viewMember.memberCreateDisplay(newMember);
          } else {
            continueManagingMembers = false;
            memberManagementHandler();
          }
          break;
        case DELETE_MEMBER:
          String deleteEmail = viewMember.memberEmailDisplay();
          dataHandler.getMember().removeMemberByEmail(deleteEmail);
          viewMember.memberDeleteDisplay();
          break;
        case VIEW_MEMBER_INFORMATION:
          String email = viewMember.memberEmailDisplay();
          Member member = dataHandler.getMember().findMemberByEmail(email);
          viewMember.memberDetailDisplay(member);
          break;
        case CHANGE_MEMBER_INFORMATION:
          changeMemberInformation();
          break;
        case LIST_MEMBERS_SIMPLE:
          List<Member> members = dataHandler.getMember().getAllMembers();
          viewMember.simpleViewDisplay(members);
          break;
        case LIST_MEMBERS_VERBOSE:
          List<Member> membersVerbose = dataHandler.getMember().getAllMembers();
          viewMember.verboseViewDisplay(membersVerbose);
          break;
        case BACK:
          continueManagingMembers = false;
          userInterface.mainMenu();
          break;
        default:
          System.out.println("Invalid option. Please try again.");
          break;
      }
    }
  }

  private MemberOptions memberManagementMenuDisplay() {
    while (true) {
      int selectedOption = viewMember.integerCheck();

      switch (selectedOption) {
        case 1:
          return MemberOptions.CREATE_MEMBER;
        case 2:
          return MemberOptions.DELETE_MEMBER;
        case 3:
          return MemberOptions.VIEW_MEMBER_INFORMATION;
        case 4:
          return MemberOptions.CHANGE_MEMBER_INFORMATION;
        case 5:
          return MemberOptions.LIST_MEMBERS_SIMPLE;
        case 6:
          return MemberOptions.LIST_MEMBERS_VERBOSE;
        case 7:
          return MemberOptions.BACK;
        default:
          System.out.println("Invalid option selected. Please enter valid optoin!");
          viewMember.memberMenuDisplay();
      }

    }

  }

  private void changeMemberInformation() {
    ChangeMemberInfo option = memberInfoChangeDisplay();
    String changeEmail = "";
    switch (option) {
      case NAME:
        changeEmail = viewMember.memberEmailDisplay();
        String newName = viewMember.changeNameDisplay();
        dataHandler.getMember().updateMemberName(changeEmail, newName);
        break;
      case EMAIL:
        changeEmail = viewMember.memberEmailDisplay();
        String newEmail = viewMember.changeEmailDisplay();
        dataHandler.getMember().updateMemberEmail(changeEmail, newEmail);
        break;
      case PHONE:
        changeEmail = viewMember.memberEmailDisplay();
        String newPhoneNumber = viewMember.changePhoneNumberDisplay();
        dataHandler.getMember().updateMemberPhoneNumber(changeEmail, newPhoneNumber);
        break;
      case BACK:
        break;
      default:
        throw new IllegalArgumentException("Invalid Input! ");
    }
  }

  private ChangeMemberInfo memberInfoChangeDisplay() {
    viewMember.memberChangeDisplay();
    int selectedOption = viewMember.integerCheck();

    switch (selectedOption) {
      case 1:
        return ChangeMemberInfo.NAME;
      case 2:
        return ChangeMemberInfo.EMAIL;
      case 3:
        return ChangeMemberInfo.PHONE;
      case 4:
        return ChangeMemberInfo.BACK;
      default:
        return null;
    }
  }
}