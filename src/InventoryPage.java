import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
            public void actionPerformed(ActionEvent e)
            {
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

    public static void main(String[] args)
    {
        InventoryPage inventoryPage = new InventoryPage(null);
    }
}
