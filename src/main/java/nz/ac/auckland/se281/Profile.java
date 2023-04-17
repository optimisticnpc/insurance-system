package nz.ac.auckland.se281;

import java.util.ArrayList;

public class Profile {
  private String name;
  private int age;
  private ArrayList<Object> profilesPolicies = new ArrayList<Object>();

  public Profile(String name, int age) {
    this.name = name;
    this.age = age;
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
}
