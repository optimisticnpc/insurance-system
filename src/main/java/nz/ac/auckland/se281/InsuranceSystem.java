package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {

    // int numberProfiles = 0;
    // array list for database

    System.out.println("Database has 0 profiles.");

    MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage();

    // MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");

    // MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberProfiles),
    // "s", ":");

  }

  public void createNewProfile(String userName, String age) {
    // TODO: convert age to integer
    // Check if profile already exists
    // Check if username is at least 3 characters
    // MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
    // Check what error to give if both name and age are wrong

    if (userName.length() >= 3) {

      int intAge = Integer.parseInt(age); // What the heck does this do though?

      if (intAge > 0) {
        MessageCli.PROFILE_CREATED.printMessage(userName, age);
      } else {
        MessageCli.INVALID_AGE.printMessage(age, userName);
      }
    } else {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
    }

  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
