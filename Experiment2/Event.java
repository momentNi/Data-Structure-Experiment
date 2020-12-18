public class Event {
	private Job j;
	private int arrival_time;
	
	Event(int time, int page, String name){
		this.arrival_time = time;
		this.j = new Job(name, page);
	}
	
	public Job getJob() {
		return j;
	}
	
	public int getTime() {
		return arrival_time;
	}
}
