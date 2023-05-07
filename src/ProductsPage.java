import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ProductsPage extends JDialog{
    private JPanel ProductsPagePanel;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JButton productButton;
    private JPanel ProductsPagePane;
    private JTextField productNameTxtField;
    private JTextField expirationDateTxtField;
    private JTextField productIDTxtField;
    private JButton addProductButton;
    private JButton updateProductButton;
    private JButton deleteProductButton;
    private JScrollPane productScrollPane;
    private JTable productTable;
    private JTextField productSerialNoTxtField;
    private JTextField priceTxtField;
    private JTextField productDescriptionTxtField;
    private JComboBox categoryNameComboBox;
    private JComboBox supplierIdComboBox;

    private Employee weisEmployee;

    public ProductsPage(Employee weisEmployee) throws SQLException
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Products Page");
        setContentPane(ProductsPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM PRODUCT");
        ResultSet resultSet = selectStatement.executeQuery();
        productTable.setModel(DbUtils.resultSetToTableModel(resultSet));


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
                try {
                    ProductsPage productsPage = new ProductsPage(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);

    }

    public static void main(String[] args) throws SQLException {
        ProductsPage productsPage = new ProductsPage(null);
    }
}
