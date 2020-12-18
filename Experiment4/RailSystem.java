import java.io.*;
import java.util.*;

class Pair{				//used in calculate
	public int dis;
	public int fee;
	public Pair(int f, int d) {
		dis = d;
		fee = f;
	}
}

public class RailSystem {
	private Map<String, LinkedList<Service> > outgoing_services;		//图邻接表，链表放edge
	private Map<String, City> cities;									//Store the City vertices
	
    public RailSystem(String filename) throws FileNotFoundException {
    	outgoing_services = new HashMap<String, LinkedList<Service> >();
    	cities = new HashMap<String, City>();
	    load_services(filename);
    }
    
	public void load_services(String filename) throws FileNotFoundException {
		String from,to;
		int fee, distance;
        
		Scanner infile = new Scanner(new FileInputStream(filename));	
        while(infile.hasNext()) {
  	       from = infile.next();
 	       to = infile.next();
 	       fee = infile.nextInt();
 	       distance = infile.nextInt();
 	       
 	       if (infile.hasNext()) {
 	    	   Service s = new Service(to, fee, distance);
 	    	   if(!cities.containsKey(from)) {
 	    		   cities.put(from, new City(from));
 	    		   outgoing_services.put(from, new LinkedList<Service>());
 	    	   }
 	    	   
 	    	   if(!cities.containsKey(to)) {
 	    		   cities.put(to, new City(to));
 	    		   outgoing_services.put(to, new LinkedList<Service>());
 	    	   }
 	    	   
 	    	   outgoing_services.get(from).add(s);
 	       }

 		   // TODO: Add entries in the cities container and services in the rail system 
 	       //for the new cities we read in.	
 	       //First, check whether 'from' is in the container.if not exists, create a new one.
        }
        infile.close();
    }
	
    public void reset() {
    	
    	for ( City i : cities.values()) {
    		i.visited = false;
    		i.total_distance = Integer.MAX_VALUE;
    		i.total_fee = Integer.MAX_VALUE;
    		i.from_city = "";
    	}
    	
    	//Turn all the 'visited' to FALSE;
    	
	    // TODO: reset the data objects of the City objects' contained in cities
    }
    
    public String recover_route(String city) {		//city = destination
    	String route = "";
    	String current = city;
    	while(current != "") {
    		route = current + route;
    		String prev = cities.get(current).from_city;
    		if (prev != "")
    			route = " to " + route;
    		current = prev;
    	}
    	
	    // TODO: walk backwards through the cities container to recover the route we found
        return route;
    }
    
	public Pair calc_route(String from, String to){
	    Queue<City> candidates = new PriorityQueue<City>();		//MinHeap
	    
	    City startCity = cities.get(from);
	    startCity.total_fee = 0;
	    startCity.total_distance = 0;
	    candidates.add(startCity);
	    while( ! candidates.isEmpty() ) {
	    	City visitCity = new City();
	    	visitCity = candidates.poll();
	    	if (!visitCity.visited) {
	    		visitCity.visited = true;
	    		LinkedList<Service> list = outgoing_services.get(visitCity.name);
	    		for (Service i : list) {
	    			City nextCity = cities.get(i.destination);
	    			int nextFee =i.fee + visitCity.total_fee;
	    			if ( (nextFee < nextCity.total_fee) && (nextCity.name != from) ) {
	    				nextCity.total_fee = nextFee;
	    				nextCity.total_distance = i.distance + visitCity.total_distance;
	    				nextCity.from_city = visitCity.name;
	    				candidates.add(nextCity);
	    			}
	    		}
	    	}
	    }
	    
        // TODO: Implement Dijkstra's shortest path algorithm to
        // find the cheapest route between the cities
	    // Return the total fee and total distance.
	    // Return (INT_MAX, INT_MAX) if not path is found.
	    
	    
	    if (cities.get(to).visited) 
		    return new Pair(cities.get(to).total_fee, cities.get(to).total_distance);
	    else 
		    return new Pair(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }


    public void output_cheapest_route(String from, String to) {
	    reset();
	    Pair totals = calc_route(from, to);

	    if (totals.dis == Integer.MAX_VALUE) {
		    System.out.println( "There is no route from " + from + " to " + to);
	    } 
	    else {
		    System.out.println( "The cheapest route from " + from + " to " + to);
		    System.out.println("costs " + totals.fee + " euros and spans " 
		                        + totals.dis + " kilometers");
            System.out.println(recover_route(to));
            System.out.println();
	    }
    }
    
    
    public boolean is_valid_city(String name) {
	    return cities.containsKey(name);
    }
}