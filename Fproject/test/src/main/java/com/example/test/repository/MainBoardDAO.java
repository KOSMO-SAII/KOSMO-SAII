package com.example.test.repository;

import com.example.test.domain.MainBoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MainBoardDAO {
    public int selectCount(Map<String, Object> map);

    public List<MainBoardDTO> selectListPage(Map<String, Object> map);
    public List<MainBoardDTO> myPage_selectListPage(Map<String, Object> map);
    public void updateVisitCount(String m_id);
    public void minusVisitCount(String m_id);
    public MainBoardDTO selectView(String m_id);
    public int insertWrite(Map<String,Object> map);
    public int updateWrite(MainBoardDTO dto, String nickname);
    public int delete(String m_id);
    public ArrayList<MainBoardDTO> getRecommendData();
    public ArrayList<MainBoardDTO> getmylist(String nickname);
    public ArrayList<MainBoardDTO> myfavolist(String nickname);
    public int mylistcount(String nickname);
    public String favoprofile (String m_id);
    public String getNickname(int course_id);
    public MainBoardDTO getMainboard(int course_id);
}
