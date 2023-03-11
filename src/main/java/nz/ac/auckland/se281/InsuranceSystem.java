package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  // Might need to remove
  ArrayList<String> databaseNames = new ArrayList<String>();
  ArrayList<String> databaseAge = new ArrayList<String>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {

    int numberUsers = databaseNames.size();

    if (numberUsers == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
      return;
    }

    if (numberUsers == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
    } else {
      String numberUsersStr = String.valueOf(numberUsers);
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(numberUsersStr, "s", ":");
    }

    // Print out list of users
    for (int i = 0; i < numberUsers; i++) {
      MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
          String.valueOf(i + 1), databaseNames.get(i), databaseAge.get(i));
    }

    // MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberProfiles),
    // "s", ":");

  }

  public void createNewProfile(String userName, String age) {
    // Check what error to give if both name and age are wrong

    // Convert user name to title case
    userName = userName.substring(0, 1).toUpperCase() + userName.substring(1).toLowerCase();

    // Check if profile already exists
    if (databaseNames.contains(userName)) {
      MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
      return;
    }

    // Check if length of user name is at least 3 characters
    if (userName.length() >= 3) {

      int intAge = Integer.parseInt(age); // What the heck does this do though?

      // Check if age is a positive integer. If it is, add the information to the database
      if (intAge > 0) {
        MessageCli.PROFILE_CREATED.printMessage(userName, age);
        databaseNames.add(userName);
        databaseAge.add(age);
      } else {
        MessageCli.INVALID_AGE.printMessage(age, userName);
      }
    } else {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
    }

    // DELETE LATER
    // System.out.println(databaseNames);
    // System.out.println(databaseAge);
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
