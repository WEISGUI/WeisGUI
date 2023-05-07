import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountPage extends JDialog{
    private JPanel AccountPagePanel;
    private JButton homeButton;
    private JButton categoriesButton;
    private JButton productLocationButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JLabel employeeEmail;
    private JLabel employeePassword;
    private JButton logOutButton;
    private JButton switchAccountsButton;
    private JLabel employeeID;
    private JButton productButton;

    private Employee weisEmployee;


    private int Employee_id;


    public AccountPage (Employee weisEmployee) {

        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Home Page");
        setContentPane(AccountPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        employeeID.setText(weisEmployee.getEmployee_id());
        employeeEmail.setText(weisEmployee.getEmployeeEmailAddress());
        employeePassword.setText(weisEmployee.getEmployeePassword());


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomePage homePage = new HomePage(weisEmployee);
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
                    ProductsLocationPage productLocationPage = new ProductsLocationPage(null);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //ProductLocationPage productLocationPage = new ProductLocationPage(weisEmployee);
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
                //SupplierPage supplierPage = new SupplierPage(weisEmployee);
            }
        });


        shipmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ShipmentsPage shipmentsPage = new ShipmentsPage(null);
                //ShipmentPage shipmentPage = new ShipmentPage(weisEmployee);
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


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        switchAccountsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                EmployeeLoginForm employeeLoginForm = new EmployeeLoginForm(null);
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
        String Employee_id = "";
        String EmployeeEmailAddress = "";
        String EmployeePassword = "";
        Employee weisEmployee = Employee.getInstance(Employee_id, EmployeeEmailAddress, EmployeePassword);
        AccountPage accountPage = new AccountPage(weisEmployee);
    }
}
