package com.pro.service;

import java.util.ArrayList;

import com.pro.dao.MemberDAO;
import com.pro.vo.MemberVO;

//book에 관련된 모든 서비스 처리
public class MemberService {
	
	//디비 관련된 것을 처리하기 위해 선언
	MemberDAO dao = new MemberDAO();
	
	//member 회원가입
	public void memberInsert(MemberVO mem){
		dao.insertMember(mem); //디비에서 처리
	}
	//member id 중복체크 
	public boolean checkOverlap(String type, String id){
		return dao.checkOverlap(type, id);
	}

	public MemberVO studentSearch(String id) {
		MemberVO member = dao.studentSearch(id);
		return member;
	}

	public void studentUpdate(MemberVO student) {
		dao.studentUpdate(student);
	}
	
	public void teacherUpdate(MemberVO teacher) {
		dao.teacherUpdate(teacher);
	}
	
	public void memberDelete(String type, String id) {
		dao.memberDelete(type, id);
	}
	
	public ArrayList<MemberVO> studentList() {
		ArrayList<MemberVO> list = dao.studentList();
		return list;
	}

}
