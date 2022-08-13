import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImagePanel extends JPanel{

    private BufferedImage image;
    public JPanel panel=new JPanel();

    public ImagePanel(String uri,int a,int b,int c,int d) {
        try
        {

            panel.setBounds(a,b,c,d);
            BufferedImage img = ImageIO.read(new File(uri));
            JLabel pic = new JLabel(new ImageIcon(img));

            panel.add(pic);

        }
        catch (Exception e){
            System.out.println("something is wrong");
            e.printStackTrace();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
    }

}