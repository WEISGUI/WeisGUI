import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InventoryPage extends JDialog {
    private JPanel InventoryPagePane;
    private JPanel InventoryPagePanel;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JButton productButton;
    private JTextField updatedAmountTxtField;
    private JTextField inventoryValueTxtField;
    private JTextField inventoryIDTxtField;
    private JButton addInventoryButton;
    private JButton updateInventoryButton;
    private JButton deleteInventoryButton;
    private JScrollPane inventoryScrollPane;
    private JTable inventoryTable;
    private JTextField updatedDateTxtField;
    private JTextField quantityTxtField;
    private JComboBox employeeIDComboBox;
    private JComboBox locationIDComboBox;
    private JComboBox productIDComboBox;

    private Employee weisEmployee;

    public InventoryPage(Employee weisEmployee)
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Inventory Page");
        setContentPane(InventoryPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Populate Inventory Table
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        PreparedStatement selectStatement = null;
        try {
            selectStatement = connection.prepareStatement("SELECT * FROM INVENTORY");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet resultSet = null;
        try {
            resultSet = selectStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        inventoryTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        
        //Go home
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage(weisEmployee);
            }
        });


        //Go to Categories
        categoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    CategoryPages categoryPages = new CategoryPages(weisEmployee);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Go to Product Location
        productLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    ProductsLocationPage productsLocationPage = new ProductsLocationPage(weisEmployee);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //Go to Suppliers
        suppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    SuppliersPage suppliersPage = new SuppliersPage(weisEmployee);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        //Go to Shipments
        shipmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShipmentsPage shipmentsPage = new ShipmentsPage(weisEmployee);
            }
        });


        //Go to Inventory
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                InventoryPage inventoryPage = new InventoryPage(weisEmployee);
            }
        });

        //Go to Account
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AccountPage accountPage = new AccountPage(weisEmployee);
            }
        });

        //Go to Product
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductsPage productsPage = new ProductsPage(weisEmployee);
            }
        });

        setVisible(true);

        addInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Connection connection = null;
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                /*
                String Inventory_id = inventoryIDTxtField.getText();
                String Updated_amount =
                String Updated_date = updatedDateTxtField.getText();
                String Quantity =
                String selectedProductID = productIDComboBox.getSelectedItem().toString();
                String selectedEmployeeID = employeeIDComboBox.getSelectedItem().toString();
                String selectedLocationID = locationIDComboBox.getSelectedItem().toString();


                 */

                //Boolean Variables to check if Product_id, Product_serial, and Product_name exists already
                Boolean CheckProductID = false;
                Boolean CheckEmployeeID = false;
                Boolean CheckLocationID = false;

                /*
                //Check if ProductID, ProductName, or ProductSerial Exists
                try {
                    String sql5 = "SELECT * FROM INVENTORY WHERE Inventory_id = ?, Inventory Value = ?, Updated_amount = ?, Updated_date = ?, Quantity = ?, Product_id = ?, Employee_id = ?, Location_id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql5);
                    preparedStatement.setString(1, Inventory_id);
                    preparedStatement.setString(2, Inventory_value);
                    preparedStatement.setString(3, Updated_amount);
                    preparedStatement.setString(4, Updated_date);
                    preparedStatement.setString(5, Quantity);
                    preparedStatement.setString(6, selectedProductID);
                    preparedStatement.setString(7, selectedEmployeeID);
                    preparedStatement.setString(8, selectedLocationID);

                    inventory = Price * Quantity;
                    ResultSet resultSet = preparedStatement.executeQuery();

                    //Delete this
                    resultSet.next();
                }
                catch(SQLException ex) {
                    ex.printStackTrace();
                }

                 */
            }
        });


        updateInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args)
    {
        InventoryPage inventoryPage = new InventoryPage(null);
    }
}
