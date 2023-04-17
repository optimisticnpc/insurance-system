package nz.ac.auckland.se281;

public class HomePolicy extends Policy {
  private String address;
  private boolean rental;

  public HomePolicy(int sumInsured, String address, boolean rental) {
    super(sumInsured);
    this.address = address;
    this.rental = rental;

    // TODO: Should this be double??
    // Check if it is a rental or not and adjust premium accordingly
    if (rental == false) {
      premiumBeforeDiscount = (int) (0.01 * sumInsured);
    } else {
      premiumBeforeDiscount = (int) (0.02 * sumInsured);
    }
  }

  @Override
  public void printPolicy(int age, int numberPolicies) {

    // Find discounted premium
    premiumAfterDiscount = findDiscount(premiumBeforeDiscount, numberPolicies);

    String premiumAfterDiscountString = Integer.toString(premiumAfterDiscount);

    String premiumBeforeDiscountString = Integer.toString(premiumBeforeDiscount);

    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
        address,
        Integer.toString(sumInsured),
        premiumBeforeDiscountString,
        premiumAfterDiscountString);
  }
}
