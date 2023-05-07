import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.sql.*;

public class SuppliersPage extends JDialog {
    private JPanel SuppliersPagePanel;

    private Employee weisEmployee;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JTextField supplierAddressTxtField;
    private JButton addSupplierButton;
    private JButton updateSupplierButton;
    private JButton deleteSupplierButton;
    private JScrollPane supplierScrollPane;
    private JTable supplierTable;
    private JTextField supplierPhoneTxtField;
    private JTextField supplierEmailTxtField;
    private JTextField supplierNameTxtField;
    private JTextField supplierIDTxtField;
    private JButton productButton;

    public SuppliersPage(Employee weisEmployee) throws SQLException
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Suppliers Page");
        setContentPane(SuppliersPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM SUPPLIER");
        ResultSet resultSet = selectStatement.executeQuery();
        supplierTable.setModel(DbUtils.resultSetToTableModel(resultSet));


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage(null);
            }
        });


        categoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    CategoryPages categoryPages = new CategoryPages(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        productLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    ProductsLocationPage productsLocationPage = new ProductsLocationPage(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        suppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    SuppliersPage suppliersPage = new SuppliersPage(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        shipmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShipmentsPage shipmentsPage = new ShipmentsPage(null);
            }
        });


        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InventoryPage inventoryPage = new InventoryPage(null);
            }
        });


        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AccountPage accountPage = new AccountPage(weisEmployee);
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductsPage productsPage = new ProductsPage(null);
            }
        });

        addSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Supplier_id = supplierIDTxtField.getText();
                String Supplier_name = supplierNameTxtField.getText();
                String Supplier_address = supplierAddressTxtField.getText();
                String Supplier_phone = supplierPhoneTxtField.getText();
                String Supplier_email = supplierEmailTxtField.getText();

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

                    String sql = "INSERT INTO SUPPLIER (Supplier_id, Supplier_name, Supplier_address, Supplier_phone, Supplier_email)"
                            + "VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, Supplier_id);
                    preparedStatement.setString(2, Supplier_name);
                    preparedStatement.setString(3, Supplier_address);
                    preparedStatement.setString(4, Supplier_phone);
                    preparedStatement.setString(5, Supplier_email);

                    preparedStatement.executeUpdate();

                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM SUPPLIER");
                    ResultSet resultSet = selectStatement.executeQuery();
                    supplierTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                    supplierIDTxtField.setText("");
                    supplierNameTxtField.setText("");
                    supplierAddressTxtField.setText("");
                    supplierPhoneTxtField.setText("");
                    supplierEmailTxtField.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        updateSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        SuppliersPage suppliersPage = new SuppliersPage(null);
    }
}
