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

    }

    public static void main(String[] args)
    {
        InventoryPage inventoryPage = new InventoryPage(null);
    }
}
