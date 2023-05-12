import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class WeisScripting extends JFrame {
    private String URL = "jdbc:mysql://triton.towson.edu:3360/bdeguz1db";
    private String Username = "bdeguz1";
    private String Password = "COSC*bo29m";

    private JTextField inputField;
    private JButton submitButton;
    private JButton HomeButton;
    private JButton CategoryButton;
    private JButton ProductsButton;
    private JButton ProductLocationButton;
    private JButton SuppliersButton;
    private JButton ShipmentsButton;
    private JButton InventoryButton;
    private JButton SearchButton;
    private JButton AccountButton;
    private JTable outputTable;

    private Connection connection;
    private Employee weisEmployee;

    public WeisScripting(Employee weisEmployee) {
        // Set title, default close operation, and size of frame
        this.weisEmployee = weisEmployee;
        setTitle("Java Swing With MySQL");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1535, 850);
        setLocationRelativeTo(null);

        // Set the layout of the frame as BorderLayout
        setLayout(new BorderLayout());

        //Create Menu Bar Panel
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        ImageIcon imageIcon = new ImageIcon("src/Weis Logo.png");
        JLabel imageLabel = new JLabel(imageIcon);
        HomeButton = new JButton("Home");
        CategoryButton = new JButton("Category");
        ProductsButton = new JButton("Product");
        ProductLocationButton = new JButton("Product Location");
        SuppliersButton = new JButton("Suppliers");
        ShipmentsButton = new JButton("Shipments");
        InventoryButton = new JButton("Inventory");
        SearchButton = new JButton("Search");
        AccountButton = new JButton("Account");

        //Set Button Colors
        HomeButton.setBackground(Color.WHITE);
        CategoryButton.setBackground(Color.WHITE);
        ProductsButton.setBackground(Color.WHITE);
        ProductLocationButton.setBackground(Color.WHITE);
        SuppliersButton.setBackground(Color.WHITE);
        ShipmentsButton.setBackground(Color.WHITE);
        InventoryButton.setBackground(Color.WHITE);
        SearchButton.setBackground(Color.WHITE);
        AccountButton.setBackground(Color.WHITE);

        //Add Buttons to Panel from Left to Right
        menuPanel.add(imageLabel);
        menuPanel.add(HomeButton);
        menuPanel.add(CategoryButton);
        menuPanel.add(ProductsButton);
        menuPanel.add(ProductLocationButton);
        menuPanel.add(SuppliersButton);
        menuPanel.add(ShipmentsButton);
        menuPanel.add(InventoryButton);
        menuPanel.add(SearchButton);
        menuPanel.add(AccountButton);

        //Listen for Button Click
        HomeButton.addActionListener(new goToHomePageListener());
        CategoryButton.addActionListener(new categoryListener());
        ProductsButton.addActionListener(new productListener());
        ProductLocationButton.addActionListener(new productLocationListener());
        SuppliersButton.addActionListener(new supplierListener());
        ShipmentsButton.addActionListener(new shipmentListener());
        InventoryButton.addActionListener(new inventoryListener());
        SearchButton.addActionListener(new searchListener());
        AccountButton.addActionListener(new accountListener());


        //Add Panel to Specific Area and set its Background Color
        menuPanel.setBackground(Color.RED);
        menuPanel.add(Box.createVerticalStrut(100));
        add(menuPanel, BorderLayout.PAGE_START);

        // Create the input field and submit button panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(20);
        inputPanel.add(inputField);
        inputPanel.setBackground(Color.WHITE);
        submitButton = new JButton("Find");
        submitButton.addActionListener(new SubmitButtonListener());
        inputPanel.add(submitButton);
        add(inputPanel, BorderLayout.CENTER);

        // Create the output table panel
        outputTable = new JTable(new DefaultTableModel(new Object[]{"Output"}, 0));
        JScrollPane scrollPane = new JScrollPane(outputTable);
        add(scrollPane, BorderLayout.SOUTH);

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

    //Go Home
    private class goToHomePageListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            HomePage homePage = new HomePage(weisEmployee);
        }
    }

    //Go to Category
    private class categoryListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            try {
                new CategoryPages(weisEmployee);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //Go to Products
    private class productListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new ProductsPage(weisEmployee);
        }
    }

    //Go to Product Location
    private class productLocationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            try {
                new ProductsLocationPage(weisEmployee);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //Go to Suppliers
    private class supplierListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            try {
                new SuppliersPage(weisEmployee);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //Go to Shipments
    private class shipmentListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new ShipmentsPage(weisEmployee);
        }
    }

    //Go to Inventory
    private class inventoryListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new InventoryPage(weisEmployee);
        }
    }

    //Go to Search
    private class searchListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new WeisScripting(weisEmployee);
        }
    }

    //Go to Account
    private class accountListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            new AccountPage(weisEmployee);
        }
    }

    public static void main(String[] args) {

        new WeisScripting(null);
    }
}
