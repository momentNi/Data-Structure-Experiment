import java.util.*;

public class Part {
	public String name;
	Map<Part, Integer> subparts = new HashMap<>();

	public Part(String n) {
		name = n;		
	}
	
	public void describe() {
		System.out.printf("Part %s subparts are:\n", name);
		Set <Part> set = subparts.keySet();
		Iterator<Part> it = set.iterator();
		if (!it.hasNext())
			System.out.printf("\tIt has no subparts.\n", name);
		while(it.hasNext()) {
			Part n = it.next();
			System.out.printf("\t%d %s \n", subparts.get(n), n.name);
		}
	}

}

		
	

