import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class raceDay extends JFrame  {
    private JTextField nameField, timeField, ageField;
    private JComboBox<String> raceTypeComboBox;
    private JButton addButton, sortBy5KButton, sortByHalfMarathonButton, sortByMarathonButton, doneButton;
    private JTextArea timeListArea;
    private ArrayList<times> raceTimes;

    
    //use enum for easier accessibility when it comes to sorting
    private enum RaceType {
        _5K, HALF_MARATHON, MARATHON
    }

    public raceDay() {
        // Initialize main frame
        setTitle("Race Day Rankiings!");
        setSize(500, 3000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize components
        JPanel inputs = new JPanel(new GridLayout(5, 2));
        nameField = new JTextField();
        timeField = new JTextField();
        ageField = new JTextField();
        raceTypeComboBox = new JComboBox<>(new String[]{"5K", "Half Marathon", "Marathon"});
        addButton = new JButton("Add Time");

        //sort buttons
        sortBy5KButton = new JButton("Filter 5k");
        sortByHalfMarathonButton = new JButton("Filter Half Marathon");
        sortByMarathonButton = new JButton("Filter Marathon");

        
        doneButton = new JButton("End of Results");

        
        timeListArea = new JTextArea();
        raceTimes = new ArrayList<>();

        // Add components to input panel
        inputs.add(new JLabel("Name:"));
        inputs.add(nameField);
        inputs.add(new JLabel("Time (minutes):"));
        inputs.add(timeField);
        inputs.add(new JLabel("Age:"));
        inputs.add(ageField);
        inputs.add(new JLabel("Race Type:"));
        inputs.add(raceTypeComboBox);
        inputs.add(addButton);

        
        JPanel sortPanel = new JPanel();
        sortPanel.add(sortBy5KButton);
        sortPanel.add(sortByHalfMarathonButton);
        sortPanel.add(sortByMarathonButton);

        // Add done button
        JPanel donePanel = new JPanel();
        donePanel.add(doneButton);

        // Add to the frame
        add(inputs, BorderLayout.NORTH);
        add(new JScrollPane(timeListArea), BorderLayout.CENTER);
        add(sortPanel, BorderLayout.SOUTH);
        add(donePanel, BorderLayout.EAST);

        // continuous input until program is terminated
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addtimes();
            }
        });

        sortBy5KButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaytimesByRaceType(RaceType._5K);
            }
        });

        sortByHalfMarathonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaytimesByRaceType(RaceType.HALF_MARATHON);
            }
        });

        sortByMarathonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaytimesByRaceType(RaceType.MARATHON);
            }
        });

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTop3LowestTimes();
            }
        });
    }

    // Method to add marathon time to the list
    private void addtimes() {
        String name = nameField.getText().trim();
        int timeInMinutes = Integer.parseInt(timeField.getText().trim());
        int age = Integer.parseInt(ageField.getText().trim());
        String raceType = (String) raceTypeComboBox.getSelectedItem();

        times raceTime = new times(name, timeInMinutes, age, raceType);
        raceTimes.add(raceTime);
        updatetimesList();
    }

    //update the displayed list of marathon times
    private void updatetimesList() {
        StringBuilder sb = new StringBuilder();
        for (times time : raceTimes) {
            sb.append(time.toString()).append("\n");
        }
        timeListArea.setText(sb.toString());
    }

    // Method to display top 3 lowest times in each race type
    private void displayTop3LowestTimes() {
        // Sorts the raceTimes list by time in ascending order by the race type
        TreeMap<Integer, times> top3_5K = new TreeMap<>();
        TreeMap<Integer, times> top3_HalfMarathon = new TreeMap<>();
        TreeMap<Integer, times> top3_Marathon = new TreeMap<>();

        // sorts the top 3 lowest times for each race type
        for (times time : raceTimes) {
            switch (time.getRaceType()) {
                case "5K":
                    top3_5K.put(time.getTimeInMinutes(), time);
                    break;
                case "Half Marathon":
                    top3_HalfMarathon.put(time.getTimeInMinutes(), time);
                    break;
                case "Marathon":
                    top3_Marathon.put(time.getTimeInMinutes(), time);
                    break;
            }
        }

        // string builder for the display of results
        StringBuilder sb = new StringBuilder();
        sb.append("Top 3 Results!:\n");
        sb.append("------------------------------\n");
        sb.append("5K:\n");
        appendTop3Times(sb, top3_5K);
        sb.append("------------------------------\n");
        sb.append("Half Marathon:\n");
        appendTop3Times(sb, top3_HalfMarathon);
        sb.append("------------------------------\n");
        sb.append("Marathon:\n");
        appendTop3Times(sb, top3_Marathon);

        timeListArea.setText(sb.toString());
    }

//keeps track of the top 3 times for each race type
    private void appendTop3Times(StringBuilder sb, TreeMap<Integer, times> top3Map) {
        int count = 0;
        for (times time : top3Map.values()) {
            sb.append("Name: ").append(time.getName()).append(", Time: ").append(time.getTimeInMinutes()).append(" minutes\n");
            count++;
            if (count >= 3) {
                break;
            }
        }
    }

    // Method to display marathon times by race type (organizational purposes)
    private void displaytimesByRaceType(RaceType raceType) {
        StringBuilder sb = new StringBuilder();
        sb.append("Race Type: ").append(raceType).append("\n");
        sb.append("--------------------------\n");

        for (times time : raceTimes) {
            if (getRaceTypeEnum(time.getRaceType()) == raceType) {
                sb.append(time.toString()).append("\n");
            }
        }
        timeListArea.setText(sb.toString());
    }

    // Helper method to convert race type string to RaceType enum
    private RaceType getRaceTypeEnum(String raceType) {
        switch (raceType) {
            case "5K":
                return RaceType._5K;
            case "Half Marathon":
                return RaceType.HALF_MARATHON;
            case "Marathon":
                return RaceType.MARATHON;
            default:
                return null;
        }
    }

   
    }












    

