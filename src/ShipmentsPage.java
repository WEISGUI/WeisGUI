import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ShipmentsPage extends JDialog{
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

    private Employee weisEmployee;

    public ShipmentsPage (Employee weisEmployee)
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Shipments Page");
        setContentPane(ShipmentsPagePanel);
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
                ProductsPage productsPage = new ProductsPage(null);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args)
    {
        ShipmentsPage shipmentsPage = new ShipmentsPage(null);
    }
}
