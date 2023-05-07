import javax.swing.*;
import java.awt.*;

public class ProductsLocationPage extends JDialog {
    private JPanel ProductLocationPanel;
    private JButton productLocationButton;
    private JButton categoriesButton;
    private JButton homeButton;
    private JButton suppliersButton;
    private JButton shipmentsButton;
    private JButton inventoryButton;
    private JButton accountButton;
    private JTextField productLocationIDTxtField;
    private JButton addProductLocation;
    private JButton updateProductLocationButton;
    private JButton deleteProductLocationButton;
    private JScrollPane productLocationScrollPane;
    private JTable productLocationTable;
    private JTextField productLocationShelfTxtField;
    private JTextField productLocationAisleNumberTxtField;

    private Employee weisEmployee;
    public ProductsLocationPage(Employee weisEmployee)
    {
        this.weisEmployee = weisEmployee;
        setTitle("Weis Markets - Products Location Page");
        setContentPane(ProductLocationPanel);
        setMinimumSize(new Dimension(1535,850));
        setModal(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        ProductsLocationPage productsLocationPage = new ProductsLocationPage(null);
    }
}
