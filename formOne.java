import Data.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.rmi.server.ExportException;

public class formOne extends JFrame{
    private JPanel panelOne;
    private JLabel NameLabel;
    private JLabel PasswordLabel;
    public JTextField userName;
    public JPasswordField password;
    public JButton signUp;
    public JButton logIn;

    private JPanel iPanel;




    public formOne()  {
        setLocationRelativeTo(null);
        this.NameLabel.setText("Name");
        this.PasswordLabel.setText("Password");

        iPanel=new ImagePanel("C:\\Users\\gundu\\IdeaProjects\\MyLibrary\\src\\Data\\Assets\\loginleft3.jpg"
                ,0,-10,800,900 ).panel;
        add(iPanel);










        add(panelOne);


        setSize(1200,830);
        setTitle("The Grand Library Of Hero's");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //X'e basıldığında kapat

        //signUP Button's properties
        signUp.setText("Kayıt Ol");


        //login Button's properties
        logIn.setText("Giriş");



        setLocationRelativeTo(null);
    }


    //Create userdata and check if user exist
    public User checkUser()
    {
        return null;
    }

    public void clearField()
    {
        userName.setText("");
        password.setText("");
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
