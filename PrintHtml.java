/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printhtml;

import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JEditorPane;

/**
 *
 * @author Jove
 */
public class PrintHtml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String printerName = args[0];
        String nprinter = args[1];
        PrintService[] printServices; // PARA WINDOWS
        PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
        printServiceAttributeSet.add(new PrinterName(printerName, null));
        printServices = PrintServiceLookup.lookupPrintServices(null, printServiceAttributeSet); //PARA WINDOWS
        PrintService printService=PrintServiceLookup.lookupDefaultPrintService();
        
        
        
        
        
        
        FileInputStream in = null;
                try {
                    in = new FileInputStream(new File("invoice"+nprinter+".txt"));
                    out.println(in);
                } catch (FileNotFoundException ex) {
            Logger.getLogger(PrintHtml.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
                
        StringBuilder sb = new StringBuilder();        
        try( BufferedReader br =
           new BufferedReader( new InputStreamReader(in )))
   {
      
      String line;
      while(( line = br.readLine()) != null ) {
         sb.append( line );
         sb.append( '\n' );
      }
      out.println(sb.toString());
   }
        
        
        
        
        
        try {
                MediaPrintableArea mpa=new MediaPrintableArea(0,0,200,275,MediaPrintableArea.MM);
                HashPrintRequestAttributeSet hpras=new HashPrintRequestAttributeSet(mpa);
                final JEditorPane ed = new JEditorPane(
                        "text/html",
                        sb.toString());
                if (/*System.getProperty("os.name").contains("Windows")*/ 1==1){ //POR EL MOTIVO QUE SEA, AHORA ME HA FUNCIONADO CON LINUX
                    ed.print(null, null, false, printServices[0], hpras, false);
                }
                else{
                    ed.print(null, null, false, printService, hpras, false);
                }
            } catch (PrinterException ex) {
                Logger.getLogger(PrintHtml.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
