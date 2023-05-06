import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class EmployeeLoginForm extends JDialog
{
    private JPanel EmployeeLoginForm;
    private JTextField TextFieldLoginEmailAddress;
    private JPasswordField PasswordFieldLoginPassword;
    private JButton LoginPageLoginButton;
    private JButton LoginPageCreateAccountButton;

    public EmployeeLoginForm(JFrame parent)
    {
        super(parent);
        setTitle("Weis Markets - Employee Login Page");
        setContentPane(EmployeeLoginForm);
        setMinimumSize(new Dimension(650,800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        LoginPageLoginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    String EmployeeEmailAddress = TextFieldLoginEmailAddress.getText();
                    String EmployeePassword = String.valueOf(PasswordFieldLoginPassword.getPassword());


                    weisEmployee = ValidatedUser(EmployeeEmailAddress, EmployeePassword);


                    if (weisEmployee != null)
                    {
                        dispose();
                        LoginSuccessful loginSuccessful = new LoginSuccessful(null);
                    }
                    else if(EmployeeEmailAddress.isEmpty())
                    {
                        JOptionPane.showMessageDialog(EmployeeLoginForm.this,
                                "Error: Email field is empty, please enter a valid email",
                                "Empty Email",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else if(EmployeePassword.isEmpty())
                    {
                        JOptionPane.showMessageDialog(EmployeeLoginForm.this,
                                "Error: Password field is empty, please enter a password",
                                "Empty Password",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(EmployeeLoginForm.this,
                                "Error: Employee credentials not found in our system! Make sure your email and password are both correct!",
                                "Invalid Credentials",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
        });

        LoginPageCreateAccountButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                CreateAccountForm createAccountForm = new CreateAccountForm(null);
            }
        });

        setVisible(true);
    }

    public Employee weisEmployee;

    private Employee ValidatedUser(String EmployeeEmailAddress, String EmployeePassword)
    {
        Employee weisEmployee = null;

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
            Statement statement = connection.createStatement();

            //localhost:3306
            String sql = "SELECT * FROM EMPLOYEE WHERE Email=? AND Password=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, EmployeeEmailAddress);
            preparedStatement.setString(2, EmployeePassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                weisEmployee = new Employee();
                weisEmployee.EmployeeEmailAddress = resultSet.getString("Email");
                weisEmployee.EmployeePassword = resultSet.getString("Password");
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return weisEmployee;
    }

    public static void main(String[] args)
    {
        EmployeeLoginForm employeeLoginForm = new EmployeeLoginForm(null);

        Employee weisEmployee = employeeLoginForm.weisEmployee;

        if(weisEmployee != null)
        {
            System.out.println("--------------------------------");
            System.out.println("Employee: " + weisEmployee.EmployeeFName + " " + weisEmployee.EmployeeMName + " " + weisEmployee.EmployeeLName + " " + "has successfully logged in");
            //System.out.println("Employee ID: " + weisEmployee.EmployeeID);
            System.out.println("Employee Email: " + weisEmployee.EmployeeEmailAddress);
            System.out.println("Employee Password: " + weisEmployee.EmployeePassword);
            System.out.println("--------------------------------");
        }
    }
}
