package federico.levels.restBack.domain;

public class ReturnMessage {

	private int errorCode;
	private String body;
	
	public ReturnMessage(){
		
	}
	
	public ReturnMessage(int errorCode, String body){
		this.errorCode = errorCode;
		this.body = body;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
}
