import java.util.Scanner;
import java.util.Calendar;

public class AgeOrDobCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendar today = Calendar.getInstance();

        // Display menu
        System.out.println("Choose an option:");
        System.out.println("1. Enter DOB to find age");
        System.out.println("2. Enter age to find DOB");
        int choice = scanner.nextInt();

        if (choice == 1) {
            // Option 1: Enter DOB to calculate age
            System.out.print("Enter your birth year (YYYY): ");
            int birthYear = scanner.nextInt();
            
            System.out.print("Enter your birth month (1-12): ");
            int birthMonth = scanner.nextInt();
            
            System.out.print("Enter your birth day (1-31): ");
            int birthDay = scanner.nextInt();

            // Optionally calculate age with reference to a specific date
            System.out.print("Would you like to enter a specific reference date? (yes/no): ");
            String refChoice = scanner.next();

            Calendar referenceDate;
            if (refChoice.equalsIgnoreCase("yes")) {
                System.out.print("Enter reference year (YYYY): ");
                int refYear = scanner.nextInt();
                
                System.out.print("Enter reference month (1-12): ");
                int refMonth = scanner.nextInt();
                
                System.out.print("Enter reference day (1-31): ");
                int refDay = scanner.nextInt();

                referenceDate = Calendar.getInstance();
                referenceDate.set(refYear, refMonth - 1, refDay);
            } else {
                referenceDate = today;
            }

            Calendar dob = Calendar.getInstance();
            dob.set(birthYear, birthMonth - 1, birthDay);

            int years = referenceDate.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            int months = referenceDate.get(Calendar.MONTH) - dob.get(Calendar.MONTH);
            int days = referenceDate.get(Calendar.DAY_OF_MONTH) - dob.get(Calendar.DAY_OF_MONTH);

            if (days < 0) {
                months--;
                days += referenceDate.getActualMaximum(Calendar.DAY_OF_MONTH);
            }
            if (months < 0) {
                years--;
                months += 12;
            }

            System.out.println("Age: " + years + " years, " + months + " months, " + days + " days.");
            
        } else if (choice == 2) {
            // Option 2: Enter age to calculate DOB
            System.out.print("Enter your age in years: ");
            int years = scanner.nextInt();

            System.out.print("Enter additional months: ");
            int months = scanner.nextInt();

            System.out.print("Enter additional days: ");
            int days = scanner.nextInt();

            Calendar dob = Calendar.getInstance();
            dob.add(Calendar.YEAR, -years);
            dob.add(Calendar.MONTH, -months);
            dob.add(Calendar.DAY_OF_MONTH, -days);

            int birthYear = dob.get(Calendar.YEAR);
            int birthMonth = dob.get(Calendar.MONTH) + 1;  // Months are 0-indexed in Calendar
            int birthDay = dob.get(Calendar.DAY_OF_MONTH);

            System.out.println("Your estimated Date of Birth is: " + birthDay + "/" + birthMonth + "/" + birthYear);
        } else {
            System.out.println("Invalid choice. Please restart the program and choose either 1 or 2.");
        }

        scanner.close();
    }
}
