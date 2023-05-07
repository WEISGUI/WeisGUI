import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ProductsLocationPage extends JDialog {
    private JPanel ProductLocationPanel;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JTextField productLocationIDTxtField;
    private JButton addProductLocation;
    private JButton updateProductLocationButton;
    private JButton deleteProductLocationButton;
    private JScrollPane productLocationScrollPane;
    private JTable productLocationTable;
    private JTextField productLocationShelfTxtField;
    private JTextField productLocationAisleNumberTxtField;
    private JButton productButton;

    private Employee weisEmployee;
    public ProductsLocationPage(Employee weisEmployee) throws SQLException
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Products Location Page");
        setContentPane(ProductLocationPanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM LOCATION");
        ResultSet resultSet = selectStatement.executeQuery();
        productLocationTable.setModel(DbUtils.resultSetToTableModel(resultSet));

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
                SuppliersPage suppliersPage = new SuppliersPage(null);
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


        addProductLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Location_id = productLocationIDTxtField.getText();
                String Aisle = productLocationAisleNumberTxtField.getText();
                String Shelf = productLocationShelfTxtField.getText();

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

                    String sql = "INSERT INTO LOCATION (Location_id, Aisle, Shelf)"
                            + "VALUES (?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, Location_id);
                    preparedStatement.setString(2, Aisle);
                    preparedStatement.setString(3, Shelf);
                    preparedStatement.executeUpdate();

                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM LOCATION");
                    ResultSet resultSet = selectStatement.executeQuery();
                    productLocationTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                    productLocationIDTxtField.setText("");
                    productLocationAisleNumberTxtField.setText("");
                    productLocationShelfTxtField.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        updateProductLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Location_id = productLocationIDTxtField.getText();
                String Aisle = productLocationAisleNumberTxtField.getText();
                String Shelf = productLocationShelfTxtField.getText();

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE LOCATION SET Aisle = ?, Shelf = ? WHERE Location_id = ?");

                    preparedStatement.setString(1, Aisle);
                    preparedStatement.setString(2, Shelf);
                    preparedStatement.setString(3, Location_id);
                    preparedStatement.executeUpdate();

                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM LOCATION");
                    ResultSet resultSet = selectStatement.executeQuery();
                    productLocationTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                    productLocationIDTxtField.setText("");
                    productLocationAisleNumberTxtField.setText("");
                    productLocationShelfTxtField.setText("");
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });
        deleteProductLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Location_id = productLocationIDTxtField.getText();
                String Aisle = productLocationAisleNumberTxtField.getText();
                String Shelf = productLocationShelfTxtField.getText();

                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
                    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM LOCATION WHERE Location_id = ? AND Aisle = ? AND Shelf = ?");

                    preparedStatement.setString(1, Location_id);
                    preparedStatement.setString(2, Aisle);
                    preparedStatement.setString(3, Shelf);
                    preparedStatement.executeUpdate();

                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM LOCATION");
                    ResultSet resultSet = selectStatement.executeQuery();
                    productLocationTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                    productLocationIDTxtField.setText("");
                    productLocationAisleNumberTxtField.setText("");
                    productLocationShelfTxtField.setText("");
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        ProductsLocationPage productsLocationPage = new ProductsLocationPage(null);
    }
}
