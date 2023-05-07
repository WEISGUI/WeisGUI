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

    public CategoryPages(JFrame parent) throws SQLException {
        super(parent);
        setTitle("Weis Markets - Category Page");
        setContentPane(CategoryPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setLocationRelativeTo(parent);
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        categoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        productLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        suppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        shipmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        setVisible(true);
    }

    public static void main(String[] args) throws SQLException
    {
        CategoryPages categoryPages = new CategoryPages(null);
    }
}
