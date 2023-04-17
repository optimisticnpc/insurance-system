package nz.ac.auckland.se281;

public abstract class Policy {
  protected int sumInsured;
  int premiumBeforeDiscount;
  int premiumAfterDiscount;

  public Policy(int sumInsured) {
    this.sumInsured = sumInsured;
  }

  public int findDiscount(int premium, int numberPolicies) {
    // If cilient has two policies, reduce by 10%
    if (numberPolicies == 2) {
      return (int) (premium * 0.9);

      // If more than two policies, reduce by 20%
    } else if (numberPolicies > 2) {
      return (int) (premium * 0.8);
    }
    // Otherwise return original premium
    return premium;
  }

  public abstract void printPolicy(int age, int numberPolicies);

  public int getPremiumAfterDiscount() {
    return premiumAfterDiscount;
  }
}
