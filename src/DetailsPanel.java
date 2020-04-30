import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.event.EventListenerList;

public class DetailsPanel extends JPanel implements ActionListener {

    final static String STRONG_STRING = "Strong";
    final static String MEDIUM_STRING = "Medium";
    final static String WEAK_STRING = "Weak";
    final static String NUMERIC_STRING = "Numeric";
    private EventListenerList listenerList = new EventListenerList();

    /**
     * Sets up the details panel on the left, with a lovely wee border.
     */
    public DetailsPanel() {
        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Control Panel"));

        ////SETUP THE LABELS ////
        JLabel passwordLabel = new JLabel("Password length: ");
        JLabel strongLabel = new JLabel("All characters   ");
        JLabel mediumLabel = new JLabel("Alpha numeric   ");
        JLabel weakLabel = new JLabel("Just the alphabet   ");
        JLabel numericLabel = new JLabel("Numeric only   ");

        ////SETUP THE TEXT FIELDS ////
        final JTextField passwordLengthField = new JTextField("8", 3);
        final JTextArea generatedPWTextArea = new JTextArea();
        final JScrollPane generatedPWScrollArea = new JScrollPane(generatedPWTextArea);
        generatedPWScrollArea.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        generatedPWScrollArea.setPreferredSize(new Dimension(200, 55));
        generatedPWTextArea.setLineWrap(true);
        generatedPWTextArea.setEditable(false);

        //// SETUP THE BUTTON ////
        JButton addBtn = new JButton("Generate");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String length = passwordLengthField.getText().trim();
                String LengthText = String.valueOf(CharLists.generator(Integer.parseInt(length)));
                fireDetailEvent(new DetailEvent(this, LengthText));
            }
        });

        ////SETUP THE OUTPUT BOX////
        addDetailListener(new DetailListener(){
            public void detailEventOccurred(DetailEvent event) {
                String text = event.getText();

                generatedPWTextArea.setText(text);
            }
        });

        //// SETUP RADIO BUTTONS ////
        JRadioButton strongButton = new JRadioButton(STRONG_STRING);
        strongButton.setMnemonic(KeyEvent.VK_B);
        strongButton.setActionCommand(STRONG_STRING);
        strongButton.setSelected(true);

        JRadioButton mediumButton = new JRadioButton(MEDIUM_STRING);
        mediumButton.setMnemonic(KeyEvent.VK_C);
        mediumButton.setActionCommand(MEDIUM_STRING);

        JRadioButton weakButton = new JRadioButton(WEAK_STRING);
        weakButton.setMnemonic(KeyEvent.VK_D);
        weakButton.setActionCommand(WEAK_STRING);

        JRadioButton numberButton = new JRadioButton(NUMERIC_STRING);
        numberButton.setMnemonic(KeyEvent.VK_R);
        numberButton.setActionCommand(NUMERIC_STRING);

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

        //// SETUP THE LAYOUT ////
        gc.anchor = GridBagConstraints.LINE_START;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        // First column
        gc.gridx = 1;
        gc.gridy = 0;
        add(strongButton, gc);
        gc.gridy = 1;
        add(mediumButton, gc);
        gc.gridy = 2;
        add(weakButton, gc);
        gc.gridy = 3;
        add(numberButton, gc);
        gc.gridy = 4;
        add(passwordLengthField, gc);


        // Second column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        add(strongLabel, gc);
        gc.gridy = 1;
        add(mediumLabel, gc);
        gc.gridy = 2;
        add(weakLabel, gc);
        gc.gridy = 3;
        add(numericLabel, gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gc);

        // Add the text panel where the password is written to
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.BASELINE;
        gc.gridy = 5;
        gc.gridwidth = 2;
        add(generatedPWScrollArea, gc);

        // Generate button Row
        gc.weighty = 10;
        gc.gridy = 6;
        add(addBtn, gc);
        //
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
        CharLists.setStrength(e.getActionCommand());
    }
}