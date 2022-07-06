package co.micol.prj.board;

public class BoardVO {

	private String id;
	private String title;
	private String content;
	private String writer;
	private String rdt;
	private String hit;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRdt() {
		return rdt;
	}
	public void setRdt(String rdt) {
		this.rdt = rdt;
	}
	public String getHit() {
		return hit;
	}
	public void setHit(String hit) {
		this.hit = hit;
	}
	
	// 생성자 꼭 만들어야하는것은 아님!
	
}
