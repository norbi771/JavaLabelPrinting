package tronix.eplprint;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Norbert Neubauer, TRONIX - http://tronix.pl
 */
public class EplPrint1 {

    private final String port;

    public EplPrint1(String port) {
	this.port = port;
    }

    public void printLabel(String label, String company, String docDate) throws FileNotFoundException {
	FileOutputStream os = new FileOutputStream(port);
	PrintStream ps = new PrintStream(os);
	
//	sent as code128 auto A,B,C modes
//	http://www.servopack.de/support/zebra/EPL2_Manual.pdf
	
	
	
	String commands = "N\n"
		+  "A1,1,0,1,1,1,N,\""+tronix.util.Utils.asciiNormalize(company)+"\"\n"
		+ "A1,20,0,1,1,1,N,\""+tronix.util.Utils.asciiNormalize("Date")+": " + docDate+"\"\n"
		+ "B1,40,0,1,3,2,80,B,\""+label+"\"\n"
		+ "P1,1\n";	
	
	ps.println(commands);
	ps.print("\f");
	ps.close();
	
	//<editor-fold defaultstate="collapsed" desc="comment">
	//pretty good
	//		+ "q1015\n"
	//		+ "Q1015,26\n"
	//		+   "B1,1,0,0,2,2,152,B,\"125AA.4.8787\"\n"
	//		+ "B1,200,0,1,2,2,152,B,\"125AA.4.8787\"\n"
	//		+ "B1,400,0,2,2,2,152,B,\"125AA.4.8787\"\n"
	//		+ "B1,600,0,3,2,2,152,B,\"125AA.4.8787\"\n"
	//		+ "P1,1\n";
	
	//original comes from 	http://stackoverflow.com/questions/1306134/java-print-barcode-labels
	//		+ "q609\n"
	//		+ "Q203,26\n"
	//		+ "B26,26,0,UA0,2,2,152,B,\"777777\"\n"
	//		+ "A253,56,0,3,1,1,N,\"JHON3:16\"\n"
	//		+ "A253,26,0,3,1,1,N,\"JESUSLOVESYOU\"\n"
	//		+ "A253,86,0,3,1,1,N,\"TEST TEST TEST\"\n"
	//		+ "A253,116,0,3,1,1,N,\"ANOTHER TEST\"\n"
	//		+ "A253,146,0,3,1,1,N,\"SOME LETTERS\"\n"
	//		+ "P1,1\n";
	
	//Having clear this:
	//
	//EPL is one command per line. A command starts out with a command identifier, typically a letter, followed by a comma-separated list of parameters specific to that command. You can look up each of these commands in the EPL2 programming documentation. Here’s an English-language version of the commands in the above example.
	//
	//    Sending an initial newline guarantees that any previous borked command is submitted.
	//    [N] Clear the image buffer. This is an important step and generally should be the first command in any EPL document; who knows what state the previous job left the printer in.
	//    [q] Set the label width to 609 dots (3 inch label x 203 dpi = 609 dots wide).
	//    [Q] Set the label height to 203 dots (1 inch label) with a 26 dot gap between the labels. (The printer will probably auto- sense, but this doesn't hurt.)
	//    [B] Draw a UPC-A barcode with value "777777" at x = 26 dots (1/8 in), y = 26 dots (1/8 in) with a narrow bar width of 2 dots and make it 152 dots (3/4 in) high. (The origin of the label coordinate system is the top left corner of the label.)
	//    [A] Draw the text "JESUSLOVESYOU" at x = 253 dots (3/4 in), y = 26 dots (1/8 in) in printer font "3", normal horizontal and vertical scaling, and no fancy white-on-black effect.
	//
	//All tha A starting lines are similar. 10. [P] Print one copy of one label.
	//
	//</editor-fold>
		
    }

    public static void main(String[] argv) throws FileNotFoundException {
	
	
//	this prints to file
	EplPrint1 p = new EplPrint1("d:\\tmp\\eplcommands.txt");

//	LPTn is also file handle so it is possible to print to LPTn
//	just like that:

//	EplPrint1 p = new EplPrint1("LPT1");
	p.printLabel("label 22xs", "Company name 1234", "2013-05-10 12:45");


//	how to print to LPT1 when we have label printer attached to USB port
//	the easiest way I found until now is:

//	sharing the printer in windows and then
//	net use LPT1: \\OurComputerName\PrinterShare

//	then whatever is send to file hande is sent directly to the printer



    }
}
