package com.example.test.domain;

import lombok.Data;

import java.util.List;

@Data
public class CourseListDTO {

    private long id;
    private long course_id;
    private String title;
    private String region;
    private long viewCount;
    private List<CourseDTO> courseDatas;
    private String createdBy;
    private String modifiedBy;
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
