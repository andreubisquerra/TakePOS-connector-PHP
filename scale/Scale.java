/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scale;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Programació
 */
public class Scale {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        String[] portNames = SerialPortList.getPortNames();
        SerialPort serialPort = null;
        for (String name : portNames) {
            //System.out.println("Port : " + name);
            if (name.equals("COM1")) {
                serialPort = new SerialPort(name);
                break;
            }
        }

        if (serialPort == null) {
            throw new IllegalStateException("COM 1 port not found");
        }

        try {
            if (!serialPort.isOpened()) {
                serialPort.openPort();
            }
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8, SerialPort.STOPBITS_2, SerialPort.PARITY_NONE);

            serialPort.writeBytes("$".getBytes());

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < serialPort.getLinesStatus().length; i++) {
                builder.append(serialPort.readString(SerialPort.STOPBITS_2));
            }
            StringBuilder filteredBuffer = new StringBuilder();
            for(char c: builder.toString().toCharArray()){
                if(Character.isDigit(c)|| c == '.'){
                    filteredBuffer.append(c);
                }
            }
            String result = filteredBuffer.toString();
            System.out.println(Double.parseDouble(result));

        } catch (SerialPortException ex) {
            Logger.getLogger(Scale.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (serialPort.isOpened()) {
                serialPort.closePort();
                //System.err.println("Serial Prot Closed!");
            } else {
                //System.err.println("Serial Prot Already Closed!");
            }
        }

    }

}
