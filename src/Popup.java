import javax.swing.*;

public class Popup {

    public static int popupStrengthWindow(){

        Object[] options = {"Strong", "Medium", "Weak", "Numbers"};
        return JOptionPane.showOptionDialog(null, "So many options using Object[]",
                "Select a password type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    public static int popupLengthWindow(){

        int repetitions = 0;
        try {
            repetitions = Integer.parseInt(JOptionPane.showInputDialog(null, "How long do you want the password?"));
        }catch (Exception anException){
            JOptionPane.showMessageDialog(null, "Please enter a number.");
        }

        return repetitions;
    }
}
