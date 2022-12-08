package com.study.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.study.springboot.dto.MainBoardDTO;

@Mapper
@Repository
public interface MainBoardDAO {
	public List<MainBoardDTO> listDao(); // 리스트
	public MainBoardDTO viewDao(String post_id); // 상세보기
	public void plusviewcountDao(String post_id); // 조회수 증가
	public void deleteDao(String post_id); // 글 삭제
}
