
public class MusicCD extends Media {

	//constructor
MusicCD(int id, String title, int year, int chapter, boolean available) {
	super(id, title, year, chapter, available);
}

@Override
public String toString() {
	//return
	//return "MusicCD id:" + this.id + " title: " + this.title + " chapter: " + this.chapter + " year: " + this.year
			//+ " available:" + this.available + "\n";
	return String.format("Music CD id: %s tittle: %3s year: %4s chapter: %s availaible: %s" , id, title, year, chapter, this.available + "\n");

	}

}