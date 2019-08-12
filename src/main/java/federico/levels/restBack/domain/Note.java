package federico.levels.restBack.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Note {

	private Long id;
	@NotNull
	@Size(min = 1, max = 250)
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
