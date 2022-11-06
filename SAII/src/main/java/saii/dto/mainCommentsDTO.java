package saii.dto;

import java.sql.Date;

public class mainCommentsDTO {
	private String c_id;
	private String nickname;
	private String comments;
	private Date c_postdate;
	private String m_id;
	private String n_profile_img;
	
	public String getN_profile_img() {
		return n_profile_img;
	}
	public void setN_profile_img(String n_profile_img) {
		this.n_profile_img = n_profile_img;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getC_postdate() {
		return c_postdate;
	}
	public void setC_postdate(Date c_postdate) {
		this.c_postdate = c_postdate;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
}
