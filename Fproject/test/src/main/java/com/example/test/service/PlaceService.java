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

    public List<PlaceDTO> getList(){

        List<Place> places = placeRepository.findAll();
        List<PlaceDTO> lists = new ArrayList<>();
        for(Place place : places){
            PlaceDTO cdto = modelMapper.map(place, PlaceDTO.class);
            int length = PlaceRepository.countById(cdto.getCourse_id());
            List<CourseDTO> datas = new ArrayList<>();
            for(int i=0; i < length;i++) {
                CourseDataId id = new CourseDataId();
                id.setId(cdto.getCourse_id());
                id.setOrder((long) i+1);
                datas.add(modelMapper.map(courseDataRepository.findById(id), CourseDTO.class));
            }
            cdto.setCourseDatas(datas);
            cdto.setCenter();
            long id = Long.parseLong(cdto.getCreatedBy());
            System.out.println(id);
            cdto.setCreatedBy(memberService.getMember(id).getNickname());
            lists.add(cdto);
        }
        return lists;
    }
}
