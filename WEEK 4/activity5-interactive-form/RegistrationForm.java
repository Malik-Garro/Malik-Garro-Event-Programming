import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrationForm extends JFrame {

    // Component declarations matching the handbook specification
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel genderLabel;
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private JRadioButton otherRadio;
    private ButtonGroup genderGroup;
    private JCheckBox sportsCheck;
    private JCheckBox musicCheck;
    private JCheckBox readingCheck;
    private JLabel countryLabel;
    private JComboBox<String> countryBox;
    private JLabel commentsLabel;
    private JTextArea commentsArea;
    private JButton submitButton;
    private JButton clearButton;
    private JButton exitButton;
    private JLabel statusLabel;

    public RegistrationForm() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 580);
        setLocationRelativeTo(null);

        // Main container panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Title Label
        titleLabel = new JLabel("Registration Form", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(25, 75, 150));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        // Reset gridwidth and anchor
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // 2. Name
        nameLabel = new JLabel("Full Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(nameLabel, gbc);

        nameField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(nameField, gbc);

        // 3. Email
        emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(emailLabel, gbc);

        emailField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(emailField, gbc);

        // 4. Password
        passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(30);
        gbc.gridx = 1;
        gbc.gridy = 3;
        mainPanel.add(passwordField, gbc);

        // 5. Gender
        genderLabel = new JLabel("Gender:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(genderLabel, gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        otherRadio = new JRadioButton("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);

        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPanel.add(genderPanel, gbc);

        // 6. Interests
        JLabel interestsLabel = new JLabel("Interests:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(interestsLabel, gbc);

        JPanel interestsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        sportsCheck = new JCheckBox("Sports");
        musicCheck = new JCheckBox("Music");
        readingCheck = new JCheckBox("Reading");
        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);

        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(interestsPanel, gbc);

        // 7. Country
        countryLabel = new JLabel("Country:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(countryLabel, gbc);

        String[] countries = {"Kenya", "Tanzania", "Uganda", "Rwanda"};
        countryBox = new JComboBox<>(countries);
        gbc.gridx = 1;
        gbc.gridy = 6;
        mainPanel.add(countryBox, gbc);

        // 8. Comments
        commentsLabel = new JLabel("Comments:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(commentsLabel, gbc);

        commentsArea = new JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        JScrollPane commentsScrollPane = new JScrollPane(commentsArea);
        gbc.gridx = 1;
        gbc.gridy = 7;
        mainPanel.add(commentsScrollPane, gbc);

        // 9. Status Label
        statusLabel = new JLabel("Status: Ready");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(statusLabel, gbc);

        // 10. Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(40, 167, 69));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);

        clearButton = new JButton("Clear");

        exitButton = new JButton("Exit");
        exitButton.setBackground(new Color(220, 53, 69));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);

        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);

        add(mainPanel);

        // Event Listeners
        submitButton.addActionListener(this::submitButtonActionPerformed);
        clearButton.addActionListener(this::clearButtonActionPerformed);
        exitButton.addActionListener(this::exitButtonActionPerformed);
    }

    // Step 5.4: Submit Button Handler
    private void submitButtonActionPerformed(ActionEvent evt) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            statusLabel.setText("Error: Please enter your name");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            statusLabel.setText("Error: Please enter a valid email");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (password.length() < 6) {
            statusLabel.setText("Error: Password must be at least 6 characters");
            statusLabel.setForeground(Color.RED);
            return;
        }

        // Gather gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Gather interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        // Gather country
        String country = (String) countryBox.getSelectedItem();

        // Gather comments
        String comments = commentsArea.getText().trim();

        // Show confirmation
        String message = "Registration Successful!\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Interests: " + (interests.length() > 0 ? interests.toString().trim() : "None") + "\n" +
                "Country: " + country + "\n" +
                "Comments: " + (comments.isEmpty() ? "None" : comments);

        JOptionPane.showMessageDialog(this, message,
                "Registration Complete",
                JOptionPane.INFORMATION_MESSAGE);

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new Color(0, 128, 0));
    }

    // Step 5.4: Clear Button Handler
    private void clearButtonActionPerformed(ActionEvent evt) {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(Color.BLACK);
    }

    // Step 5.4: Exit Button Handler
    private void exitButtonActionPerformed(ActionEvent evt) {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}
