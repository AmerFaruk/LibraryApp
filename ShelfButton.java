import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShelfButton extends JButton {

    public insideBookshelf inside;

    public ShelfButton(String insideName){
        super(insideName);
        setAction();
    }

    private void setAction()
    {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //inside= new insideBookshelf();
            }
        });
    }
}
