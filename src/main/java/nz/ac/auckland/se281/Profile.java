package nz.ac.auckland.se281;

public class Profile {
  private String name;
  private int age;

  public Profile(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getAgeStr() {
    String ageStr = Integer.toString(age);
    return ageStr;
  }

  public String getName() {
    String name = this.name;
    return name;
  }
}
