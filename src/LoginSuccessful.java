import javax.swing.*;
import java.awt.*;

public class LoginSuccessful extends JDialog
{
    private JPanel LoginSucessfulPanel;
    private JButton GoToAccountButton;

    public LoginSuccessful (JFrame parent)
    {
        super(parent);
        setTitle("Weis Markets - Login Successful");
        setContentPane(LoginSucessfulPanel);
        setMinimumSize(new Dimension(450 , 400));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        LoginSuccessful loginSuccessful = new LoginSuccessful(null);
    }
}
