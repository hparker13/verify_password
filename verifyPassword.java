package password_verify2;

import java.util.Scanner;

public class VerifyPassword {
    private static String username = "johndoe";
    private static String password = "ABC_1234";

    public static void main(String[] args) {

        printRules();
        Scanner scanner = new Scanner(System.in);
        boolean valid;

        do {
            System.out.println("Please enter a NEW Password: ");
            String newPassword = scanner.next();
            valid = verifyNewPassword(newPassword);
        } while(!valid);

        System.out.println("Password Updated");
        scanner.close();
    }

    public static void printRules() {
        System.out.println("Password can NOT contain your username & MUST be different from your current password.");
        System.out.println("Password MUST be at least 8 characters, contain an uppercase, & a special character.");
    }

    public static boolean verifyNewPassword(String newPassword) {
        boolean valid = true;
        String errorMessage = "";


        // Does NOT contain the username (johndoe)
        // Change password & username to lowercase in order to compare
        String lowercaseNewPassword = newPassword.toLowerCase();
        String lowercaseUsername = username.toLowerCase();

        if (lowercaseNewPassword.contains(lowercaseUsername)) {
            errorMessage += " New password cannot contain username.";
            valid = false;
        }


        // NOT the same as old password (ABC_1234)
        if (newPassword.equals(password)) {
            errorMessage += "\n New Password must be different from current password.";
            valid = false;
        }


        // At least 8 characters long
        if (newPassword.length() < 8) {
            errorMessage += "\n New password must be at least 8 characters long.";
            valid = false;
        }


        // Contains an uppercase letter
        boolean containsUppercase = false;
        for (int i = 0; i < newPassword.length(); i++) {
            char ch = newPassword.charAt(i);
            if (Character.isUpperCase(ch)) {
                containsUppercase = true;
            }
        }

        if (!containsUppercase) {
            errorMessage += "\n New password must include an uppercase letter.";
            valid = false;
        }


        // Contains a special character
        int count = 0;
        for (int i = 0; i < newPassword.length(); i++) {

            // Check character is not letter, digit, or space
            if (!Character.isLetter(newPassword.charAt(i))
                    && !Character.isDigit(newPassword.charAt(i))
                    && !Character.isWhitespace(newPassword.charAt(i))) {

                // Incrementing count for special characters
                count++;
            }
        }

        // When no special character is encountered
        if (count == 0) {
            errorMessage += "\n New password must contain a special character.";
            valid = false;
        }



        // Print error message if not valid
        if (!valid) {
            System.out.println(errorMessage);
        }

        return valid;
    }
}
