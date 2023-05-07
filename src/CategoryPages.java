import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public CategoryPages(JFrame parent)
    {
        super(parent);
        setTitle("Weis Markets - Category Page");
        setContentPane(CategoryPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setLocationRelativeTo(parent);
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

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
    }

    public static void main(String[] args) {
        CategoryPages categoryPages = new CategoryPages(null);
    }

}
