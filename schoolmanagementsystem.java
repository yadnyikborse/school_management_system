import java.util.HashMap;
import java.util.Scanner;

class User {
    String id;
    String name;
    String role; // e.g., Student, Teacher, Staff

    User(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Role: " + role;
    }
}

public class schoolmanagementsystem {
    private static HashMap<String, User> userDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- School Management System ---");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. View All Users");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addUser() {
        System.out.print("Enter User ID: ");
        String id = scanner.nextLine();
        if (userDatabase.containsKey(id)) {
            System.out.println("User with this ID already exists!");
            return;
        }
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role (Student/Teacher/Staff): ");
        String role = scanner.nextLine();

        User user = new User(id, name, role);
        userDatabase.put(id, user);
        System.out.println("User added successfully!");
    }

    private static void updateUser() {
        System.out.print("Enter User ID to update: ");
        String id = scanner.nextLine();
        if (!userDatabase.containsKey(id)) {
            System.out.println("User not found!");
            return;
        }
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Role: ");
        String role = scanner.nextLine();

        User user = userDatabase.get(id);
        user.name = name;
        user.role = role;
        System.out.println("User updated successfully!");
    }

    private static void deleteUser() {
        System.out.print("Enter User ID to delete: ");
        String id = scanner.nextLine();
        if (userDatabase.remove(id) != null) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    private static void viewAllUsers() {
        if (userDatabase.isEmpty()) {
            System.out.println("No users found!");
        } else {
            System.out.println("\n--- User List ---");
            for (User user : userDatabase.values()) {
                System.out.println(user);
            }
        }
    }
}
