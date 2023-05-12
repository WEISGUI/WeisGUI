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
    private JList<String> queryList;

    private Connection connection;
    private Employee weisEmployee;

    // Query scripts

    final String productPrices = "select Product_name, Price from PRODUCT";
    final String lowInventory = "select Product_name, Quantity from PRODUCT, INVENTORY where PRODUCT.Product_id = INVENTORY.Product_id AND Quantity <= 15";
    final String highInventory = "select Product_name, Quantity from PRODUCT, INVENTORY where PRODUCT.Product_id = INVENTORY.Product_id AND Quantity > 15";
    final String cheapProduct = "select Product_name, Price from PRODUCT where Price <= 5";
    final String expensiveProduct = "select Product_name, Price from PRODUCT where Price > 5";
    final String k = "";

    public WeisScripting(Employee weisEmployee) {
        // Set title, default close operation, and size of frame
        this.weisEmployee = weisEmployee;
        setTitle("Java Swing With MySQL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.red, 10));


        // Set the layout of the frame as BorderLayout
        // setLayout(new BorderLayout());

        // Create the input field and submit button panel
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(30);
        inputPanel.add(inputField);
        submitButton = new JButton("Send");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.add(submitButton);
        add(inputPanel, BorderLayout.NORTH);
        inputPanel.setBackground(Color.red);
        inputField.setPreferredSize(new Dimension(200, 50));
        inputField.setFont(new Font("Arial", Font.BOLD, 20));
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.setForeground(Color.red);
        submitButton.setForeground(Color.red);
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        inputField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));


        // Create the output table panel
        outputTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(outputTable);
        add(scrollPane, BorderLayout.CENTER);
        outputTable.setBackground(Color.red);
        outputTable.setForeground(Color.white);
        outputTable.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        scrollPane.setBackground(Color.red);
        scrollPane.setForeground(Color.red);

        // Create list model to use with the JList instruction panel
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("product price");
        listModel.addElement("low inventory (15 and below)");
        listModel.addElement("high inventory (more than 15)");
        listModel.addElement("cheap product (5 and below)");
        listModel.addElement("expensive product (more than 5)");


        // Create instruction panel
        JPanel instructPanel = new JPanel();
        JLabel label = new JLabel("EXAMPLE QUERIES");
        JList<String> list = new JList<>(listModel);
        instructPanel.add(label, BorderLayout.LINE_START);
        instructPanel.add(list, BorderLayout.NORTH);
        add(instructPanel, BorderLayout.SOUTH);
        instructPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        list.setBackground(Color.red);
        list.setForeground(Color.white);
        label.setPreferredSize(new Dimension(500, 200));
        list.setFont(new Font("Arial", Font.BOLD, 30));
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        list.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        instructPanel.setBackground(Color.red);


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
            String input;
            switch (inputField.getText()) {
                case "product price":
                    input = productPrices;
                    break;
                case "low inventory":
                    input = lowInventory;
                    break;
                case "high inventory":
                    input = highInventory;
                    break;
                case "cheap product":
                    input = cheapProduct;
                    break;
                case "expensive product":
                    input = expensiveProduct;
                    break;
                default:
                    input = "select * from EMPLOYEE";
                    break;

            }


            // String input = inputField.getText().toUpperCase();
            // System.out.println(input);
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
