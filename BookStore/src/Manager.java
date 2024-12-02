
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import java.util.stream.IntStream;

public class Manager {

	//array list
	static List<Media> list=new ArrayList<>();

	//constructor
	Manager() {
	}
	//method to load file
	public boolean LoadMedia(String path) {
		try {

			File myObj = new File(path);

			Scanner myReader = new Scanner(myObj); // reading file

			System.out.printf("\n|%-6s %-6s %-6s %-6s %-6s %-6s" , "TYPE","\t|ID" ,"\t|TITTLE", "\t\t|YEAR", "\t|CHAPTER"," |AVAILABLE\n");
			System.out.print("----------------------------------------------------------------------------\n");

			while (myReader.hasNextLine()) { // if file has content

				String data = myReader.nextLine();
				String [] str = data.split(" ");

				if (str[0].equalsIgnoreCase("EBook")) { // comparing str file with text

					list.add(new EBook(Integer.parseInt(str[1]), str[2], Integer.parseInt (str[3]),
							Integer.parseInt(str[4]), Boolean.parseBoolean(str[5])));


				}else if(str[0].equalsIgnoreCase("MusicCD")) { // comparing str file with text
					list.add(new MusicCD(Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]),
							Integer.parseInt(str[4]), Boolean.parseBoolean(str[5])));

				}else if (str[0].equalsIgnoreCase("MovieDVD")) {
					list.add(new MovieDVD(Integer.parseInt(str[1]), str[2], Integer.parseInt(str[3]),
							Integer.parseInt(str[4]), Boolean.parseBoolean(str[5])));

				}


				for (int t =0; t < 1; t++) {

					for (int i = 0; i < str.length; i ++) {

						System.out.printf( "| %-6s", (str[i]) + " \t");

					}

					System.out.println("");
				}
			}
			//System.out.println("");
			//System.out.println(list);

			myReader.close();
			return true ;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
			return false;
		}
	}
	//method to find media
	public void findMedia(String title) {

		boolean found = false;

		for (Media m : list) {
			if (m.getTitle().equalsIgnoreCase(title)) {
				System.out.print(m.toString());
				found = true;
			}
		}
		if (!found) {
			System.out.println("Media "+ title + " not found try another tittle");
		}
	}
	//method to rent outmedia
	public void rentMedia(int id) {

		boolean found = false;
		for(Media m : list ) {

			if(m.getId()==id) {

				if(m.isAvailable())
					System.out.println(m.toString()+ "Succesfully rented Thank you");
				else
					System.out.println(m.toString() + "Not available for rent ");
				found = true;
			}
		}
		if (!found) {
			System.out.println("Media "+ id + " not existing try another id#.");
		}
	}

}
