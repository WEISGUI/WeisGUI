import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ShipmentsPage extends JDialog {
    private JPanel ShipmentsPagePane;
    private JPanel ShipmentsPagePanel;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JButton productButton;
    private JTextField orderDateTxtField;
    private JTextField shipmentPriceTxtField;
    private JTextField shipmentIDTxtField;
    private JButton addShipmentButton;
    private JButton updateShipmentButton;
    private JButton deleteShipmentButton;
    private JScrollPane shipmentsScrollPane;
    private JTable shipmentsTable;
    private JTextField deliveryDateTxtField;
    private JTextField quantityTxtField;
    private JComboBox productIDComboBox;
    private JComboBox inventoryIDComboBox;
    private JComboBox supplierIDComboBox;
    private JComboBox employeeIDComboBox;
    private JTextField supplierSelectText;
    private JTextField productSelectText;
    private JTextField inventorySelectText;
    private JTextField employeeSelectText;


    private Employee weisEmployee;

    public ShipmentsPage(Employee weisEmployee) {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Shipments Page");
        setContentPane(ShipmentsPagePanel);
        setMinimumSize(new Dimension(1535, 850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //Establish Connection and Populate the Form
        try {
            createConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //Go to Home
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
            public void actionPerformed(ActionEvent e) {
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

        //Go to Products
        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductsPage productsPage = new ProductsPage(weisEmployee);
            }
        });
        //Add Shipments
        addShipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Shipment_id = shipmentIDTxtField.getText();
                String Shipment_price = shipmentPriceTxtField.getText();
                String Order_date = orderDateTxtField.getText();
                String Delivery_date = deliveryDateTxtField.getText();
                String Quantity = quantityTxtField.getText();
                String selectedSupplier = supplierIDComboBox.getSelectedItem().toString();
                String selectedProduct = productIDComboBox.getSelectedItem().toString();
                String selectedInventory = inventoryIDComboBox.getSelectedItem().toString();
                String selectedEmployee = employeeIDComboBox.getSelectedItem().toString();

                //Boolean Variables to check if ShipmentID
                Boolean checkShipmentID = false;


                //Check if ShipmentID Exists
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
                    String sql = "SELECT * FROM SHIPMENT WHERE Shipment_id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, Shipment_id);


                    ResultSet resultSet = preparedStatement.executeQuery();

                    //While there is an entry in the PRODUCT Table check if the Product_id, Product_name, or Product_serial is the same as the entered in Product_id, Product_name, or Product_serial
                    while (resultSet.next()) {
                        if (resultSet.getString("Product_id").equals(Shipment_id)) {
                            checkShipmentID = true;
                        }

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                //Get Supplier Selection
                supplierIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        supplierSelectText.setText(selectedSupplier);
                    }
                });

                //Get Product Selection
                productIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        productSelectText.setText(selectedProduct);
                    }
                });
                //Get Inventory Selection
                inventoryIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inventorySelectText.setText(selectedInventory);
                    }
                });
                //Get Employee Selection
                employeeIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        employeeSelectText.setText(selectedEmployee);
                    }
                });
                if (Shipment_id.isEmpty()) {
                    JOptionPane.showMessageDialog(ShipmentsPage.this,
                            "Error: Shipment ID field is empty, please enter a Shipment ID",
                            "Empty Shipment ID",
                            JOptionPane.ERROR_MESSAGE);

                } else {
                    try {

                        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

                        String sql = "INSERT INTO SHIPMENT (Shipment_id, Shipment_price, Order_date, Delivery_date, Quantity, Supplier_id, Product_id, Inventory_id, Employee_id)"
                                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, Shipment_id);
                        preparedStatement.setString(2, Shipment_price);
                        preparedStatement.setString(3, Order_date);
                        preparedStatement.setString(4, Delivery_date);
                        preparedStatement.setString(5, Quantity);
                        preparedStatement.setString(6, selectedSupplier);
                        preparedStatement.setString(7, selectedProduct);
                        preparedStatement.setString(8, selectedInventory);
                        preparedStatement.setString(9, selectedEmployee);
                        preparedStatement.executeUpdate();

                        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM SHIPMENT");
                        ResultSet resultSet = selectStatement.executeQuery();
                        shipmentsTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                        shipmentIDTxtField.setText("");
                        shipmentPriceTxtField.setText("");
                        orderDateTxtField.setText("");
                        deliveryDateTxtField.setText("");
                        quantityTxtField.setText("");
                        supplierSelectText.setText("");
                        productSelectText.setText("");
                        inventorySelectText.setText("");
                        employeeSelectText.setText("");

                        supplierIDComboBox.setVisible(true);
                        productIDComboBox.setVisible(true);
                        inventoryIDComboBox.setVisible(true);
                        employeeIDComboBox.setVisible(true);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Update Shipment
        updateShipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Shipment_id = shipmentIDTxtField.getText();
                String Shipment_price = shipmentPriceTxtField.getText();
                String Order_date = orderDateTxtField.getText();
                String Delivery_date = deliveryDateTxtField.getText();
                String Quantity = quantityTxtField.getText();
                String selectedSupplier = supplierIDComboBox.getSelectedItem().toString();
                String selectedProduct = productIDComboBox.getSelectedItem().toString();
                String selectedInventory = inventoryIDComboBox.getSelectedItem().toString();
                String selectedEmployee = employeeIDComboBox.getSelectedItem().toString();

                //Boolean Variables to check if ShipmentID
                Boolean checkShipmentID = false;


                //Check if ShipmentID Exists
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
                    String sql = "SELECT * FROM SHIPMENT WHERE Shipment_id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, Shipment_id);


                    ResultSet resultSet = preparedStatement.executeQuery();

                    //While there is an entry in the Shipment Table
                    while (resultSet.next()) {
                        if (resultSet.getString("Product_id").equals(Shipment_id)) {
                            checkShipmentID = true;
                        }

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                //Get Supplier Selection
                supplierIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        supplierSelectText.setText(selectedSupplier);
                    }
                });

                //Get Product Selection
                productIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        productSelectText.setText(selectedProduct);
                    }
                });
                //Get Inventory Selection
                inventoryIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inventorySelectText.setText(selectedInventory);
                    }
                });
                //Get Employee Selection
                employeeIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        employeeSelectText.setText(selectedEmployee);
                    }
                });
                if (checkShipmentID) {
                    JOptionPane.showMessageDialog(ShipmentsPage.this,
                            "Error: Shipment ID field is empty, please enter a Shipment ID",
                            "Empty Shipment ID",
                            JOptionPane.ERROR_MESSAGE);

                } else {
                    try {

                        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

                        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE PRODUCT SET Shipment_price = ?, Order_date = ?, Delivery_date = ?, Quantity = ?, Supplier_id = ?, Product_id = ?, Inventory_id = ?, Employee_id = ? WHERE Shipment_id = ?");


                        preparedStatement.setString(1, Shipment_price);
                        preparedStatement.setString(2, Order_date);
                        preparedStatement.setString(3, Delivery_date);
                        preparedStatement.setString(4, Quantity);
                        preparedStatement.setString(5, selectedSupplier);
                        preparedStatement.setString(6, selectedProduct);
                        preparedStatement.setString(7, selectedInventory);
                        preparedStatement.setString(8, selectedEmployee);
                        preparedStatement.setString(9, Shipment_id);
                        preparedStatement.executeUpdate();

                        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM SHIPMENT");
                        ResultSet resultSet = selectStatement.executeQuery();
                        shipmentsTable.setModel(DbUtils.resultSetToTableModel(resultSet));


                        shipmentPriceTxtField.setText("");
                        orderDateTxtField.setText("");
                        deliveryDateTxtField.setText("");
                        quantityTxtField.setText("");
                        supplierSelectText.setText("");
                        productSelectText.setText("");
                        inventorySelectText.setText("");
                        employeeSelectText.setText("");
                        shipmentIDTxtField.setText("");

                        supplierIDComboBox.setVisible(true);
                        productIDComboBox.setVisible(true);
                        inventoryIDComboBox.setVisible(true);
                        employeeIDComboBox.setVisible(true);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        // Delete Shipment
        deleteShipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Shipment_id = shipmentIDTxtField.getText();
                String Shipment_price = shipmentPriceTxtField.getText();
                String Order_date = orderDateTxtField.getText();
                String Delivery_date = deliveryDateTxtField.getText();
                String Quantity = quantityTxtField.getText();
                String selectedSupplier = supplierIDComboBox.getSelectedItem().toString();
                String selectedProduct = productIDComboBox.getSelectedItem().toString();
                String selectedInventory = inventoryIDComboBox.getSelectedItem().toString();
                String selectedEmployee = employeeIDComboBox.getSelectedItem().toString();

                //Get Supplier Selection
                supplierIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        supplierSelectText.setText(selectedSupplier);
                    }
                });

                //Get Product Selection
                productIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        productSelectText.setText(selectedProduct);
                    }
                });
                //Get Inventory Selection
                inventoryIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        inventorySelectText.setText(selectedInventory);
                    }
                });
                //Get Employee Selection
                employeeIDComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        employeeSelectText.setText(selectedEmployee);
                    }
                });
                if (Shipment_id.isEmpty()) {
                    JOptionPane.showMessageDialog(ShipmentsPage.this,
                            "Error: ShipmentID is empty, please enter a Shipment to be deleted",
                            "Empty Shipment ID",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {

                        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
                        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM SHIPMENT WHERE Shipment_id = ?");

                        preparedStatement.setString(1, Shipment_id);

                        preparedStatement.executeUpdate();


                        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM SHIPMENT");
                        ResultSet resultSet = selectStatement.executeQuery();
                        shipmentsTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                        shipmentIDTxtField.setText("");
                        shipmentPriceTxtField.setText("");
                        orderDateTxtField.setText("");
                        deliveryDateTxtField.setText("");
                        quantityTxtField.setText("");
                        supplierSelectText.setText("");
                        productSelectText.setText("");
                        inventorySelectText.setText("");
                        employeeSelectText.setText("");

                        supplierIDComboBox.setVisible(true);
                        productIDComboBox.setVisible(true);
                        inventoryIDComboBox.setVisible(true);
                        employeeIDComboBox.setVisible(true);

                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        setVisible(true);
    }

    public void createConnection() throws SQLException {
        //Populate Products Table
        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM SHIPMENT");
        ResultSet resultSet = selectStatement.executeQuery();
        shipmentsTable.setModel(DbUtils.resultSetToTableModel(resultSet));


        //Populate Supplier ComboBox

        try {

            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

            String sql = "SELECT * FROM SUPPLIER";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while (resultSet1.next()) {
                supplierIDComboBox.addItem(resultSet1.getString("Supplier_id"));
                supplierIDComboBox.setVisible(true);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        //Populate Product ComboBox

        try {
            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

            String sql = "SELECT * FROM PRODUCT";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                productIDComboBox.addItem(resultSet2.getString("Product_id"));
                productIDComboBox.setVisible(true);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //Populate Inventory ComboBox
        try {
            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

            String sql = "SELECT * FROM INVENTORY";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                inventoryIDComboBox.addItem(resultSet2.getString("Inventory_id"));
                inventoryIDComboBox.setVisible(true);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        //Populate Employee ComboBox
        try {
            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

            String sql = "SELECT * FROM EMPLOYEE";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                employeeIDComboBox.addItem(resultSet2.getString("Employee_id"));
                employeeIDComboBox.setVisible(true);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static void main(String[] args) {
        ShipmentsPage shipmentsPage = new ShipmentsPage(null);
    }
}
