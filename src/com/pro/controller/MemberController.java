package com.pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pro.service.MemberService;
import com.pro.vo.MemberVO;

@Controller
public class MemberController {

	
	@RequestMapping(value="memProc.do", method = RequestMethod.GET)
	public String memberInput(){		
		return "memberInput";
	}
	
	
	@RequestMapping(value="memProc.do", method = RequestMethod.POST)
	public String regMember(@ModelAttribute("mem") MemberVO member){//알아서 데이터 바인딩이 실행됨
		System.out.println(member);
		MemberService service = new MemberService();
		if(!service.checkOverlap(member.getType(), member.getId())){
			service.memberInsert(member);
		}else{
			//ID 중복메세지 띄우고 다시 회원가입 화면으로
			return "memberOut";
		}
		return "Login";//회원 가입성공시 로그인 화면으로
	}
	
	@RequestMapping(value="memdelet.do", method = RequestMethod.POST)
	public String delMember(@ModelAttribute("mem") MemberVO member){//알아서 데이터 바인딩이 실행됨
		System.out.println(member);
		MemberService service = new MemberService();
			service.memberDelete(member.getType(), member.getId());
		return "Login";//회원 가입성공시 로그인 화면으로
	}
	
}
