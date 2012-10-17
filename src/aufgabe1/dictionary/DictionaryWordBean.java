package aufgabe1.dictionary;

public class DictionaryWordBean {

	private String english;
	private String german;
	
	public DictionaryWordBean() {
		super();
	}	
	
	public DictionaryWordBean(String de, String en) {
		super();
		this.english = en;
		this.german = de;
	}
	
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getGerman() {
		return german;
	}
	public void setGerman(String german) {
		this.german = german;
	}
	
}