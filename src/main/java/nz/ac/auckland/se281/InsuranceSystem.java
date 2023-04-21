package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {

  private ArrayList<Profile> database = new ArrayList<Profile>();
  private int indexLoadedProfile = -1;

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
      // Convert the number of profiles into a string for display
      String numberProfilesString = String.valueOf(numberProfiles);
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(numberProfilesString, "s", ":");
    }

    // Print out list of profiles

    for (int i = 0; i < numberProfiles; i++) {
      Profile currentProfile = database.get(i);

      // Get number of policies for current profile
      int numberPolicies = currentProfile.getNumberPolicies();

      // Get the total value of the policies
      int totalPolicyValue = currentProfile.getTotalPolicyValue();
      String totalPolicyValueString = Integer.toString(totalPolicyValue);

      // Check if profile is loaded profile
      if (i == indexLoadedProfile) {

        // Cases for if the profile is currently loaded
        if (numberPolicies > 1) {
          // Case for more than 1 policy
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              String.valueOf(i + 1),
              currentProfile.getName(),
              currentProfile.getAgeStr(),
              String.valueOf(numberPolicies),
              "ies",
              totalPolicyValueString);

        } else if (numberPolicies == 1) {
          // Case for exactly 1 policy
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              String.valueOf(i + 1),
              currentProfile.getName(),
              currentProfile.getAgeStr(),
              "1",
              "y",
              totalPolicyValueString);

        } else {
          // Case for 0 policies
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              String.valueOf(i + 1),
              currentProfile.getName(),
              currentProfile.getAgeStr(),
              "0",
              "ies",
              "0");
        }

      } else {
        // Cases for profiles that are not loaded:

        if (numberPolicies > 1) {
          // Case for more than 1 policy
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              " ",
              String.valueOf(i + 1),
              currentProfile.getName(),
              currentProfile.getAgeStr(),
              String.valueOf(numberPolicies),
              "ies",
              totalPolicyValueString);

        } else if (numberPolicies == 1) {
          // Case for exactly 1 policy
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              " ",
              String.valueOf(i + 1),
              currentProfile.getName(),
              currentProfile.getAgeStr(),
              "1",
              "y",
              totalPolicyValueString);

        } else {
          // Case for 0 policies
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              " ",
              String.valueOf(i + 1),
              currentProfile.getName(),
              currentProfile.getAgeStr(),
              "0",
              "ies",
              "0");
        }
      }

      // Display policy details
      if (numberPolicies > 0) {
        for (int j = 0; j < numberPolicies; j++) {
          // TODO: FIX THIS
          Object currentPolicy = currentProfile.getPolicy(j);

          if (currentPolicy instanceof HomePolicy) {
            HomePolicy currentHomePolicy = (HomePolicy) currentPolicy;
            // TODO: Fix this, does the homepolicy method need to overide
            currentHomePolicy.printPolicy(-1, numberPolicies);

          } else if (currentPolicy instanceof CarPolicy) {
            CarPolicy currentHomePolicy = (CarPolicy) currentPolicy;
            currentHomePolicy.printPolicy(currentProfile.getAge(), numberPolicies);

          } else if (currentPolicy instanceof LifePolicy) {
            LifePolicy currentHomePolicy = (LifePolicy) currentPolicy;
            currentHomePolicy.printPolicy(currentProfile.getAge(), numberPolicies);
          }
        }
      }
    }
  }

  public void createNewProfile(String username, String age) {
    // Check if there is a profile is loaded. If there is, do not create a new profile
    if (indexLoadedProfile >= 0) {
      MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(
          database.get(indexLoadedProfile).getName());
      return;
    }

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
        database.add(new Profile(username, intAge));
        MessageCli.PROFILE_CREATED.printMessage(username, age);
      } else {
        MessageCli.INVALID_AGE.printMessage(age, username);
      }
    } else {
      MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(username);
    }
  }

  public void loadProfile(String username) {
    // Convert username to title case
    username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();

    // Loop through arraylist to find if profile exists
    for (int i = 0; i < database.size(); i++) {
      String currentUsername = database.get(i).getName();
      if (currentUsername.equals(username)) {
        // Load the profile
        indexLoadedProfile = i;
        MessageCli.PROFILE_LOADED.printMessage(username);
        return;
      }
    }

    // Otherwise display message that no profile was found to load
    MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(username);
  }

  public void unloadProfile() {
    if (indexLoadedProfile >= 0) {
      MessageCli.PROFILE_UNLOADED.printMessage(database.get(indexLoadedProfile).getName());
      indexLoadedProfile = -1;
      return;
    }
    MessageCli.NO_PROFILE_LOADED.printMessage();
  }

  public void deleteProfile(String username) {
    // Convert username to title case
    username = username.substring(0, 1).toUpperCase() + username.substring(1).toLowerCase();

    for (int i = 0; i < database.size(); i++) {
      String currentUsername = database.get(i).getName();
      if (currentUsername.equals(username)) {

        // Case 1: Check if specified profile is loaded profile, if so display error message
        if (indexLoadedProfile == i) {
          MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(username);
          return;
        }

        // Case 2: If profile found and it is not loaded, successfully delete the profile and
        // display success message
        database.remove(i);
        MessageCli.PROFILE_DELETED.printMessage(username);
        // If the profile was deleted profile was before the loaded profile
        // Adjust the index
        if (i < indexLoadedProfile) {
          indexLoadedProfile--;
        }
        return;
      }
    }
    // Case 3: We didn't find a profile to delete. Display cannot find message
    MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(username);
  }

  public void createPolicy(PolicyType type, String[] options) {
    // Check if there is a loaded profile, if there isn't display error message
    if (indexLoadedProfile < 0) {
      MessageCli.NO_PROFILE_FOUND_TO_CREATE_POLICY.printMessage();
      return;
    }

    // Convert the sum insured into an integer
    int sumInsured = Integer.parseInt(options[0]);

    Profile loadedProfile = database.get(indexLoadedProfile);
    String loadedProfileName = loadedProfile.getName();

    // Case for Home Policy
    if (type == PolicyType.HOME) {
      // Find if variable rental should be true or false
      // Will count as 'true' if the string contains a the letter 'y' irrespective of
      // lower/uppercase
      boolean rental = false;
      if (options[2].contains("y") || options[2].contains("Y")) {
        rental = true;
      }
      loadedProfile.addPolicy(new HomePolicy(sumInsured, options[1], rental));

      // Case for Car Policy
    } else if (type == PolicyType.CAR) {

      // Find if variable mechanicalBreakdown should be true or false
      boolean mechanicalBreakdown = false;
      if (options[3].contains("y") || options[3].contains("Y")) {
        mechanicalBreakdown = true;
      }

      loadedProfile.addPolicy(
          new CarPolicy(
              sumInsured, options[1], options[3], mechanicalBreakdown, loadedProfile.getAge()));

      // Case for Life Policy
    } else {
      // Check if age is over limit
      if (loadedProfile.getAge() > 100) {
        MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(loadedProfileName);
        return;
      }
      // If they already have a life policy, do not create a new one, display error message
      if (loadedProfile.getLifePolicyStatus()) {
        MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(loadedProfileName);
        return;
      }
      // Otherwise add the life policy
      loadedProfile.addPolicy(new LifePolicy(sumInsured, loadedProfile.getAge()));
      // Update life policy status
      loadedProfile.updateLifePolicyStatus(true);
    }

    // Display new policy created message
    String typeString = null;

    if (type == PolicyType.HOME) {
      typeString = "home";
    } else if (type == PolicyType.CAR) {
      typeString = "car";
    } else {
      typeString = "life";
    }

    MessageCli.NEW_POLICY_CREATED.printMessage(typeString, loadedProfileName);
  }
}
