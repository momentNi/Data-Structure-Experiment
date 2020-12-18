import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class hospital {
	static public NameContainer partContainer;
	static Stack<Integer> res = new Stack<>();
	
    static public void load_definitions(String filename) throws FileNotFoundException {
       Scanner infile = new Scanner(new FileInputStream(filename));
       String part,subpart;
       int quantity;
       while(infile.hasNext()) {
    	   part = infile.next();
    	   quantity = infile.nextInt();
    	   subpart = infile.next();
    	   add_part(part,quantity,subpart);
       }
   	   infile.close();
    }

    static void whatis_query(String x) {
    	Part xp = partContainer.lookup(x);
//    	if(xp != null) 
    	    xp.describe();
//    	else 
//    		System.out.printf("Part %s subparts are:\n\tIt has no subparts.\n", x); 	
    }
 
    static void howmany_query(String x, String y) {
    	Part xp = partContainer.lookup(x);
    	Part yp = partContainer.lookup(y);
    	System.out.printf("%s has ", yp.name);
    	count_howmany(yp,xp);
    	System.out.printf(" %s(s)\n", xp.name);
	}

    public static void count_howmany(Part startp, Part endp) {
    	if (startp.equals(endp)) {
    		calculate(res);
    		return;
    	}
		Set <Part> set = startp.subparts.keySet();
		Iterator <Part> it = set.iterator();
		while (it.hasNext()) {
			Part t = it.next();
			res.push(startp.subparts.get(t));
			if (t.name.equals(endp.name)) {
				calculate(res);
				return;
			}
			else {
				if ( partContainer.lookup(t.name) != null) {
					Part nextp = partContainer.lookup(t.name);
					count_howmany(nextp, endp);
					if (!res.isEmpty())
						res.pop();
					else
						return;
				}
				else {
					res.pop();
				}
			}
		}
	}
		
	public static void calculate(Stack<Integer> res) {
		int sum = 1;
		while (!res.isEmpty())
			sum *= res.pop();
		System.out.printf("%d", sum);
	}
	
    static void process_queries(String filename) throws FileNotFoundException {
        String query, x, y;
        Scanner infile = new Scanner(new FileInputStream(filename));
     
        while(infile.hasNext()) {
     	   query = infile.next();
     	   x = infile.next();
           if(query.equals("howmany")) {
     	       y = infile.next();
			  howmany_query(x, y);
           }
		   else if ( query.equals("whatis") )
			   whatis_query(x);     	   
        }
    	infile.close();
    }
    
    static public void add_part(String x, int q, String  y) {
    	
    	if (partContainer.lookup(x) == null) {
    		Part part = new Part(x);
        	Part part1 = new Part(y);
        	partContainer.name_map.put(x, part);
        	partContainer.name_map.put(y, part1);
    		part.subparts.put(part1, q);
    	}
    	else {
    		Part part1 = new Part(y);
    		partContainer.lookup(x).subparts.put(part1, q);
        	partContainer.name_map.put(y, part1);
    	}        
    }

    public static void main(String[] args) throws FileNotFoundException{
    	partContainer = new NameContainer();
    	load_definitions("E:\\Java\\eclipse\\workspace\\Experiment3\\src\\definitions.txt");
    	process_queries("E:\\Java\\eclipse\\workspace\\Experiment3\\src\\queries.txt");
    	
    }
}

