    
    import java.util.Scanner;
public class StudentgradeCalculator {
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            // Input the number of subjects
            System.out.print("Enter the number of subjects: ");
            int numSubjects = scanner.nextInt();
    
            // Initialize variables to hold total marks and individual subject marks
            int totalMarks = 0;
            int[] marks = new int[numSubjects];
            
            // Input marks for each subject
            for (int i = 0; i < numSubjects; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
                marks[i] = scanner.nextInt();
                totalMarks += marks[i];
            }
    
            // Calculate average percentage
            double averagePercentage = (double) totalMarks / numSubjects;
    
            // Determine the grade
            String grade = determineGrade(averagePercentage);
    
            // Display results
            System.out.println("\nTotal Marks: " + totalMarks);
            System.out.println("Average Percentage: " + averagePercentage + "%");
            System.out.println("Grade: " + grade);
            
            scanner.close();
        }
    
        // Method to determine the grade based on average percentage
        private static String determineGrade(double percentage) {
            if (percentage >= 90) {
                return "A+";
            } else if (percentage >= 80) {
                return "A";
            } else if (percentage >= 70) {
                return "B+";
            } else if (percentage >= 60) {
                return "B";
            } else if (percentage >= 50) {
                return "C";
            } else {
                return "D";
            }
        }
    }
    
