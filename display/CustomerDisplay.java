/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerdisplay;

import java.util.Arrays;
import jssc.SerialPort;
import jssc.SerialPortList;

/**
 *
 * @author POS
 */
public class CustomerDisplay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        try {
            System.out.println("Args " + Arrays.toString(args));
            if (args.length != 2) {
                System.err.println("You should provide 2 arguments");
                System.exit(0);
            }
            boolean flag = false;
            for (String com : SerialPortList.getPortNames()) {

                if (com.equalsIgnoreCase(args[0])) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.err.println(args[0] + " not found!");
                System.exit(0);
            }

            SerialPort port = new SerialPort(args[0].toUpperCase());
            if (!port.isOpened()) {
                port.openPort();
            }
            port.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            if (port.isOpened()) {
                port.writeBytes(new byte[]{0x0C});
                port.writeBytes(args[1].getBytes());
                port.closePort();
                System.out.println(args[1]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            port.closePort();
        }

    }

}
