import javax.swing.*;

public class formOnePointOne extends JFrame{
    public JPanel panelOnePoint;
    private JTextField realName;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JLabel realname;
    private JTextField userName;
    private JLabel username;
    private JLabel password;
    private JLabel passwordCheck;
    public JButton confirm;
    public JButton back;

    public formOnePointOne()
    {


        add(panelOnePoint);
        setSize(800,600);
        setTitle("The Grand Library Of Hero's");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //X'e basıldığında kapat

        realname.setText("What is your name?");
        username.setText("How people call you?");
        password.setText("Enter a password");
        passwordCheck.setText("Enter password again");
        back.setText("Geri Dön");
        confirm.setText("Onayla");


        setLocationRelativeTo(null);
    }

    public void clearField()
    {
        realName.setText("");
        userName.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }
}
