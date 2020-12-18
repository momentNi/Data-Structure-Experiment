
public class Simulator {
	protected int seconds_per_page;		//determine how long a print job takes to print
	protected Queue<Event> workload = new Queue<Event>();		

	public Simulator(int spp) {
		seconds_per_page = spp;
		return;
	}
	
	public void addWorkLoad(Event event) throws Exception {		//load the printing event data from corresponding files
		workload.enQueue(event);
	}
	
	public void loadworkload(String file) throws Exception {
		String[] s = new String[3];
		int m = 0;
		for (String i : file.split(" ", 3))
			s[m++] = i;
		Event ev = new Event(Integer.parseInt(s[0]), Integer.parseInt(s[1]), s[2]);
		addWorkLoad(ev);
	}
	
	public boolean isAvailable(Queue<Event> q) {
		if (q.isEmpty())
			return true;
		else
			return false;
	}
}
