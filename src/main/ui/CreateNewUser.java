package ui;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Represents a window to create a new user
public class CreateNewUser extends JFrame implements ActionListener {
    JLabel name = new JLabel("Name");
    JLabel gender = new JLabel("Gender");
    JLabel height = new JLabel("Height (in centimeters)");
    JLabel weight = new JLabel("Weight (in kilograms)");
    JLabel age = new JLabel("Age");
    JLabel activityLevelLabel = new JLabel("Choose your activity level");
    JTextField nameField = new JTextField();
    JComboBox activityLevel;
    JComboBox genderComboBox;
    JButton submit = new JButton("Submit");
    JTextField heightField = new JTextField();
    JTextField weightField = new JTextField();
    JTextField ageField = new JTextField();
    WindowListener exitListener;
    JPanel namePanel;
    JPanel heightPanel;
    JPanel weightPanel;
    JPanel genderPanel;
    JPanel agePanel;
    JPanel intensityPanel;
    JFrame previousFrame;

    // MODIFIES: this
    // EFFECTS: creates the GUI window
    public CreateNewUser(JFrame frame) {
        super("Create New User");
        previousFrame = frame;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setLayout(new GridLayout(7, 1));
        setLocationRelativeTo(null);

        String[] availableGenders = {"Male", "Female"};
        String[] intensity = {"1. sedentary (little or no exercise)",
                "2. light activity (light exercise/sports 1 to 3 hours per week)",
                "3. moderate activity (moderate exercise/sports 4 to 6 hours per week)",
                "4. very active (hard exercise/sports 7 to 9 hours per week)",
                "5. extra active (very hard exercise/sports 10+ hours per week)"
        };

        genderComboBox = new JComboBox(availableGenders);
        activityLevel = new JComboBox(intensity);

        setLabels();
        initialisePanels();
        addFields();

        add(submit);
        submit.addActionListener(this);
        makeClosingAction();
    }

    // MODIFIES: this
    // EFFECTS: sets the labels for different text fields
    private void setLabels() {
        name.setLabelFor(nameField);
        gender.setLabelFor(genderComboBox);
        height.setLabelFor(heightField);
        weight.setLabelFor(weightField);
        age.setLabelFor(ageField);
        activityLevelLabel.setLabelFor(activityLevel);
    }

    // MODIFIES: this
    // EFFECTS: initialises all the panels
    private void initialisePanels() {
        namePanel = new JPanel();
        heightPanel = new JPanel();
        weightPanel = new JPanel();
        genderPanel = new JPanel();
        agePanel = new JPanel();
        intensityPanel = new JPanel();
    }

    // MODIFIES: this
    // EFFECTS: adds the fields to the panels and the panels to the frame
    private void addFields() {
        add(namePanel);
        nameField.setColumns(30);
        namePanel.add(name);
        namePanel.add(nameField);

        add(heightPanel);
        heightField.setColumns(10);
        heightPanel.add(height);
        heightPanel.add(heightField);

        add(weightPanel);
        weightField.setColumns(10);
        weightPanel.add(weight);
        weightPanel.add(weightField);

        add(genderPanel);
        genderPanel.add(gender);
        genderPanel.add(genderComboBox);

        add(agePanel);
        ageField.setColumns(10);
        agePanel.add(age);
        agePanel.add(ageField);

        add(intensityPanel);
        intensityPanel.add(activityLevelLabel);
        intensityPanel.add(activityLevel);
    }

    // MODIFIES: this
    // EFFECTS: Overrides the closing action when the window is closed to
    // ask the user if they want to save the application or not
    private void makeClosingAction() {
        exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Do you want to save the application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, Start.image, null, null);
                if (confirm == 0) {
                    Start.saveData();
                }
                dispose();
            }
        };
        addWindowListener(exitListener);
    }

    // MODIFIES: this
    // EFFECTS: creates a new person and adds that person to people and then opens new Window
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit && checkNotNull()) {
            String name = nameField.getText();
            if (alreadyExists(name)) {
                JOptionPane.showMessageDialog(null, "Someone with that name already exists in records!",
                        "Existing name chosen", JOptionPane.PLAIN_MESSAGE, Start.image);
                return;
            }

            Person newPerson = makeNewPerson(name);
            Start.people.addPerson(newPerson);
            JOptionPane.showMessageDialog(null,
                    "Your Total Daily Energy Expenditure is - " + newPerson.getTdee()
                            + "\n Eat more calories to gain weight, eat less calories to lose weight!", "TDEE",
                    JOptionPane.PLAIN_MESSAGE, Start.image);
            new ModifyUser(newPerson);
            Start.createSoundEffect();
            previousFrame.dispose();
            dispose();
        }
        if (e.getSource() == submit && !checkNotNull()) {
            Start.createSoundEffect();
            JOptionPane.showMessageDialog(null, "Please enter all the specified fields.", "Enter every input",
                    JOptionPane.PLAIN_MESSAGE, Start.image);
        }
    }

    // EFFECTS: makes a new person with the inputs specified by the user
    private Person makeNewPerson(String name) {
        int age = Integer.parseInt(ageField.getText());
        int height = Integer.parseInt(heightField.getText());
        int weight = Integer.parseInt(weightField.getText());
        double intensity = setIntensity(activityLevel.getSelectedIndex() + 1);
        String gender = setGender(genderComboBox.getSelectedIndex());

        return new Person(name, age, height, weight, intensity, gender);
    }

    // EFFECTS: If a user with name already exists in people then returns true, else returns false
    private boolean alreadyExists(String name) {
        for (Person p : Start.people.getEveryone()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: i in [1,2,3,4,5]
    // EFFECTS: returns 1.15 if i is 1, returns 1.35 if i is 2, returns 1.5 if i is 3,
    // returns 1.725 if i is 4, returns 1.9 if i is 5, returns 0 otherwise
    private double setIntensity(int i) {
        switch (i) {
            case 1:
                return 1.15;
            case 2:
                return 1.35;
            case 3:
                return 1.5;
            case 4:
                return 1.725;
            case 5:
                return 1.9;
            default:
                return 0;
        }
    }

    // REQUIRES: g is 0 or 1
    // EFFECTS: returns "Male" is g is 0, returns "Female" otherwise
    private String setGender(int g) {
        if (g == 0) {
            return "Male";
        } else {
            return "Female";
        }
    }

    // EFFECTS: if all the input fields are not null then returns true, otherwise false
    private boolean checkNotNull() {
        return !nameField.getText().isEmpty()
                && !heightField.getText().isEmpty()
                && !weightField.getText().isEmpty()
                && !ageField.getText().isEmpty();
    }
}
