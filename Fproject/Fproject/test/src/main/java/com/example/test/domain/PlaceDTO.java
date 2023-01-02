package com.example.test.domain;

import com.example.test.entity.Place;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PlaceDTO {

    private Long id;

    private String address_id;

    private String address_name;

    private String road_address_name;

    private String phone_number;

    private String place_name;

    private String place_url;

    private String x;

    private String y;

    private ModelMapper modelMapper = new ModelMapper();

    public PlaceDTO map(Place place){
        return modelMapper.map(place,PlaceDTO.class);
    }
}
