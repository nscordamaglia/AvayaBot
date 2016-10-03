/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avayabot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author u189299
 */
public class ToHTML {
    
    public void Start(){
        try {
            String html = "<html>" +
                    "<h1>:)</h1>" +
                    "Hello World!<br>" +
                    "<img src=\"http://www.google.com/images/branding/googlelogo/1x/googlelogo_color_150x54dp.png\">" +
                    "</html>";

            JLabel label = new JLabel(html);
            label.setSize(200, 120);

            BufferedImage image = new BufferedImage(
                    label.getWidth(), label.getHeight(), 
                    BufferedImage.TYPE_INT_ARGB);

            {
                // paint the html to an image
                Graphics g = image.getGraphics();
                g.setColor(Color.BLACK);
                label.paint(g);
                g.dispose();
            }

            // get the byte array of the image (as jpeg)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] bytes = baos.toByteArray();

    

            ImageIO.write(image, "png", new File("test.png"));
            
        } catch (IOException ex) {
            Logger.getLogger(ToHTML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
