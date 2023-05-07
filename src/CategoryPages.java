import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

public class CategoryPages extends JDialog {
    private JPanel CategoryPagePane;
    private JPanel CategoryPagePanel;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JTextField categoryIDTxtField;
    private JTextField categoryNameTxtField;
    private JTextField categoryDescriptionTxtField;
    private JButton addCategoryButton;
    private JButton updateCategoryButton;
    private JButton deleteCategoryButton;
    private JTable categoryTable;
    private JScrollPane categoryScrollPane;
    private JButton productButton;
    private Employee weisEmployee;
    public CategoryPages(Employee weisEmployee) throws SQLException {

        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Category Page");
        setContentPane(CategoryPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM CATEGORY");
        ResultSet resultSet = selectStatement.executeQuery();
        categoryTable.setModel(DbUtils.resultSetToTableModel(resultSet));
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
                CategoryPage categoryPage = new CategoryPage(null);
            }
        });
        productLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductsLocationPage productsLocationPage = new ProductsLocationPage(null);
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
        addCategoryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Category_id = categoryIDTxtField.getText();
                String Category_name = categoryNameTxtField.getText();
                String Category_description = categoryDescriptionTxtField.getText();

                try {

                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");

                    String sql = "INSERT INTO CATEGORY (Category_id, Category_name, Category_description)"
                            + "VALUES (?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, Category_id);
                    preparedStatement.setString(2, Category_name);
                    preparedStatement.setString(3, Category_description);
                    preparedStatement.executeUpdate();


                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM CATEGORY");
                    ResultSet resultSet = selectStatement.executeQuery();
                    categoryTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                    categoryIDTxtField.setText("");
                    categoryNameTxtField.setText("");
                    categoryDescriptionTxtField.setText("");

                    System.out.println(Category_id);


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductsPage productsPage = new ProductsPage(null);
            }
        });

        updateCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Category_id = categoryIDTxtField.getText();
                String Category_name = categoryNameTxtField.getText();

                try{
                    Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
                    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM CATEGORY WHERE Category_id = ? AND Category_name = ?");

                    preparedStatement.setString(1, Category_id);
                    preparedStatement.setString(2, Category_name);
                    preparedStatement.executeUpdate();

                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM CATEGORY");
                    ResultSet resultSet = selectStatement.executeQuery();
                    categoryTable.setModel(DbUtils.resultSetToTableModel(resultSet));

                    categoryIDTxtField.setText("");
                    categoryNameTxtField.setText("");
                    categoryDescriptionTxtField.setText("");
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) throws SQLException
    {
        CategoryPages categoryPages = new CategoryPages(null);
    }
}
