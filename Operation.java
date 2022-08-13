import Data.DataWorking;
import Data.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Operation {

    public static DataWorking dataContainer=new DataWorking();
    private formOne formOne=new formOne();
    private formOnePointOne formOnePointOne=new formOnePointOne();

    public userPage userPage;

    public insideBookshelf insideBookshelf;

    public Operation()
    {
        //creates data readers in dataworking
        //this.dataContainer=new DataWorking();

        //initialize
        formOne.setVisible(true);
        //initializeButtons
        formOneButtons();




    }

    public void formOneButtons()
    {
        formOne.signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formOne.dispose();
                formOne.clearField();
                formOnePointOne.setVisible(true);
            }
        });
        formOne.logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (dataContainer.getUsers().checkByElement
                        (formOne.userName.getText(),
                                String.valueOf(formOne.password.getPassword()),
                                "USERNAME","PASSWORD"))
                {
                    User currentUser=new User(dataContainer.getUsers().checkByElement(formOne.userName.getText(),"USERNAME"));
                    userPage=new userPage(currentUser);
                    userPageButtons();
                    insideBookshelfButtons();
                    userPage.setVisible(true);

                    formOne.clearField();
                    formOne.dispose();

                    System.out.println("succesfull");

                }



            }
        });
        formOne.logIn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {


            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        formOnePointOne.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formOne.setVisible(true);
                formOnePointOne.clearField();
                formOnePointOne.dispose();
            }
        });
        //not done yet
        formOnePointOne.confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



    }
    public void userPageButtons()
    {
        userPage.backButtonOfUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formOne.setVisible(true);
                userPage.dispose();
                System.out.println("succesfull");
            }
        });
    }
    public void insideBookshelfButtons()
    {
        userPage.insideBookshelf.back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userPage.setVisible(true);
                userPage.insideBookshelf.dispose();

                System.out.println("succesfull");

            }
        });

    }


}
