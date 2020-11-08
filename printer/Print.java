/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

/**
 *
 * @author Jove
 */
public class Print {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String printerName = args[0];
    String nprinter = args[1];
    boolean printerCheck = false;
    DocPrintJob job = null;
    PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printer : printServices) {
            if (printer.getName().contains(printerName)) {
                FileInputStream in = null;
                try {
                    in = new FileInputStream(new File("invoice"+nprinter+".txt"));
                    DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                    Doc doc = new SimpleDoc(in, flavor, null);
                    job = printer.createPrintJob();
                    try {
                        job.print(doc, null);
                    } catch (PrintException ex) {
                        Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    printerCheck = true;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        if (printerCheck == false) {
            System.out.println("The printer you were searching for could not be found.");
        }
    }
}
