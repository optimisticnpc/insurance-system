package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  private String name;
  private int age;
  private ArrayList<Object> profilesPolicies = new ArrayList<Object>();
  private boolean hasLifePolicy;

  public Profile(String name, int age) {
    this.name = name;
    this.age = age;
    hasLifePolicy = false;
  }

  public int getAge() {
    return age;
  }

  public String getAgeStr() {
    String ageStr = Integer.toString(age);
    return ageStr;
  }

  public String getName() {
    String name = this.name;
    return name;
  }

  // TODO: FIX THIS
  public void addPolicy(Object policy) {
    profilesPolicies.add(policy);
  }

  public int getNumberPolicies() {
    return profilesPolicies.size();
  }

  public Object getPolicy(int i) {
    return profilesPolicies.get(i);
  }

  public void updateLifePolicyStatus(boolean status) {
    hasLifePolicy = status;
  }

  public boolean getLifePolicyStatus() {
    return hasLifePolicy;
  }

  public int getTotalPolicyValue() {
    int totalValue = 0;
    // If no policies, return
    int numPolicies = this.getNumberPolicies();
    if (numPolicies == 0) {
      return 0;
    }

    for (int i = 0; i < numPolicies; i++) {
      Policy currentPolicy = (Policy) profilesPolicies.get(i);
      totalValue = currentPolicy.premiumBeforeDiscount + totalValue;
    }
    // Apply discount
    // TODO: IS THIS GOOD PRACTICE
    Policy firstPolicy = (Policy) profilesPolicies.get(0);
    totalValue = firstPolicy.findDiscount(totalValue, numPolicies);

    return totalValue;
  }
}
