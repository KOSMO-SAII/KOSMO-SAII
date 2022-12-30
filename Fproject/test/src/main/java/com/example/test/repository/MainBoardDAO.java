package com.example.test.repository;

import java.util.List;
import java.util.Map;

import com.example.test.domain.MainBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface MainBoardDAO {
	public List<MainBoardDTO> listDao(); // 리스트
	public MainBoardDTO viewDao(String post_id); // 상세보기
	public void plusviewcountDao(String post_id); // 조회수 증가
	public void deleteDao(String post_id); // 글 삭제
	public int goodcountDao(String post_id); // 좋아요 횟수 표시
	public void writeDao(String course_id, String user_id, String title, String region);//임시

	public String getNickname(int course_id);//임시

	public MainBoardDTO getMainboard(int course_id);//임시

	public void insertWrite(MainBoardDTO mdto);
}
