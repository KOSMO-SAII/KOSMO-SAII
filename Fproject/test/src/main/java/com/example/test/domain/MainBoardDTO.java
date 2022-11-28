package com.example.test.domain;

import lombok.Data;

@Data
public class MainBoardDTO {
    private String post_id;
    private String title;
    private String region;
    private String user_id;
    private String course_id;
    private java.sql.Date m_postdate;
    private int visitcount;
    private int goodcount;
}
