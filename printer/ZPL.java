/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zpl;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Windows10
 */
public class ZPL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try {
            FileOutputStream os = new FileOutputStream("\\\\127.0.0.1\\godex");
            PrintStream ps = new PrintStream(os); 
            String commands =  
"^XA"+
"^CF0,35"+
"^FD1.33EUR^FS"+
"^BY2.5,2,60"+
"^FO100,60^BC^FD2240878500518^FS"+
"^XZ"+
""
        ;
            ps.println(commands);
            ps.print("\f");
            ps.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        
        
    }
    
}
