import java.io.*;

public class Fifo extends Simulator {
		
	public Fifo(int spp) {
		super(spp);
	}
	
	public void simulate(String file) throws IOException {
		Queue<Event> doing = new Queue<Event>();
		Queue<Event> arrive = new Queue<Event>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		BufferedWriter bf = new BufferedWriter(new FileWriter("E:\\Java\\eclipse\\workspace\\Experiment2\\src\\arbitrary.out"));
		
		int count = 0;
		int next = 0;
		int total = 0;
		int aggregate_latency = 0;
		
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isr);
			String line;
			bf.write("FIFO Simulation");
			bf.newLine();
			while ( (line = br.readLine()) != null) 
				loadworkload(line);
			
			while(true) {
				while(!workload.isEmpty() && count >= workload.getFront().getTime() ) {
					bf.write("\tArriving:  " + workload.getFront().getJob().getPage() + " pages from " + workload.getFront().getJob().getUser() + " at " + workload.getFront().getTime() + "seconds." + "\n");
					bf.newLine();
					arrive.enQueue(workload.getFront());
					total++;
					workload.deQueue();
				}
					if (isAvailable(doing) && !arrive.isEmpty()) {
						doing.enQueue(arrive.getFront());
						bf.write("\tServicing: " + arrive.getFront().getJob().getPage() + " pages from " + arrive.getFront().getJob().getUser() + " at " + count + " seconds." + "\n");
						bf.newLine();
						aggregate_latency += (count - arrive.getFront().getTime());
						arrive.deQueue();
						next = count + doing.getFront().getJob().getPage() * seconds_per_page;
					}
					if (count == next && !doing.isEmpty()) {
						doing.deQueue();
						if (!arrive.isEmpty()) {
							doing.enQueue(arrive.getFront());
							bf.write("\tServicing: " + arrive.getFront().getJob().getPage() + " pages from " + arrive.getFront().getJob().getUser() + " at " + count + " seconds." + "\n");
							bf.newLine();
							aggregate_latency += (count - arrive.getFront().getTime());
							arrive.deQueue();
							next = count + doing.getFront().getJob().getPage() * seconds_per_page;
						}
					}	
				count++;
				if (workload.isEmpty() && arrive.isEmpty())
					break;
			}
			
			bf.newLine();
			bf.write("\tTotal jobs: " + total + "\n");
			bf.newLine();
			bf.write("\tAggregate latency: " + aggregate_latency + " seconds. " + "\n");
			bf.newLine();
			double mean_latency = aggregate_latency / (double)total;
			bf.write("\tMean latency: " +  mean_latency + " seconds. " + "\n");
			bf.newLine();
		}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
			
			finally 
			{
				bf.close();
				try {
					br.close();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
			}
	}
	
	public static void main(String[] args) {
		Fifo fifo = new Fifo(2);
		try {
			fifo.simulate("E:\\Java\\eclipse\\workspace\\Experiment2\\src\\arbitrary.run");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}