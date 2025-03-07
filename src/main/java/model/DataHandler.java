package model;

/**
 * This class handles data management.
 */
public class DataHandler {
  private final MemberRegistration member;
  private final ItemRegistration item;
  private final ContractRegistration contract;

  /**
   * Constructor for the data handler.
   *
   * @param dateTracker The day counter, which is the time tracker.
   */
  public DataHandler(DayCounter dateTracker) {
    member = new MemberRegistration(dateTracker);
    item = new ItemRegistration(dateTracker);
    contract = new ContractRegistration(dateTracker);
  }

  public MemberRegistration getMember() {
    return member;
  }

  public ItemRegistration getItem() {
    return item;
  }

  public ContractRegistration getContract() {
    return contract;
  }

  /**
   * Inbuild data in the database.
   */
  public void insetData() {
    Member member1 = member.registerNewMember("Kevin", "ks111@gmail.com", "0736587412");
    Member member2 = member.registerNewMember("Alex", "al222@gmail.com", "0720283883");
    Member member3 = member.registerNewMember("Joseph", "jm333@gmail.com", "0708788512");

    //final Item item1 = item.registerNewItem(null, null, null, 0, member1);
    // Create items for member1
    final Item item1 = item.registerNewItem(ItemCategories.TOOL, "Hammer", "Durable hammer", 50, member1);
    item.registerNewItem(ItemCategories.TOOL, "Screwdriver", "Cheap screwdriver", 10, member1);
    final Item item2 = item.registerNewItem(ItemCategories.SPORT, "Baseball", "Training use baseball", 10, member1);
    item.registerNewItem(ItemCategories.GAME, "PS3", "Playstation 3", 10, member2);
    final Item item3 = item.registerNewItem(ItemCategories.OTHER, "Jacket", "A black winter jacket", 5, member2);
    item.registerNewItem(ItemCategories.VEHICHLE, "Scooter", "Eletric scooter", 15, member3);
    item.registerNewItem(ItemCategories.TOY, "LEGO", "A pack of lego", 5, member3);
    // Display to verify

    // gave each member 500 credits
    member1.addCredits(500);
    member2.addCredits(500);
    member3.addCredits(500);

    contract.createNewContract(member1, member2, item1, 7);
    contract.createNewContract(member1, member3, item2, 5);
    contract.createNewContract(member3, member1, item3, 1);

  }
}
