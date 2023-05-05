import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JDialog{
    private JPanel HomePagePanel;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton productLocationButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;

    public HomePage (JFrame parent)
    {
        super(parent);
        setTitle("Weis Markets - Home Page");
        setContentPane(HomePagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setLocationRelativeTo(parent);
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
                CategoryPage categoryPage = new CategoryPage(null);
            }
        });

        productLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ProductLocationPage productLocationPage = new ProductLocationPage(null);
            }
        });

        suppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SupplierPage supplierPage = new SupplierPage(null);
            }
        });


        shipmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShipmentPage shipmentPage = new ShipmentPage(null);
            }
        });


        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AccountPage accountPage = new AccountPage(null);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args)
    {
        HomePage homePage = new HomePage(null);
    }
}
