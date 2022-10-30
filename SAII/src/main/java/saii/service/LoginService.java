package saii.service;

import saii.domain.memberDAO;
import saii.dto.memberDTO;

public class LoginService {
	
	public memberDTO doPost(String id, String pw) {
		
		memberDTO memberDto = new memberDTO();
		
		memberDAO dao =new memberDAO();

		memberDto =dao.getmemberDTO(id, pw);
		dao.close();
		
		return memberDto;
		
	}

}
