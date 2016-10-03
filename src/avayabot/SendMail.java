/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avayabot;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author u189299
 */
public class SendMail {
    
    
    private Address[] destinos;
    

    public SendMail() {
        
        
        int lenght = ConfigManager.getAppSetting("mailto").split(";").length;
        this.destinos = new Address[lenght];
        for (int i = 0; i<lenght; i++){
            try {
                this.destinos[i]=new InternetAddress(ConfigManager.getAppSetting("mailto").split(";")[i]);
            } catch (AddressException ex) {
                Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
    }
    
    
    
      //Recipient's email ID needs to be mentioned.
      //String to = ConfigManager.getAppSetting("mailto");
      
       
      
      // Sender's email ID needs to be mentioned
      String from = ConfigManager.getAppSetting("mailfrom");
      
      // Assuming you are sending email from localhost
      String host = ConfigManager.getAppSetting("smtp");

    public void Ready (String path){
    
    
            // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipients(Message.RecipientType.TO, destinos);

         // Set Subject: header field
         message.setSubject("Reporte Reprogramados Semanal");

         // Send the actual HTML message, as big as you like
         message.setContent("<h1>Reporte Semanal</h1>", "text/html" );
         
         MimeBodyPart messageBodyPart = new MimeBodyPart();
         String filename = path;
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         message.setContent(multipart);

         // Send message
         Transport.send(message);
         Save save = new Save();
         save.file("Se envia satisfactoriamente el archivo semanal", "logs/logserver.log");
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
         
      }
    
    }

}
