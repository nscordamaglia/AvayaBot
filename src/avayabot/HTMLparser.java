/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avayabot;



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



/**
 *
 * @author u189299
 */
public class HTMLparser {
    
    String html;
    String jobs;
    String activity;
    ArrayList<String> url;
    ArrayList<NodeList> nodearray;
    Save save = new Save();

    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(ArrayList<String> url) {
        this.url = url;
    }

    public ArrayList<NodeList> getNodearray() {
        return nodearray;
    }

    public void setNodearray(ArrayList<NodeList> nodearray) {
        this.nodearray = nodearray;
    }

    public String getJobs() {
        return jobs;
    }

    public void setJobs(String jobs) {
        this.jobs = jobs;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    
   
    

    public HTMLparser() {
        
            this.url = IsRady(ConfigManager.getAppSetting("url"));
            this.jobs = ConfigManager.getAppSetting("job");
            this.activity = ConfigManager.getAppSetting("activity");
            this.nodearray = new ArrayList<>();
           
    }
    
   
    
    public void HTMLextract(int index){
    
            DOMParser parser = new DOMParser();           
            InputSource url = new InputSource(getUrl().get(index));
            try{
                parser.parse(url);
                Document document = parser.getDocument();  
                
                NodeList list = document.getElementsByTagName("TD");
                System.out.println("existen " + list.getLength() + " TD");
                nodearray.add(list);
                
                
              
            }catch(Exception e){
                e.printStackTrace();
            }

       }

    private ArrayList IsRady(String url) {
        
        ArrayList<String> resultURL = new ArrayList<String>();
        
        
      
        
        for (int i = 0;i<4; i++){
            try {
                Request testing = new Request(url.split(";")[i],null);
                testing.SendGet();
                if (testing.getGetResponse() != "fail"){
                
                    
                    resultURL.add(url.split(";")[i]);
                    save.file("conexion exitosa con: " + url.split(";")[i] , "logs/logserver.log");
                
                }else{
                
                    save.file("conexion fallida con: " + url.split(";")[i] , "logs/logserver.log");
                
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(HTMLparser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HTMLparser.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        
        }
        
        
        return resultURL;
    }

   
    
}
