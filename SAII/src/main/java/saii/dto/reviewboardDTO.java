package saii.dto;

public class reviewboardDTO {

	private int r_id;
	private String r_category;
	private String r_title;
	private String content;
	private String nickname;
	private java.sql.Date r_postdate;
	private int visitcount;
	private String o_file;
	private String n_file;
	private String ccnt;

	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getR_category() {
		return r_category;
	}

	public void setR_category(String r_category) {
		this.r_category = r_category;
	}

	public String getR_title() {
		return r_title;
	}

	public void setR_title(String r_title) {
		this.r_title = r_title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public java.sql.Date getR_postdate() {
		return r_postdate;
	}

	public void setR_postdate(java.sql.Date r_postdate) {
		this.r_postdate = r_postdate;
	}

	public int getVisitcount() {
		return visitcount;
	}

	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}

	public String getO_file() {
		return o_file;
	}

	public void setO_file(String o_file) {
		this.o_file = o_file;
	}

	public String getN_file() {
		return n_file;
	}

	public void setN_file(String n_file) {
		this.n_file = n_file;
	}

	public String getCcnt() {
		return ccnt;
	}

	public void setCcnt(String ccnt) {
		this.ccnt = ccnt;
	}

}
