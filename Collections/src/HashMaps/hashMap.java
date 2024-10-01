package HashMaps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class hashMap {
	public static void main(String[] args)
	{
		HashMap<String,Integer> hMap = new HashMap<>();
		
		hMap.put("Dushyant0", 123);
		hMap.put("Dushyant1", 123);
		hMap.put("Dushyant2", 123);
		hMap.put("Dushyant3", 123);
		hMap.put("Dushyant4", 123);
		
		Set s1 = hMap.entrySet();
		Iterator itr = s1.iterator();
		while(itr.hasNext()) {
			Map.Entry<String, Integer> m1 = (Entry<String, Integer>) itr.next();
			itr.remove();
			//System.out.println(m1.getKey() + " " + m1.getValue());
		}
		for(String key : hMap.keySet()) {
			System.out.println(key + " " + hMap.get(key));
		}
	}
	
}
