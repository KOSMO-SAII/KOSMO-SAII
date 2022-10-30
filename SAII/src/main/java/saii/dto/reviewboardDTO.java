package saii.dto;

public class reviewboardDTO {
	private String r_id;
	private String r_category;
	private String r_title;
	private String nickname;
	private java.sql.Date p_postdate;
	private int visitcount;
	private String o_file;
	private String n_file;
	
	public String getR_id() {
		return r_id;
	}
	public String getR_category() {
		return r_category;
	}
	public String getR_title() {
		return r_title;
	}
	public String getNickname() {
		return nickname;
	}
	public java.sql.Date getP_postdate() {
		return p_postdate;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public String getO_file() {
		return o_file;
	}
	public String getN_file() {
		return n_file;
	}
	public void setR_id(String r_id) {
		this.r_id = r_id;
	}
	public void setR_category(String r_category) {
		this.r_category = r_category;
	}
	public void setR_title(String r_title) {
		this.r_title = r_title;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setP_postdate(java.sql.Date p_postdate) {
		this.p_postdate = p_postdate;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	public void setO_file(String o_file) {
		this.o_file = o_file;
	}
	public void setN_file(String n_file) {
		this.n_file = n_file;
	}
}
