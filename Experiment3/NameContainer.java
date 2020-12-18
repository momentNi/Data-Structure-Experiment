import java.util.*;

public class NameContainer  {
	public Map<String,Part> name_map;
	
    public NameContainer() {
    	name_map = new HashMap<>();
    	name_map.clear();
    }

	public Part lookup(String name) {
		
		return name_map.get(name);
		
	}
}