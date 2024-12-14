import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

class User {
    String id;
    String name;
    String role;

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

public class SchoolManagementSystemUI {
    private HashMap<String, User> userDatabase = new HashMap<>();
    private JFrame frame;
    private JTextArea outputArea;

    public SchoolManagementSystemUI() {
        // Initialize the main frame
        frame = new JFrame("School Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLayout(new BorderLayout());

        // Header panel with title and icon
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());
        headerPanel.setBackground(new Color(70, 130, 180)); // Steel blue color
        JLabel headerLabel = new JLabel("School Management System");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Output area with scroll pane
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBackground(new Color(240, 248, 255)); // Light background
        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel with action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(224, 255, 255)); // Light cyan background

        // Buttons with icons and tooltips
        JButton addButton = createButton("Add User", "add_icon.png", "Add a new user to the system");
        JButton updateButton = createButton("Update User", "update_icon.png", "Update user information");
        JButton deleteButton = createButton("Delete User", "delete_icon.png", "Delete an existing user");
        JButton viewButton = createButton("View Users", "view_icon.png", "View all users");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addButton.addActionListener(e -> addUser());
        updateButton.addActionListener(e -> updateUser());
        deleteButton.addActionListener(e -> deleteUser());
        viewButton.addActionListener(e -> viewAllUsers());

        frame.setVisible(true);
    }

    private JButton createButton(String text, String iconPath, String tooltip) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setToolTipText(tooltip);

        // Add icon if available
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // If icon not found, do nothing
        }

        return button;
    }

    private void addUser() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField roleField = new JTextField();
        Object[] inputFields = {
            "ID:", idField,
            "Name:", nameField,
            "Role (Student/Teacher/Staff):", roleField
        };

        int option = JOptionPane.showConfirmDialog(frame, inputFields, "Add User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String name = nameField.getText();
            String role = roleField.getText();

            if (id.isEmpty() || name.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (userDatabase.containsKey(id)) {
                JOptionPane.showMessageDialog(frame, "User with this ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                userDatabase.put(id, new User(id, name, role));
                outputArea.append("User added: " + id + " - " + name + " (" + role + ")\n");
            }
        }
    }

    private void updateUser() {
        String id = JOptionPane.showInputDialog(frame, "Enter User ID to update:");
        if (id == null || id.isEmpty()) return;

        if (!userDatabase.containsKey(id)) {
            JOptionPane.showMessageDialog(frame, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField nameField = new JTextField(userDatabase.get(id).name);
        JTextField roleField = new JTextField(userDatabase.get(id).role);
        Object[] inputFields = {
            "Name:", nameField,
            "Role (Student/Teacher/Staff):", roleField
        };

        int option = JOptionPane.showConfirmDialog(frame, inputFields, "Update User", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String role = roleField.getText();

            if (name.isEmpty() || role.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            User user = userDatabase.get(id);
            user.name = name;
            user.role = role;
            outputArea.append("User updated: " + id + " - " + name + " (" + role + ")\n");
        }
    }

    private void deleteUser() {
        String id = JOptionPane.showInputDialog(frame, "Enter User ID to delete:");
        if (id == null || id.isEmpty()) return;

        if (userDatabase.remove(id) != null) {
            outputArea.append("User deleted: " + id + "\n");
        } else {
            JOptionPane.showMessageDialog(frame, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAllUsers() {
        if (userDatabase.isEmpty()) {
            outputArea.append("No users found!\n");
        } else {
            outputArea.append("--- User List ---\n");
            for (User user : userDatabase.values()) {
                outputArea.append(user + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SchoolManagementSystemUI::new);
    }
}
