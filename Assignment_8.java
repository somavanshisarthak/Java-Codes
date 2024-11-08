//Author: Sarthak Somavanshi
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private String rollNo;
    private String studentClass;
    private double marks;
    private String address;

    public Student(int id, String name, String rollNo, String studentClass, double marks, String address) {
        this.id = id;
        this.name = name;
        this.rollNo = rollNo;
        this.studentClass = studentClass;
        this.marks = marks;
        this.address = address;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + rollNo + "," + studentClass + "," + marks + "," + address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getRollNo() {
        return rollNo;
    }
}

public class StudentRecordDatabase {
    private static final String FILE_NAME = "student_records.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Create Database");
            System.out.println("2. Display Database");
            System.out.println("3. Clear Database");
            System.out.println("4. Modify Database");
            System.out.println("5. Search Database");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createDatabase(scanner);
                    break;
                case 2:
                    displayDatabase();
                    break;
                case 3:
                    clearDatabase();
                    break;
                case 4:
                    modifyDatabase(scanner);
                    break;
                case 5:
                    searchDatabase(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void createDatabase(Scanner scanner) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Roll No: ");
            String rollNo = scanner.nextLine();
            System.out.print("Class: ");
            String studentClass = scanner.nextLine();
            System.out.print("Marks: ");
            double marks = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Address: ");
            String address = scanner.nextLine();

            students.add(new Student(id, name, rollNo, studentClass, marks, address));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
            System.out.println("Database created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void displayDatabase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\nStudent Records:");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.printf("ID: %s, Name: %s, Roll No: %s, Class: %s, Marks: %s, Address: %s%n",
                        data[0], data[1], data[2], data[3], data[4], data[5]);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    private static void clearDatabase() {
        try {
            new PrintWriter(FILE_NAME).close();
            System.out.println("Database cleared successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Error clearing database: " + e.getMessage());
        }
    }

    private static void modifyDatabase(Scanner scanner) {
        List<Student> students = loadDatabase();
        if (students.isEmpty()) {
            System.out.println("No records found to modify.");
            return;
        }

        System.out.print("Enter ID of the student to modify: ");
        int idToModify = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        boolean found = false;

        for (Student student : students) {
            if (student.getId() == idToModify) {
                found = true;
                System.out.println("Current details: " + student);
                System.out.print("Enter new Name (or press Enter to keep current): ");
                String name = scanner.nextLine();
                System.out.print("Enter new Roll No (or press Enter to keep current): ");
                String rollNo = scanner.nextLine();
                System.out.print("Enter new Class (or press Enter to keep current): ");
                String studentClass = scanner.nextLine();
                System.out.print("Enter new Marks (or press Enter to keep current): ");
                String marksInput = scanner.nextLine();
                System.out.print("Enter new Address (or press Enter to keep current): ");
                String address = scanner.nextLine();

                // Modify fields if new values are provided
                if (!name.isEmpty()) student.name = name; // Assuming name is accessible
                if (!rollNo.isEmpty()) student.rollNo = rollNo; // Assuming rollNo is accessible
                if (!studentClass.isEmpty()) student.studentClass = studentClass; // Assuming studentClass is accessible
                if (!marksInput.isEmpty()) student.marks = Double.parseDouble(marksInput); // Assuming marks is accessible
                if (!address.isEmpty()) student.address = address; // Assuming address is accessible

                System.out.println("Student record updated.");
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + idToModify + " not found.");
        }

        // Save modified records back to file
        saveDatabase(students);
    }

    private static void searchDatabase(Scanner scanner) {
        List<Student> students = loadDatabase();
        if (students.isEmpty()) {
            System.out.println("No records found to search.");
            return;
        }

        System.out.print("Enter ID or Name of the student to search: ");
        String query = scanner.nextLine().trim();
        boolean found = false;

        for (Student student : students) {
            if (String.valueOf(student.getId()).equals(query) || student.getName().equalsIgnoreCase(query)) {
                found = true;
                System.out.println("Found: " + student);
            }
        }

        if (!found) {
            System.out.println("No records found for: " + query);
        }
    }

    private static List<Student> loadDatabase() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        Double.parseDouble(data[4]),
                        data[5]
                ));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return students;
    }

    private static void saveDatabase(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}