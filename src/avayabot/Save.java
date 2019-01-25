
package avayabot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.nio.file.*;

/**
 * Clase para poder grabar en archivo
 * @author Nicolas Scordamaglia
 */
class Save {
    
     /**
             * Metodo que recibe el texto y la ruta para poder guardar en archivo segun se requiera
             * @param data
             * @param path 
             */
    
            public void file(String data, String path){
                
            
            //String headerCSV = "TKT webApp;TKT Simplit;Grupo Simplit;Estado del TKT Simplit;Fecha de Apertura;Fecha de Cierre;Texto de apertura;Texto de cierre;Fecha de consulta";
            Date now = new Date();
            String dataExtended = data +" - "+ DateFormat.getInstance().format(now);
                
                //creo el archivo para guardar la hash para otro momento
                            File file = null;
                            FileWriter filew = null;
                            PrintWriter pw = null;
                            try
                            {
                                
                                file = new File(path);/*  destino de fichero */
                                
                                filew = new FileWriter(path,true);
                                pw = new PrintWriter(filew);
                                
                                if ("logs/reporting.csv".equals(path)||"logs/reportingHist.csv".equals(path) ){
                                    
                                pw.println(data);
                                
                                }else{
                                
                                    pw.println(data);
                                
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                               try {
                               // Nuevamente aprovechamos el finally para 
                               // asegurarnos que se cierra el fichero.
                               if (null != filew)
                                  filew.close();
                               } catch (Exception e2) {
                                  e2.printStackTrace();
                               }
                            }
                
            
            }
            
            /**
             * Metodo que verifica la existencia del archivo para evaluar su creacion de ser necesario
             * @param path
             * @throws IOException 
             */
            public void Exist() throws IOException {
                
                 
                 File file = null;
                 File file_copy = null;                 
                 file = new File(ConfigManager.getAppSetting("local_file"));
                 file_copy = new File(ConfigManager.getAppSetting("local_file_copy"));
              
                
                
                    //si no existe lo copio
                    if(!file_copy.exists()){
                            this.OtherCopy(ConfigManager.getAppSetting("local_file"),ConfigManager.getAppSetting("local_file_copy"));


                    }else{

                       //si existe borro el viejo y lo vuelvo a copiar
                        file.delete();
                        file_copy.renameTo(file);                                            


                    }      
            
        
            
            }
            
            
            public void OtherCopy(String path, String pathcopy) throws IOException{
            
                Path source = Paths.get(path);
                Path destination = Paths.get(pathcopy);
                Files.copy(source,destination);
                
                
            
            
            
            }
            
            public boolean IfExist(String path){
                
                 File file = null;                          
                 file = new File(path);
                 
                    //si no existe lo copio
                    if(!file.exists()){
                        
                        System.out.println("No existe archivo semanal");    
                        return false;
                            
                            
                    }else{

                        System.out.println("Existe archivo semanal");
                        return true;                                      


                    }      
            
            
            }
}
