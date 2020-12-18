public class Job {
	private String user;
	private int number_of_pages;
	
	Job(String name, int page){
		this.user = name;
		this.number_of_pages = page;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getPage() {
		return number_of_pages;
	}
}
