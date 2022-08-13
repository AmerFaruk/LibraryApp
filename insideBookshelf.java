import Data.DataWorking;
import Data.SaveData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class insideBookshelf extends JFrame{
    private JPanel bookshelf;
    private JTextField bookshelfLabel;
    private JList table1;
    private JButton removeButton;
    private JButton addBook;
    private JButton changeBookShelfName;
    public JButton back;
    private JPanel addingBookPanel;
    private JLabel bookName;
    private JTextArea textArea1;
    private JLabel pageNumber;
    private JLabel writerName;
    private JLabel publisher;
    private JLabel bookType;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private JButton confirm;
    private JLabel message;


    public String ID;

    private String[] listBook;

    private int listBookFilledSize;

    public insideBookshelf(String ID)
    {
        add(bookshelf);
        setSize(1120,840);
        setTitle("The Grand Library Of Hero's");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.ID=ID;


        //component of addingBookPanel
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textArea1.getText().equals("")|
                        textArea2.getText().equals("")|
                        textArea3.getText().equals("")|
                        textArea4.getText().equals("")|
                        textArea5.getText().equals(""))
                {message.setText("Warning! All blanks must be filled");}
                else
                {
                addnewBook(textArea1.getText(),textArea2.getText(),
                        textArea3.getText(),textArea4.getText(),textArea5.getText());
                updateBookList(textArea1.getText());
                updateBookshelf(Operation.dataContainer.getAllBooks().size);
                }
                message.setText("Succesfully added new book");
                addingBookPanel.setVisible(false);
                textArea1.setText("");
                textArea2.setText("");
                textArea3.setText("");
                textArea4.setText("");
                textArea5.setText("");
                }
        });

        addingBookPanel.setVisible(false);

        //Add book button shows addingBookPanel
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addingBookPanel.setVisible(true);

            }
        });

        //deletes book from list
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook(table1.getSelectedValue().toString());
            }
        });
        //changes bookshelf name
        changeBookShelfName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replaceItem();

            }
        });

        setLocationRelativeTo(null);

    }

    //sets inside of the page according to bookshelf
    public void setInside(String idNo)
    {

        this.ID=idNo;
        //gets id of books as string list
        String[] current=Operation.dataContainer.getBooks().checkByElement(idNo,"ID");

        bookshelfLabel.setText(current[2]);

        String[] listBook=new String[32];

        int count=0;
        for (int i=3;i<current.length;i++)
        {
            //gets name of the book adds to Jlist
            String[] currentBook=Operation.dataContainer.getAllBooks().checkByElement(current[i],"ID");
            if (currentBook==null){break;}
           count++;
           listBook[i-3]=currentBook[2];
        }

        listBookFilledSize=count;
        this.listBook=listBook;
        table1.setListData(listBook);

    }

    public void setID(String ID) {
        this.ID = ID;
    }

    //saves added books data
    public void addnewBook(String name,String pageNum
            ,String writer,String publisherName,String type)
    {
        Operation.dataContainer.getAllBooks().increaseSize();
            new SaveData(Operation.dataContainer.getAllBooks(),
                    Operation.dataContainer.getAllBooks().size
                            +"\t"+Operation.dataContainer.getAllBooks().size
                            +"\t"+name+"\t"+pageNum+"\t"+writer+"\t"+publisherName+"\t"+type+"\n");

    }

    //updates bookList
    public void updateBookList(String newBook)
    {
        int sizeList=listBookFilledSize;
        String[] newList=new String[sizeList+1];
        for (int u=0;u<sizeList;u++)
        {
            newList[u]=listBook[u];
        }
        listBookFilledSize++;
        newList[sizeList]=newBook;
        table1.setListData(newList);
        listBook=newList;

    }

    //update books.txt file with new books
    public void updateBookshelf(int bookNO)
    {
        System.out.println(ID);
        String[] oldLine=Operation.dataContainer.getBooks().checkByElement(ID,"ID");
        String newLine="\t"+bookNO;
        //update data of Bookshelfs.txt
        new SaveData(Operation.dataContainer.getBooks(),newLine,oldLine);
    }

    //remove's book from books.txt and allBooks.txt
    public void deleteBook(String selected)
    {
        //delete from list
        String[] currentList=listBook;
        String[] tempLine=Operation.dataContainer.getBooks().checkByElement(ID,"ID");
        for (int i=0;i<currentList.length;i++)
        {
            if (selected.equals(currentList[i]))
            {
                currentList[i]="";
                table1.setListData(currentList);
            }
        }

        //delete from books.txt
        String[] bookLine=Operation.dataContainer.getAllBooks().checkByElement(selected,"Name");
        String[] oldLine=Operation.dataContainer.getBooks().checkByElement(ID,"ID");

        //updates books.txt
        for (int j=0;j<oldLine.length;j++)
        {
            if (bookLine[1].equals(oldLine[j]))
            {
                oldLine[j]="";
                for (int p=j; p<oldLine.length-1; p++)
                {

                    if (oldLine[p+1].equals("")){oldLine[p]="";break;}
                    oldLine[p]=oldLine[p+1];
                }
                break;
            }
        }

        //delete from books.txt and decrease data size
        new SaveData(Operation.dataContainer.getBooks(),oldLine,tempLine,false);
        Operation.dataContainer.getBooks().decreaseSize();

        //delete from allbooks.txt and decrease data size
        new SaveData(Operation.dataContainer.getAllBooks(),null,bookLine,true);
        Operation.dataContainer.getAllBooks().decreaseSize();


    }

    public void replaceItem()
    {
        String[] oldLine= Operation.dataContainer.getBooks().checkByElement(ID,"ID");
        String[] newLine= oldLine;
        newLine[2]=bookshelfLabel.getText();
        String newBrandLine=String.join("\t",newLine);
        new SaveData(Operation.dataContainer.getBooks(),newBrandLine,Operation.dataContainer.getBooks().checkByElement(ID,"ID"),false);

    }

    public void deleteBookshelf()
    {

    }
}
