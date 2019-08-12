package federico.levels.restBack.domain;

public class Note {

	private Long id;
	private String body;
	
	public Note(){
		super();
	}

	public Note(String body) {
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String toString(){
		return body;
	}
	
	
}
