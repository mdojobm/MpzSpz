package sk.mmi.mpzspz;

public class SpzModel {

	long id;
	String code;
	String code_meaning;
	String region;
	String wiki_link;
	
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
	
	public String getCodeMeaning(){
		return this.code_meaning;
	}
	public void setCodeMeaning(String _code_meaning){
		this.code_meaning = _code_meaning;
	}
	
	public String getRegion(){
		return this.region;
	}
	public void setRegion(String _region){
		this.region = _region;
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
		return code+" - "+code_meaning;
	}
}
