import java.applet.Applet;
import java.io.FileNotFoundException;
import tronix.eplprint.EplPrint1;

/**
 *
 * @author Norbert Neubauer, TRONIX - http://tronix.pl
 */
public class EplPrintApplet extends Applet {

    private String docNumber;
    private String portName;
    private String docDate;
    private String companyName;

    /**
     * Initialization method that will be called after the applet is loaded into the browser.
     */
    @Override
    public void init() {
	docNumber = this.getParameter("docNumber");
	docDate = this.getParameter("docDate");
	companyName = this.getParameter("companyName");
	portName = this.getParameter("portName");

	if (portName != null && docNumber != null && companyName != null && docDate != null) {
	    EplPrint1 p = new tronix.eplprint.EplPrint1(portName);
	    try {
		p.printLabel(docNumber,companyName,docDate);
	    } catch (FileNotFoundException ex) {
		System.err.println("Print error:" + ex.getMessage());
	    }
	}else{
		System.err.println("Print parameters not set !!!");
		System.err.println("docNumber: " + docNumber);
		System.err.println("docDate: " + docDate);
		System.err.println("companyName: " + companyName);
		System.err.println("portName: " + portName);
	}

    }
}
