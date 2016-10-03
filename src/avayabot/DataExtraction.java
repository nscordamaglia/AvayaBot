/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avayabot;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NodeList;

/**
 *
 * @author u189299
 */
public class DataExtraction {
    
    String job;
    String activity;
    String field;
    String value;
   
   

    public DataExtraction() {
        
       
    }

    

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public void WriteXLS(String job, String act, NodeList list, Excel xls){
        
        try {
            /**
             * Se debe escribir linea por linea en el/los excel
             */
            
            
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy",Locale.US);           
                        Date now = new Date();
                        Date date = formatter.parse(now.toString());
                        String datetime = new SimpleDateFormat("dd-MMM").format(date).toString();
            
            int index = xls.findNullRow();
            for (int i = 0 ; i<list.getLength()-1 ; i = i+2){
                
                
                     xls.updateDataToExcelFile(index, 0, (job).trim()); 
                     xls.updateDataToExcelFile(index, 1, (act).trim());
                     xls.updateDataToExcelFile(index, 2, (list.item(i).getTextContent()).trim() );
                     xls.updateDataToExcelFile(index, 3, (list.item(i+1).getTextContent()).trim() );
                     xls.updateDataToExcelFile(index, 4, "VLOOKUP(C"+(index+1)+",Tablas!$A$1:$B$27,2,0)");
                     xls.updateDataToExcelFile(index, 5, datetime);
                   index++;
                   
                }
        } catch (ParseException ex) {
            Logger.getLogger(DataExtraction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void WriteTXT(String job, String act, NodeList list){
        
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd kk:mm:ss zzz yyyy",Locale.US);
                            Date now = new Date();
                            Date date;
        try {
            
            date = formatter.parse(now.toString());
            String datetime = new SimpleDateFormat("dd-MMM").format(date).toString();

                for (int i =0;i<list.getLength()-1;i = i + 2){

                            Save save = new Save();
                            save.file(job +"\t"+act+"\t"+(list.item(i).getTextContent()).trim()+"\t"+(list.item(i+1).getTextContent()).trim()+"\t\t"+datetime, "logs/semanal.txt");


                }
         } catch (ParseException ex) {
            Logger.getLogger(DataExtraction.class.getName()).log(Level.SEVERE, null, ex);
        }        
}
    
    
    
    public void Chart(){
            /**
             * Se debe escribir linea por linea en el/los excel de cct
             */
             
            String js_txt ="";
            Save js = new Save();
            js.file("", "D:\\xampp\\htdocs\\devoop\\devoops\\js\\script.js");
                
    
    }
    
         
    
    
}
