
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
 * @author Nicolas Scordamaglia
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


      // Configurar origen
      String from = ConfigManager.getAppSetting("mailfrom");

      // Configurar smtp
      String host = ConfigManager.getAppSetting("smtp");

    public void Ready (String path){


      // Configura propiedades
      Properties properties = System.getProperties();

      // Configurar smtp
      properties.setProperty("mail.smtp.host", host);

      // Sesion por defecto
      Session session = Session.getDefaultInstance(properties);

      try{
         // Crea mensaje
         MimeMessage message = new MimeMessage(session);

         // Setea origen
         message.setFrom(new InternetAddress(from));

         // Setea destino
         message.addRecipients(Message.RecipientType.TO, destinos);

         // Setea asunto
         message.setSubject("Reporte Reprogramados Semanal");

         // Setea mensaje
         message.setContent("<h1>Reporte Semanal</h1>", "text/html" );

         MimeBodyPart messageBodyPart = new MimeBodyPart();
         String filename = path;
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(messageBodyPart);
         message.setContent(multipart);

         // Envia mensaje
         Transport.send(message);
         Save save = new Save();
         save.file("Se envia satisfactoriamente el archivo semanal", "logs/logserver.log");
         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();

      }

    }

}
