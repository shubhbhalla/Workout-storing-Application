package ui;

import model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Represents a GUI Frame in which a user can choose from an existing person available
public class ExistingUsers extends JFrame implements ActionListener {
    JButton submit = new JButton();
    Person thisPerson;
    WindowListener exitListener;
    JFrame previousFrame;
    JTable personTable;
    DefaultTableModel tableModel;

    // EFFECTS: Constructor creates a GUI window
    public ExistingUsers(JFrame frame) {
        super("Select an existing person");
        previousFrame = frame;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(previousFrame);
        setVisible(true);
        setSize(new Dimension(450, 300));
        setResizable(false);

        initialiseTable();

        personTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
        personTable.setFillsViewportHeight(true);

        add(new JScrollPane(personTable));

        submit.addActionListener(this);
        submit.setText("Submit");
        submit.setFocusable(false);
        add(submit, BorderLayout.CENTER);

        makeClosingAction();
    }

    // MODIFIES: this
    // EFFECTS: adds rows to the table displaying data of the user
    private void initialiseTable() {
        String[] columnNames = {"Name", "Age", "Gender", "Weight", "Height", "TDEE"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            public boolean isCellEditable(int row, int column) {
                return false; //This causes all cells to be not editable
            }
        };
        personTable = new JTable(tableModel);

        for (Person p : Start.people.getEveryone()) {
            ArrayList<String> output = new ArrayList<>();
            output.add(p.getName());
            output.add("" + p.getAge());
            output.add(p.getGender());
            output.add("" + p.getWeight());
            output.add("" + p.getHeight());
            output.add("" + p.getTdee());
            Object[] data = output.toArray();

            tableModel.addRow(data);
        }
    }

    // MODIFIES: this
    // EFFECTS: Overrides the closing action when the window is closed, to
    // ask the user if they want to save the application or not
    private void makeClosingAction() {
        exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        };
        addWindowListener(exitListener);
    }

    // MODIFIES: this
    // EFFECTS: opens a new window if the name matches a name in the records
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit && personTable.getSelectedRow() >= 0) {
            int row = personTable.getSelectedRow();
            String name = (String) personTable.getValueAt(row, 0);
            int found = findPerson(name);
            if (found == 1) {
                new ModifyUser(thisPerson);
                Start.createSoundEffect();
                previousFrame.dispose();
                dispose();
            }
        }
        if (e.getSource() == submit && personTable.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "Please choose a name", "No Name Chosen",
                    JOptionPane.PLAIN_MESSAGE, Start.image);
        }
    }

    // MODIFIES: this
    // EFFECTS: tries to find a person with the given name,
    // returns 1 if found, returns 0 otherwise
    private int findPerson(String name) {
        for (Person p : Start.people.getEveryone()) {
            if (p.getName().equalsIgnoreCase(name)) {
                thisPerson = p;
                return 1;
            }
        }
        JOptionPane.showMessageDialog(null, "Name not found", "Not Found",
                JOptionPane.PLAIN_MESSAGE, Start.image);
        return 0;
    }
}
