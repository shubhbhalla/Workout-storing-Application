package ui;

import model.People;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
Used some code from -
https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 */

// starts the application
public class Start extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/people.json";
    private static JsonWriter jsonWriter = new JsonWriter(Start.JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    public static People people = new People();
    public static ImageIcon image = new ImageIcon("./data/bodybuilding.jpg");

    JRadioButton exists = new JRadioButton();
    JRadioButton newUser = new JRadioButton();
    ButtonGroup bg = new ButtonGroup();
    WindowListener exitListener;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuLoad;
    JMenuItem readMe;
    JMenuItem menuQuit;

    // MODIFIES: this
    // EFFECTS: Create the GUI Window
    public Start() {
        super("Workout app");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(200, 150);
        setLayout(new GridLayout(2, 1));
        setLocationRelativeTo(null);
        setResizable(false);

        createMenuBar();
        setJMenuBar(createMenuBar());

        exists.setText("Existing user");
        exists.addActionListener(this);
        exists.setHorizontalAlignment(JLabel.CENTER);

        newUser.setText("New user");
        newUser.addActionListener(this);
        newUser.setHorizontalAlignment(JLabel.CENTER);

        bg.add(exists);
        bg.add(newUser);

        add(exists);
        add(newUser);
        setVisible(true);

        makeClosingAction();
        addWindowListener(exitListener);
    }

    // EFFECTS: generates a sound whenever called
    public static void createSoundEffect() {
        File file = new File("./data/sound.wav");
        Clip clip;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.start();
        } catch (Exception e) {
            System.out.println("Couldn't play audio");
        }
    }

    // EFFECTS: saves the data to a file
    public static void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(Start.people);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + Start.JSON_STORE);
        }
    }

    public static void main(String[] args) {
        new Start();
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a menu bar
    private JMenuBar createMenuBar() {

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Menu");
        menu.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(menu);

        //a group of JMenuItems
        menuLoad = new JMenuItem("Load Data");
        menuLoad.addActionListener(this);
        menu.add(menuLoad);

        menuQuit = new JMenuItem("Quit Application");
        menuQuit.addActionListener(this);
        menu.add(menuQuit);

        readMe = new JMenuItem("Open ReadMe file");
        readMe.addActionListener(this);
        menu.add(readMe);

        return menuBar;
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
    }

    // MODIFIES: this
    // EFFECTS: opens a new window according to the selected checkbox checked,
    // loads data, quits app, opens readme according to the button pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exists) {
            createSoundEffect();
            new ExistingUsers(this);
        } else if (e.getSource() == newUser) {
            createSoundEffect();
            new CreateNewUser(this);
        }
        if (e.getSource() == menuLoad) {
            readData();
        }
        if (e.getSource() == menuQuit) {
            dispose();
        }
        if (e.getSource() == readMe) {
            openReadMe();
        }
    }

    // EFFECTS: opens the project's readme file on the computer
    private void openReadMe() {
        File file = new File("./README.md");
        if (!Desktop.isDesktopSupported()) {
            System.out.println("not supported");
            return;
        }
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            try {
                desktop.open(file);              //opens the specified file
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the data from the JSON file containing all the people,
    // throws IOException if cannot read from file
    private void readData() {
        people = new People();
        try {
            people = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to load data from file: " + JSON_STORE);
        }
    }
}
