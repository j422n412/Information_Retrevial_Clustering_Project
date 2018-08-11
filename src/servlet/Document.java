package servlet;

public class Document{
	private String docId;
	private String url;
	private String title;
	
	public  Document(String docId, String url, String title) {
		this.docId = docId; 
		this.url = url;
		this.title = title;
	}
	
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
