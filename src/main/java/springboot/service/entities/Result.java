package springboot.service.entities;

public class Result {

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFinalResult() {
		return finalResult;
	}
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
	public Result(String status, String finalResult) {
		super();
		this.status = status;
		this.finalResult = finalResult;
	}
	public Result() {
		// TODO Auto-generated constructor stub
	}

	private String status;
	private String finalResult;
}
