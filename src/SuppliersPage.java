import javax.swing.*;
import java.awt.*;

public class SuppliersPage extends JDialog {
    private JPanel SuppliersPagePanel;

    private Employee weisEmployee;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JTextField supplierAddressTxtField;
    private JButton addSupplierButton;
    private JButton updateSupplierButton;
    private JButton deleteSupplierButton;
    private JScrollPane supplierScrollPane;
    private JTable supplierTable;
    private JTextField supplierPhoneTxtField;
    private JTextField supplierEmailTxtField;
    private JTextField supplierNameTxtField;
    private JTextField supplierIDTxtField;

    public SuppliersPage(Employee weisEmployee)
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Suppliers Page");
        setContentPane(SuppliersPagePanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SuppliersPage suppliersPage = new SuppliersPage(null);
    }
}
