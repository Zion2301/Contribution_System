package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ContributionImpl cont = new ContributionImpl();

        try (BufferedWriter write = new BufferedWriter(new FileWriter("Contributors.txt", true))) {
            while (true) {
                System.out.println("*******************************************");
                System.out.println("*                                         *");
                System.out.println("*   Welcome to the Contribution System     *");
                System.out.println("*                                         *");
                System.out.println("*******************************************");
                System.out.println();
                System.out.println("   1. ➤ Add Contribution");
                System.out.println("   2. ➤ Select Contribution");
                System.out.println("   3. ➤ Display ALL Contributions");
                System.out.println("   4. ➤ Delete Contributions");
                System.out.println("   5. ➤ Exit");
                System.out.println();
                System.out.println("*******************************************");
                System.out.println("*   Please select an option from 1 to 5   *");
                System.out.println("*******************************************");

                int choice = scan.nextInt();
                scan.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        String first;
                        String last;
                        double amount;

                        // Validate FirstName
                        do {
                            System.out.println("Enter FirstName (only letters):");
                            first = scan.nextLine();
                        } while (!isValidName(first));

                        // Validate LastName
                        do {
                            System.out.println("Enter LastName (only letters):");
                            last = scan.nextLine();
                        } while (!isValidName(last));

                        // Validate contribution amount
                        while (true) {
                            System.out.println("Enter contribution (in numbers, 1 decimal place):");
                            try {
                                amount = Double.parseDouble(scan.nextLine());
                                if (amount > 0) {
                                    break; // Valid amount entered
                                } else {
                                    System.out.println("Contribution must be greater than zero.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid contribution amount. Please enter a number.");
                            }
                        }

                        // Add contribution and write to file
                        if (cont.addConribution(first, last, amount)) {
                            // Write the contribution to the file
                            write.write("FirstName: " + first + ", LastName: " + last + ", Contribution: " +  "$" + amount + "\n");
                            write.flush(); // Flush after writing to ensure it's saved
                            System.out.println("Contribution added successfully.");
                        } else {
                            System.out.println("Failed to add contribution.");
                        }
                        break;

                    case 2:
                        System.out.println("Enter LastName to search");
                        String person = scan.nextLine();
                        ContributionParams foundContribution = cont.searchContribution(person);
                        if (foundContribution != null) {
                            System.out.println(foundContribution);
                        } else {
                            System.out.println("Contribution not found.");
                        }
                        break;

                    case 3:
                        System.out.println("Viewing all contributions:");
                        for (ContributionParams contribution : cont.viewContributions()) {
                            System.out.println(contribution);
                        }
                        break;

                    case 4:
                        System.out.println("Enter LastName to delete");
                        String delete = scan.nextLine();
                        cont.deleteContribution(delete);
                        break;

                    case 5:
                        System.out.println("Exiting right now........");
                        scan.close();
                        return;

                    default:
                        System.out.println("Invalid Option");
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }

    // Method to validate names (only alphabetic characters)
    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
