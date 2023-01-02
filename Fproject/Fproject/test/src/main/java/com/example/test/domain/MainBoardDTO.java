package com.example.test.domain;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MainBoardDTO {
	private String post_id;
	private Date create_date;
	private Date update_date;
	private String created_by;
	private String modified_by;
	private String course_id;
	private String region;
	private String title;
	private int viewcount;
}
