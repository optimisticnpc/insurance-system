package nz.ac.auckland.se281;

public class CarPolicy extends Policy {
  private String makeAndModel;
  private String licensePlate;
  private boolean mechanicalBreakdown;

  public CarPolicy(
      int sumInsured, String makeAndModel, String licensePlate, boolean mechanicalBreakdown) {
    super(sumInsured);
    this.makeAndModel = makeAndModel;
    this.licensePlate = licensePlate;
    this.mechanicalBreakdown = mechanicalBreakdown;
  }

  // TODO: DOES IT HAVE TO OVERIDE
  public void printPolicy(int age) {

    int intPremium = 0;

    // If the client is under 25 years old, 15% of the sum insured.
    if (age < 25) {
      intPremium = (int) (0.15 * sumInsured);
    } else {
      // If the client is 25 years old or older,  10% of the sum insured.
      intPremium = (int) (0.1 * sumInsured);
    }

    // If car is to be covered for mechanical breakdown, add $80
    if (mechanicalBreakdown == true) {
      intPremium = intPremium + 80;
    }

    String premium = Integer.toString(intPremium);

    MessageCli.PRINT_DB_CAR_POLICY.printMessage(
        makeAndModel, Integer.toString(sumInsured), premium, premium);
  }
}
