package com.example.test.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class CourseListDTO {

    private long id;
    private long courseid;
    private String title;
    private String region;
    private long viewCount;
    private List<CourseDTO> courseDatas;
    private String createdBy;
    private String modifiedBy;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createDate;
    private double xPoint;
    private double yPoint;

    public void setCenter(){

        if(courseDatas.size() < 1) {
            System.out.println("=====setCenter Error=====");
            return;
        }

        for(CourseDTO dto : courseDatas){
            xPoint += Double.parseDouble(dto.getX());
            yPoint += Double.parseDouble(dto.getY());
        }

        xPoint = xPoint / courseDatas.size();
        yPoint = yPoint / courseDatas.size();
    }

    public String getOrders(){
        String orders = "";

        for(CourseDTO dto : courseDatas){
            orders += dto.getPlace_name();
            orders += " >> ";
        }

        return orders;
    }

}
