import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.event.EventListenerList;

public class DetailsPanel extends JPanel implements ActionListener {

    static String strongString = "Strong";
    static String mediumString = "Medium";
    static String weakString = "Weak";
    static String numericString = "Numeric";
    /**
     * Sets up the details panel on the left, with a lovely wee border.
     */

    private EventListenerList listenerList = new EventListenerList();

    public DetailsPanel() {
        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Control Panel"));

        JLabel passwordLabel = new JLabel("Password length: ");
        final JTextField passwordLengthField = new JTextField("8", 10);


        JButton addBtn = new JButton("Generate");

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String length = passwordLengthField.getText().trim();
                String text = CharLists.generator(Integer.parseInt(length)) + "\n";
                fireDetailEvent(new DetailEvent(this, text));
            }
        });

        //Create the radio buttons.
        JRadioButton strongButton = new JRadioButton(strongString);
        strongButton.setMnemonic(KeyEvent.VK_B);
        strongButton.setActionCommand(strongString);
        strongButton.setSelected(true);

        JRadioButton mediumButton = new JRadioButton(mediumString);
        mediumButton.setMnemonic(KeyEvent.VK_C);
        mediumButton.setActionCommand(mediumString);

        JRadioButton weakButton = new JRadioButton(weakString);
        weakButton.setMnemonic(KeyEvent.VK_D);
        weakButton.setActionCommand(weakString);

        JRadioButton numberButton = new JRadioButton(numericString);
        numberButton.setMnemonic(KeyEvent.VK_R);
        numberButton.setActionCommand(numericString);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(strongButton);
        group.add(mediumButton);
        group.add(weakButton);
        group.add(numberButton);

        //Register a listener for the radio buttons.
        strongButton.addActionListener(this);
        mediumButton.addActionListener(this);
        weakButton.addActionListener(this);
        numberButton.addActionListener(this);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        //// First column, first row ////
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        add(strongButton, gc);
        add(mediumButton, gc);
        add(weakButton, gc);
        add(numberButton, gc);

        add(passwordLabel, gc);
        //// First column, second row ////
        gc.anchor = GridBagConstraints.LINE_END;

        gc.gridx = 0;
        gc.gridy = 1;

        add(passwordLabel, gc);

        //// Second column, first row ////
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 1;
        add(passwordLengthField, gc);


        //// Final Row ////
        gc.weighty = 10;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        add(addBtn, gc);
    }

    public void fireDetailEvent(DetailEvent event) { // Clearly this is just some kind of wizardry.
        Object[] listeners = listenerList.getListenerList();

        for(int i = 0; i < listeners.length; i += 2) {
            // I mean really?! We're going through this in double
            // steps to find the DetailListener object or some shit..?
            if(listeners[i] == DetailListener.class){
                ((DetailListener)listeners[i+1]).detailEventOccurred(event);
            }
        }
    }

    public void addDetailListener(DetailListener listener) {
        listenerList.add(DetailListener.class, listener);
    }

    public void removeDetailListener(DetailListener listener) {
        listenerList.remove(DetailListener.class, listener);
        // One day ill do this. If I need to.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}