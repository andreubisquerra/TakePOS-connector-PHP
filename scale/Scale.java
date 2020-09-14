/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scale;

import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Programació
 */
public class Scale {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
       SerialPort serialPort = new SerialPort("COM1");

              
       try {
            
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            serialPort.writeBytes("$".getBytes());
            byte buffer[] = serialPort.readBytes(10);
            String s = new String(buffer, StandardCharsets.UTF_8);
            out.println(s);
            serialPort.closePort();
        } catch (SerialPortException ex) {
            Logger.getLogger(Scale.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
        
        
        
    }
    
}
