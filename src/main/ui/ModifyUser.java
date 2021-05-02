package ui;

import model.Exercise;
import model.Person;
import model.Workout;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

// Represents a window asking the user if they want to add a new record or view previous records
public class ModifyUser extends JFrame implements ActionListener {

    Border border = BorderFactory.createLineBorder(Color.BLACK, 3);
    JTextArea area = new JTextArea();
    Person person;
    JRadioButton addNewWorkout = new JRadioButton();
    JRadioButton viewPreviousRecords = new JRadioButton();
    ButtonGroup bg = new ButtonGroup();
    WindowListener exitListener;
    JPanel p1;
    JPanel p5;
    JScrollPane scrollPanel;

    // MODIFIES: this
    // EFFECTS: creates a GUI Window
    public ModifyUser(Person p) {
        super("Modify Records Window");
        person = p;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        p1 = new JPanel(new GridLayout(2, 1));
        p1.setPreferredSize(new Dimension(100, 100));

        p5 = new JPanel();

        initialiseFields();

        makeClosingAction();
    }

    // MODIFIES: this
    // EFFECTS: initialises all the fields for the window
    private void initialiseFields() {
        p5.setBackground(Color.CYAN);

        add(p1, BorderLayout.NORTH);
        add(p5);

        addNewWorkout.setText("Add New Workout");
        addNewWorkout.addActionListener(this);
        addNewWorkout.setHorizontalAlignment(SwingConstants.CENTER);
        bg.add(addNewWorkout);
        p1.add(addNewWorkout);

        viewPreviousRecords.setText("View Previous Records");
        viewPreviousRecords.addActionListener(this);
        viewPreviousRecords.setHorizontalAlignment(SwingConstants.CENTER);
        bg.add(viewPreviousRecords);
        p1.add(viewPreviousRecords);

        area.setBorder(border);
        area.setEditable(false);
        area.setBackground(Color.white);

        scrollPanel = new JScrollPane(area);
        scrollPanel.setPreferredSize(new Dimension(600, 600));

        p5.add(scrollPanel);
        p5.setVisible(false);
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
    // EFFECTS: if show records checkbox is selected then shows the user their records,
    // otherwise if new record is selected then opens a new window to add new record
    @Override
    public void actionPerformed(ActionEvent e) {
        if (viewPreviousRecords.isSelected()) {
            p1.setBackground(Color.cyan);
            area.setText(getRecords());
            p5.setVisible(true);
        }
        if (addNewWorkout.isSelected()) {
            new MakeNewRecord(person);
            Start.createSoundEffect();
            dispose();
        }
    }

    // EFFECTS: if len(workouts) !=0 then prints the weight lifting record of the person,
    // else prints "No records available"
    private String getRecords() {
        String output;
        if (person.getWorkoutRecords().size() == 0) {
            output = "No records available";
            return output;
        }
        output = "Name of User: " + person.getName() + "\n\n";
        int i = 1;
        for (Workout w : person.getWorkoutRecords()) {
            output = output.concat("\nWorkout " + i + ", Duration of this workout: "
                    + w.getWorkoutDuration() + " minutes\n");
            for (Exercise ex : w.getAllExercise()) {
                output = output.concat(printInfoOfExercise(ex));
            }
            i++;
        }
        output = output.concat("________________________________________\n");
        return output;
    }

    // EFFECTS: prints out the information of the exercise in format:
    // "name" for "# of sets" sets (weight * reps, weight * reps, .... weight * reps)
    private String printInfoOfExercise(Exercise ex) {
        String repsAndWeight = "";
        for (int i = 0; i < ex.getSets(); i++) {
            repsAndWeight = repsAndWeight.concat(ex.getWeight().get(i) + " * " + ex.getReps().get(i) + ", ");
        }
        return ("\n\t - " + ex.getName() + " for " + ex.getSets() + " sets ("
                + repsAndWeight.substring(0, repsAndWeight.length() - 2) + ")\n");
    }
}
