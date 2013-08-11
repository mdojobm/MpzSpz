package sk.mmi.mpzspz;

public class MpzModel {

	long id;
	String code;
	String country;
	String continent;
	String wiki_link;
	
/*	// constructor
	public MpzModel(){
		
	}
	
	// constructor
	public MpzModel(String _code,	String _country, String _flag, String _wiki_link){
		this.code = _code;
		this.country = _country;
		this.flag = _flag;
		this.wiki_link = _flag;
	}*/
	
	public long getId() {
		return id;
	}

	public void setId(long _id) {
		this.id = _id;
	}
		  
	public String getCode(){
		return this.code;
	}
	public void setCode(String _code){
		this.code = _code;
	}
	
	public String getCountry(){
		return this.country;
	}
	public void setCountry(String _country){
		this.country = _country;
	}
	
	public String getContinent(){
		return this.continent;
	}
	public void setContinent(String _continent){
		this.continent = _continent;
	}
	
	public String getWikiLink(){
		return this.wiki_link;
	}
	public void setWikiLink(String _wiki_link){
		this.wiki_link = _wiki_link;
	}
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return code+" - "+country;
	}
}
