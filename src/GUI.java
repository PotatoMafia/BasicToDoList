import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class GUI extends JFrame {
    private JButton button = new JButton("ADD / UPDATE");
    private JTextField input = new JTextField("", 5);
    private JTextField inputDescription = new JTextField("", 5);
    private JLabel label = new JLabel("To Do List", JLabel.CENTER);

    private JButton deleteButton = new JButton("Delete Selected");
    private JScrollPane scrollPane;
    private JPanel buttonPanel;

    private void addingTaskes(){
        BD bd = new BD();
       List<String> tasks = new ArrayList<>();
       tasks.addAll(bd.takeTask());

        List<String> descriptionOfTasks = new ArrayList<>();
        descriptionOfTasks.addAll(bd.takeDecriptionOfTheTask());

        for(int i = 0; i < tasks.size(); i++){
            JCheckBox checkBox = new JCheckBox(tasks.get(i));
            checkBox.setFont(new Font("Arial", Font.PLAIN, 12));
            checkBox.setForeground(Color.BLACK);
            checkBox.setSelected(false);
            checkBox.setToolTipText(descriptionOfTasks.get(i));
            this.buttonPanel.add(new JCheckBox("<html><font color='black'>"+ tasks.get(i) + "</font><br><font color='gray' size='2'>"+descriptionOfTasks.get(i)+"</font></html>"));

        }
    }

    public GUI() {
        super("Simple Example");
        this.setBounds(100, 100, 600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        label.setHorizontalTextPosition(JLabel.CENTER);
        container.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        addingTaskes();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Устанавливаем вертикальное расположение

        scrollPane = new JScrollPane(buttonPanel);

        container.add(label, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);

        container.add(input, BorderLayout.SOUTH);
        container.add(inputDescription, BorderLayout.SOUTH);

        ButtonGroup group = new ButtonGroup();
        button.addActionListener(new ButtonEventListener());
        button.setSize(10,10);
        container.add(button, BorderLayout.EAST);
        deleteButton.addActionListener(new DeleteButtonEventListener());
        deleteButton.setSize(10, 10);
        container.add(deleteButton, BorderLayout.WEST);
    }

    class DeleteButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Component[] components = buttonPanel.getComponents();

            for (Component component : components) {
                if (component instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) component;
                    if (checkBox.isSelected()) {
                        String taskText = checkBox.getText();

                        String pattern = "<font color='black'>(.*?)</font>";

                        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
                        java.util.regex.Matcher matcher = regex.matcher(taskText);

                        while (matcher.find()) {
                            String match = matcher.group(1);
                            //System.out.println(match);
                            taskText = match;
                        }

                        BD bd = new BD();
                        bd.deleteElement(taskText);
                        //System.out.println(taskText);


                        buttonPanel.remove(checkBox);
                    }
                }
            }

            revalidate();
            repaint();
        }
    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String task = input.getText();
            String description = inputDescription.getText();

            if (!task.isEmpty()) {

                BD bd = new BD();
                bd.addObj(task, description);
            }

            buttonPanel.removeAll();
            addingTaskes();

            input.setText("");
            inputDescription.setText("");

            revalidate();
            repaint();
        }






}

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);
    }
}
