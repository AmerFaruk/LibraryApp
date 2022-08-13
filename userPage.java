import Data.SaveData;
import Data.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class userPage extends JFrame {
    private JPanel userPage;
    private JLabel welcomeMessage;
    private JLabel userNameLabel;
    public JButton backButtonOfUser;
    private JButton addBSright;
    private JButton r1c1;
    private JButton r2c2;
    private JButton r3c1;
    private JButton r3c2;
    private JButton r2c3;
    private JButton r3c3;
    private JButton r2c1;
    private JButton r1c2;
    private JButton r1c3;
    private JButton friendsButton;
    private JButton writers;
    private JButton allBooks;
    private JPanel bsPanel;
    private JPanel rightPanel;
    private LayoutManager rightLay=new GridLayout(3,0);
    private JPanel bottomPanel;
    private LayoutManager bottomLay=new GridLayout(0,3);
    private JButton addBSbottom;
    public ArrayList<JButton> shelfButtons=new ArrayList<>();
    public insideBookshelf insideBookshelf;
    private User currentUser;

    private int countTOInside=1;
    private int countTORight=1;
    private int countTOBottom=1;


    public userPage(User currentUser)
    {
        add(userPage);
        setSize(1120,840);
        setTitle("The Grand Library Of Hero's");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //X'e basıldığında kapat
        setUserNameLabel(currentUser.getName());
        activateBSButtons();
        bottomPanel.setLayout(bottomLay);
        rightPanel.setLayout(rightLay);
        this.currentUser=currentUser;
        this.insideBookshelf=new insideBookshelf(currentUser.getID());

        setButtons();

        String[] shelfs=Operation.dataContainer.getBookshelfs().checkByElement(currentUser.getID(),"ID");

        if (shelfs==null){System.out.println(currentUser.getID());}

        for (int i=2,j=0;i<shelfs.length&&j<shelfButtons.size();i++,j++)
        {
            if (j!=shelfs.length) {
                //take bookshelf names from user.txt
                String[] books=Operation.dataContainer.getBooks().checkByElement(shelfs[i],"ID");
                shelfButtons.get(j).setText(books[2]);}
        }

        if (shelfs.length>11 )
        {
            for (int i=11;i<shelfs.length&&i<20;i++)
            {

                countTORight++;
            JButton button=new JButton("Right");
            button.setFont(new Font(Font.DIALOG,Font.BOLD,14));
            button.setMinimumSize(new Dimension(120,40));
            int j =i;
                String[] bookshelfLine=Operation.dataContainer.getBooks().checkByElement(currentUser.getID()+"-R"+(j-10),"ID");
                button.setText(bookshelfLine[2]);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    insideBookshelf.setInside(currentUser.getID()+"-R"+(j-10));
                    insideBookshelf.setVisible(true);
                    dispose();
                }
            });
                if (countTORight==10){addBSbottom.setEnabled(true);addBSright.setEnabled(false);}
            rightPanel.add(button);
            rightPanel.validate();
            userPage.validate();
            }
        }

        if (shelfs.length>20)
        {
            for (int i=20;i<shelfs.length;i++)
            {
                countTOBottom++;
                JButton button=new JButton("Bottom");
                button.setFont(new Font(Font.DIALOG,Font.BOLD,14));
                button.setMinimumSize(new Dimension(120,40));
                int j=i;
                String[] bookshelfLine=Operation.dataContainer.getBooks().checkByElement(currentUser.getID()+"-B"+(j-19),"ID");
                button.setText(bookshelfLine[2]);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        insideBookshelf.setInside(currentUser.getID()+"-B"+(j-19));
                        insideBookshelf.setVisible(true);
                        dispose();
                    }
                });
                if (countTOBottom==10) {addBSbottom.setEnabled(false);}
                bottomPanel.add(button);
                bottomPanel.validate();
                userPage.validate();}

        }

        setLocationRelativeTo(null);

    }

    public void setUserNameLabel(String userNameString) {
        this.userNameLabel.setText(userNameString);
    }
    public void activateBSButtons()
    {

        setActionListener(r1c1);
        setActionListener(r1c2);
        setActionListener(r1c3);
        setActionListener(r2c1);
        setActionListener(r2c2);
        setActionListener(r2c3);
        setActionListener(r3c1);
        setActionListener(r3c2);
        setActionListener(r3c3);

        //adding bookshelf to the right
        addBSright.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {

                //properties of added bookshelf
                JButton button=new JButton("Right");
                button.setFont(new Font(Font.DIALOG,Font.BOLD,14));
                button.setMinimumSize(new Dimension(120,40));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        insideBookshelf.setInside(currentUser.getID()+"-R"+String.valueOf(countTORight-1));
                        insideBookshelf.setVisible(true);
                        dispose();
                    }
                });

                //new line to replace and old line
                String[] oldLine=Operation.dataContainer.getBookshelfs().checkByElement(currentUser.getID(),"ID");
                String newLine="\t"+currentUser.getID()+"-R"+String.valueOf(countTORight++);
                //update data of Bookshelfs.txt
                new SaveData(Operation.dataContainer.getBookshelfs(),newLine,oldLine);
                //adds new line to books.txt
                new SaveData(Operation.dataContainer.getBooks(),
                        String.valueOf(Operation.dataContainer.getBooks().size)
                        +"\t"+currentUser.getID()+"-R"+String.valueOf(countTORight-1)+"\tnewcategory"+"\n");
                Operation.dataContainer.getBooks().increaseSize();

                rightPanel.add(button);
                rightPanel.validate();

                userPage.validate();

                if (countTORight==10){addBSbottom.setEnabled(true);
                addBSright.setEnabled(false);}


            }
        });
        //adding bookshelf to the bottom
        addBSbottom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button=new JButton("Bottom");
                button.setFont(new Font(Font.DIALOG,Font.BOLD,14));
                button.setMinimumSize(new Dimension(120,40));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new insideBookshelf(currentUser.getID()+"-B"+(countTOBottom-1));
                        insideBookshelf.setVisible(true);
                        dispose();
                    }
                });
                String[] oldLine=Operation.dataContainer.getBookshelfs().checkByElement(currentUser.getID(),"ID");
                String newLine="\t"+currentUser.getID()+"-B"+String.valueOf(countTOBottom++);
                //update data of Bookshelfs
                new SaveData(Operation.dataContainer.getBookshelfs(),newLine,oldLine);
                new SaveData(Operation.dataContainer.getBooks(),
                        String.valueOf(Operation.dataContainer.getBooks().size)
                                +"\t"+currentUser.getID()+"-B"+String.valueOf(countTOBottom-1)+"\tnewcategory"+"\n");
                Operation.dataContainer.getBooks().increaseSize();
                bottomPanel.add(button);
                bottomPanel.validate();
                userPage.validate();
                if (countTOBottom==10)
                {
                    addBSbottom.setEnabled(false);
                }




            }
        });
    }
    public void setButtons()
    {
        r1c1.setMinimumSize(new Dimension(120,40));
        r1c2.setMinimumSize(new Dimension(120,40));
        r1c3.setMinimumSize(new Dimension(120,40));
        r2c1.setMinimumSize(new Dimension(120,40));
        r2c2.setMinimumSize(new Dimension(120,40));
        r2c3.setMinimumSize(new Dimension(120,40));
        r3c1.setMinimumSize(new Dimension(120,40));
        r3c2.setMinimumSize(new Dimension(120,40));
        r3c3.setMinimumSize(new Dimension(120,40));
        shelfButtons.add(r1c1);
        shelfButtons.add(r1c2);
        shelfButtons.add(r1c3);
        shelfButtons.add(r2c1);
        shelfButtons.add(r2c2);
        shelfButtons.add(r2c3);
        shelfButtons.add(r3c1);
        shelfButtons.add(r3c2);
        shelfButtons.add(r3c3);

    }

    public void setActionListener(JButton jButton)
    {
        r1c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-1");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r1c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-2");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r1c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-3");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r2c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-4");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r2c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-5");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r2c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-6");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r3c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-7");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r3c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-8");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });
        r3c3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insideBookshelf.setInside(currentUser.getID()+"-9");
                insideBookshelf.setVisible(true);
                dispose();

            }
        });

    }
}
