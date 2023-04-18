package nz.ac.auckland.se281;

public class CarPolicy extends Policy {
  private String makeAndModel;
  private String licensePlate;
  private boolean mechanicalBreakdown;

  public CarPolicy(
      int sumInsured,
      String makeAndModel,
      String licensePlate,
      boolean mechanicalBreakdown,
      int age) {
    super(sumInsured);
    this.makeAndModel = makeAndModel;
    this.licensePlate = licensePlate;
    this.mechanicalBreakdown = mechanicalBreakdown;

    // If the client is under 25 years old, 15% of the sum insured.
    if (age < 25) {
      premiumBeforeDiscount = (int) (0.15 * sumInsured);
    } else {
      // If the client is 25 years old or older,  10% of the sum insured.
      premiumBeforeDiscount = (int) (0.1 * sumInsured);
    }

    // If car is to be covered for mechanical breakdown, add $80
    if (this.mechanicalBreakdown == true) {
      premiumBeforeDiscount = premiumBeforeDiscount + 80;
    }
  }

  // TODO: DOES IT HAVE TO OVERIDE
  @Override
  public void printPolicy(int age, int numberPolicies) {

    // Find discounted premium
    premiumAfterDiscount = findDiscount(premiumBeforeDiscount, numberPolicies);
    String premiumAfterDiscountString = Integer.toString(premiumAfterDiscount);

    String premiumBeforeDiscountString = Integer.toString(premiumBeforeDiscount);

    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
        makeAndModel,
        Integer.toString(sumInsured),
        premiumBeforeDiscountString,
        premiumAfterDiscountString);
  }
}
