import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StudentRecords {

    private static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n==============================");
            System.out.println("      STUDENT RECORDS");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    addStudent(input);
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent(input);
                    break;

                case 4:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 4);

        input.close();
    }

    // Add a new student to the file
    public static void addStudent(Scanner input) {

        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter student ID: ");
        String id = input.nextLine();

        System.out.print("Enter student major: ");
        String major = input.nextLine();

        try {
            FileWriter writer = new FileWriter(FILE_NAME, true);

            writer.write(id + " | " + name + " | " + major + "\n");

            writer.close();

            System.out.println("Student added successfully!");

        } catch (IOException e) {

            System.out.println("An error occurred while saving the student.");
        }
    }

    // Display all students
    public static void viewStudents() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No student records found.");
            return;
        }

        try {
            Scanner fileReader = new Scanner(file);

            System.out.println("\n==============================");
            System.out.println("       STUDENT LIST");
            System.out.println("==============================");

            while (fileReader.hasNextLine()) {

                String student = fileReader.nextLine();

                System.out.println(student);
            }

            fileReader.close();

        } catch (IOException e) {

            System.out.println("An error occurred while reading the file.");
        }
    }

    // Search for a student by ID
    public static void searchStudent(Scanner input) {

        System.out.print("Enter student ID to search: ");
        String searchId = input.nextLine();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No student records found.");
            return;
        }

        boolean found = false;

        try {
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {

                String student = fileReader.nextLine();

                if (student.startsWith(searchId + " | ")) {

                    System.out.println("\nStudent found:");
                    System.out.println(student);

                    found = true;
                    break;
                }
            }

            fileReader.close();

            if (!found) {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {

            System.out.println("An error occurred while searching.");
        }
    }
        }
