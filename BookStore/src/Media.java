
public class Media {

int id;
String title;
int year, chapter;
boolean available;

Media() {
}

//constructor
public Media(int id, String title, int year, int chapter, boolean available) {
	this.id = id;
	this.title = title;
	this.year = year;
	this.chapter = chapter;
	this.available = available;
}

//getter setters
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public int getYear() {
	return year;
}

public void setYear(int year) {
	this.year = year;
}

public int getChapter() {
	return chapter;
}

public void setChapter(int chapter) {
	this.chapter = chapter;
}
public boolean isAvailable() {
return available;
}

public void setAvailable(boolean available) {
	this.available = available;
}
  
}