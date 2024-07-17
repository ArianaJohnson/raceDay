
import javax.swing.*;
//main method to invoke the start of program
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                raceDay raceDay = new raceDay();
                raceDay.setVisible(true);
            }
        });
    }
}







