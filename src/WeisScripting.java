import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WeisScripting extends JFrame {
    private String URL = "jdbc:mysql://triton.towson.edu:3360/bdeguz1db";
    private String Username = "bdeguz1";
    private String Password = "COSC*bo29m";

    private JTextField inputField;
    private JButton submitButton;
    private JTable outputTable;

    private Connection connection;
    private Employee weisEmployee;

    public WeisScripting(Employee weisEmployee) {
        // Set title, default close operation, and size of frame
        this.weisEmployee = weisEmployee;
        setTitle("Java Swing With MySQL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // Set the layout of the frame as BorderLayout
        setLayout(new BorderLayout());

        // Create the input field and submit button panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(20);
        inputPanel.add(inputField);
        submitButton = new JButton("Find");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.add(submitButton);
        add(inputPanel, BorderLayout.NORTH);

        // Create the output table panel
        outputTable = new JTable(new DefaultTableModel(new Object[]{"Output"}, 0));
        JScrollPane scrollPane = new JScrollPane(outputTable);
        add(scrollPane, BorderLayout.CENTER);

        // Connect to database
        try {
            connection = DriverManager.getConnection(URL, Username, Password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the text from the input field
            /*
            * switch (i)
            *
            * case: a - low inventory
            *  String input = "select * from LOCATION";
            * b -
             *
             * */
           String input = inputField.getText();
            try {
                // Create statement
                Statement statement = connection.createStatement();

                // Execute query or update
                if (statement.execute(input)) {
                    // Display result in table
                    ResultSet resultSet = statement.getResultSet();

                    // Get metadata for result set
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    System.out.println(resultSet);
                    System.out.println(metaData);
                    int columnCount = metaData.getColumnCount();

                    // Create column headers for output table
                    Object[] columnNames = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames[i - 1] = metaData.getColumnLabel(i);
                    }

                    // Create default table model with column headers
                    DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                    // Loop through result set and add rows to table model
                    while (resultSet.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 1; i <= columnCount; i++) {
                            row[i - 1] = resultSet.getObject(i);
                        }
                        model.addRow(row);
                    }
                    // Set table model in output table
                    outputTable.setModel(model);
                } else {
                    // Display update count
                    int updateCount = statement.getUpdateCount();
                    DefaultTableModel model = new DefaultTableModel(new Object[]{"Output"}, 0);
                    model.addRow(new Object[]{"Updated " + updateCount + " rows"});
                    outputTable.setModel(model);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // Empty the text field after query
            inputField.setText("");
        }
    }

    public static void main(String[] args) {

        new WeisScripting(null);
    }
}
