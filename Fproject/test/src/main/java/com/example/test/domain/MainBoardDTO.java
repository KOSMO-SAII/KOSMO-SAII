package com.study.springboot.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MainBoardDTO {
	private String post_id;
	private date create_date;
	private date update_date;
	private String created_by;
	private String modified_by;
	private String course_id;
	private String region;
	private String title;
	private int viewcount;
}
