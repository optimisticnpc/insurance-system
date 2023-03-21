package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<Profile> database = new ArrayList<Profile>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    int numberProfiles = database.size();

    // Case for 0 names in database
    if (numberProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
      return;
    }

    // Case for 1 or more names in database
    if (numberProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("1", "", ":");
    } else {
      // Conver the number of profiles into a string for display
      String numberProfilesStr = String.valueOf(numberProfiles);
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(numberProfilesStr, "s", ":");
    }

    // Print out list of profiles
    for (int i = 0; i < numberProfiles; i++) {
      Profile currentProfile = database.get(i);
      MessageCli.PRINT_DB_PROFILE_HEADER_MINIMAL.printMessage(
          String.valueOf(i + 1), currentProfile.getName(), currentProfile.getAgeStr());
    }
  }

  public void createNewProfile(String username, String age) {
    // Note: Method does not work if user has entered a $ symbol in any of the arguments

    // Convert username to title case
    username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();

    // Check if profile already exists
    for (int i = 0; i < database.size(); i++) {
      String currentUsername = database.get(i).getName();
      if (currentUsername.equals(username)) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(username);
        return;
      }
    }

    // Check if length of username is at least 3 characters
    if (username.length() >= 3) {

      // Check if the age input can be converted to an integer
      try {
        Integer.parseInt(age);
      } catch (NumberFormatException e) {
        MessageCli.INVALID_AGE.printMessage(age, username);
        return;
      }

      int intAge = Integer.parseInt(age);

      // Check if age is a positive integer. If it is, add the new profile to the database
      if (intAge > 0) {
        MessageCli.PROFILE_CREATED.printMessage(username, age);
        database.add(new Profile(username, intAge));
      } else {
        MessageCli.INVALID_AGE.printMessage(age, username);
      }
    } else {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(username);
    }
  }

  public void loadProfile(String username) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String username) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
