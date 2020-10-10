/**
 *
 * @author Jove
 */
public class LoadPrinters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);
        Wini ini = null;
        try {
            ini = new Wini(new File("printers.ini"));
        } catch (IOException ex) {
            Logger.getLogger(LoadPrinters.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        if (ini.containsKey("Printers")) { 
            ini.remove(ini.get("Printers"));
        
        }
        int i = 0;
        for (PrintService printer : printServices)
        {
            try{
                ini.put("Printers", Integer.toString(i), printer.getName());
                i++;
            }catch(Exception e){
                System.err.println(e.getMessage());
            }
            
            try {
                ini.store();
            } catch (IOException ex) {
                Logger.getLogger(LoadPrinters.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
