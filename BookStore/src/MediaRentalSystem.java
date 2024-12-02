
import java.util.Scanner;

public class MediaRentalSystem {

public static void main(String[] args) {
	System.out.println("\nWelcome to media rental System rental");
	Scanner sc = new Scanner(System.in); // reading
	
	while (Menu()) {
		
		int choice = 0;
		System.out.println("Enter choice");
		choice = sc.nextInt();
		Manager m = new Manager();
		switch (choice) { // switch case when found exit the case 
		case 1:
			System.out.println("\nEnter file path load media ");
			String path=sc.next();
			m.LoadMedia(path);
			break;
		case 2:
			System.out.println("\nEnter media title ");
			sc.nextLine();
			String title=sc.nextLine();
			m.findMedia(title);
			break;
		case 3:
			System.out.println("\nEnter media id :");
			int id =sc.nextInt();
			m.rentMedia(id);
			break;

		case 9:
			System.out.println("\nExiting the program...");
			System.exit(0);
			break;

		default:
			System.out.println("\nEnter valid choice ");
			break;
		}
	}
	sc.close();
}

private static boolean Menu() {
	System.out.println("\n\tMake a selection");
	System.out.println("1: Load Media objects ");
	System.out.println("2: Find Media objects ");
	System.out.println("3: Rent Media objects ");
	System.out.println("9: exit ");
	return true;
	}
}