package nz.ac.auckland.se281;

public class LifePolicy extends Policy {

  public LifePolicy(int sumInsured, int age) {
    super(sumInsured);
    double percentPremium;

    // TODO; Do I need these .0s
    percentPremium = (1 + age / 100.0) / 100.0;

    premiumBeforeDiscount = (int) (sumInsured * percentPremium);
  }

  @Override
  public void printPolicy(int age, int numberPolicies) {

    // Find discounted premium
    premiumAfterDiscount = findDiscount(premiumBeforeDiscount, numberPolicies);
    String premiumAfterDiscountString = Integer.toString(premiumAfterDiscount);
    String premiumBeforeDiscountString = Integer.toString(premiumBeforeDiscount);

    MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
        Integer.toString(sumInsured), premiumBeforeDiscountString, premiumAfterDiscountString);
  }
}
