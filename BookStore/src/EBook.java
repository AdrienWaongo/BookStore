
public class EBook extends Media {

	// constructor
EBook(int id, String title, int year, int chapter, boolean available) {
	super(id, title, year, chapter, available);
}

@Override
public String toString() {
	
	//return String.format("EBook [id:%s " + this.id + " title: " + this.title + " chapter: " + this.chapter + " year: " + this.year
			//+  " available: " , this.available + "]\n");
	

	return String.format("EBook id: %s tittle: %3s year: %4s chapter: %s availaible: %s" , id, title, year, chapter, this.available + "\n");
	
	
}

}