package ui;

import model.Exercise;
import model.Person;
import model.Workout;
import ui.enums.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Represents a window to add a new Record
public class MakeNewRecord extends JFrame implements ActionListener {

    Scanner input = new Scanner(System.in);
    Workout record;
    JTextField durationField = new JTextField();
    JButton submit = new JButton();
    JLabel numberOfExercises = new JLabel();
    JLabel duration = new JLabel();
    Integer exNumber = 1;
    JComboBox comboBox;
    Integer exercises = 0;
    Person person;
    WindowListener exitListener;
    JPanel durationPanel;
    JPanel exercisesPanel;

    // MODIFIES: this
    // EFFECTS: creates a GUI to add a new record
    public MakeNewRecord(Person p) {
        super("Make New Record");
        setLayout(new GridLayout(4, 1));
        person = p;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);
        setVisible(true);

        Integer[] numberOfEx = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
                12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};
        comboBox = new JComboBox(numberOfEx);

        durationPanel = new JPanel();
        exercisesPanel = new JPanel();
        add(durationPanel);
        add(exercisesPanel);

        initialiseLayout();

        makeClosingAction();
    }

    // MODIFIES: this
    // EFFECTS: initialises the whole window layout
    private void initialiseLayout() {
        duration.setText("Duration (in minutes)");
        numberOfExercises.setText("Number of Exercises Performed during workout");

        numberOfExercises.setLabelFor(comboBox);
        duration.setLabelFor(durationField);
        durationField.setColumns(20);
        durationPanel.add(duration);
        durationPanel.add(durationField);

        exercisesPanel.add(numberOfExercises);
        exercisesPanel.add(comboBox);

        submit.addActionListener(this);
        submit.setText("Submit");
        add(submit);

        JLabel viewConsole = new JLabel("!!Please view the console after clicking submit!!");
        viewConsole.setHorizontalAlignment(SwingConstants.CENTER);
        add(viewConsole);
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
    // EFFECTS: saves the application and closes it
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            record = new Workout(Integer.parseInt(durationField.getText()));
            exercises = comboBox.getSelectedIndex() + 1;
            for (int i = 0; i < exercises; i++) {
                Exercise ex = createExercise();
                record.addExercise(ex);
            }
            exNumber = 1;
            person.addWorkoutRecord(record);
            saveApp();
            Start.createSoundEffect();
            dispose();
        }
    }

    // EFFECTS: if user inputs 0 then closes application with saving,
    // otherwise closes application without saving
    private void saveApp() {
        System.out.println("Do you want to save the data?"
                + "\n Type \" 0 \" to save\n Type anything else to not save");
        int confirm = 1;
        try {
            confirm = input.nextInt();
        } catch (Exception exception) {
            System.out.println("Did not save data");
            dispose();
        }
        if (confirm == 0) {
            System.out.println("Data successfully saved!");
            Start.saveData();
        }
    }

    // EFFECTS: asks user for information about the exercise that they performed during their workout
    // and returns a new Exercise object made of the inputs given by the user
    private Exercise createExercise() {
        List<Double> weights = new ArrayList<>();
        List<Integer> repetitions = new ArrayList<>();

        displayMuscleGroups();
        int choiceOfMuscleGroup = input.nextInt();
        int c = 0;
        String nameOfExercise = "";
        System.out.println("Please choose the exercise: ");
        displayExercisesForMuscle(choiceOfMuscleGroup);
        c = input.nextInt();
        nameOfExercise = getSelectedExercise(choiceOfMuscleGroup, c);
        System.out.println("Please enter the number of sets of the exercise");
        int sets = input.nextInt();
        for (int i = 0; i < sets; i++) {
            System.out.println("Please enter the weight of the exercise in pounds for set " + (i + 1));
            double weight = input.nextInt();
            System.out.println("Please enter the reps for the set " + (i + 1));
            int reps = input.nextInt();
            weights.add(weight);
            repetitions.add(reps);
        }
        return new Exercise(nameOfExercise, weights, sets, repetitions);
    }

    // EFFECTS: returns the name of the exercise at index choiceOfExercise of muscle group selected by choice
    private String getSelectedExercise(int choice, int choiceOfExercise) {
        try {
            switch (choice) {
                case 1:
                    return Chest.values()[choiceOfExercise].name();
                case 2:
                    return Back.values()[choiceOfExercise].name();
                case 3:
                    return Arms.values()[choiceOfExercise].name();
                case 4:
                    return Legs.values()[choiceOfExercise].name();
                case 5:
                    return Shoulders.values()[choiceOfExercise].name();
                default:
                    return Abs.values()[choiceOfExercise].name();
            }
        } catch (Exception e) {
            System.out.println("Please choose from the exercises specified");
            return "Random Exercise";
        }
    }

    // EFFECTS: prints out the muscle group options to choose from
    private void displayMuscleGroups() {
        System.out.println("Please select which Muscle Group you targeted in your " + exNumber
                + " exercise: \n 1. Chest \n "
                + "2. Back \n 3. Arms \n 4. Legs \n 5. Shoulders \n 6. Abs");
        exNumber++;
    }

    // EFFECTS: prints out the exercises available from muscle group chest if choice is 1, back if choice is 2,
    // arms if choice is 3, legs if choice is 4, shoulders if choice is 5, abs otherwise
    private void displayExercisesForMuscle(int choice) {
        List<String> values;
        switch (choice) {
            case 1: values = Stream.of(Chest.values()).map(Enum::name)
                        .collect(Collectors.toList());
                break;
            case 2: values = Stream.of(Back.values()).map(Enum::name)
                        .collect(Collectors.toList());
                break;
            case 3: values = Stream.of(Arms.values()).map(Enum::name)
                        .collect(Collectors.toList());
                break;
            case 4: values = Stream.of(Legs.values()).map(Enum::name)
                        .collect(Collectors.toList());
                break;
            case 5: values = Stream.of(Shoulders.values()).map(Enum::name)
                        .collect(Collectors.toList());
                break;
            default: values = Stream.of(Abs.values()).map(Enum::name)
                        .collect(Collectors.toList());
        }
        printExercises(values);
    }

    // EFFECTS: prints the names of the exercises from values
    private void printExercises(List<String> values) {
        int i = 0;
        for (String name : values) {
            System.out.println(i + ": " + name);
            i++;
        }
    }
}
