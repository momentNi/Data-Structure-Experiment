import java.io.FileNotFoundException;
import java.util.Scanner;

public class extExample {
	
    public static void main(String[] args) throws FileNotFoundException {
        RailSystem rs = new RailSystem("E:\\Java\\eclipse\\workspace\\Experiment4\\src\\services.txt");
        Scanner in = new Scanner(System.in);
        
        while (true) {
            System.out.println("Enter a start and destination city:  ('quit' to exit)");
            String from, to;
            from = in.next();
            if (from.equals("quit")) {
            	System.out.println("Terminated!");
            	break;
            }
            to = in.next();

            if (rs.is_valid_city(from) && rs.is_valid_city(to)) {
                rs.output_cheapest_route (from, to);
            }
            else {
                System.out.println("Please enter valid cities\n\n");
            }

        }
        in.close();
        return;
    }
    
}