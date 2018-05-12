import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
/**
 * Connected: Application to check if two cities are connected based on a text file with list of connections. 
 * 
 * @author Ravish
 *
 */
public class Connected {
	
	static final int CITIESLIST = 0;
	static final int STARTCITY = 1;
	static final int ENDCITY = 2;
	static final int ARGSLEN = 3;

	public static void main(String[] args) {
		
		if(args.length != ARGSLEN){
			System.out.println("Input arguments not valid. Please check the input and try again.");
			return;
		}
		//Assume if startcity and endcity are the same, then they are connected.
		if(args[STARTCITY].equalsIgnoreCase(args[ENDCITY])){
			System.out.println("Yes");
			return;
		}
		try{
			ConnectedDriver cd = new ConnectedDriver(args[CITIESLIST]);
			cd.isConnected(args[STARTCITY].toLowerCase(), args[ENDCITY].toLowerCase());
		} 
		catch (FileNotFoundException filenotfound){
			System.out.println("Input file not found.");
			return;
		}
		catch (MalformedInputException malformedinputexception){
			System.out.println("Malformed input while trying to read input file. Should contain 2 city names separated by a '"+ConnectedDriver.DELIMITER+"'");
			return;
		}
		catch (IOException ioexception){
			System.out.println("IO exception while trying to read input file.");
			return;
		}		
	}

}
