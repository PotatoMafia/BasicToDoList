import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI extends JFrame {
    private JButton button = new JButton("First Button");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("To Do List",JLabel.CENTER);
    private JList<String> listOfTasks = new JList();
    //private JCheckBox checkBox = new JCheckBox("Check",false);
    public SimpleGUI(){
        super("Simple Example");
        this.setBounds(100,100, 1000, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        label.setHorizontalTextPosition(JLabel.CENTER);
        container.setLayout(new GridLayout(5,1,1,1));
        container.add(label);
        container.add(listOfTasks);
       // container.add(label);
        container.add(input);

        ButtonGroup group = new ButtonGroup();
        //group.add(radio1);
        //group.add(radio2);
       // container.add(radio1);
        //radio1.setSelected(true);
        //container.add(radio2);
        //container.add(checkBox);
        button.addActionListener(new ButtonEventListener ());
        container.add(button);

    }

    class ButtonEventListener implements ActionListener {
        public  void actionPerformed(ActionEvent e) {
            String message = "";
            message += "Button was pressed\n";
            message += "Text is " + input.getText();
           // message += (radio1.isSelected() ? "Radio #1" : "Radio #2") + "is selected!\n";
            //message += "Chechbox is " + ((checkBox.isSelected())? "checked" : "unchecked");
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);

        }
    }
}
