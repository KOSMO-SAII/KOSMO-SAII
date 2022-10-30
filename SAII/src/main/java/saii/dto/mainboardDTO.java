package saii.dto;

public class mainboardDTO {
	private String m_id;
	private String m_title;
	private String region;
	private String course_name;
	private String content;
	private String nickname;
	private java.sql.Date m_postdate;
	private int visitcount;
	private int goodcount;
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
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
	public java.sql.Date getM_postdate() {
		return m_postdate;
	}
	public void setM_postdate(java.sql.Date m_postdate) {
		this.m_postdate = m_postdate;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	public int getGoodcount() {
		return goodcount;
	}
	public void setGoodcount(int goodcount) {
		this.goodcount = goodcount;
	}
	
}
