public class City implements Comparable<City>{
	
	public String name;
	// bookkeeping info
	public boolean	visited;	//S[v] True--Confirmed
	public int total_fee;		//d[v]
	public int total_distance;	
	public String from_city;	//P[V]

	public City() {				//City extends Comparable, Rewrite CompareTo Function
		name = new String();
		visited = false;
		total_fee = 0;
		total_distance = 0;
		from_city = new String("");
	}
	    
	public City(String s){
		name = s;
		visited = false;
		total_fee = 0;
		total_distance = 0;
		from_city = new String("");
	}

	@Override
	public int compareTo(City arg0) {
		return this.total_fee - arg0.total_fee;
	}

}