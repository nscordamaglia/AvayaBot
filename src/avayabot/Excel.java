/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avayabot;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * A simple POI example of opening an Excel spreadsheet
 * and writing its contents to the command line.
 * @author  Tony Sintes
 */
public class Excel {

     final String reprogramados = ConfigManager.getAppSetting("local_file_copy");

    
   
    public Excel(){
        
        
        try {
            Save check = new Save();
            check.Exist();
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    

    public int findNullRow(){
        
        int rowNum = 0;
        boolean status = false;
        FileInputStream fis = null;
        try {
            
            //loop buscando la primer fila vacia
            fis = new FileInputStream(reprogramados);
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Datos");
            
            while (status == false){
            //Retrieve the row and check for null
            XSSFRow sheetrow = sheet.getRow(rowNum);
            
                if(sheetrow == null){
                    status = true;
                    
                }else{
                
                rowNum++;
                }
            }
            fis.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return rowNum;
    }
    
    
    public  void updateDataToExcelFile(int rowNum, int cellNum,String data){
        try {
            FileInputStream fis = null;
           
                fis = new FileInputStream(reprogramados);
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workbook.getSheet("Datos");
                
                //Retrieve the row and check for null
                XSSFRow sheetrow = sheet.getRow(rowNum);
                if(sheetrow == null){
                    sheetrow = sheet.createRow(rowNum);
                }
                //Update the value of cell
                XSSFCell cell = sheetrow.getCell(cellNum);
                if(cell == null){
                    cell = sheetrow.createCell(cellNum);
                }
                System.out.println("edito fila " + rowNum + " y columna " + cellNum + " valor " + data);
                if (cellNum==3){
                    
                    cell.setCellValue(Double.parseDouble(data));
                
                }else if (cellNum==4){
                    
                    cell.setCellType(Cell.CELL_TYPE_FORMULA);
                    cell.setCellFormula(data);
                   
                    
                }else{
                
                    cell.setCellValue(data);
                    
                }
                fis.close();
                
                //write
                FileOutputStream out = new FileOutputStream(reprogramados);
                workbook.write(out);
                out.flush();
                out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        


    }
    
    public  void updateCCT(int job, int rowNum, int cellNum,String data){
        try {
            FileInputStream fis = null;
           
                fis = new FileInputStream("");
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workbook.getSheet("datos"+job);
                
                //Retrieve the row and check for null
                XSSFRow sheetrow = sheet.getRow(rowNum);
                if(sheetrow == null){
                    sheetrow = sheet.createRow(rowNum);
                }
                //Update the value of cell
                XSSFCell cell = sheetrow.getCell(cellNum);
                if(cell == null){
                    cell = sheetrow.createCell(cellNum);
                }
                System.out.println("edito fila " + rowNum + " y columna " + cellNum + " valor " + data);
                if (cellNum==1){
                    
                    cell.setCellValue(Double.parseDouble(data));
                
                }else{
                
                    cell.setCellValue(data);
                    
                }
                fis.close();
                
                //write
                FileOutputStream out = new FileOutputStream("");
                workbook.write(out);
                out.flush();
                out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        


    }
    
    

   
    
}
