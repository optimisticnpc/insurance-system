package nz.ac.auckland.se281;

public class HomePolicy extends Policy {
  private String address;
  private boolean rental;

  public HomePolicy(int sumInsured, String address, boolean rental) {
    super(sumInsured);
    this.address = address;
    this.rental = rental;
  }

  // @Override
  public void printPolicy() {

    // TODO: Should this be double??
    int intPremium = 0;

    // Check if it is a rental or not and adjust premium accordingly
    if (rental == false) {
      intPremium = (int) (0.01 * sumInsured);
    } else {
      intPremium = (int) (0.02 * sumInsured);
    }

    String premium = Integer.toString(intPremium);

    MessageCli.PRINT_DB_HOME_POLICY.printMessage(
        address, Integer.toString(sumInsured), premium, premium);
  }
}
