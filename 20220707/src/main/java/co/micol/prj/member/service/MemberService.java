package co.micol.prj.member.service;

import java.util.List;

import co.micol.prj.member.vo.MemberVO;

public interface MemberService { // vo 객체로 담고 넘기고 받고?
	List<MemberVO> memberSelectList(); // 멤버 전체조회 R
	MemberVO memberSelectOne(MemberVO vo); // 멤버 조회 R
	int memberInsert(MemberVO vo); //멤버 등록 C
	int memberUpdate(MemberVO vo); //멤버 수정 U
	int memberDelete(MemberVO vo); //멤버 삭제 D
	
	boolean isMemberIdCheck(String id); // 회원가입 시! 아이디 중복 체크 / 존재하면 false를 리턴?
	MemberVO memberLogin(MemberVO vo); // 로그인 처리 R
}
