import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;
import java.util.HashSet;

/**
 * ConnectedDriver class is used to help build internal memory structure from the list of cities file and
 * return if a connection between two cities exist or not.
 * 
 */
public class ConnectedDriver {
	HashMap<String, Integer> cityMap;
	HashMap<Integer, HashSet<String>> cityIndices;
	public static final String DELIMITER = ",";
	

	public ConnectedDriver(String file) throws IOException, MalformedInputException{
		buildMap(file);
	}
	
	void buildMap(String file) throws IOException, MalformedInputException {
		cityMap = new HashMap<String, Integer>();
		cityIndices = new HashMap<Integer, HashSet<String>>();
		//Read the file and build the city map and city indices
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader bReader = new BufferedReader(isr);
		String line;
		int counter = 1;
		while ((line = bReader.readLine()) != null) {
			processLine(line,counter);
			counter++;
		}
		bReader.close();
	}
	/**
	 * ProcessLine: Used to process each line from the list of cities file and gradually build the cityMap.
	 * 
	 * @param line - Line with the connection from the file.
	 * @param counter - value to be used to assign a "Jurisdiction" to the cities.
	 * @throws MalformedInputException
	 */
	void processLine(String line, int counter) throws MalformedInputException{
		
		if(line.split(DELIMITER).length != 2){
			throw new MalformedInputException(0);
		}
		else{
			String cityA = line.split(DELIMITER)[0].trim().toLowerCase();
			String cityB = line.split(DELIMITER)[1].trim().toLowerCase();
			
			if(cityMap.get(cityA) == null){
				if(cityMap.get(cityB) == null){
					cityMap.put(cityA, counter);
					cityMap.put(cityB, counter);
					HashSet<String> hs = new HashSet<String>();
					hs.add(cityA);
					hs.add(cityB);
					cityIndices.put(counter,hs);
				}
				else{
					cityMap.put(cityA, cityMap.get(cityB));
					cityIndices.get(cityMap.get(cityB)).add(cityA);
				}
			} else if(cityMap.get(cityB) == null){
					cityMap.put(cityB, cityMap.get(cityA));
					cityIndices.get(cityMap.get(cityA)).add(cityB);
			} else{
				if(cityMap.get(cityA) != cityMap.get(cityB)){
					int oldLocation = cityMap.get(cityB); 
					for(String s : cityIndices.get(cityMap.get(cityB))){
						cityMap.remove(s);
						cityMap.put(s, cityMap.get(cityA));
						cityIndices.get((cityMap).get(cityA)).remove(s);
						cityIndices.get((cityMap).get(cityA)).add(s);
					}
					cityIndices.remove(oldLocation);
				}
			}
		}
	}
	
	/**
	 * isConnected - Checks to see if startCity and endCity are in the same jurisdiction. If so, return Yes else No.
	 *               
	 * @param startCity
	 * @param endCity
	 */
	void isConnected(String startCity, String endCity){
		boolean result = ((cityMap.get(startCity)!=null) && (cityMap.get(endCity)!=null)) && ((cityMap.get(startCity) == cityMap.get(endCity)));
		System.out.println(result ? "Yes" : "No");
	}
}
