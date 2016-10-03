/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avayabot;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u189299
 */
public class AvayaBot {

  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            HTTPserver server = new HTTPserver();
            server.start();
            
        } catch (Exception ex) {
           
        }
    }

    static void start() {
        
        Save check = new Save();
        try {         
            HTMLparser parser = new HTMLparser();
            DataExtraction extract = new DataExtraction();
            //Excel xls = new Excel(); //instancio solo un objeto excel en todo el proceso
            
            if ( parser.getUrl() != null){
                            //si tengo al menos una webmonitor para mostrar
                            //formateo la fecha
                            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy",Locale.US);
                            Date now = new Date();
                            Date date = formatter.parse(now.toString());
                            String namefile = new SimpleDateFormat("dd-MM-yyyy").format(date).toString();
                            Save save = new Save();
                            
                            //detecto el dia
                            int day = getDayOfTheWeek(date);
                            
                            
                            //if day = Lunes(2) save en el mismo else save en uno nuevo
                            if(day == 2){
                                
                                                      
                                        //creo una copia de semanal y le cambio el nombre, despues envio mail
                                        save.OtherCopy("logs/semanal.txt","logs/"+namefile+"_semanal.txt");
                                        SendMail mail = new SendMail();
                                        mail.Ready("logs/"+namefile+"_semanal.txt");
                                   
                            }
                
                    int URLlenght = parser.getUrl().size(); System.out.println("lenght: " + URLlenght);
                    for (int i=0; i< URLlenght ;i++){

                        //ciclo para escribir en los n webmonitor en el archivo
                        parser.HTMLextract(i);
                        extract.WriteTXT(parser.getJobs().split(";")[i],parser.getActivity().split(";")[i],parser.getNodearray().get(i));
                        //debo indicarle al excel la fila donde empieza
                        //extract.WriteXLS(parser.getJobs().split(";")[i],parser.getActivity().split(";")[i],parser.getNodearray().get(i),xls);
                        //extract.Chart();

                    }
            }else{
            
                check.file("conexion fallida con WebMonitor" , "logs/logserver.log");
            
            }
            
            
            //check.Exist(); solo para la ccion de excritura en excel
        } catch (IOException ex) {
            Logger.getLogger(AvayaBot.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AvayaBot.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
     private  static int getDayOfTheWeek(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d);
	return cal.get(Calendar.DAY_OF_WEEK);		
    }
}
