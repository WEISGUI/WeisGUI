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
    private JTextField categorySelectionTxtField;
    private JTextField supplierIDtxtField;

    private Employee weisEmployee;

    public ProductsPage(Employee weisEmployee) throws SQLException
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Products Page");
        setContentPane(ProductsPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Populate Products Table
        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM PRODUCT");
        ResultSet resultSet = selectStatement.executeQuery();
        productTable.setModel(DbUtils.resultSetToTableModel(resultSet));


        //Populate Supplier ComboBox
        String supplierID = null;
        try {

            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

            String sql = "SELECT * FROM SUPPLIER";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet1 = preparedStatement.executeQuery();

            while(resultSet1.next())
            {
                supplierIdComboBox.addItem(resultSet1.getString("Supplier_name"));
                supplierIdComboBox.setVisible(true);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


        //Populate Category ComboBox
        String categoryID = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

            String sql = "SELECT * FROM CATEGORY";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet2 = preparedStatement.executeQuery();

            while (resultSet2.next()) {
                categoryNameComboBox.addItem(resultSet2.getString("Category_name"));
                categoryNameComboBox.setVisible(true);
            }
        } catch (SQLException ex){
            throw new RuntimeException(ex);
        }


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

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Product_id = productIDTxtField.getText();
                String Exp_date = expirationDateTxtField.getText();
                String Product_name = productNameTxtField.getText();
                String Product_serial = productSerialNoTxtField.getText();
                String Price = priceTxtField.getText();
                String Product_description = productDescriptionTxtField.getText();
                String selectedCategory = categoryNameComboBox.getSelectedItem().toString();
                String selectedSupplier = supplierIdComboBox.getSelectedItem().toString();

                //Get Category Selection
                categoryNameComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        categorySelectionTxtField.setText(selectedCategory);
                    }
                });

                //Get Supplier Selection
                supplierIDtxtField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        supplierIDtxtField.setText(selectedSupplier);
                    }
                });


                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

                    String sql = "INSERT INTO PRODUCT (Product_id, Exp_date, Product_name, Product_serial, Price, Product_description, Category_name, Supplier_name)"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, Product_id);
                    preparedStatement.setString(2, Exp_date);
                    preparedStatement.setString(3, Product_name);
                    preparedStatement.setString(4, Product_serial);
                    preparedStatement.setString(5, Price);
                    preparedStatement.setString(6, Product_description);
                    preparedStatement.setString(7, selectedCategory);
                    preparedStatement.setString(8, selectedSupplier);
                    preparedStatement.executeUpdate();

                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM PRODUCT");
                    ResultSet resultSet = selectStatement.executeQuery();
                    productTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                    productIDTxtField.setText("");
                    expirationDateTxtField.setText("");
                    productNameTxtField.setText("");
                    productSerialNoTxtField.setText("");
                    priceTxtField.setText("");
                    productDescriptionTxtField.setText("");
                    categorySelectionTxtField.setText("");
                    supplierIDtxtField.setText("");

                    categoryNameComboBox.setVisible(true);
                    supplierIdComboBox.setVisible(true);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        updateProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) throws SQLException {
        ProductsPage productsPage = new ProductsPage(null);
    }
}
