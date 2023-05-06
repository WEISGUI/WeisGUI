import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CreateAccountForm extends JDialog
{
    private JLabel CreateAccountTextLogo;
    private JLabel WeisTextLogo;
    private JTextField TextFieldFName;
    private JTextField TextFieldMName;
    private JTextField TextFieldLName;
    private JTextField TextFieldCreateAccountEmailAddress;
    private JTextField TextFieldAddress;
    private JTextField TextFieldPhoneNumber;
    private JPasswordField PasswordFieldEmployeePassword;
    private JPasswordField PasswordFieldSSN;
    private JButton CreateAccountButton;
    private JButton GoToLoginPageButton;
    private JPanel EmployeeCreateAccountPanel;

    public CreateAccountForm (JFrame parent)
    {
        super(parent);
        setTitle("Weis Markets - Create Employee Account");
        setContentPane(EmployeeCreateAccountPanel);
        setMinimumSize(new Dimension(650,800));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        CreateAccountButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Variables
                String EmployeeEmailAddress = TextFieldCreateAccountEmailAddress.getText();
                String EmployeePassword = String.valueOf(PasswordFieldEmployeePassword.getPassword());
                String EmployeeFName = TextFieldFName.getText();
                String EmployeeMName = TextFieldMName.getText();
                String EmployeeLName = TextFieldLName.getText();
                String EmployeeAddress = TextFieldAddress.getText();
                String EmployeePhoneNumber = TextFieldPhoneNumber.getText();
                String EmployeeSSN = String.valueOf(PasswordFieldSSN.getPassword());

                weisNewEmployee = createNewWeisEmployee(EmployeeFName, EmployeeMName, EmployeeLName, EmployeeEmailAddress, EmployeeAddress, EmployeePhoneNumber, EmployeeSSN, EmployeePassword);


                if (weisNewEmployee != null)
                {
                    dispose();
                }
                else if(EmployeeFName.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: First name field is empty, please enter your first name",
                            "Empty First Name",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeeMName.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: Middle name field is empty, please enter your middle name",
                            "Empty Middle Name",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeeLName.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: Last name field is empty, please enter your last name",
                            "Empty Last Name",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeeEmailAddress.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: Email field is empty, please enter an email",
                            "Empty Create Account Email",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeeAddress.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: Address field is empty, please enter your address",
                            "Empty Address",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeePhoneNumber.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: Phone number field is empty, please enter your phone number",
                            "Empty Phone Number",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeePhoneNumber.length() != 10)
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: Phone number is incorrect length, please enter your 10 digit phone number",
                            "Invalid Phone Number Length",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeeSSN.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: SSN field is empty, please enter your SSN",
                            "Empty SSN",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(EmployeeSSN.length() != 9)
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: SSN length is incorrect length, please enter a valid 9 digit SSN",
                            "Invalid SSN Length",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (EmployeePassword.isEmpty())
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: password field is empty, please enter your password",
                            "Empty Create Account Password",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {

                }

                weisNewEmployee = createNewWeisEmployee(EmployeeFName, EmployeeMName, EmployeeLName, EmployeeEmailAddress, EmployeeAddress, EmployeePhoneNumber, EmployeeSSN, EmployeePassword);

                if(weisNewEmployee != null)
                {
                    dispose();
                    CreateAccountSuccessful createAccountSuccessful = new CreateAccountSuccessful(null);
                }
                else
                {
                    JOptionPane.showMessageDialog(CreateAccountForm.this,
                            "Error: Account Created Failed",
                            "Account Not Created",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        GoToLoginPageButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                EmployeeLoginForm employeeLoginForm = new EmployeeLoginForm(null);
            }
        });

        setVisible(true);
    }

    public Employee weisNewEmployee;

    private Employee createNewWeisEmployee(String EmployeeFName, String EmployeeMName, String EmployeeLName, String EmployeeEmailAddress, String EmployeeAddress, String EmployeePhoneNumber, String EmployeeSSN, String EmployeePassword)
    {
        Employee newWeisEmployee = null;

        try
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://triton.towson.edu:3360/bdeguz1db", "bdeguz1", "COSC*bo29m");
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO EMPLOYEE (First_name, Middle_name, Last_name, Email, Address, Phone, Ssn, Password)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, EmployeeFName);
            preparedStatement.setString(2, EmployeeMName);
            preparedStatement.setString(3, EmployeeLName);
            preparedStatement.setString(4, EmployeeEmailAddress);
            preparedStatement.setString(5, EmployeeAddress);
            preparedStatement.setString(6, EmployeePhoneNumber);
            preparedStatement.setString(7, EmployeeSSN);
            preparedStatement.setString(8, EmployeePassword);

            int employeeTableRows = preparedStatement.executeUpdate();
            if (employeeTableRows > 0)
            {
                newWeisEmployee = new Employee();
                newWeisEmployee.EmployeeEmailAddress = EmployeeEmailAddress;
                newWeisEmployee.EmployeePassword = EmployeePassword;
                newWeisEmployee.EmployeeFName = EmployeeFName;
                newWeisEmployee.EmployeeMName = EmployeeMName;
                newWeisEmployee.EmployeeLName = EmployeeLName;
                newWeisEmployee.EmployeePhoneNumber = EmployeePhoneNumber;
                newWeisEmployee.EmployeeSSN = EmployeeSSN;
                newWeisEmployee.EmployeeAddress = EmployeeAddress;
            }

            statement.close();
            connection.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


        return newWeisEmployee;
    }




    public static void main(String[] args)
    {
        CreateAccountForm createAccountForm = new CreateAccountForm(null);

        Employee newWeisEmployee = createAccountForm.weisNewEmployee;

        if(newWeisEmployee != null)
        {
            System.out.println("Registration Successful");
        }
    }
}
