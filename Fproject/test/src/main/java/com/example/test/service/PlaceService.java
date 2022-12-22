package com.example.test.service;

import com.example.test.domain.CourseDTO;
import com.example.test.domain.CourseListDTO;
import com.example.test.domain.PlaceDTO;
import com.example.test.entity.CourseDataId;
import com.example.test.entity.CourseList;
import com.example.test.entity.Place;
import com.example.test.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private ModelMapper modelMapper = new ModelMapper();

    private PlaceRepository placeRepository;

    private Place placelist(long id){

       return placeRepository.findById(id).orElseThrow();
    }


}
